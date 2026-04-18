package android.support.v4.view;

import android.os.Build;
import android.view.View;

/* loaded from: classes.dex */
public class ad {
    static final aj a;

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 16 || Build.VERSION.CODENAME.equals("JellyBean")) {
            a = new ai();
            return;
        }
        if (i >= 14) {
            a = new ah();
            return;
        }
        if (i >= 11) {
            a = new ag();
        } else if (i >= 9) {
            a = new af();
        } else {
            a = new ae();
        }
    }

    public static int a(View view) {
        return a.a(view);
    }

    public static void a(View view, a aVar) {
        a.a(view, aVar);
    }

    public static boolean a(View view, int i) {
        return a.a(view, i);
    }

    public static void b(View view) {
        a.b(view);
    }

    public static void b(View view, int i) {
        a.b(view, i);
    }

    public static int c(View view) {
        return a.c(view);
    }
}
