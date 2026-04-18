package uk.co.dancingganesh.pocketvedas;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public class k extends Fragment {
    private static int a = 0;
    private static WeakHashMap b = new WeakHashMap();
    private int e;
    private String f;
    private String h;
    private WebView c = null;
    private LinearLayout d = null;
    private float g = 0.0f;
    private transient String i = null;

    public k() {
        b.put(this, false);
    }

    public static void a(k kVar) throws Resources.NotFoundException {
        for (k kVar2 : b.keySet()) {
            if (kVar2 != kVar) {
                kVar2.a();
            }
        }
    }

    @SuppressLint({"NewApi"})
    private void w() throws Resources.NotFoundException {
        android.support.v4.app.h hVarH = h();
        if (hVarH == null) {
            return;
        }
        this.c = new WebView(hVarH);
        this.c.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        this.c.setPictureListener(new l(this));
        this.c.setWebViewClient(new n(this));
        WebSettings settings = this.c.getSettings();
        if (Build.VERSION.SDK_INT < 19) {
            settings.setBuiltInZoomControls(true);
        } else {
            settings.setBuiltInZoomControls(false);
            settings.setSupportZoom(false);
        }
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(h());
        a = defaultSharedPreferences.getInt("pref_zoom", 133);
        if (Build.VERSION.SDK_INT < 19) {
            this.c.setInitialScale(a);
        } else {
            settings.setTextZoom(a);
        }
        if (this.i == null) {
            this.i = uk.co.dancingganesh.pocketvedas.a.c.a().a(this.e, this.f);
        }
        String strZ = z();
        this.c.loadDataWithBaseURL("file:///android_asset/web/", this.h != null ? String.valueOf(strZ) + SearchActivity.b(this.i, this.h) : String.valueOf(strZ) + this.i, "text/html", "UTF-8", null);
        this.c.setKeepScreenOn(defaultSharedPreferences.getBoolean("pref_keep_awake", false));
        this.d.addView(this.c);
    }

    private float x() {
        float scrollY = this.c.getScrollY() / this.c.getContentHeight();
        if (scrollY > 1.0f) {
            return 1.0f;
        }
        return scrollY;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public o y() {
        return ((ReadingActivity) h()).f();
    }

    private String z() throws Resources.NotFoundException {
        boolean z = i().getBoolean(R.bool.isTablet);
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(h());
        boolean z2 = defaultSharedPreferences.getBoolean("pref_text", true);
        boolean z3 = defaultSharedPreferences.getBoolean("pref_synonyms", true);
        boolean z4 = defaultSharedPreferences.getBoolean("pref_translation", true);
        boolean z5 = defaultSharedPreferences.getBoolean("pref_purport", true);
        boolean z6 = defaultSharedPreferences.getBoolean("pref_reverse", true);
        return "<HTML xmlns:vb=\"http://www.vedabase.com\"><HEAD> <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"> <STYLE TYPE=\"text/css\">@font-face {font-family: DejaVu; src: url(DejaVuSans.ttf); }@font-face {font-family: DejaVuBold; src: url(DejaVuSans-Bold.ttf); }@font-face {font-family: DejaVuItalic; src: url(DejaVuSans-Oblique.ttf); }BODY {margin-top: 1%; padding: 0; text-indent: 0px; text-align: justify; background: [background] url(bg-texture.png) repeat;color: [foreground]; font-weight: normal; font-style: normal; font-variant: normal; font-size: 12pt; font-family: DejaVu;margin-top: 32pt; margin-bottom: 100pt; margin-left: [margin]px; margin-right: [margin]px; -webkit-text-size-adjust: none; }.space {clear: both; }.header {color: #009966; text-align: center; font-family: DejaVuBold; text-decoration: underline; margin-bottom: 18pt; }.text {[textOn]}.text-inner {}.verse {text-indent: 0px; display: table; margin: 10px auto; }.verse br {display: none; }.prelude {text-align: center; margin-top: 0pt; }.odd-line {text-align: left; margin-top: 0pt; }.even-line {text-indent: 20px; text-align: left; margin-top: 0pt; }.section-title {display: none; clear: both; text-align: center; font-family: DejaVuBold; text-decoration: underline;margin-top: 18pt; margin-bottom: 18pt; }.synonyms {[synonymOn] text-indent: 0; margin-top: 0pt; }.synonym {display: inline; }.word {color: #009966; display: inline; }.meaning {display: inline; }.translation {[translationOn] font-family: DejaVuBold; }.purport {[purportOn] text-indent: 24pt; }.body {text-indent: 24pt; }.paragraph {margin-bottom: 0; margin-top: 18pt; }.heading {margin-top: 18pt; border-top: 1px solid [foreground]; border-bottom: 1px solid [foreground]; font-weight: bold; text-indent: 0; }OL LI { text-indent: 0pt; margin-top: 5pt; }A.link:link {color: #990000; text-decoration: none; }A.link:active  {color: #990000; text-decoration: none; }A.link:visited {color: #990000; text-decoration: none; }.quote {text-align: center; margin-top: 10pt; }.foreign {font-family: DejaVuItalic; display: inline; }.highlight {background-color: yellow; color: black; display: inline; }UL {list-style-type: none; text-indent: 0px; -webkit-padding-start: 0px; }UL LI {margin-bottom: 10px; }.indexTarget {display: inline; }.indexTitle {display: inline; }</style><META NAME=\"viewport\" CONTENT=\"width=480, maximum-scale=8.0, initial-scale=1.0, user-scalable=yes\"/>".replace("[margin]", z ? "30" : "5").replace("[textOn]", z2 ? "" : "display:none;").replace("[synonymOn]", z3 ? "" : "display:none;").replace("[translationOn]", z4 ? "" : "display:none;").replace("[purportOn]", z5 ? "" : "display:none;").replace("[background]", z6 ? "white" : "black").replace("[foreground]", z6 ? "black" : "white");
    }

    @Override // android.support.v4.app.Fragment
    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) throws Resources.NotFoundException {
        if (bundle != null) {
            this.e = bundle.getInt("DIVISION");
            this.f = bundle.getString("PATH");
            this.g = bundle.getFloat("POSITION");
            this.h = bundle.getString("HIGHLIGHT");
        }
        c(true);
        this.c = null;
        this.d = new LinearLayout(h());
        w();
        return this.d;
    }

    public void a() throws Resources.NotFoundException {
        if (this.c != null) {
            this.d.removeView(this.c);
            this.c = null;
        }
        w();
    }

    public void a(float f) {
        this.g = f;
    }

    public void a(int i, String str) {
        this.e = i;
        this.f = str;
    }

    protected void a(WebView webView) throws Resources.NotFoundException {
        int scale;
        if (Build.VERSION.SDK_INT < 19 && a != (scale = (int) (100.0f * webView.getScale()))) {
            a = scale;
            android.support.v4.app.h hVarH = h();
            if (hVarH != null) {
                SharedPreferences.Editor editorEdit = PreferenceManager.getDefaultSharedPreferences(hVarH).edit();
                editorEdit.putInt("pref_zoom", a);
                editorEdit.commit();
            }
            a(this);
        }
    }

    public void a(String str) {
        this.h = str;
    }

    @Override // android.support.v4.app.Fragment
    public boolean a(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_add_bookmark /* 2131296275 */:
                a aVar = new a();
                aVar.a(this.f, x());
                aVar.a(y());
                aVar.a(h().e(), "bookmark");
                return true;
            default:
                return super.a(menuItem);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void e(Bundle bundle) {
        bundle.putInt("DIVISION", this.e);
        bundle.putString("PATH", this.f);
        bundle.putFloat("POSITION", x());
        bundle.putString("HIGHLIGHT", this.h);
    }

    @Override // android.support.v4.app.Fragment
    public void o() {
        super.o();
        y().a(this.e, this.f, x());
    }
}
