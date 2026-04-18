package android.support.v4.view;

import android.os.Parcel;
import android.support.v4.view.ViewPager;

/* loaded from: classes.dex */
final class bc implements android.support.v4.b.c {
    bc() {
    }

    @Override // android.support.v4.b.c
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public ViewPager.SavedState a(Parcel parcel, ClassLoader classLoader) {
        return new ViewPager.SavedState(parcel, classLoader);
    }

    @Override // android.support.v4.b.c
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public ViewPager.SavedState[] a(int i) {
        return new ViewPager.SavedState[i];
    }
}
