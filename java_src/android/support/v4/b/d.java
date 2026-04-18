package android.support.v4.b;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
class d implements Parcelable.ClassLoaderCreator {
    private final c a;

    public d(c cVar) {
        this.a = cVar;
    }

    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        return this.a.a(parcel, null);
    }

    @Override // android.os.Parcelable.ClassLoaderCreator
    public Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return this.a.a(parcel, classLoader);
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return this.a.a(i);
    }
}
