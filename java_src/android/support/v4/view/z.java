package android.support.v4.view;

import android.view.VelocityTracker;

/* loaded from: classes.dex */
class z implements ab {
    z() {
    }

    @Override // android.support.v4.view.ab
    public float a(VelocityTracker velocityTracker, int i) {
        return velocityTracker.getXVelocity();
    }
}
