package uk.co.dancingganesh.pocketvedas

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.preference.PreferenceManager
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream

/**
 * Database access layer — direct port of iOS AppDatabase.swift.
 * Opens the bundled vedabase.db (read-only) and a writable bookmarks.db.
 */
class AppDatabase(private val context: Context) {

    var vedabase: SQLiteDatabase? = null
        private set
    var bookmarkDB: SQLiteDatabase? = null
        private set

    var books: List<Book> = emptyList()
        private set
    var bookmarks: List<Bookmark> = emptyList()
        private set

    var lastError: String? = null

    // ── preferences (mirroring iOS UserDefaults) ───────────────────────────────
    private val prefs get() = PreferenceManager.getDefaultSharedPreferences(context)
    private val showText get() = prefs.getBoolean("pref_text", true)
    private val showSynonyms get() = prefs.getBoolean("pref_synonyms", true)
    private val showTranslation get() = prefs.getBoolean("pref_translation", true)
    private val showPurport get() = prefs.getBoolean("pref_purport", true)
    private val textSizePercent get() = prefs.getInt("pref_zoom", 133)
    private val blackOnWhite get() = prefs.getBoolean("pref_reverse", true)

    init {
        try {
            val filesDir = context.getExternalFilesDir(null)
                ?: context.filesDir

            // ── vedabase.db ──
            val vedaFile = File(filesDir, "vedabase.db")
            if (!vedaFile.canRead()) {
                copyAsset("raw/vedabase.db", vedaFile)
            }
            vedabase = SQLiteDatabase.openDatabase(
                vedaFile.absolutePath, null,
                SQLiteDatabase.OPEN_READONLY or SQLiteDatabase.NO_LOCALIZED_COLLATORS
            )

            // ── bookmarks.db ──
            val bmFile = File(filesDir, "bookmarks.db")
            if (!bmFile.canRead()) {
                copyAsset("raw/bookmarks.db", bmFile)
            }
            bookmarkDB = SQLiteDatabase.openDatabase(
                bmFile.absolutePath, null,
                SQLiteDatabase.OPEN_READWRITE or SQLiteDatabase.NO_LOCALIZED_COLLATORS
            )

            books = loadBooks()
            bookmarks = loadBookmarks()
        } catch (t: Throwable) {
            lastError = t.toString()
            t.printStackTrace()
        }
    }

    // ── public API ──────────────────────────────────────────────────────────────

    fun readerPage(path: String): ReaderPage {
        val resolved = resolveRedirect(path)
        val rowId = rowId(resolved)
        val title = scalarString(vedabase, "SELECT title FROM verse WHERE rowid = ?", arrayOf(rowId.toString()))
        val content = scalarString(vedabase, "SELECT content FROM verse WHERE rowid = ?", arrayOf(rowId.toString()))
        return ReaderPage(id = rowId, title = title, path = resolved, html = htmlShell(content))
    }

    fun siblingPaths(path: String): SiblingResult {
        val resolved = resolveRedirect(path)
        val currentRowId = rowId(resolved)

        var parentId = 0L
        var bookId = 0L
        query(vedabase, "SELECT parent, book FROM division WHERE rowid = ?", arrayOf(currentRowId.toString())) { c ->
            parentId = c.getLong(0)
            bookId = c.getLong(1)
        }

        // Book-level: siblings are index pages of ALL books (same as iOS)
        if (parentId == 0L) {
            val allPaths = mutableListOf<String>()
            var currentIndex = 0
            for (book in books) {
                if (book.id == bookId) {
                    currentIndex = allPaths.size
                }
                allPaths.add("${book.name}/index")
            }
            return SiblingResult(allPaths, currentIndex)
        }

        // Inner hierarchy: siblings share the same parent
        val paths = mutableListOf<String>()
        var currentIndex = 0
        query(
            vedabase,
            "SELECT rowid FROM division WHERE parent = ? AND book = ? ORDER BY sequence",
            arrayOf(parentId.toString(), bookId.toString())
        ) { c ->
            val siblingId = c.getLong(0)
            val p = try { pathForDivision(siblingId) } catch (_: Exception) { null }
            if (p != null) {
                if (siblingId == currentRowId) {
                    currentIndex = paths.size
                }
                paths.add(p)
            }
        }
        return SiblingResult(paths, currentIndex)
    }

    fun search(queryString: String): List<SearchResult> {
        val normalized = queryString.trim()
        if (normalized.isEmpty()) return emptyList()

        val sql = """
            SELECT rowid,
                   COALESCE((SELECT title FROM verse WHERE verse.rowid = searching.rowid), ''),
                   snippet(searching, 0, '<mark>', '</mark>', ' ... ', 18)
            FROM searching
            WHERE plain MATCH ?
            LIMIT 100
        """.trimIndent()

        val results = mutableListOf<SearchResult>()
        query(vedabase, sql, arrayOf(normalized)) { c ->
            val rowId = c.getLong(0)
            val title = c.getString(1) ?: ""
            val snippet = c.getString(2) ?: ""
            val p = try { pathForDivision(rowId) } catch (_: Exception) { null }
            if (p != null) {
                results.add(SearchResult(id = rowId, title = title, snippet = snippet, path = p))
            }
        }
        return results
    }

    fun addBookmark(path: String, description: String = "", smart: Boolean = false, offset: Double = 0.0) {
        val db = bookmarkDB ?: return
        val name = displayName(path)
        val cv = ContentValues().apply {
            put("path", path)
            put("name", name)
            put("description", description)
            put("smart", if (smart) 1 else 0)
            put("offset", offset)
        }
        db.insert("bookmark", null, cv)
        bookmarks = loadBookmarks()
    }

    fun removeBookmark(id: Long) {
        val db = bookmarkDB ?: return
        db.delete("bookmark", "rowid = ?", arrayOf(id.toString()))
        bookmarks = loadBookmarks()
    }

    fun aboutHtml(): String {
        return try {
            context.assets.open("raw/info.html").bufferedReader().readText()
        } catch (_: Exception) {
            "<html><body><p>About content unavailable.</p></body></html>"
        }
    }

    fun refreshBookmarks() {
        bookmarks = loadBookmarks()
    }

    // ── private helpers ─────────────────────────────────────────────────────────

    private fun copyAsset(assetPath: String, dest: File) {
        dest.parentFile?.mkdirs()
        BufferedInputStream(context.assets.open(assetPath)).use { input ->
            FileOutputStream(dest).use { output ->
                input.copyTo(output)
            }
        }
    }

    private fun loadBooks(): List<Book> {
        val loaded = mutableListOf<Book>()
        query(vedabase, "SELECT rowid, name, cover FROM book ORDER BY name") { c ->
            val id = c.getLong(0)
            val name = c.getString(1)
            val cover = c.getBlob(2)
            loaded.add(Book(id = id, name = name, coverData = cover))
        }
        // Pin BG, SB, CC to the front — same as iOS
        val pinnedOrder = listOf("BG", "SB", "CC")
        return loaded.sortedWith(compareBy<Book> {
            val idx = pinnedOrder.indexOf(it.name)
            if (idx >= 0) idx else Int.MAX_VALUE
        }.thenBy { it.name })
    }

    private fun loadBookmarks(): List<Bookmark> {
        val loaded = mutableListOf<Bookmark>()
        query(
            bookmarkDB,
            "SELECT rowid, path, name, description, smart, offset FROM bookmark ORDER BY rowid DESC"
        ) { c ->
            loaded.add(
                Bookmark(
                    id = c.getLong(0),
                    path = c.getString(1) ?: "",
                    name = c.getString(2) ?: "",
                    description = c.getString(3) ?: "",
                    smart = c.getInt(4) != 0,
                    offset = c.getDouble(5)
                )
            )
        }
        return loaded
    }

    private fun resolveRedirect(path: String): String {
        return optionalString(
            vedabase,
            "SELECT target FROM redirection WHERE source = ?",
            arrayOf(path)
        ) ?: path
    }

    private fun rowId(path: String): Long {
        val components = path.split("/")
        val bookName = components.firstOrNull()
            ?: throw IllegalArgumentException("Invalid path: $path")

        val bookId = scalarLong(
            vedabase,
            "SELECT rowid FROM book WHERE name = ?",
            arrayOf(bookName)
        )

        val indexId = indexDivisionId(bookId)
        if (components.size == 1 || (components.size == 2 && components[1] == "index")) {
            return indexId
        }

        var parent = indexId
        var current = indexId
        var pathParts = components.drop(1).toMutableList()
        if (pathParts.size > 1 && pathParts.last() == "index") {
            pathParts.removeAt(pathParts.lastIndex)
        }

        for (part in pathParts) {
            current = divisionId(part, bookId, parent)
            parent = current
        }
        return current
    }

    private fun indexDivisionId(bookId: Long): Long {
        return scalarLong(
            vedabase,
            "SELECT rowid FROM division WHERE name = 'index' AND book = ? AND parent = 0",
            arrayOf(bookId.toString())
        )
    }

    private fun divisionId(name: String, bookId: Long, parent: Long): Long {
        // Exact match
        val exact = optionalLong(
            vedabase,
            "SELECT rowid FROM division WHERE name = ? AND book = ? AND parent = ?",
            arrayOf(name, bookId.toString(), parent.toString())
        )
        if (exact != null) return exact

        // Numeric range match
        val numPrefix = numericPrefix(name)
        if (numPrefix != null) {
            val rangeMatch = optionalLong(
                vedabase,
                """SELECT rowid FROM division 
                   WHERE CAST(substr(name, 1, instr(name || '-', '-') - 1) AS INTEGER) <= ? 
                   AND CAST(substr(name, instr(name || '-', '-') + 1) AS INTEGER) >= ? 
                   AND instr(name, '-') > 0 
                   AND book = ? AND parent = ? LIMIT 1""",
                arrayOf(numPrefix.toString(), numPrefix.toString(), bookId.toString(), parent.toString())
            )
            if (rangeMatch != null) return rangeMatch
        }

        throw IllegalArgumentException("Could not resolve division: $name")
    }

    private fun numericPrefix(value: String): Long? {
        val digits = value.takeWhile { it.isDigit() }
        return if (digits.isNotEmpty()) digits.toLongOrNull() else null
    }

    private fun pathForDivision(rowId: Long): String {
        val components = mutableListOf<String>()
        var current = rowId
        var bookId = 0L

        while (current != 0L) {
            var found = false
            query(
                vedabase,
                "SELECT name, parent, book FROM division WHERE rowid = ?",
                arrayOf(current.toString())
            ) { c ->
                val name = c.getString(0)
                val parent = c.getLong(1)
                bookId = c.getLong(2)
                if (!(name == "index" && parent == 0L)) {
                    components.add(0, name)
                }
                current = parent
                found = true
            }
            if (!found) throw IllegalArgumentException("Unknown division $rowId")
        }

        val bookName = scalarString(
            vedabase,
            "SELECT name FROM book WHERE rowid = ?",
            arrayOf(bookId.toString())
        )
        return if (components.isEmpty()) {
            "$bookName/index"
        } else {
            (listOf(bookName) + components).joinToString("/")
        }
    }

    private fun displayName(path: String): String {
        return path.split("/")
            .reversed()
            .filter { it != "index" }
            .joinToString(" ")
    }

    // ── HTML shell — mirrors iOS AppDatabase.htmlShell() ────────────────────────

    fun htmlShell(content: String): String {
        val foreground = if (blackOnWhite) "#1f1d1a" else "#f7f2ea"
        val background = "transparent"
        val displayText = if (showText) "block" else "none"
        val displaySynonyms = if (showSynonyms) "block" else "none"
        val displayTranslation = if (showTranslation) "block" else "none"
        val displayPurport = if (showPurport) "block" else "none"
        val fontSize = (12.0 * textSizePercent / 100.0).coerceIn(8.0, 36.0)

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
                  background: $background url('bg-texture.png') repeat;
                  color: $foreground;
                  font: ${fontSize}pt/1.55 DejaVu, serif;
                  -webkit-text-size-adjust: none;
                }
                .header, .section-title {
                  color: #0b775e;
                  text-align: center;
                  font-family: DejaVuBold, serif;
                  text-decoration: underline;
                }
                .space { clear: both; }
                .text { display: $displayText; }
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
                  display: $displaySynonyms;
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
                  display: $displayTranslation;
                  font-family: DejaVuBold, serif;
                }
                .purport {
                  display: $displayPurport;
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
                  border-top: 1px solid $foreground;
                  border-bottom: 1px solid $foreground;
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
            $content
        """.trimIndent()
    }

    // ── low-level SQLite helpers ─────────────────────────────────────────────────

    private fun query(
        db: SQLiteDatabase?,
        sql: String,
        args: Array<String>? = null,
        row: (Cursor) -> Unit
    ) {
        db ?: return
        db.rawQuery(sql, args)?.use { cursor ->
            while (cursor.moveToNext()) {
                row(cursor)
            }
        }
    }

    private fun scalarString(db: SQLiteDatabase?, sql: String, args: Array<String>? = null): String {
        return optionalString(db, sql, args)
            ?: throw IllegalStateException("Query returned no rows: $sql")
    }

    private fun optionalString(db: SQLiteDatabase?, sql: String, args: Array<String>? = null): String? {
        db ?: return null
        db.rawQuery(sql, args)?.use { cursor ->
            if (cursor.moveToFirst()) {
                return cursor.getString(0)
            }
        }
        return null
    }

    private fun scalarLong(db: SQLiteDatabase?, sql: String, args: Array<String>? = null): Long {
        return optionalLong(db, sql, args)
            ?: throw IllegalStateException("Query returned no rows: $sql")
    }

    private fun optionalLong(db: SQLiteDatabase?, sql: String, args: Array<String>? = null): Long? {
        db ?: return null
        db.rawQuery(sql, args)?.use { cursor ->
            if (cursor.moveToFirst()) {
                return cursor.getLong(0)
            }
        }
        return null
    }
}
