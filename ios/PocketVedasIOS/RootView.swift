import SwiftUI

struct RootView: View {
    @EnvironmentObject private var database: AppDatabase
    private var showingError: Binding<Bool> {
        Binding(
            get: { database.lastError != nil },
            set: { if !$0 { database.lastError = nil } }
        )
    }

    var body: some View {
        TabView {
            NavigationStack {
                BooksView()
            }
            .tabItem {
                Label("Books", systemImage: "books.vertical")
            }

            NavigationStack {
                SearchScreen()
            }
            .tabItem {
                Label("Search", systemImage: "magnifyingglass")
            }

            NavigationStack {
                BookmarksView()
            }
            .tabItem {
                Label("Bookmarks", systemImage: "bookmark")
            }
        }
        .alert("Database Error", isPresented: showingError, actions: {
            Button("OK") { database.lastError = nil }
        }, message: {
            Text(database.lastError ?? "")
        })
    }
}

struct BooksView: View {
    @EnvironmentObject private var database: AppDatabase
    @State private var showAbout = false

    private let columns = [GridItem(.adaptive(minimum: 130), spacing: 16)]

    var body: some View {
        ScrollView {
            LazyVGrid(columns: columns, spacing: 16) {
                ForEach(database.books) { book in
                    NavigationLink(value: "book:\(book.id)") {
                        VStack(spacing: 10) {
                            if let coverData = book.coverData, let image = UIImage(data: coverData) {
                                Image(uiImage: image)
                                    .resizable()
                                    .scaledToFit()
                                    .frame(height: 180)
                                    .shadow(radius: 6, y: 3)
                            } else {
                                RoundedRectangle(cornerRadius: 14)
                                    .fill(Color.orange.opacity(0.18))
                                    .frame(height: 180)
                                    .overlay(Image(systemName: "book.closed").font(.largeTitle))
                            }

                            Text(book.name)
                                .font(.headline)
                                .multilineTextAlignment(.center)
                                .foregroundStyle(.primary)
                        }
                        .padding(12)
                        .frame(maxWidth: .infinity)
                        .background(.thinMaterial, in: RoundedRectangle(cornerRadius: 20))
                    }
                }
            }
            .padding()
        }
        .background(
            LinearGradient(colors: [Color(red: 0.99, green: 0.95, blue: 0.9), Color(red: 0.96, green: 0.9, blue: 0.82)],
                           startPoint: .topLeading,
                           endPoint: .bottomTrailing)
                .ignoresSafeArea()
        )
        .navigationTitle("Pocket Vedas")
        .toolbar {
            ToolbarItem(placement: .topBarTrailing) {
                Button("About") { showAbout = true }
            }
        }
        .navigationDestination(for: String.self) { token in
            if token.hasPrefix("book:"),
               let id = Int64(token.replacingOccurrences(of: "book:", with: "")),
               let book = database.books.first(where: { $0.id == id }) {
                ReaderScreen(initialPath: "\(book.name)/index")
            }
        }
        .sheet(isPresented: $showAbout) {
            NavigationStack {
                StaticHTMLScreen(title: "About", html: database.aboutHTML())
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
                    Text(result.snippet.replacingOccurrences(of: "<mark>", with: "")
                        .replacingOccurrences(of: "</mark>", with: ""))
                        .font(.subheadline)
                        .foregroundStyle(.secondary)
                }
            }
        }
        .navigationTitle("Search")
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
        .navigationTitle("Bookmarks")
        .navigationDestination(isPresented: Binding(
            get: { !selectedPath.isEmpty },
            set: { if !$0 { selectedPath = "" } }
        )) {
            ReaderScreen(initialPath: selectedPath)
        }
    }
}

struct StaticHTMLScreen: View {
    let title: String
    let html: String

    var body: some View {
        HTMLWebView(html: html, onOpenPath: { _ in })
            .navigationTitle(title)
            .navigationBarTitleDisplayMode(.inline)
    }
}
