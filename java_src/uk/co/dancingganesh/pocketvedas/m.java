package uk.co.dancingganesh.pocketvedas;

/* loaded from: classes.dex */
class m implements Runnable {
    final /* synthetic */ l a;

    m(l lVar) {
        this.a = lVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.a.c.scrollTo(0, (int) (this.a.a.c.getContentHeight() * this.a.a.g));
    }
}
