import SwiftUI
import WebKit

struct HTMLWebView: UIViewRepresentable {
    let html: String
    let onOpenPath: (String) -> Void

    init(html: String, onOpenPath: @escaping (String) -> Void) {
        self.html = html
        self.onOpenPath = onOpenPath
    }

    func makeCoordinator() -> Coordinator {
        Coordinator(onOpenPath: onOpenPath)
    }

    func makeUIView(context: Context) -> WKWebView {
        let configuration = WKWebViewConfiguration()
        let webView = WKWebView(frame: .zero, configuration: configuration)
        webView.navigationDelegate = context.coordinator
        webView.scrollView.contentInsetAdjustmentBehavior = .never
        webView.isOpaque = false
        webView.backgroundColor = .clear
        return webView
    }

    func updateUIView(_ webView: WKWebView, context: Context) {
        guard context.coordinator.lastHTML != html else { return }
        context.coordinator.lastHTML = html

        let pageDirectory = FileManager.default.temporaryDirectory
            .appendingPathComponent("PocketVedasWebView", isDirectory: true)
        let webURL = pageDirectory.appendingPathComponent("web", isDirectory: true)
        let htmlToLoad = htmlWithBaseURL(webURL)
        let fileURL = pageDirectory.appendingPathComponent("\(UUID().uuidString).html")

        do {
            try prepareWebDirectory(at: pageDirectory)
            try htmlToLoad.write(to: fileURL, atomically: true, encoding: .utf8)
            webView.loadFileURL(fileURL, allowingReadAccessTo: pageDirectory)
        } catch {
            webView.loadHTMLString(htmlToLoad, baseURL: webURL)
        }
    }

    private func prepareWebDirectory(at pageDirectory: URL) throws {
        let fileManager = FileManager.default
        try fileManager.createDirectory(at: pageDirectory, withIntermediateDirectories: true)

        let destinationWebURL = pageDirectory.appendingPathComponent("web", isDirectory: true)
        if fileManager.fileExists(atPath: destinationWebURL.appendingPathComponent("bg-texture.png").path),
           fileManager.fileExists(atPath: destinationWebURL.appendingPathComponent("DejaVuSans.ttf").path) {
            return
        }

        if fileManager.fileExists(atPath: destinationWebURL.path) {
            try fileManager.removeItem(at: destinationWebURL)
        }

        if let sourceWebURL = Bundle.main.resourceURL?.appendingPathComponent("web", isDirectory: true),
           fileManager.fileExists(atPath: sourceWebURL.path) {
            try fileManager.copyItem(at: sourceWebURL, to: destinationWebURL)
        }
    }

    private func htmlWithBaseURL(_ baseURL: URL?) -> String {
        guard let baseURL else { return html }
        let tag = #"<base href="\#(baseURL.absoluteString)">"#

        if html.range(of: "<head>", options: [.caseInsensitive]) != nil {
            return html.replacingOccurrences(
                of: "(?i)<head>",
                with: "<head>\n\(tag)",
                options: .regularExpression,
                range: nil
            )
        }

        if html.range(of: "<HEAD>", options: [.caseInsensitive]) != nil {
            return html.replacingOccurrences(
                of: "(?i)<HEAD>",
                with: "<HEAD>\n\(tag)",
                options: .regularExpression,
                range: nil
            )
        }

        return """
        <html>
        <head>
        \(tag)
        </head>
        <body>
        \(html)
        </body>
        </html>
        """
    }

    final class Coordinator: NSObject, WKNavigationDelegate {
        let onOpenPath: (String) -> Void
        var lastHTML = ""

        init(onOpenPath: @escaping (String) -> Void) {
            self.onOpenPath = onOpenPath
        }

        func webView(_ webView: WKWebView, decidePolicyFor navigationAction: WKNavigationAction) async -> WKNavigationActionPolicy {
            guard let url = navigationAction.request.url else {
                return .allow
            }

            if url.scheme == "veda" {
                var path = url.absoluteString.replacingOccurrences(of: "veda:", with: "")
                path = path.replacingOccurrences(of: "//", with: "")
                onOpenPath(path)
                return .cancel
            }

            return .allow
        }
    }
}
