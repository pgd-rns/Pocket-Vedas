package android.support.v4.b;

import android.os.Build;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class a {
    public static Parcelable.Creator a(c cVar) {
        if (Build.VERSION.SDK_INT >= 13) {
            e.a(cVar);
        }
        return new b(cVar);
    }
}
