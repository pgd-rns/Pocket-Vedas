package android.support.v4.view;

import android.view.View;

/* loaded from: classes.dex */
class ae implements aj {
    ae() {
    }

    @Override // android.support.v4.view.aj
    public int a(View view) {
        return 2;
    }

    long a() {
        return 10L;
    }

    @Override // android.support.v4.view.aj
    public void a(View view, a aVar) {
    }

    @Override // android.support.v4.view.aj
    public boolean a(View view, int i) {
        return false;
    }

    @Override // android.support.v4.view.aj
    public void b(View view) {
        view.postInvalidateDelayed(a());
    }

    @Override // android.support.v4.view.aj
    public void b(View view, int i) {
    }

    @Override // android.support.v4.view.aj
    public int c(View view) {
        return 0;
    }
}
