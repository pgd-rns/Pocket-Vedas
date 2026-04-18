package android.support.v4.view;

import android.view.View;

/* loaded from: classes.dex */
public class an {
    public static void a(View view) {
        view.postInvalidateOnAnimation();
    }

    public static void a(View view, int i) {
        view.setImportantForAccessibility(i);
    }

    public static int b(View view) {
        return view.getImportantForAccessibility();
    }
}
