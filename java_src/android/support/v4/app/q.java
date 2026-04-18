package android.support.v4.app;

import android.view.animation.Animation;

/* loaded from: classes.dex */
class q implements Animation.AnimationListener {
    final /* synthetic */ Fragment a;
    final /* synthetic */ n b;

    q(n nVar, Fragment fragment) {
        this.b = nVar;
        this.a = fragment;
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationEnd(Animation animation) {
        if (this.a.k != null) {
            this.a.k = null;
            this.b.a(this.a, this.a.l, 0, 0, false);
        }
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationRepeat(Animation animation) {
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationStart(Animation animation) {
    }
}
