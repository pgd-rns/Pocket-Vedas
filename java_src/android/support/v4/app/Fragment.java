package android.support.v4.app;

import android.app.Activity;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.HashMap;

/* loaded from: classes.dex */
public class Fragment implements ComponentCallbacks, View.OnCreateContextMenuListener {
    private static final HashMap a = new HashMap();
    int A;
    n B;
    h C;
    int D;
    int E;
    String F;
    boolean G;
    boolean H;
    boolean I;
    boolean J;
    boolean K;
    boolean M;
    int N;
    ViewGroup O;
    View P;
    View Q;
    boolean R;
    x T;
    boolean U;
    boolean V;
    View k;
    int l;
    Bundle m;
    SparseArray n;
    String p;
    Bundle q;
    Fragment r;
    int t;
    boolean u;
    boolean v;
    boolean w;
    boolean x;
    boolean y;
    boolean z;
    int j = 0;
    int o = -1;
    int s = -1;
    boolean L = true;
    boolean S = true;

    public class SavedState implements Parcelable {
        public static final Parcelable.Creator CREATOR = new g();
        final Bundle a;

        SavedState(Bundle bundle) {
            this.a = bundle;
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            this.a = parcel.readBundle();
            if (classLoader == null || this.a == null) {
                return;
            }
            this.a.setClassLoader(classLoader);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeBundle(this.a);
        }
    }

    public static Fragment a(Context context, String str) {
        return a(context, str, (Bundle) null);
    }

    public static Fragment a(Context context, String str, Bundle bundle) throws ClassNotFoundException {
        try {
            Class<?> clsLoadClass = (Class) a.get(str);
            if (clsLoadClass == null) {
                clsLoadClass = context.getClassLoader().loadClass(str);
                a.put(str, clsLoadClass);
            }
            Fragment fragment = (Fragment) clsLoadClass.newInstance();
            if (bundle != null) {
                bundle.setClassLoader(fragment.getClass().getClassLoader());
                fragment.q = bundle;
            }
            return fragment;
        } catch (ClassNotFoundException e) {
            throw new f("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e);
        } catch (IllegalAccessException e2) {
            throw new f("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e2);
        } catch (InstantiationException e3) {
            throw new f("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e3);
        }
    }

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return null;
    }

    public Animation a(int i, boolean z, int i2) {
        return null;
    }

    final void a(int i) {
        this.o = i;
        this.p = "android:fragment:" + this.o;
    }

    public void a(int i, int i2, Intent intent) {
    }

    public void a(Activity activity) {
        this.M = true;
    }

    public void a(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        this.M = true;
    }

    public void a(Intent intent) {
        if (this.C == null) {
            throw new IllegalStateException("Fragment " + this + " not attached to Activity");
        }
        this.C.a(this, intent, -1);
    }

    public void a(Bundle bundle) {
        this.M = true;
    }

    public void a(SavedState savedState) {
        if (this.o >= 0) {
            throw new IllegalStateException("Fragment already active");
        }
        this.m = (savedState == null || savedState.a == null) ? null : savedState.a;
    }

    public void a(Menu menu) {
    }

    public void a(Menu menu, MenuInflater menuInflater) {
    }

    public void a(View view, Bundle bundle) {
    }

    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mFragmentId=#");
        printWriter.print(Integer.toHexString(this.D));
        printWriter.print(" mContainerId=#");
        printWriter.print(Integer.toHexString(this.E));
        printWriter.print(" mTag=");
        printWriter.println(this.F);
        printWriter.print(str);
        printWriter.print("mState=");
        printWriter.print(this.j);
        printWriter.print(" mIndex=");
        printWriter.print(this.o);
        printWriter.print(" mWho=");
        printWriter.print(this.p);
        printWriter.print(" mBackStackNesting=");
        printWriter.println(this.A);
        printWriter.print(str);
        printWriter.print("mAdded=");
        printWriter.print(this.u);
        printWriter.print(" mRemoving=");
        printWriter.print(this.v);
        printWriter.print(" mResumed=");
        printWriter.print(this.w);
        printWriter.print(" mFromLayout=");
        printWriter.print(this.x);
        printWriter.print(" mInLayout=");
        printWriter.println(this.y);
        printWriter.print(str);
        printWriter.print("mHidden=");
        printWriter.print(this.G);
        printWriter.print(" mDetached=");
        printWriter.print(this.H);
        printWriter.print(" mMenuVisible=");
        printWriter.print(this.L);
        printWriter.print(" mHasMenu=");
        printWriter.println(this.K);
        printWriter.print(str);
        printWriter.print("mRetainInstance=");
        printWriter.print(this.I);
        printWriter.print(" mRetaining=");
        printWriter.print(this.J);
        printWriter.print(" mUserVisibleHint=");
        printWriter.println(this.S);
        if (this.B != null) {
            printWriter.print(str);
            printWriter.print("mFragmentManager=");
            printWriter.println(this.B);
        }
        if (this.C != null) {
            printWriter.print(str);
            printWriter.print("mActivity=");
            printWriter.println(this.C);
        }
        if (this.q != null) {
            printWriter.print(str);
            printWriter.print("mArguments=");
            printWriter.println(this.q);
        }
        if (this.m != null) {
            printWriter.print(str);
            printWriter.print("mSavedFragmentState=");
            printWriter.println(this.m);
        }
        if (this.n != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewState=");
            printWriter.println(this.n);
        }
        if (this.r != null) {
            printWriter.print(str);
            printWriter.print("mTarget=");
            printWriter.print(this.r);
            printWriter.print(" mTargetRequestCode=");
            printWriter.println(this.t);
        }
        if (this.N != 0) {
            printWriter.print(str);
            printWriter.print("mNextAnim=");
            printWriter.println(this.N);
        }
        if (this.O != null) {
            printWriter.print(str);
            printWriter.print("mContainer=");
            printWriter.println(this.O);
        }
        if (this.P != null) {
            printWriter.print(str);
            printWriter.print("mView=");
            printWriter.println(this.P);
        }
        if (this.Q != null) {
            printWriter.print(str);
            printWriter.print("mInnerView=");
            printWriter.println(this.P);
        }
        if (this.k != null) {
            printWriter.print(str);
            printWriter.print("mAnimatingAway=");
            printWriter.println(this.k);
            printWriter.print(str);
            printWriter.print("mStateAfterAnimating=");
            printWriter.println(this.l);
        }
        if (this.T != null) {
            printWriter.print(str);
            printWriter.println("Loader Manager:");
            this.T.a(str + "  ", fileDescriptor, printWriter, strArr);
        }
    }

    public boolean a(MenuItem menuItem) {
        return false;
    }

    public LayoutInflater b(Bundle bundle) {
        return this.C.getLayoutInflater();
    }

    public void b() {
        this.M = true;
    }

    public void b(Menu menu) {
    }

    public void b(boolean z) {
    }

    public boolean b(MenuItem menuItem) {
        return false;
    }

    public void c() {
        this.M = true;
        if (this.U) {
            return;
        }
        this.U = true;
        if (!this.V) {
            this.V = true;
            this.T = this.C.a(this.o, this.U, false);
        }
        if (this.T != null) {
            this.T.b();
        }
    }

    public void c(boolean z) {
        if (this.K != z) {
            this.K = z;
            if (!k() || l()) {
                return;
            }
            this.C.c();
        }
    }

    public void d() {
        this.M = true;
    }

    public void d(Bundle bundle) {
        this.M = true;
    }

    public void d(boolean z) {
        if (this.L != z) {
            this.L = z;
            if (this.K && k() && !l()) {
                this.C.c();
            }
        }
    }

    public void e() {
        this.M = true;
    }

    public void e(Bundle bundle) {
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    final void f() {
        if (this.n != null) {
            this.Q.restoreHierarchyState(this.n);
            this.n = null;
        }
    }

    final boolean g() {
        return this.A > 0;
    }

    public final h h() {
        return this.C;
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public final Resources i() {
        if (this.C == null) {
            throw new IllegalStateException("Fragment " + this + " not attached to Activity");
        }
        return this.C.getResources();
    }

    public final l j() {
        return this.B;
    }

    public final boolean k() {
        return this.C != null && this.u;
    }

    public final boolean l() {
        return this.G;
    }

    public View m() {
        return this.P;
    }

    public void n() {
        this.M = true;
    }

    public void o() {
        this.M = true;
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        this.M = true;
    }

    @Override // android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        h().onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        this.M = true;
    }

    public void p() {
        this.M = true;
        if (!this.V) {
            this.V = true;
            this.T = this.C.a(this.o, this.U, false);
        }
        if (this.T != null) {
            this.T.h();
        }
    }

    void q() {
        this.o = -1;
        this.p = null;
        this.u = false;
        this.v = false;
        this.w = false;
        this.x = false;
        this.y = false;
        this.z = false;
        this.A = 0;
        this.B = null;
        this.C = null;
        this.D = 0;
        this.E = 0;
        this.F = null;
        this.G = false;
        this.H = false;
        this.J = false;
        this.T = null;
        this.U = false;
        this.V = false;
    }

    public void r() {
    }

    void s() {
        c();
        if (this.T != null) {
            this.T.g();
        }
    }

    void t() {
        d();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        android.support.v4.c.a.a(this, sb);
        if (this.o >= 0) {
            sb.append(" #");
            sb.append(this.o);
        }
        if (this.D != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.D));
        }
        if (this.F != null) {
            sb.append(" ");
            sb.append(this.F);
        }
        sb.append('}');
        return sb.toString();
    }

    void u() {
        if (this.U) {
            this.U = false;
            if (!this.V) {
                this.V = true;
                this.T = this.C.a(this.o, this.U, false);
            }
            if (this.T != null) {
                if (this.C.g) {
                    this.T.d();
                } else {
                    this.T.c();
                }
            }
        }
    }

    void v() {
        e();
        if (this.T != null) {
            this.T.f();
        }
    }
}
