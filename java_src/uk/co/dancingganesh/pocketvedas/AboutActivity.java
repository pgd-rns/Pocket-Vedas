package uk.co.dancingganesh.pocketvedas;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class AboutActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) throws IOException {
        super.onCreate(bundle);
        setContentView(R.layout.activity_about);
        WebView webView = (WebView) findViewById(R.id.aboutWebView);
        try {
            InputStream inputStreamOpen = getAssets().open("raw/info.html");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            uk.co.dancingganesh.pocketvedas.a.e.a(inputStreamOpen, byteArrayOutputStream);
            webView.loadData(byteArrayOutputStream.toString(), "text/html", "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
