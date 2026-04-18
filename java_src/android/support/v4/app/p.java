package android.support.v4.app;

/* loaded from: classes.dex */
class p implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ int b;
    final /* synthetic */ n c;

    p(n nVar, int i, int i2) {
        this.c = nVar;
        this.a = i;
        this.b = i2;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.c.a(this.c.o.a, (String) null, this.a, this.b);
    }
}
