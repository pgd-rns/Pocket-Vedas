import Foundation

struct Book: Identifiable, Hashable {
    let id: Int64
    let name: String
    let coverData: Data?
}

struct Bookmark: Identifiable, Hashable {
    let id: Int64
    let path: String
    let name: String
    let description: String
    let smart: Bool
    let offset: Double
}

struct SearchResult: Identifiable, Hashable {
    let id: Int64
    let title: String
    let snippet: String
    let path: String
}

struct ReaderPage: Identifiable, Hashable {
    let id: Int64
    let title: String
    let path: String
    let html: String
}
