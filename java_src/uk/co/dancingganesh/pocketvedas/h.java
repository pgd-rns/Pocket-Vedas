package uk.co.dancingganesh.pocketvedas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;

/* loaded from: classes.dex */
public class h extends Fragment {
    private WebView a = null;
    private LinearLayout b = null;
    private String c;
    private String d;
    private String e;
    private String f;

    private void a() {
        android.support.v4.app.h hVarH = h();
        if (hVarH == null) {
            return;
        }
        this.a = new WebView(hVarH);
        this.a.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        this.a.setWebViewClient(new i(this));
        String strReplace = "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><STYLE TYPE=\"text/css\">@font-face {font-family: DejaVu; src: url(DejaVuSans.ttf); }@font-face {font-family: DejaVuBold; src: url(DejaVuSans-Bold.ttf); }@font-face {font-family: DejaVuItalic; src: url(DejaVuSans-Oblique.ttf); }BODY {margin-top: 1%; background: #00c8f0 url(bg-texture.png) repeat; color: #ffffff;font-weight: normal; font-style: normal; font-variant: normal; font-size: 15pt; font-family: DejaVu;}.name {margin-top: 20px; font-family: DejaVuItalic; }#top {display:table; width: 100%; height: 50%; padding-left: 10%; padding-right: 10%; }#end {display:table-cell; vertical-align: middle; text-align: left; }#bottom {display:table; width: 100%; height: 50%; padding-left: 10%; padding-right: 10%; [nextVisible] }#next {display:table-cell; vertical-align: top; text-align: right; }a {color: #ffffff;}</STYLE></head><body><div id=\"top\"><div id=\"end\">[thusEnds]</div></div></div><div id=\"bottom\"><div id=\"next\">Next:<a href=\"veda:[nextUrl]\"><div class=\"name\">[nextTitle]</div></a></div></div></body></html>".replace("[thusEnds]", this.c);
        this.a.loadDataWithBaseURL("file:///android_asset/web/", this.d == null ? strReplace.replace("[nextVisible]", "display: none;") : strReplace.replace("[nextVisible]", "").replace("[nextTitle]", this.d).replace("[nextUrl]", this.e), "text/html", "UTF-8", null);
        this.b.addView(this.a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public o w() {
        return ((ReadingActivity) h()).f();
    }

    @Override // android.support.v4.app.Fragment
    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (bundle != null) {
            this.c = bundle.getString("TEXT");
            this.d = bundle.getString("TITLE");
            this.e = bundle.getString("URL");
            this.f = bundle.getString("PATH");
        }
        this.b = new LinearLayout(h());
        a();
        return this.b;
    }

    public void a(uk.co.dancingganesh.pocketvedas.a.d dVar, String str) {
        this.c = dVar.a();
        this.d = dVar.b();
        this.e = dVar.c();
        this.f = str;
    }

    @Override // android.support.v4.app.Fragment
    public boolean a(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_add_bookmark /* 2131296275 */:
                a aVar = new a();
                aVar.a(this.f, 0.0f);
                aVar.a(w());
                aVar.a(h().e(), "bookmark");
                return true;
            default:
                return super.a(menuItem);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void e(Bundle bundle) {
        bundle.putString("TEXT", this.c);
        bundle.putString("TITLE", this.d);
        bundle.putString("URL", this.e);
        bundle.putString("PATH", this.f);
    }

    @Override // android.support.v4.app.Fragment
    public void o() {
        super.o();
        w().a(0, this.f, 0.0f);
    }
}
