package uk.co.dancingganesh.pocketvedas;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/* loaded from: classes.dex */
class n extends WebViewClient {
    final /* synthetic */ k a;

    n(k kVar) {
        this.a = kVar;
    }

    @Override // android.webkit.WebViewClient
    public void onScaleChanged(WebView webView, float f, float f2) throws Resources.NotFoundException {
        if (Build.VERSION.SDK_INT < 19) {
            this.a.a(webView);
        }
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (!str.startsWith("veda:")) {
            return false;
        }
        Intent intent = new Intent(this.a.h(), (Class<?>) ReadingActivity.class);
        intent.putExtra("uk.co.dancingganesh.pocketvedas.BOOK_ADDRESS", str.substring(5));
        this.a.y().a(this.a.e, intent);
        this.a.a(intent);
        return true;
    }
}
