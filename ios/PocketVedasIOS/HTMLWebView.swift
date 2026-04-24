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
        let baseURL = Bundle.main.resourceURL?.appendingPathComponent("web", isDirectory: true)
        webView.loadHTMLString(html, baseURL: baseURL)
    }

    final class Coordinator: NSObject, WKNavigationDelegate {
        let onOpenPath: (String) -> Void

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
