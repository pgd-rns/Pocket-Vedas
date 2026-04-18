package android.support.v4.d;

import android.database.DataSetObserver;

/* loaded from: classes.dex */
class d extends DataSetObserver {
    final /* synthetic */ a a;

    private d(a aVar) {
        this.a = aVar;
    }

    @Override // android.database.DataSetObserver
    public void onChanged() {
        this.a.a = true;
        this.a.notifyDataSetChanged();
    }

    @Override // android.database.DataSetObserver
    public void onInvalidated() {
        this.a.a = false;
        this.a.notifyDataSetInvalidated();
    }
}
