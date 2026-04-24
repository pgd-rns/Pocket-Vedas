import SwiftUI

struct ReaderScreen: View {
    @EnvironmentObject private var database: AppDatabase
    let initialPath: String

    @State private var currentPage: ReaderPage?
    @State private var currentPath: String

    init(initialPath: String) {
        self.initialPath = initialPath
        _currentPath = State(initialValue: initialPath)
    }

    var body: some View {
        Group {
            if let currentPage {
                HTMLWebView(html: currentPage.html) { path in
                    currentPath = path
                    loadPage()
                }
            } else {
                ProgressView()
            }
        }
        .navigationTitle(currentPage?.title ?? "Reading")
        .navigationBarTitleDisplayMode(.inline)
        .toolbar {
            ToolbarItem(placement: .topBarTrailing) {
                Button {
                    do {
                        try database.addBookmark(path: currentPath)
                    } catch {
                        database.lastError = error.localizedDescription
                    }
                } label: {
                    Image(systemName: "bookmark.badge.plus")
                }
            }
        }
        .task(id: currentPath) {
            loadPage()
        }
    }

    private func loadPage() {
        do {
            currentPage = try database.readerPage(forPath: currentPath)
        } catch {
            database.lastError = error.localizedDescription
        }
    }
}
