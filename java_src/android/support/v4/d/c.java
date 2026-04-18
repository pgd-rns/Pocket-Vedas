package android.support.v4.d;

import android.database.ContentObserver;
import android.os.Handler;

/* loaded from: classes.dex */
class c extends ContentObserver {
    final /* synthetic */ a a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(a aVar) {
        super(new Handler());
        this.a = aVar;
    }

    @Override // android.database.ContentObserver
    public boolean deliverSelfNotifications() {
        return true;
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z) {
        this.a.b();
    }
}
