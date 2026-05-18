import Foundation
import SQLite3

@MainActor
final class AppDatabase: ObservableObject {
    @Published private(set) var books: [Book] = []
    @Published private(set) var bookmarks: [Bookmark] = []
    @Published var lastError: String?

    private var vedabase: OpaquePointer?
    private var bookmarkDB: OpaquePointer?

    private var showText: Bool { settingBool("pref_text", defaultValue: true) }
    private var showSynonyms: Bool { settingBool("pref_synonyms", defaultValue: true) }
    private var showTranslation: Bool { settingBool("pref_translation", defaultValue: true) }
    private var showPurport: Bool { settingBool("pref_purport", defaultValue: true) }
    private var textSizePercent: Double { settingDouble("pref_zoom", defaultValue: 133.0) }
    private var blackOnWhite: Bool { settingBool("pref_reverse", defaultValue: true) }

    init() {
        do {
            try prepareDatabases()
            try loadBooks()
            try loadBookmarks()
        } catch {
            lastError = error.localizedDescription
        }
    }

    deinit {
        sqlite3_close(vedabase)
        sqlite3_close(bookmarkDB)
    }

    func readerPage(forPath path: String) throws -> ReaderPage {
        let resolvedPath = try resolveRedirect(for: path)
        let rowID = try rowID(forPath: resolvedPath)
        let title = try scalarString(
            db: vedabase,
            sql: "SELECT title FROM verse WHERE rowid = ?",
            bind: { sqlite3_bind_int64($0, 1, rowID) }
        )
        let content = try scalarString(
            db: vedabase,
            sql: "SELECT content FROM verse WHERE rowid = ?",
            bind: { sqlite3_bind_int64($0, 1, rowID) }
        )
        return ReaderPage(id: rowID, title: title, path: resolvedPath, html: htmlShell(content))
    }

    func search(query queryString: String) throws -> [SearchResult] {
        let normalized = queryString.trimmingCharacters(in: .whitespacesAndNewlines)
        guard !normalized.isEmpty else { return [] }

        let sql = """
        SELECT rowid,
               COALESCE((SELECT title FROM verse WHERE verse.rowid = searching.rowid), ''),
               snippet(searching, 0, '<mark>', '</mark>', ' ... ', 18)
        FROM searching
        WHERE plain MATCH ?
        LIMIT 100
        """

        var results: [SearchResult] = []
        try query(db: vedabase, sql: sql, bind: { sqlite3_bind_text($0, 1, normalized, -1, SQLITE_TRANSIENT) }) { stmt in
            let rowID = sqlite3_column_int64(stmt, 0)
            let title = String(cString: sqlite3_column_text(stmt, 1))
            let snippet = String(cString: sqlite3_column_text(stmt, 2))
            let path = try self.path(forDivision: rowID)
            results.append(SearchResult(id: rowID, title: title, snippet: snippet, path: path))
        }
        return results
    }

    func addBookmark(path: String, description: String = "", smart: Bool = false, offset: Double = 0) throws {
        let name = try displayName(forPath: path)
        let sql = "INSERT INTO bookmark(path, name, description, smart, offset) VALUES(?, ?, ?, ?, ?)"
        try execute(db: bookmarkDB, sql: sql) { stmt in
            sqlite3_bind_text(stmt, 1, path, -1, SQLITE_TRANSIENT)
            sqlite3_bind_text(stmt, 2, name, -1, SQLITE_TRANSIENT)
            sqlite3_bind_text(stmt, 3, description, -1, SQLITE_TRANSIENT)
            sqlite3_bind_int(stmt, 4, smart ? 1 : 0)
            sqlite3_bind_double(stmt, 5, offset)
        }
        try loadBookmarks()
    }

    func removeBookmark(id: Int64) throws {
        try execute(db: bookmarkDB, sql: "DELETE FROM bookmark WHERE rowid = ?") {
            sqlite3_bind_int64($0, 1, id)
        }
        try loadBookmarks()
    }

    func aboutHTML() -> String {
        guard let url = Bundle.main.url(forResource: "info", withExtension: "html", subdirectory: "raw"),
              let html = try? String(contentsOf: url) else {
            return "<html><body><p>About content unavailable.</p></body></html>"
        }
        return html
    }

    private func prepareDatabases() throws {
        let fileManager = FileManager.default
        let supportDirectory = try fileManager.url(
            for: .applicationSupportDirectory,
            in: .userDomainMask,
            appropriateFor: nil,
            create: true
        ).appendingPathComponent("PocketVedas", isDirectory: true)
        try fileManager.createDirectory(at: supportDirectory, withIntermediateDirectories: true)

        guard let vedabaseURL = bundleResourceURL(named: "vedabase", ext: "db", subdirectory: "raw") else {
            throw DatabaseError.missingResource("vedabase.db")
        }
        let bookmarksURL = try copyBundleResourceIfNeeded(
            named: "bookmarks",
            ext: "db",
            subdirectory: "raw",
            to: supportDirectory.appendingPathComponent("bookmarks.db")
        )

        guard sqlite3_open_v2(vedabaseURL.path, &vedabase, SQLITE_OPEN_READONLY, nil) == SQLITE_OK else {
            throw DatabaseError.openFailed("Unable to open vedabase.db")
        }
        guard sqlite3_open(bookmarksURL.path, &bookmarkDB) == SQLITE_OK else {
            throw DatabaseError.openFailed("Unable to open bookmarks.db")
        }
    }

    private func copyBundleResourceIfNeeded(named: String, ext: String, subdirectory: String, to destination: URL) throws -> URL {
        let fileManager = FileManager.default
        if !fileManager.fileExists(atPath: destination.path) {
            guard let source = bundleResourceURL(named: named, ext: ext, subdirectory: subdirectory) else {
                throw DatabaseError.missingResource("\(named).\(ext)")
            }
            try fileManager.copyItem(at: source, to: destination)
        }
        return destination
    }

    private func bundleResourceURL(named: String, ext: String, subdirectory: String) -> URL? {
        Bundle.main.url(forResource: named, withExtension: ext, subdirectory: subdirectory)
            ?? Bundle.main.url(forResource: named, withExtension: ext)
    }

    private func loadBooks() throws {
        var loaded: [Book] = []
        try query(db: vedabase, sql: "SELECT rowid, name, cover FROM book ORDER BY name") { stmt in
            let id = sqlite3_column_int64(stmt, 0)
            let name = String(cString: sqlite3_column_text(stmt, 1))
            let blob = sqlite3_column_blob(stmt, 2)
            let length = Int(sqlite3_column_bytes(stmt, 2))
            let cover = blob.map { Data(bytes: $0, count: length) }
            loaded.append(Book(id: id, name: name, coverData: cover))
        }
        books = loaded
    }

    private func loadBookmarks() throws {
        var loaded: [Bookmark] = []
        try query(db: bookmarkDB, sql: "SELECT rowid, path, name, description, smart, offset FROM bookmark ORDER BY rowid DESC") { stmt in
            let id = sqlite3_column_int64(stmt, 0)
            let path = String(cString: sqlite3_column_text(stmt, 1))
            let name = String(cString: sqlite3_column_text(stmt, 2))
            let description = String(cString: sqlite3_column_text(stmt, 3))
            let smart = sqlite3_column_int(stmt, 4) != 0
            let offset = sqlite3_column_double(stmt, 5)
            loaded.append(Bookmark(id: id, path: path, name: name, description: description, smart: smart, offset: offset))
        }
        bookmarks = loaded
    }

    private func resolveRedirect(for path: String) throws -> String {
        let redirected = try optionalString(
            db: vedabase,
            sql: "SELECT target FROM redirection WHERE source = ?",
            bind: { sqlite3_bind_text($0, 1, path, -1, SQLITE_TRANSIENT) }
        )
        return redirected ?? path
    }

    private func rowID(forPath path: String) throws -> Int64 {
        let components = path.split(separator: "/").map(String.init)
        guard let bookName = components.first else {
            throw DatabaseError.invalidPath(path)
        }

        let bookID = try scalarInt64(
            db: vedabase,
            sql: "SELECT rowid FROM book WHERE name = ?",
            bind: { sqlite3_bind_text($0, 1, bookName, -1, SQLITE_TRANSIENT) }
        )

        let indexID = try indexDivisionID(forBookID: bookID)
        if components.count == 1 || (components.count == 2 && components[1] == "index") {
            return indexID
        }

        var parent = indexID
        var current = indexID
        var pathParts = Array(components.dropFirst())
        if pathParts.count > 1 && pathParts.last == "index" {
            pathParts.removeLast()
        }

        for part in pathParts {
            current = try divisionID(named: part, bookID: bookID, parent: parent)
            parent = current
        }
        return current
    }

    private func indexDivisionID(forBookID bookID: Int64) throws -> Int64 {
        try scalarInt64(
            db: vedabase,
            sql: "SELECT rowid FROM division WHERE name = 'index' AND book = ? AND parent = 0",
            bind: { sqlite3_bind_int64($0, 1, bookID) }
        )
    }

    private func divisionID(named name: String, bookID: Int64, parent: Int64) throws -> Int64 {
        if let exact = try optionalInt64(
            db: vedabase,
            sql: "SELECT rowid FROM division WHERE name = ? AND book = ? AND parent = ?",
            bind: {
                sqlite3_bind_text($0, 1, name, -1, SQLITE_TRANSIENT)
                sqlite3_bind_int64($0, 2, bookID)
                sqlite3_bind_int64($0, 3, parent)
            }
        ) {
            return exact
        }

        if let numericPrefix = numericPrefix(in: name),
           let rangeMatch = try optionalInt64(
            db: vedabase,
            sql: "SELECT rowid FROM division WHERE CAST(substr(name, 1, instr(name || '-', '-') - 1) AS INTEGER) <= ? AND CAST(substr(name, instr(name || '-', '-') + 1) AS INTEGER) >= ? AND instr(name, '-') > 0 AND book = ? AND parent = ? LIMIT 1",
            bind: {
                sqlite3_bind_int64($0, 1, numericPrefix)
                sqlite3_bind_int64($0, 2, numericPrefix)
                sqlite3_bind_int64($0, 3, bookID)
                sqlite3_bind_int64($0, 4, parent)
            }
           ) {
            return rangeMatch
        }

        throw DatabaseError.invalidPath(name)
    }

    private func numericPrefix(in value: String) -> Int64? {
        let digits = value.prefix { $0.isNumber }
        guard !digits.isEmpty else { return nil }
        return Int64(digits)
    }

    private func path(forDivision rowID: Int64) throws -> String {
        var components: [String] = []
        var current = rowID
        var bookID: Int64 = 0

        while current != 0 {
            let sql = "SELECT name, parent, book FROM division WHERE rowid = ?"
            var found = false
            try query(db: vedabase, sql: sql, bind: { sqlite3_bind_int64($0, 1, current) }) { stmt in
                let name = String(cString: sqlite3_column_text(stmt, 0))
                let parent = sqlite3_column_int64(stmt, 1)
                bookID = sqlite3_column_int64(stmt, 2)
                if !(name == "index" && parent == 0) {
                    components.insert(name, at: 0)
                }
                current = parent
                found = true
            }
            if !found {
                throw DatabaseError.invalidPath("Unknown division \(rowID)")
            }
        }

        let bookName = try scalarString(
            db: vedabase,
            sql: "SELECT name FROM book WHERE rowid = ?",
            bind: { sqlite3_bind_int64($0, 1, bookID) }
        )
        if components.isEmpty {
            return "\(bookName)/index"
        }
        return ([bookName] + components).joined(separator: "/")
    }

    private func displayName(forPath path: String) throws -> String {
        let parts = path.split(separator: "/").map(String.init).reversed()
        return parts
            .filter { $0 != "index" }
            .joined(separator: " ")
    }

    private func settingBool(_ key: String, defaultValue: Bool) -> Bool {
        guard UserDefaults.standard.object(forKey: key) != nil else { return defaultValue }
        return UserDefaults.standard.bool(forKey: key)
    }

    private func settingDouble(_ key: String, defaultValue: Double) -> Double {
        guard UserDefaults.standard.object(forKey: key) != nil else { return defaultValue }
        return UserDefaults.standard.double(forKey: key)
    }

    private func htmlShell(_ content: String) -> String {
        let foreground = blackOnWhite ? "#1f1d1a" : "#f7f2ea"
        let background = blackOnWhite ? "#f5f0e7" : "#000000"
        let displayText = showText ? "block" : "none"
        let displaySynonyms = showSynonyms ? "block" : "none"
        let displayTranslation = showTranslation ? "block" : "none"
        let displayPurport = showPurport ? "block" : "none"
        let fontSize = max(8, min(36, 12.0 * textSizePercent / 100.0))

        return """
        <HTML xmlns:vb="http://www.vedabase.com">
        <HEAD>
          <meta charset="utf-8">
          <meta name="viewport" content="width=device-width, initial-scale=1">
          <style>
            @font-face { font-family: DejaVu; src: url('DejaVuSans.ttf'); }
            @font-face { font-family: DejaVuBold; src: url('DejaVuSans-Bold.ttf'); }
            @font-face { font-family: DejaVuItalic; src: url('DejaVuSans-Oblique.ttf'); }
            body {
              margin: 0;
              padding: 20px 14px 100px;
              background: \(background) url('bg-texture.png') repeat;
              color: \(foreground);
              font: \(fontSize)pt/1.55 DejaVu, serif;
              -webkit-text-size-adjust: none;
            }
            .header, .section-title {
              color: #0b775e;
              text-align: center;
              font-family: DejaVuBold, serif;
              text-decoration: underline;
            }
            .space { clear: both; }
            .text { display: \(displayText); }
            .text-inner {}
            .verse {
              color: #009966;
              text-indent: 0;
              display: table;
              margin: 10px auto;
            }
            .verse br { display: none; }
            .prelude {
              text-align: center;
              margin-top: 0;
            }
            .odd-line {
              text-align: left;
              margin-top: 0;
            }
            .even-line {
              text-indent: 20px;
              text-align: left;
              margin-top: 0;
            }
            .quote {
              text-align: center;
              margin-top: 10px;
            }
            .synonyms {
              display: \(displaySynonyms);
              text-indent: 0;
              margin-top: 0;
            }
            .synonym,
            .word,
            .meaning,
            .indexTarget,
            .indexTitle {
              display: inline;
            }
            .word { color: #009966; }
            .translation {
              display: \(displayTranslation);
              font-family: DejaVuBold, serif;
            }
            .purport {
              display: \(displayPurport);
              text-indent: 24px;
            }
            .body { text-indent: 24px; }
            .paragraph { margin-top: 16px; }
            .foreign {
              font-family: DejaVuItalic, serif;
              display: inline;
            }
            .heading {
              margin-top: 18px;
              border-top: 1px solid \(foreground);
              border-bottom: 1px solid \(foreground);
              font-weight: bold;
              text-indent: 0;
            }
            ol li {
              text-indent: 0;
              margin-top: 5px;
            }
            ul {
              list-style-type: none;
              text-indent: 0;
              -webkit-padding-start: 0;
            }
            ul li { margin-bottom: 10px; }
            .highlight {
              background-color: yellow;
              color: black;
              display: inline;
            }
            a { color: #7e2b23; text-decoration: none; }
            mark { background: #ffef8a; }
          </style>
        \(content)
        """
    }

    private func execute(
        db: OpaquePointer?,
        sql: String,
        bind: ((OpaquePointer?) -> Void)? = nil
    ) throws {
        var stmt: OpaquePointer?
        guard sqlite3_prepare_v2(db, sql, -1, &stmt, nil) == SQLITE_OK else {
            throw DatabaseError.queryFailed(sql)
        }
        defer { sqlite3_finalize(stmt) }
        bind?(stmt)
        guard sqlite3_step(stmt) == SQLITE_DONE else {
            throw DatabaseError.queryFailed(sql)
        }
    }

    private func query(
        db: OpaquePointer?,
        sql: String,
        bind: ((OpaquePointer?) -> Void)? = nil,
        row: (OpaquePointer?) throws -> Void
    ) throws {
        var stmt: OpaquePointer?
        guard sqlite3_prepare_v2(db, sql, -1, &stmt, nil) == SQLITE_OK else {
            throw DatabaseError.queryFailed(sql)
        }
        defer { sqlite3_finalize(stmt) }
        bind?(stmt)
        while sqlite3_step(stmt) == SQLITE_ROW {
            try row(stmt)
        }
    }

    private func scalarString(
        db: OpaquePointer?,
        sql: String,
        bind: ((OpaquePointer?) -> Void)? = nil
    ) throws -> String {
        guard let value = try optionalString(db: db, sql: sql, bind: bind) else {
            throw DatabaseError.queryFailed(sql)
        }
        return value
    }

    private func optionalString(
        db: OpaquePointer?,
        sql: String,
        bind: ((OpaquePointer?) -> Void)? = nil
    ) throws -> String? {
        var stmt: OpaquePointer?
        guard sqlite3_prepare_v2(db, sql, -1, &stmt, nil) == SQLITE_OK else {
            throw DatabaseError.queryFailed(sql)
        }
        defer { sqlite3_finalize(stmt) }
        bind?(stmt)
        guard sqlite3_step(stmt) == SQLITE_ROW else { return nil }
        guard let text = sqlite3_column_text(stmt, 0) else { return nil }
        return String(cString: text)
    }

    private func optionalInt64(
        db: OpaquePointer?,
        sql: String,
        bind: ((OpaquePointer?) -> Void)? = nil
    ) throws -> Int64? {
        var stmt: OpaquePointer?
        guard sqlite3_prepare_v2(db, sql, -1, &stmt, nil) == SQLITE_OK else {
            throw DatabaseError.queryFailed(sql)
        }
        defer { sqlite3_finalize(stmt) }
        bind?(stmt)
        guard sqlite3_step(stmt) == SQLITE_ROW else { return nil }
        return sqlite3_column_int64(stmt, 0)
    }

    private func scalarInt64(
        db: OpaquePointer?,
        sql: String,
        bind: ((OpaquePointer?) -> Void)? = nil
    ) throws -> Int64 {
        var stmt: OpaquePointer?
        guard sqlite3_prepare_v2(db, sql, -1, &stmt, nil) == SQLITE_OK else {
            throw DatabaseError.queryFailed(sql)
        }
        defer { sqlite3_finalize(stmt) }
        bind?(stmt)
        guard sqlite3_step(stmt) == SQLITE_ROW else {
            throw DatabaseError.queryFailed(sql)
        }
        return sqlite3_column_int64(stmt, 0)
    }
}

private let SQLITE_TRANSIENT = unsafeBitCast(-1, to: sqlite3_destructor_type.self)

enum DatabaseError: LocalizedError {
    case missingResource(String)
    case openFailed(String)
    case invalidPath(String)
    case queryFailed(String)

    var errorDescription: String? {
        switch self {
        case .missingResource(let resource):
            return "Missing bundled resource: \(resource)"
        case .openFailed(let message):
            return message
        case .invalidPath(let path):
            return "Could not resolve path: \(path)"
        case .queryFailed(let sql):
            return "SQLite query failed: \(sql)"
        }
    }
}
