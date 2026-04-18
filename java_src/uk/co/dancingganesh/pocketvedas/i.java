package uk.co.dancingganesh.pocketvedas;

import android.content.Intent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/* loaded from: classes.dex */
class i extends WebViewClient {
    final /* synthetic */ h a;

    i(h hVar) {
        this.a = hVar;
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (!str.startsWith("veda:")) {
            return false;
        }
        Intent intent = new Intent(this.a.h(), (Class<?>) ReadingActivity.class);
        intent.putExtra("uk.co.dancingganesh.pocketvedas.BOOK_ADDRESS", str.substring(5));
        this.a.w().a(0, intent);
        this.a.a(intent);
        return true;
    }
}
