package uk.co.dancingganesh.pocketvedas

import android.annotation.SuppressLint
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import kotlin.math.abs

/**
 * WebView composable — mirrors iOS HTMLWebView.swift.
 * Loads HTML content with the web/ assets base URL and intercepts veda:// links.
 *
 * Swipe detection is done at the native Android level via GestureDetector so
 * that it works correctly alongside WebView's own vertical scrolling — mirroring
 * how iOS adds gesture recognisers directly to the WKWebView.
 *
 * onSwipeLeft  → finger moves left  → navigate to next sibling
 * onSwipeRight → finger moves right → navigate to prev sibling
 */
@SuppressLint("SetJavaScriptEnabled", "ClickableViewAccessibility")
@Composable
fun HtmlWebView(
    html: String,
    onOpenPath: (String) -> Unit,
    onSwipeLeft: (() -> Unit)? = null,
    onSwipeRight: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    // Keep swipe callbacks in a mutable ref so the GestureDetector (created once
    // in `factory`) always calls the latest lambda even after recomposition.
    val swipeLeftRef  = remember { mutableStateOf(onSwipeLeft) }
    val swipeRightRef = remember { mutableStateOf(onSwipeRight) }

    AndroidView(
        factory = { ctx ->
            WebView(ctx).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                settings.apply {
                    javaScriptEnabled = false
                    allowFileAccess = true
                    setSupportZoom(false)
                    builtInZoomControls = false
                    textZoom = 100
                    loadWithOverviewMode = true
                    useWideViewPort = true
                }
                setBackgroundColor(android.graphics.Color.TRANSPARENT)
                isVerticalScrollBarEnabled = true
                isHorizontalScrollBarEnabled = false
                overScrollMode = WebView.OVER_SCROLL_NEVER

                webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(
                        view: WebView?,
                        request: WebResourceRequest?
                    ): Boolean {
                        val url = request?.url?.toString() ?: return false
                        if (url.startsWith("veda:")) {
                            onOpenPath(url.removePrefix("veda:").removePrefix("//"))
                            return true
                        }
                        return false
                    }

                    @Deprecated("Deprecated in Java")
                    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                        url ?: return false
                        if (url.startsWith("veda:")) {
                            onOpenPath(url.removePrefix("veda:").removePrefix("//"))
                            return true
                        }
                        return false
                    }
                }

                // ── Swipe gesture detector ──────────────────────────────────────
                // Uses GestureDetector.onFling so vertical WebView scrolling is
                // completely unaffected; only clear horizontal flings fire callbacks.
                val gestureDetector = GestureDetector(
                    ctx,
                    object : GestureDetector.SimpleOnGestureListener() {
                        override fun onFling(
                            e1: MotionEvent?,
                            e2: MotionEvent,
                            velocityX: Float,
                            velocityY: Float
                        ): Boolean {
                            val startEvent = e1 ?: return false
                            val diffX = e2.x - startEvent.x
                            val diffY = e2.y - startEvent.y
                            // Only trigger when the gesture is clearly horizontal
                            if (abs(diffX) > abs(diffY) * 1.5f &&
                                abs(diffX) > 80f &&
                                abs(velocityX) > 200f
                            ) {
                                if (diffX < 0) swipeLeftRef.value?.invoke()   // finger moved left → next
                                else           swipeRightRef.value?.invoke()  // finger moved right → prev
                                return true
                            }
                            return false
                        }
                    }
                )

                // Pass all touch events to the GestureDetector first, then let
                // the WebView handle them normally (returning false preserves
                // native WebView scrolling behaviour).
                setOnTouchListener { _, event ->
                    gestureDetector.onTouchEvent(event)
                    false
                }
            }
        },
        update = { webView ->
            // Update swipe refs whenever the composable recomposes with new lambdas
            swipeLeftRef.value  = onSwipeLeft
            swipeRightRef.value = onSwipeRight

            webView.loadDataWithBaseURL(
                "file:///android_asset/web/",
                html,
                "text/html",
                "UTF-8",
                null
            )
        },
        modifier = modifier
    )
}
