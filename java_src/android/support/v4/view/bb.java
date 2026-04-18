package android.support.v4.view;

import android.database.DataSetObserver;

/* loaded from: classes.dex */
class bb extends DataSetObserver {
    final /* synthetic */ ViewPager a;

    private bb(ViewPager viewPager) {
        this.a = viewPager;
    }

    /* synthetic */ bb(ViewPager viewPager, at atVar) {
        this(viewPager);
    }

    @Override // android.database.DataSetObserver
    public void onChanged() {
        this.a.b();
    }

    @Override // android.database.DataSetObserver
    public void onInvalidated() {
        this.a.b();
    }
}
