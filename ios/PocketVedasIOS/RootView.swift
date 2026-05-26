import SwiftUI

enum AppTab: Hashable {
    case books
    case search
    case bookmarks
}

@MainActor
final class AppNavigationState: ObservableObject {
    @Published var selectedTab: AppTab = .books
    @Published var showAbout = false
    @Published var showSettings = false
}

struct RootView: View {
    @EnvironmentObject private var database: AppDatabase
    @StateObject private var navigation = AppNavigationState()

    private var showingError: Binding<Bool> {
        Binding(
            get: { database.lastError != nil },
            set: { if !$0 { database.lastError = nil } }
        )
    }

    var body: some View {
        TabView(selection: $navigation.selectedTab) {
            NavigationStack {
                BooksView()
            }
            .tag(AppTab.books)
            .tabItem {
                Label("Books", systemImage: "books.vertical")
            }

            NavigationStack {
                SearchScreen()
            }
            .tag(AppTab.search)
            .tabItem {
                Label("Search", systemImage: "magnifyingglass")
            }

            NavigationStack {
                BookmarksView()
            }
            .tag(AppTab.bookmarks)
            .tabItem {
                Label("Bookmarks", systemImage: "bookmark")
            }
        }
        .environmentObject(navigation)
        .alert("Database Error", isPresented: showingError, actions: {
            Button("OK") { database.lastError = nil }
        }, message: {
            Text(database.lastError ?? "")
        })
        .sheet(isPresented: $navigation.showAbout) {
            NavigationStack {
                StaticHTMLScreen(title: "About", html: database.aboutHTML())
            }
        }
        .sheet(isPresented: $navigation.showSettings) {
            NavigationStack {
                SettingsScreen()
            }
        }
    }
}

struct BooksView: View {
    @EnvironmentObject private var database: AppDatabase

    private let columns = [GridItem(.adaptive(minimum: 110), spacing: 14)]

    var body: some View {
        ScrollView {
            LazyVGrid(columns: columns, spacing: 14) {
                ForEach(database.books) { book in
                    NavigationLink(value: "book:\(book.id)") {
                        VStack(spacing: 8) {
                            if let coverData = book.coverData, let image = UIImage(data: coverData) {
                                Image(uiImage: image)
                                    .resizable()
                                    .scaledToFill()
                                    .aspectRatio(2/3, contentMode: .fit)
                                    .clipped()
                                    .clipShape(RoundedRectangle(cornerRadius: 10))
                                    .shadow(radius: 5, y: 3)
                            } else {
                                RoundedRectangle(cornerRadius: 10)
                                    .fill(Color.orange.opacity(0.18))
                                    .aspectRatio(2/3, contentMode: .fit)
                                    .overlay(Image(systemName: "book.closed").font(.largeTitle))
                            }

                            Text(book.name)
                                .font(.caption)
                                .fontWeight(.semibold)
                                .multilineTextAlignment(.center)
                                .foregroundStyle(.primary)
                                .lineLimit(2)
                        }
                        .padding(10)
                        .frame(maxWidth: .infinity)
                        .background(.thinMaterial, in: RoundedRectangle(cornerRadius: 16))
                    }
                }
            }
            .padding()
        }
        .pocketScreenBackground()
        .navigationTitle("PV")
        .navigationBarTitleDisplayMode(.inline)
        .pocketToolbar(title: "PV")
        .navigationDestination(for: String.self) { token in
            if token.hasPrefix("book:"),
               let id = Int64(token.replacingOccurrences(of: "book:", with: "")),
               let book = database.books.first(where: { $0.id == id }) {
                ReaderScreen(initialPath: "\(book.name)/index")
            }
        }
    }
}

struct SearchScreen: View {
    @EnvironmentObject private var database: AppDatabase
    @State private var query = ""
    @State private var results: [SearchResult] = []
    @State private var selectedPath = ""

    var body: some View {
        List(results) { result in
            Button {
                selectedPath = result.path
            } label: {
                VStack(alignment: .leading, spacing: 6) {
                    Text(result.title).font(.headline)
                    Text(highlightedSnippet(result.snippet))
                        .font(.subheadline)
                        .foregroundStyle(.secondary)
                }
            }
            .listRowBackground(Color.clear)
        }
        .scrollContentBackground(.hidden)
        .pocketScreenBackground()
        .navigationTitle("Search")
        .navigationBarTitleDisplayMode(.inline)
        .pocketToolbar(title: "PV")
        .searchable(text: $query, prompt: "Search scripture")
        .onSubmit(of: .search) {
            do {
                results = try database.search(query: query)
            } catch {
                database.lastError = error.localizedDescription
            }
        }
        .navigationDestination(isPresented: Binding(
            get: { !selectedPath.isEmpty },
            set: { if !$0 { selectedPath = "" } }
        )) {
            ReaderScreen(initialPath: selectedPath)
        }
    }

    /// Parses a snippet string with `<mark>…</mark>` tags and returns an
    /// AttributedString where matched keywords are highlighted in yellow/bold.
    private func highlightedSnippet(_ snippet: String) -> AttributedString {
        var result = AttributedString()
        var remaining = snippet

        while !remaining.isEmpty {
            if let markRange = remaining.range(of: "<mark>") {
                // Append plain text before the mark
                let before = String(remaining[remaining.startIndex..<markRange.lowerBound])
                if !before.isEmpty {
                    result += AttributedString(before)
                }
                remaining = String(remaining[markRange.upperBound...])

                // Find closing tag
                if let closeRange = remaining.range(of: "</mark>") {
                    let keyword = String(remaining[remaining.startIndex..<closeRange.lowerBound])
                    var highlighted = AttributedString(keyword)
                    highlighted.backgroundColor = .init(UIColor(red: 1, green: 0.94, blue: 0.54, alpha: 1))
                    highlighted.font = .subheadline.bold()
                    highlighted.foregroundColor = .init(UIColor(red: 0.1, green: 0.1, blue: 0.1, alpha: 1))
                    result += highlighted
                    remaining = String(remaining[closeRange.upperBound...])
                } else {
                    // Malformed — no closing tag, append the rest as plain
                    result += AttributedString(remaining)
                    break
                }
            } else {
                // No more marks — append rest as plain text
                result += AttributedString(remaining)
                break
            }
        }
        return result
    }
}


struct BookmarksView: View {
    @EnvironmentObject private var database: AppDatabase
    @State private var selectedPath = ""

    var body: some View {
        List {
            ForEach(database.bookmarks) { bookmark in
                Button {
                    selectedPath = bookmark.path
                } label: {
                    VStack(alignment: .leading, spacing: 4) {
                        Text(bookmark.name).font(.headline)
                        if !bookmark.description.isEmpty {
                            Text(bookmark.description).font(.subheadline).foregroundStyle(.secondary)
                        }
                    }
                }
                .listRowBackground(Color.clear)
            }
            .onDelete { indexSet in
                for index in indexSet {
                    do {
                        try database.removeBookmark(id: database.bookmarks[index].id)
                    } catch {
                        database.lastError = error.localizedDescription
                    }
                }
            }
        }
        .scrollContentBackground(.hidden)
        .pocketScreenBackground()
        .navigationTitle("Bookmarks")
        .navigationBarTitleDisplayMode(.inline)
        .pocketToolbar(title: "PV")
        .navigationDestination(isPresented: Binding(
            get: { !selectedPath.isEmpty },
            set: { if !$0 { selectedPath = "" } }
        )) {
            ReaderScreen(initialPath: selectedPath)
        }
    }
}

struct SettingsScreen: View {
    @AppStorage("pref_text") private var showText = true
    @AppStorage("pref_synonyms") private var showSynonyms = true
    @AppStorage("pref_translation") private var showTranslation = true
    @AppStorage("pref_purport") private var showPurport = true
    @AppStorage("pref_zoom") private var textSize = 133.0
    @AppStorage("pref_reverse") private var blackOnWhite = true
    @AppStorage("pref_keep_awake") private var keepAwake = false

    var body: some View {
        Form {
            Section("Content") {
                Toggle("Display Text", isOn: $showText)
                Toggle("Display Synonyms", isOn: $showSynonyms)
                Toggle("Display Translation", isOn: $showTranslation)
                Toggle("Display Purport", isOn: $showPurport)
            }

            Section("Display") {
                Stepper("Text size \(Int(textSize))%", value: $textSize, in: 50...300, step: 5)
                Toggle("Black on White", isOn: $blackOnWhite)
                Toggle("Keep awake", isOn: $keepAwake)
            }
        }
        .scrollContentBackground(.hidden)
        .pocketScreenBackground()
        .navigationTitle("Settings")
        .navigationBarTitleDisplayMode(.inline)
        .pocketToolbar(title: "Settings")
    }
}

struct StaticHTMLScreen: View {
    let title: String
    let html: String

    var body: some View {
        HTMLWebView(html: html, onOpenPath: { _ in })
            .navigationTitle(title)
            .navigationBarTitleDisplayMode(.inline)
            .pocketToolbar(title: title)
    }
}

struct PocketToolbarModifier: ViewModifier {
    @EnvironmentObject private var navigation: AppNavigationState
    @EnvironmentObject private var database: AppDatabase

    let title: String
    let bookmarkPath: String?
    @State private var showAddBookmark = false

    func body(content: Content) -> some View {
        content
            .toolbar {
                ToolbarItem(placement: .principal) {
                    HStack(spacing: 8) {
                        Image("LauncherIcon")
                            .resizable()
                            .frame(width: 24, height: 24)
                            .clipShape(RoundedRectangle(cornerRadius: 5))

                        Text(title)
                            .font(.headline)
                            .foregroundStyle(.black)
                            .lineLimit(1)
                            .truncationMode(.tail)
                    }
                }
                
                ToolbarItemGroup(placement: .topBarTrailing) {
                    Button {
                        navigation.selectedTab = .search
                    } label: {
                        Image(systemName: "magnifyingglass")
                    }

                    Menu {
                        if bookmarkPath != nil {
                            Button {
                                showAddBookmark = true
                            } label: {
                                Label("Add bookmark", systemImage: "bookmark")
                            }
                        }

                        Button {
                            navigation.showSettings = true
                        } label: {
                            Label("Settings", systemImage: "gearshape")
                        }

                        Button {
                            navigation.showAbout = true
                        } label: {
                            Label("About", systemImage: "info.circle")
                        }

                        Button {
                            navigation.selectedTab = .bookmarks
                        } label: {
                            Label("Bookmarks", systemImage: "bookmark")
                        }
                    } label: {
                        Image(systemName: "ellipsis.circle")
                    }
                }
            }
            .toolbarColorScheme(.light, for: .navigationBar)
            .toolbarBackground(Color(.systemBackground), for: .navigationBar)
            .toolbarBackground(.visible, for: .navigationBar)
            .sheet(isPresented: $showAddBookmark) {
                if let bookmarkPath {
                    NavigationStack {
                        AddBookmarkScreen(path: bookmarkPath)
                            .environmentObject(database)
                    }
                }
            }
    }
}

struct AddBookmarkScreen: View {
    @Environment(\.dismiss) private var dismiss
    @EnvironmentObject private var database: AppDatabase

    let path: String
    @State private var description = ""
    @State private var dynamic = false

    var body: some View {
        Form {
            Section {
                TextField("Bookmark description", text: $description)
                Toggle("Dynamic", isOn: $dynamic)
            }
        }
        .navigationTitle("Add bookmark")
        .navigationBarTitleDisplayMode(.inline)
        .toolbar {
            ToolbarItem(placement: .cancellationAction) {
                Button("Cancel") { dismiss() }
            }

            ToolbarItem(placement: .confirmationAction) {
                Button("OK") {
                    do {
                        try database.addBookmark(path: path, description: description, smart: dynamic)
                        dismiss()
                    } catch {
                        database.lastError = error.localizedDescription
                    }
                }
            }
        }
    }
}

extension View {
    func pocketToolbar(title: String, bookmarkPath: String? = nil) -> some View {
        modifier(PocketToolbarModifier(title: title, bookmarkPath: bookmarkPath))
    }

    func pocketScreenBackground() -> some View {
        modifier(PocketScreenBackgroundModifier())
    }
}

struct PocketScreenBackgroundModifier: ViewModifier {
    @AppStorage("pref_reverse") private var blackOnWhite = true

    func body(content: Content) -> some View {
        content
            .background(background.ignoresSafeArea())
            .preferredColorScheme(blackOnWhite ? .light : .dark)
    }

    private var background: some View {
        Group {
            if blackOnWhite {
                LinearGradient(
                    colors: [
                        Color(red: 0.99, green: 0.95, blue: 0.90),
                        Color(red: 0.96, green: 0.90, blue: 0.82)
                    ],
                    startPoint: .topLeading,
                    endPoint: .bottomTrailing
                )
            } else {
                Color.black
            }
        }
    }
}
