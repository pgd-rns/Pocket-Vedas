package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class h extends Activity {
    final Handler a = new i(this);
    final n b = new n();
    boolean c;
    boolean d;
    boolean e;
    boolean f;
    boolean g;
    boolean h;
    boolean i;
    boolean j;
    android.support.v4.c.c k;
    x l;

    x a(int i, boolean z, boolean z2) {
        if (this.k == null) {
            this.k = new android.support.v4.c.c();
        }
        x xVar = (x) this.k.a(i);
        if (xVar != null) {
            xVar.a(this);
            return xVar;
        }
        if (!z2) {
            return xVar;
        }
        x xVar2 = new x(this, z);
        this.k.b(i, xVar2);
        return xVar2;
    }

    protected void a() {
        this.b.m();
    }

    void a(int i) {
        x xVar;
        if (this.k == null || (xVar = (x) this.k.a(i)) == null || xVar.f) {
            return;
        }
        xVar.h();
        this.k.c(i);
    }

    public void a(Fragment fragment) {
    }

    public void a(Fragment fragment, Intent intent, int i) {
        if (i == -1) {
            super.startActivityForResult(intent, -1);
        } else {
            if (((-65536) & i) != 0) {
                throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
            }
            super.startActivityForResult(intent, ((fragment.o + 1) << 16) + (65535 & i));
        }
    }

    void a(boolean z) {
        if (this.f) {
            return;
        }
        this.f = true;
        this.g = z;
        this.a.removeMessages(1);
        d();
    }

    public Object b() {
        return null;
    }

    public void c() {
        if (Build.VERSION.SDK_INT >= 11) {
            a.a(this);
        } else {
            this.h = true;
        }
    }

    void d() {
        if (this.j) {
            this.j = false;
            if (this.l != null) {
                if (this.g) {
                    this.l.d();
                } else {
                    this.l.c();
                }
            }
        }
        this.b.p();
    }

    @Override // android.app.Activity
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        if (Build.VERSION.SDK_INT >= 11) {
        }
        printWriter.print(str);
        printWriter.print("Local FragmentActivity ");
        printWriter.print(Integer.toHexString(System.identityHashCode(this)));
        printWriter.println(" State:");
        String str2 = str + "  ";
        printWriter.print(str2);
        printWriter.print("mCreated=");
        printWriter.print(this.c);
        printWriter.print("mResumed=");
        printWriter.print(this.d);
        printWriter.print(" mStopped=");
        printWriter.print(this.e);
        printWriter.print(" mReallyStopped=");
        printWriter.println(this.f);
        printWriter.print(str2);
        printWriter.print("mLoadersStarted=");
        printWriter.println(this.j);
        if (this.l != null) {
            printWriter.print(str);
            printWriter.print("Loader Manager ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this.l)));
            printWriter.println(":");
            this.l.a(str + "  ", fileDescriptor, printWriter, strArr);
        }
        this.b.a(str, fileDescriptor, printWriter, strArr);
    }

    public l e() {
        return this.b;
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        int i3 = i >> 16;
        if (i3 == 0) {
            super.onActivityResult(i, i2, intent);
            return;
        }
        int i4 = i3 - 1;
        if (this.b.f == null || i4 < 0 || i4 >= this.b.f.size()) {
            Log.w("FragmentActivity", "Activity result fragment index out of range: 0x" + Integer.toHexString(i));
            return;
        }
        Fragment fragment = (Fragment) this.b.f.get(i4);
        if (fragment == null) {
            Log.w("FragmentActivity", "Activity result no fragment exists for index: 0x" + Integer.toHexString(i));
        } else {
            fragment.a(65535 & i, i2, intent);
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (this.b.c()) {
            return;
        }
        finish();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.b.a(configuration);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        this.b.a(this);
        if (getLayoutInflater().getFactory() == null) {
            getLayoutInflater().setFactory(this);
        }
        super.onCreate(bundle);
        k kVar = (k) getLastNonConfigurationInstance();
        if (kVar != null) {
            this.k = kVar.e;
        }
        if (bundle != null) {
            this.b.a(bundle.getParcelable("android:support:fragments"), kVar != null ? kVar.d : null);
        }
        this.b.j();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onCreatePanelMenu(int i, Menu menu) {
        if (i != 0) {
            return super.onCreatePanelMenu(i, menu);
        }
        boolean zOnCreatePanelMenu = super.onCreatePanelMenu(i, menu) | this.b.a(menu, getMenuInflater());
        if (Build.VERSION.SDK_INT >= 11) {
            return zOnCreatePanelMenu;
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.app.Activity, android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        Object[] objArr = 0;
        if (!"fragment".equals(str)) {
            return super.onCreateView(str, context, attributeSet);
        }
        String attributeValue = attributeSet.getAttributeValue(null, "class");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.a);
        if (attributeValue == null) {
            attributeValue = typedArrayObtainStyledAttributes.getString(0);
        }
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(1, -1);
        String string = typedArrayObtainStyledAttributes.getString(2);
        typedArrayObtainStyledAttributes.recycle();
        int id = 0 != 0 ? (objArr == true ? 1 : 0).getId() : 0;
        if (id == -1 && resourceId == -1 && string == null) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + attributeValue);
        }
        Fragment fragmentA = resourceId != -1 ? this.b.a(resourceId) : null;
        if (fragmentA == null && string != null) {
            fragmentA = this.b.a(string);
        }
        if (fragmentA == null && id != -1) {
            fragmentA = this.b.a(id);
        }
        if (n.a) {
            Log.v("FragmentActivity", "onCreateView: id=0x" + Integer.toHexString(resourceId) + " fname=" + attributeValue + " existing=" + fragmentA);
        }
        if (fragmentA == null) {
            Fragment fragmentA2 = Fragment.a(this, attributeValue);
            fragmentA2.x = true;
            fragmentA2.D = resourceId != 0 ? resourceId : id;
            fragmentA2.E = id;
            fragmentA2.F = string;
            fragmentA2.y = true;
            fragmentA2.B = this.b;
            fragmentA2.a(this, attributeSet, fragmentA2.m);
            this.b.a(fragmentA2, true);
            fragmentA = fragmentA2;
        } else {
            if (fragmentA.y) {
                throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(id) + " with another fragment for " + attributeValue);
            }
            fragmentA.y = true;
            if (!fragmentA.J) {
                fragmentA.a(this, attributeSet, fragmentA.m);
            }
            this.b.c(fragmentA);
        }
        if (fragmentA.P == null) {
            throw new IllegalStateException("Fragment " + attributeValue + " did not create a view.");
        }
        if (resourceId != 0) {
            fragmentA.P.setId(resourceId);
        }
        if (fragmentA.P.getTag() == null) {
            fragmentA.P.setTag(string);
        }
        return fragmentA.P;
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        a(false);
        this.b.q();
        if (this.l != null) {
            this.l.h();
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 5 || i != 4 || keyEvent.getRepeatCount() != 0) {
            return super.onKeyDown(i, keyEvent);
        }
        onBackPressed();
        return true;
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        this.b.r();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        switch (i) {
            case 0:
                return this.b.a(menuItem);
            case 6:
                return this.b.b(menuItem);
            default:
                return false;
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i, Menu menu) {
        switch (i) {
            case 0:
                this.b.b(menu);
                break;
        }
        super.onPanelClosed(i, menu);
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        this.d = false;
        if (this.a.hasMessages(2)) {
            this.a.removeMessages(2);
            a();
        }
        this.b.n();
    }

    @Override // android.app.Activity
    protected void onPostResume() {
        super.onPostResume();
        this.a.removeMessages(2);
        a();
        this.b.e();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onPreparePanel(int i, View view, Menu menu) {
        if (i != 0 || menu == null) {
            return super.onPreparePanel(i, view, menu);
        }
        if (this.h) {
            this.h = false;
            menu.clear();
            onCreatePanelMenu(i, menu);
        }
        return (super.onPreparePanel(i, view, menu) || this.b.a(menu)) && menu.hasVisibleItems();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        this.a.sendEmptyMessage(2);
        this.d = true;
        this.b.e();
    }

    @Override // android.app.Activity
    public final Object onRetainNonConfigurationInstance() {
        boolean z;
        if (this.e) {
            a(true);
        }
        Object objB = b();
        ArrayList arrayListG = this.b.g();
        boolean z2 = false;
        if (this.k != null) {
            int iA = this.k.a() - 1;
            while (iA >= 0) {
                x xVar = (x) this.k.f(iA);
                if (xVar.f) {
                    z = true;
                } else {
                    xVar.h();
                    this.k.d(iA);
                    z = z2;
                }
                iA--;
                z2 = z;
            }
        }
        if (arrayListG == null && !z2 && objB == null) {
            return null;
        }
        k kVar = new k();
        kVar.a = null;
        kVar.b = objB;
        kVar.c = null;
        kVar.d = arrayListG;
        kVar.e = this.k;
        return kVar;
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Parcelable parcelableH = this.b.h();
        if (parcelableH != null) {
            bundle.putParcelable("android:support:fragments", parcelableH);
        }
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        this.e = false;
        this.f = false;
        this.a.removeMessages(1);
        if (!this.c) {
            this.c = true;
            this.b.k();
        }
        this.b.i();
        this.b.e();
        if (!this.j) {
            this.j = true;
            if (this.l != null) {
                this.l.b();
            } else if (!this.i) {
                this.l = a(-1, this.j, false);
            }
            this.i = true;
        }
        this.b.l();
        if (this.k != null) {
            for (int iA = this.k.a() - 1; iA >= 0; iA--) {
                x xVar = (x) this.k.f(iA);
                xVar.e();
                xVar.g();
            }
        }
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        this.e = true;
        this.a.sendEmptyMessage(1);
        this.b.o();
    }

    @Override // android.app.Activity
    public void startActivityForResult(Intent intent, int i) {
        if (i != -1 && ((-65536) & i) != 0) {
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        }
        super.startActivityForResult(intent, i);
    }
}
