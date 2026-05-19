import SwiftUI

struct ReaderScreen: View {
    @EnvironmentObject private var database: AppDatabase
    let initialPath: String

    @State private var paths: [String] = []
    @State private var currentPath: String
    @State private var currentPage: ReaderPage?
    @State private var nextPath = ""

    init(initialPath: String) {
        self.initialPath = initialPath
        _currentPath = State(initialValue: initialPath)
    }

    var body: some View {
        Group {
            if paths.isEmpty {
                ProgressView()
            } else {
                TabView(selection: $currentPath) {
                    ForEach(paths, id: \.self) { path in
                        ReaderPageView(path: path, nextPath: $nextPath)
                            .tag(path)
                    }
                }
                .tabViewStyle(.page(indexDisplayMode: .never))
            }
        }
        .navigationTitle(currentPage?.title ?? "Reading")
        .navigationBarTitleDisplayMode(.inline)
        .pocketToolbar(title: currentPage?.title ?? "Reading", bookmarkPath: currentPath)
        .toolbar(.hidden, for: .tabBar)
        .task(id: initialPath) {
            loadPaths()
        }
        .task(id: currentPath) {
            loadPage(for: currentPath)
        }
        .navigationDestination(isPresented: Binding(
            get: { !nextPath.isEmpty },
            set: { if !$0 { nextPath = "" } }
        )) {
            ReaderScreen(initialPath: nextPath)
        }
    }

    private func loadPaths() {
        do {
            let result = try database.siblingPaths(forPath: initialPath)
            if result.paths.isEmpty {
                paths = [initialPath]
                currentPath = initialPath
            } else {
                paths = result.paths
                currentPath = paths[result.currentIndex]
            }
        } catch {
            database.lastError = error.localizedDescription
            paths = [initialPath]
            currentPath = initialPath
        }
    }

    private func loadPage(for path: String) {
        do {
            currentPage = try database.readerPage(forPath: path)
        } catch {
            database.lastError = error.localizedDescription
        }
    }
}

struct ReaderPageView: View {
    @EnvironmentObject private var database: AppDatabase
    let path: String
    @Binding var nextPath: String

    @State private var page: ReaderPage?

    var body: some View {
        Group {
            if let page {
                HTMLWebView(html: page.html) { link in
                    nextPath = link
                }
            } else {
                ProgressView()
            }
        }
        .task(id: path) {
            do {
                page = try database.readerPage(forPath: path)
            } catch {
                database.lastError = error.localizedDescription
            }
        }
    }
}
