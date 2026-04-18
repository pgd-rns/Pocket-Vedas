package android.support.v4.view;

import android.os.Build;
import android.view.KeyEvent;

/* loaded from: classes.dex */
public class n {
    static final q a;

    static {
        if (Build.VERSION.SDK_INT >= 11) {
            a = new p();
        } else {
            a = new o();
        }
    }

    public static boolean a(KeyEvent keyEvent) {
        return a.b(keyEvent.getMetaState());
    }

    public static boolean a(KeyEvent keyEvent, int i) {
        return a.a(keyEvent.getMetaState(), i);
    }
}
