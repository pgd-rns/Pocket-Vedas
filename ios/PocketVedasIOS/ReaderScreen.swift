import SwiftUI

struct ReaderScreen: View {
    @EnvironmentObject private var database: AppDatabase
    let initialPath: String

    @State private var siblings: [String] = []
    @State private var currentIndex: Int = 0
    @State private var currentPage: ReaderPage?
    @State private var nextPath = ""

    // We keep a random ID to force SwiftUI to recreate the view 
    // transition when the page changes to create a sliding effect.
    @State private var transitionId = UUID()
    @State private var transitionEdge: Edge = .trailing

    @Environment(\.dismiss) private var dismiss

    init(initialPath: String) {
        self.initialPath = initialPath
    }

    var body: some View {
        Group {
            if siblings.isEmpty || currentPage == nil {
                ProgressView()
            } else if let page = currentPage {
                HTMLWebView(
                    html: page.html,
                    onOpenPath: { link in nextPath = link },
                    onSwipeLeft: { navigateToSibling(offset: 1) },
                    onSwipeRight: { navigateToSibling(offset: -1) }
                )
                .id(transitionId)
                .transition(.asymmetric(
                    insertion: .move(edge: transitionEdge),
                    removal: .opacity
                ))
                .animation(.easeInOut(duration: 0.3), value: transitionId)
            }
        }
        .navigationTitle(currentPage?.title ?? "Reading")
        .navigationBarTitleDisplayMode(.inline)
        .navigationBarBackButtonHidden(true)
        .toolbar {
            ToolbarItem(placement: .topBarLeading) {
                Button(action: { dismiss() }) {
                    Image(systemName: "chevron.left")
                        .font(.body.weight(.semibold))
                }
            }
        }
        .pocketToolbar(title: currentPage?.title ?? "Reading", bookmarkPath: currentPage?.path ?? initialPath)
        .toolbar(.hidden, for: .tabBar)
        .task(id: initialPath) {
            loadInitialData()
        }
        .navigationDestination(isPresented: Binding(
            get: { !nextPath.isEmpty },
            set: { if !$0 { nextPath = "" } }
        )) {
            ReaderScreen(initialPath: nextPath)
        }
    }

    private func loadInitialData() {
        do {
            let result = try database.siblingPaths(forPath: initialPath)
            if result.paths.isEmpty {
                siblings = [initialPath]
                currentIndex = 0
            } else {
                siblings = result.paths
                currentIndex = result.currentIndex
            }
            loadCurrentPage()
        } catch {
            database.lastError = error.localizedDescription
            siblings = [initialPath]
            currentIndex = 0
        }
    }

    private func loadCurrentPage() {
        guard siblings.indices.contains(currentIndex) else { return }
        let path = siblings[currentIndex]
        do {
            currentPage = try database.readerPage(forPath: path)
        } catch {
            database.lastError = error.localizedDescription
        }
    }

    private func navigateToSibling(offset: Int) {
        let newIndex = currentIndex + offset
        guard newIndex >= 0 && newIndex < siblings.count else { return }
        
        transitionEdge = offset > 0 ? .trailing : .leading
        
        withAnimation {
            currentIndex = newIndex
            transitionId = UUID()
            loadCurrentPage()
        }
    }
}


