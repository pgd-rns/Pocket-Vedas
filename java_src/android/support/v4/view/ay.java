package android.support.v4.view;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

/* loaded from: classes.dex */
class ay extends a {
    final /* synthetic */ ViewPager b;

    ay(ViewPager viewPager) {
        this.b = viewPager;
    }

    @Override // android.support.v4.view.a
    public void a(View view, android.support.v4.view.a.a aVar) {
        super.a(view, aVar);
        aVar.a(ViewPager.class.getName());
        aVar.a(this.b.g != null && this.b.g.b() > 1);
        if (this.b.g != null && this.b.h >= 0 && this.b.h < this.b.g.b() - 1) {
            aVar.a(4096);
        }
        if (this.b.g == null || this.b.h <= 0 || this.b.h >= this.b.g.b()) {
            return;
        }
        aVar.a(8192);
    }

    @Override // android.support.v4.view.a
    public boolean a(View view, int i, Bundle bundle) {
        if (super.a(view, i, bundle)) {
            return true;
        }
        switch (i) {
            case 4096:
                if (this.b.g == null || this.b.h < 0 || this.b.h >= this.b.g.b() - 1) {
                    return false;
                }
                this.b.setCurrentItem(this.b.h + 1);
                return true;
            case 8192:
                if (this.b.g == null || this.b.h <= 0 || this.b.h >= this.b.g.b()) {
                    return false;
                }
                this.b.setCurrentItem(this.b.h - 1);
                return true;
            default:
                return false;
        }
    }

    @Override // android.support.v4.view.a
    public void d(View view, AccessibilityEvent accessibilityEvent) {
        super.d(view, accessibilityEvent);
        accessibilityEvent.setClassName(ViewPager.class.getName());
    }
}
