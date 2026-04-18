package android.support.v4.view;

import android.os.Build;
import android.view.ViewConfiguration;

/* loaded from: classes.dex */
public class ao {
    static final ar a;

    static {
        if (Build.VERSION.SDK_INT >= 11) {
            a = new aq();
        } else {
            a = new ap();
        }
    }

    public static int a(ViewConfiguration viewConfiguration) {
        return a.a(viewConfiguration);
    }
}
