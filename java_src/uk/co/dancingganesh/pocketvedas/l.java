package uk.co.dancingganesh.pocketvedas;

import android.content.res.Resources;
import android.graphics.Picture;
import android.webkit.WebView;

/* loaded from: classes.dex */
class l implements WebView.PictureListener {
    final /* synthetic */ k a;

    l(k kVar) {
        this.a = kVar;
    }

    @Override // android.webkit.WebView.PictureListener
    public void onNewPicture(WebView webView, Picture picture) throws Resources.NotFoundException {
        this.a.a(webView);
        if (this.a.g > 0.0f) {
            this.a.c.post(new m(this));
        }
    }
}
