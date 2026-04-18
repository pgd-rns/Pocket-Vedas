package android.support.v4.app;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes.dex */
final class n extends l {
    static final Interpolator A;
    static boolean a = false;
    static final boolean b;
    static final Interpolator x;
    static final Interpolator y;
    static final Interpolator z;
    ArrayList c;
    Runnable[] d;
    boolean e;
    ArrayList f;
    ArrayList g;
    ArrayList h;
    ArrayList i;
    ArrayList j;
    ArrayList k;
    ArrayList l;
    ArrayList m;
    h o;
    boolean p;
    boolean q;
    boolean r;
    String s;
    boolean t;
    int n = 0;
    Bundle u = null;
    SparseArray v = null;
    Runnable w = new o(this);

    static {
        b = Build.VERSION.SDK_INT >= 11;
        x = new DecelerateInterpolator(2.5f);
        y = new DecelerateInterpolator(1.5f);
        z = new AccelerateInterpolator(2.5f);
        A = new AccelerateInterpolator(1.5f);
    }

    n() {
    }

    static Animation a(Context context, float f, float f2) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(f, f2);
        alphaAnimation.setInterpolator(y);
        alphaAnimation.setDuration(220L);
        return alphaAnimation;
    }

    static Animation a(Context context, float f, float f2, float f3, float f4) {
        AnimationSet animationSet = new AnimationSet(false);
        ScaleAnimation scaleAnimation = new ScaleAnimation(f, f2, f, f2, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setInterpolator(x);
        scaleAnimation.setDuration(220L);
        animationSet.addAnimation(scaleAnimation);
        AlphaAnimation alphaAnimation = new AlphaAnimation(f3, f4);
        alphaAnimation.setInterpolator(y);
        alphaAnimation.setDuration(220L);
        animationSet.addAnimation(alphaAnimation);
        return animationSet;
    }

    public static int b(int i, boolean z2) {
        switch (i) {
            case 4097:
                return z2 ? 1 : 2;
            case 4099:
                return z2 ? 5 : 6;
            case 8194:
                return z2 ? 3 : 4;
            default:
                return -1;
        }
    }

    public static int c(int i) {
        switch (i) {
            case 4097:
                return 8194;
            case 4099:
                return 4099;
            case 8194:
                return 4097;
            default:
                return 0;
        }
    }

    private void s() {
        if (this.q) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
        if (this.s != null) {
            throw new IllegalStateException("Can not perform this action inside of " + this.s);
        }
    }

    public int a(b bVar) {
        int size;
        synchronized (this) {
            if (this.l == null || this.l.size() <= 0) {
                if (this.k == null) {
                    this.k = new ArrayList();
                }
                size = this.k.size();
                if (a) {
                    Log.v("FragmentManager", "Setting back stack index " + size + " to " + bVar);
                }
                this.k.add(bVar);
            } else {
                size = ((Integer) this.l.remove(this.l.size() - 1)).intValue();
                if (a) {
                    Log.v("FragmentManager", "Adding back stack index " + size + " with " + bVar);
                }
                this.k.set(size, bVar);
            }
        }
        return size;
    }

    @Override // android.support.v4.app.l
    public Fragment.SavedState a(Fragment fragment) {
        Bundle bundleG;
        if (fragment.o < 0) {
            throw new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager");
        }
        if (fragment.j <= 0 || (bundleG = g(fragment)) == null) {
            return null;
        }
        return new Fragment.SavedState(bundleG);
    }

    public Fragment a(int i) {
        if (this.g != null) {
            for (int size = this.g.size() - 1; size >= 0; size--) {
                Fragment fragment = (Fragment) this.g.get(size);
                if (fragment != null && fragment.D == i) {
                    return fragment;
                }
            }
        }
        if (this.f != null) {
            for (int size2 = this.f.size() - 1; size2 >= 0; size2--) {
                Fragment fragment2 = (Fragment) this.f.get(size2);
                if (fragment2 != null && fragment2.D == i) {
                    return fragment2;
                }
            }
        }
        return null;
    }

    @Override // android.support.v4.app.l
    public Fragment a(Bundle bundle, String str) {
        int i = bundle.getInt(str, -1);
        if (i == -1) {
            return null;
        }
        if (i >= this.f.size()) {
            throw new IllegalStateException("Fragement no longer exists for key " + str + ": index " + i);
        }
        Fragment fragment = (Fragment) this.f.get(i);
        if (fragment == null) {
            throw new IllegalStateException("Fragement no longer exists for key " + str + ": index " + i);
        }
        return fragment;
    }

    public Fragment a(String str) {
        if (this.g != null && str != null) {
            for (int size = this.g.size() - 1; size >= 0; size--) {
                Fragment fragment = (Fragment) this.g.get(size);
                if (fragment != null && str.equals(fragment.F)) {
                    return fragment;
                }
            }
        }
        if (this.f != null && str != null) {
            for (int size2 = this.f.size() - 1; size2 >= 0; size2--) {
                Fragment fragment2 = (Fragment) this.f.get(size2);
                if (fragment2 != null && str.equals(fragment2.F)) {
                    return fragment2;
                }
            }
        }
        return null;
    }

    @Override // android.support.v4.app.l
    public u a() {
        return new b(this);
    }

    Animation a(Fragment fragment, int i, boolean z2, int i2) {
        int iB;
        Animation animationLoadAnimation;
        Animation animationA = fragment.a(i, z2, fragment.N);
        if (animationA != null) {
            return animationA;
        }
        if (fragment.N != 0 && (animationLoadAnimation = AnimationUtils.loadAnimation(this.o, fragment.N)) != null) {
            return animationLoadAnimation;
        }
        if (i != 0 && (iB = b(i, z2)) >= 0) {
            switch (iB) {
                case 1:
                    return a(this.o, 1.125f, 1.0f, 0.0f, 1.0f);
                case 2:
                    return a(this.o, 1.0f, 0.975f, 1.0f, 0.0f);
                case 3:
                    return a(this.o, 0.975f, 1.0f, 0.0f, 1.0f);
                case 4:
                    return a(this.o, 1.0f, 1.075f, 1.0f, 0.0f);
                case 5:
                    return a(this.o, 0.0f, 1.0f);
                case 6:
                    return a(this.o, 1.0f, 0.0f);
                default:
                    if (i2 == 0 && this.o.getWindow() != null) {
                        i2 = this.o.getWindow().getAttributes().windowAnimations;
                    }
                    return i2 == 0 ? null : null;
            }
        }
        return null;
    }

    @Override // android.support.v4.app.l
    public void a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("Bad id: " + i);
        }
        a((Runnable) new p(this, i, i2), false);
    }

    void a(int i, int i2, int i3, boolean z2) {
        if (this.o == null && i != 0) {
            throw new IllegalStateException("No activity");
        }
        if (z2 || this.n != i) {
            this.n = i;
            if (this.f != null) {
                int i4 = 0;
                boolean zA = false;
                while (i4 < this.f.size()) {
                    Fragment fragment = (Fragment) this.f.get(i4);
                    if (fragment != null) {
                        a(fragment, i, i2, i3, false);
                        if (fragment.T != null) {
                            zA |= fragment.T.a();
                        }
                    }
                    i4++;
                    zA = zA;
                }
                if (!zA) {
                    d();
                }
                if (this.p && this.o != null && this.n == 5) {
                    this.o.c();
                    this.p = false;
                }
            }
        }
    }

    public void a(int i, b bVar) {
        synchronized (this) {
            if (this.k == null) {
                this.k = new ArrayList();
            }
            int size = this.k.size();
            if (i < size) {
                if (a) {
                    Log.v("FragmentManager", "Setting back stack index " + i + " to " + bVar);
                }
                this.k.set(i, bVar);
            } else {
                while (size < i) {
                    this.k.add(null);
                    if (this.l == null) {
                        this.l = new ArrayList();
                    }
                    if (a) {
                        Log.v("FragmentManager", "Adding available back stack index " + size);
                    }
                    this.l.add(Integer.valueOf(size));
                    size++;
                }
                if (a) {
                    Log.v("FragmentManager", "Adding back stack index " + i + " with " + bVar);
                }
                this.k.add(bVar);
            }
        }
    }

    void a(int i, boolean z2) {
        a(i, 0, 0, z2);
    }

    public void a(Configuration configuration) {
        if (this.g == null) {
            return;
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.g.size()) {
                return;
            }
            Fragment fragment = (Fragment) this.g.get(i2);
            if (fragment != null) {
                fragment.onConfigurationChanged(configuration);
            }
            i = i2 + 1;
        }
    }

    @Override // android.support.v4.app.l
    public void a(Bundle bundle, String str, Fragment fragment) {
        if (fragment.o < 0) {
            throw new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager");
        }
        bundle.putInt(str, fragment.o);
    }

    void a(Parcelable parcelable, ArrayList arrayList) {
        if (parcelable == null) {
            return;
        }
        FragmentManagerState fragmentManagerState = (FragmentManagerState) parcelable;
        if (fragmentManagerState.a != null) {
            if (arrayList != null) {
                for (int i = 0; i < arrayList.size(); i++) {
                    Fragment fragment = (Fragment) arrayList.get(i);
                    if (a) {
                        Log.v("FragmentManager", "restoreAllState: re-attaching retained " + fragment);
                    }
                    FragmentState fragmentState = fragmentManagerState.a[fragment.o];
                    fragmentState.k = fragment;
                    fragment.n = null;
                    fragment.A = 0;
                    fragment.y = false;
                    fragment.u = false;
                    fragment.r = null;
                    if (fragmentState.j != null) {
                        fragmentState.j.setClassLoader(this.o.getClassLoader());
                        fragment.n = fragmentState.j.getSparseParcelableArray("android:view_state");
                    }
                }
            }
            this.f = new ArrayList(fragmentManagerState.a.length);
            if (this.h != null) {
                this.h.clear();
            }
            for (int i2 = 0; i2 < fragmentManagerState.a.length; i2++) {
                FragmentState fragmentState2 = fragmentManagerState.a[i2];
                if (fragmentState2 != null) {
                    Fragment fragmentA = fragmentState2.a(this.o);
                    if (a) {
                        Log.v("FragmentManager", "restoreAllState: adding #" + i2 + ": " + fragmentA);
                    }
                    this.f.add(fragmentA);
                    fragmentState2.k = null;
                } else {
                    if (a) {
                        Log.v("FragmentManager", "restoreAllState: adding #" + i2 + ": (null)");
                    }
                    this.f.add(null);
                    if (this.h == null) {
                        this.h = new ArrayList();
                    }
                    if (a) {
                        Log.v("FragmentManager", "restoreAllState: adding avail #" + i2);
                    }
                    this.h.add(Integer.valueOf(i2));
                }
            }
            if (arrayList != null) {
                for (int i3 = 0; i3 < arrayList.size(); i3++) {
                    Fragment fragment2 = (Fragment) arrayList.get(i3);
                    if (fragment2.s >= 0) {
                        if (fragment2.s < this.f.size()) {
                            fragment2.r = (Fragment) this.f.get(fragment2.s);
                        } else {
                            Log.w("FragmentManager", "Re-attaching retained fragment " + fragment2 + " target no longer exists: " + fragment2.s);
                            fragment2.r = null;
                        }
                    }
                }
            }
            if (fragmentManagerState.b != null) {
                this.g = new ArrayList(fragmentManagerState.b.length);
                for (int i4 = 0; i4 < fragmentManagerState.b.length; i4++) {
                    Fragment fragment3 = (Fragment) this.f.get(fragmentManagerState.b[i4]);
                    if (fragment3 == null) {
                        throw new IllegalStateException("No instantiated fragment for index #" + fragmentManagerState.b[i4]);
                    }
                    fragment3.u = true;
                    if (a) {
                        Log.v("FragmentManager", "restoreAllState: making added #" + i4 + ": " + fragment3);
                    }
                    this.g.add(fragment3);
                }
            } else {
                this.g = null;
            }
            if (fragmentManagerState.c == null) {
                this.i = null;
                return;
            }
            this.i = new ArrayList(fragmentManagerState.c.length);
            for (int i5 = 0; i5 < fragmentManagerState.c.length; i5++) {
                b bVarA = fragmentManagerState.c[i5].a(this);
                if (a) {
                    Log.v("FragmentManager", "restoreAllState: adding bse #" + i5 + " (index " + bVarA.o + "): " + bVarA);
                }
                this.i.add(bVarA);
                if (bVarA.o >= 0) {
                    a(bVarA.o, bVarA);
                }
            }
        }
    }

    public void a(Fragment fragment, int i, int i2) {
        if (a) {
            Log.v("FragmentManager", "remove: " + fragment + " nesting=" + fragment.A);
        }
        boolean z2 = !fragment.g();
        if (!fragment.H || z2) {
            if (this.g != null) {
                this.g.remove(fragment);
            }
            if (fragment.K && fragment.L) {
                this.p = true;
            }
            fragment.u = false;
            fragment.v = true;
            a(fragment, z2 ? 0 : 1, i, i2, false);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0041 A[FALL_THROUGH, PHI: r11
      0x0041: PHI (r11v5 int) = 
      (r11v3 int)
      (r11v3 int)
      (r11v3 int)
      (r11v3 int)
      (r11v3 int)
      (r11v3 int)
      (r11v4 int)
      (r11v3 int)
      (r11v6 int)
      (r11v6 int)
     binds: [B:116:0x02a9, B:118:0x02ad, B:120:0x02b2, B:194:0x047e, B:198:0x0489, B:197:0x0484, B:128:0x02c7, B:28:0x003e, B:106:0x0259, B:114:0x02a1] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void a(Fragment fragment, int i, int i2, int i3, boolean z2) {
        ViewGroup viewGroup;
        if (!fragment.u && i > 1) {
            i = 1;
        }
        if (fragment.v && i > fragment.j) {
            i = fragment.j;
        }
        if (fragment.R && fragment.j < 4 && i > 3) {
            i = 3;
        }
        if (fragment.j >= i) {
            if (fragment.j > i) {
                switch (fragment.j) {
                    case 5:
                        if (i < 5) {
                            if (a) {
                                Log.v("FragmentManager", "movefrom RESUMED: " + fragment);
                            }
                            fragment.M = false;
                            fragment.o();
                            if (!fragment.M) {
                                throw new aa("Fragment " + fragment + " did not call through to super.onPause()");
                            }
                            fragment.w = false;
                        }
                    case 4:
                        if (i < 4) {
                            if (a) {
                                Log.v("FragmentManager", "movefrom STARTED: " + fragment);
                            }
                            fragment.M = false;
                            fragment.t();
                            if (!fragment.M) {
                                throw new aa("Fragment " + fragment + " did not call through to super.onStop()");
                            }
                        }
                    case 3:
                        if (i < 3) {
                            if (a) {
                                Log.v("FragmentManager", "movefrom STOPPED: " + fragment);
                            }
                            fragment.u();
                        }
                    case 2:
                        if (i < 2) {
                            if (a) {
                                Log.v("FragmentManager", "movefrom ACTIVITY_CREATED: " + fragment);
                            }
                            if (fragment.P != null && !this.o.isFinishing() && fragment.n == null) {
                                f(fragment);
                            }
                            fragment.M = false;
                            fragment.v();
                            if (!fragment.M) {
                                throw new aa("Fragment " + fragment + " did not call through to super.onDestroyView()");
                            }
                            if (fragment.P != null && fragment.O != null) {
                                Animation animationA = (this.n <= 0 || this.r) ? null : a(fragment, i2, false, i3);
                                if (animationA != null) {
                                    fragment.k = fragment.P;
                                    fragment.l = i;
                                    animationA.setAnimationListener(new q(this, fragment));
                                    fragment.P.startAnimation(animationA);
                                }
                                fragment.O.removeView(fragment.P);
                            }
                            fragment.O = null;
                            fragment.P = null;
                            fragment.Q = null;
                        }
                        break;
                    case 1:
                        if (i < 1) {
                            if (this.r && fragment.k != null) {
                                View view = fragment.k;
                                fragment.k = null;
                                view.clearAnimation();
                            }
                            if (fragment.k != null) {
                                fragment.l = i;
                                i = 1;
                            } else {
                                if (a) {
                                    Log.v("FragmentManager", "movefrom CREATED: " + fragment);
                                }
                                if (!fragment.J) {
                                    fragment.M = false;
                                    fragment.p();
                                    if (!fragment.M) {
                                        throw new aa("Fragment " + fragment + " did not call through to super.onDestroy()");
                                    }
                                }
                                fragment.M = false;
                                fragment.b();
                                if (!fragment.M) {
                                    throw new aa("Fragment " + fragment + " did not call through to super.onDetach()");
                                }
                                if (!z2) {
                                    if (fragment.J) {
                                        fragment.C = null;
                                        fragment.B = null;
                                    } else {
                                        e(fragment);
                                    }
                                }
                            }
                        }
                        break;
                }
            }
        } else {
            if (fragment.x && !fragment.y) {
                return;
            }
            if (fragment.k != null) {
                fragment.k = null;
                a(fragment, fragment.l, 0, 0, true);
            }
            switch (fragment.j) {
                case 0:
                    if (a) {
                        Log.v("FragmentManager", "moveto CREATED: " + fragment);
                    }
                    if (fragment.m != null) {
                        fragment.n = fragment.m.getSparseParcelableArray("android:view_state");
                        fragment.r = a(fragment.m, "android:target_state");
                        if (fragment.r != null) {
                            fragment.t = fragment.m.getInt("android:target_req_state", 0);
                        }
                        fragment.S = fragment.m.getBoolean("android:user_visible_hint", true);
                        if (!fragment.S) {
                            fragment.R = true;
                            if (i > 3) {
                                i = 3;
                            }
                        }
                    }
                    fragment.C = this.o;
                    fragment.B = this.o.b;
                    fragment.M = false;
                    fragment.a(this.o);
                    if (!fragment.M) {
                        throw new aa("Fragment " + fragment + " did not call through to super.onAttach()");
                    }
                    this.o.a(fragment);
                    if (!fragment.J) {
                        fragment.M = false;
                        fragment.a(fragment.m);
                        if (!fragment.M) {
                            throw new aa("Fragment " + fragment + " did not call through to super.onCreate()");
                        }
                    }
                    fragment.J = false;
                    if (fragment.x) {
                        fragment.P = fragment.a(fragment.b(fragment.m), (ViewGroup) null, fragment.m);
                        if (fragment.P != null) {
                            fragment.Q = fragment.P;
                            fragment.P = z.a(fragment.P);
                            if (fragment.G) {
                                fragment.P.setVisibility(8);
                            }
                            fragment.a(fragment.P, fragment.m);
                        } else {
                            fragment.Q = null;
                        }
                    }
                case 1:
                    if (i > 1) {
                        if (a) {
                            Log.v("FragmentManager", "moveto ACTIVITY_CREATED: " + fragment);
                        }
                        if (!fragment.x) {
                            if (fragment.E != 0) {
                                viewGroup = (ViewGroup) this.o.findViewById(fragment.E);
                                if (viewGroup == null && !fragment.z) {
                                    throw new IllegalArgumentException("No view found for id 0x" + Integer.toHexString(fragment.E) + " for fragment " + fragment);
                                }
                            } else {
                                viewGroup = null;
                            }
                            fragment.O = viewGroup;
                            fragment.P = fragment.a(fragment.b(fragment.m), viewGroup, fragment.m);
                            if (fragment.P != null) {
                                fragment.Q = fragment.P;
                                fragment.P = z.a(fragment.P);
                                if (viewGroup != null) {
                                    Animation animationA2 = a(fragment, i2, true, i3);
                                    if (animationA2 != null) {
                                        fragment.P.startAnimation(animationA2);
                                    }
                                    viewGroup.addView(fragment.P);
                                }
                                if (fragment.G) {
                                    fragment.P.setVisibility(8);
                                }
                                fragment.a(fragment.P, fragment.m);
                            } else {
                                fragment.Q = null;
                            }
                        }
                        fragment.M = false;
                        fragment.d(fragment.m);
                        if (!fragment.M) {
                            throw new aa("Fragment " + fragment + " did not call through to super.onActivityCreated()");
                        }
                        if (fragment.P != null) {
                            fragment.f();
                        }
                        fragment.m = null;
                    }
                    break;
                case 2:
                case 3:
                    if (i > 3) {
                        if (a) {
                            Log.v("FragmentManager", "moveto STARTED: " + fragment);
                        }
                        fragment.M = false;
                        fragment.s();
                        if (!fragment.M) {
                            throw new aa("Fragment " + fragment + " did not call through to super.onStart()");
                        }
                    }
                case 4:
                    if (i > 4) {
                        if (a) {
                            Log.v("FragmentManager", "moveto RESUMED: " + fragment);
                        }
                        fragment.M = false;
                        fragment.w = true;
                        fragment.n();
                        if (!fragment.M) {
                            throw new aa("Fragment " + fragment + " did not call through to super.onResume()");
                        }
                        fragment.m = null;
                        fragment.n = null;
                    }
                default:
                    fragment.j = i;
            }
        }
        fragment.j = i;
    }

    public void a(Fragment fragment, boolean z2) {
        if (this.g == null) {
            this.g = new ArrayList();
        }
        if (a) {
            Log.v("FragmentManager", "add: " + fragment);
        }
        d(fragment);
        if (fragment.H) {
            return;
        }
        this.g.add(fragment);
        fragment.u = true;
        fragment.v = false;
        if (fragment.K && fragment.L) {
            this.p = true;
        }
        if (z2) {
            c(fragment);
        }
    }

    public void a(h hVar) {
        if (this.o != null) {
            throw new IllegalStateException();
        }
        this.o = hVar;
    }

    public void a(Runnable runnable, boolean z2) {
        if (!z2) {
            s();
        }
        synchronized (this) {
            if (this.o == null) {
                throw new IllegalStateException("Activity has been destroyed");
            }
            if (this.c == null) {
                this.c = new ArrayList();
            }
            this.c.add(runnable);
            if (this.c.size() == 1) {
                this.o.a.removeCallbacks(this.w);
                this.o.a.post(this.w);
            }
        }
    }

    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int size2;
        int size3;
        int size4;
        int size5;
        int size6;
        String str2 = str + "    ";
        if (this.f != null && (size6 = this.f.size()) > 0) {
            printWriter.print(str);
            printWriter.print("Active Fragments in ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(":");
            for (int i = 0; i < size6; i++) {
                Fragment fragment = (Fragment) this.f.get(i);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i);
                printWriter.print(": ");
                printWriter.println(fragment);
                if (fragment != null) {
                    fragment.a(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }
        if (this.g != null && (size5 = this.g.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i2 = 0; i2 < size5; i2++) {
                Fragment fragment2 = (Fragment) this.g.get(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(fragment2.toString());
            }
        }
        if (this.j != null && (size4 = this.j.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i3 = 0; i3 < size4; i3++) {
                Fragment fragment3 = (Fragment) this.j.get(i3);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i3);
                printWriter.print(": ");
                printWriter.println(fragment3.toString());
            }
        }
        if (this.i != null && (size3 = this.i.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i4 = 0; i4 < size3; i4++) {
                b bVar = (b) this.i.get(i4);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i4);
                printWriter.print(": ");
                printWriter.println(bVar.toString());
                bVar.a(str2, fileDescriptor, printWriter, strArr);
            }
        }
        synchronized (this) {
            if (this.k != null && (size2 = this.k.size()) > 0) {
                printWriter.print(str);
                printWriter.println("Back Stack Indices:");
                for (int i5 = 0; i5 < size2; i5++) {
                    Object obj = (b) this.k.get(i5);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i5);
                    printWriter.print(": ");
                    printWriter.println(obj);
                }
            }
            if (this.l != null && this.l.size() > 0) {
                printWriter.print(str);
                printWriter.print("mAvailBackStackIndices: ");
                printWriter.println(Arrays.toString(this.l.toArray()));
            }
        }
        if (this.c != null && (size = this.c.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Pending Actions:");
            for (int i6 = 0; i6 < size; i6++) {
                Object obj2 = (Runnable) this.c.get(i6);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i6);
                printWriter.print(": ");
                printWriter.println(obj2);
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.n);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.q);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.r);
        if (this.p) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.p);
        }
        if (this.s != null) {
            printWriter.print(str);
            printWriter.print("  mNoTransactionsBecause=");
            printWriter.println(this.s);
        }
        if (this.h == null || this.h.size() <= 0) {
            return;
        }
        printWriter.print(str);
        printWriter.print("  mAvailIndices: ");
        printWriter.println(Arrays.toString(this.h.toArray()));
    }

    boolean a(Handler handler, String str, int i, int i2) {
        if (this.i == null) {
            return false;
        }
        if (str == null && i < 0 && (i2 & 1) == 0) {
            int size = this.i.size() - 1;
            if (size < 0) {
                return false;
            }
            ((b) this.i.remove(size)).b(true);
            f();
        } else {
            int i3 = -1;
            if (str != null || i >= 0) {
                int size2 = this.i.size() - 1;
                while (size2 >= 0) {
                    b bVar = (b) this.i.get(size2);
                    if ((str != null && str.equals(bVar.c())) || (i >= 0 && i == bVar.o)) {
                        break;
                    }
                    size2--;
                }
                if (size2 < 0) {
                    return false;
                }
                if ((i2 & 1) != 0) {
                    size2--;
                    while (size2 >= 0) {
                        b bVar2 = (b) this.i.get(size2);
                        if ((str == null || !str.equals(bVar2.c())) && (i < 0 || i != bVar2.o)) {
                            break;
                        }
                        size2--;
                    }
                }
                i3 = size2;
            }
            if (i3 == this.i.size() - 1) {
                return false;
            }
            ArrayList arrayList = new ArrayList();
            for (int size3 = this.i.size() - 1; size3 > i3; size3--) {
                arrayList.add(this.i.remove(size3));
            }
            int size4 = arrayList.size() - 1;
            int i4 = 0;
            while (i4 <= size4) {
                if (a) {
                    Log.v("FragmentManager", "Popping back stack state: " + arrayList.get(i4));
                }
                ((b) arrayList.get(i4)).b(i4 == size4);
                i4++;
            }
            f();
        }
        return true;
    }

    public boolean a(Menu menu) {
        if (this.g == null) {
            return false;
        }
        boolean z2 = false;
        for (int i = 0; i < this.g.size(); i++) {
            Fragment fragment = (Fragment) this.g.get(i);
            if (fragment != null && !fragment.G && fragment.K && fragment.L) {
                z2 = true;
                fragment.a(menu);
            }
        }
        return z2;
    }

    public boolean a(Menu menu, MenuInflater menuInflater) {
        boolean z2;
        ArrayList arrayList = null;
        if (this.g != null) {
            int i = 0;
            z2 = false;
            while (i < this.g.size()) {
                Fragment fragment = (Fragment) this.g.get(i);
                if (fragment != null && !fragment.G && fragment.K && fragment.L) {
                    z2 = true;
                    fragment.a(menu, menuInflater);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(fragment);
                }
                i++;
                z2 = z2;
            }
        } else {
            z2 = false;
        }
        if (this.j != null) {
            for (int i2 = 0; i2 < this.j.size(); i2++) {
                Fragment fragment2 = (Fragment) this.j.get(i2);
                if (arrayList == null || !arrayList.contains(fragment2)) {
                    fragment2.r();
                }
            }
        }
        this.j = arrayList;
        return z2;
    }

    public boolean a(MenuItem menuItem) {
        if (this.g == null) {
            return false;
        }
        for (int i = 0; i < this.g.size(); i++) {
            Fragment fragment = (Fragment) this.g.get(i);
            if (fragment != null && !fragment.G && fragment.K && fragment.L && fragment.a(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void b(int i) {
        synchronized (this) {
            this.k.set(i, null);
            if (this.l == null) {
                this.l = new ArrayList();
            }
            if (a) {
                Log.v("FragmentManager", "Freeing back stack index " + i);
            }
            this.l.add(Integer.valueOf(i));
        }
    }

    public void b(Fragment fragment) {
        if (fragment.R) {
            if (this.e) {
                this.t = true;
            } else {
                fragment.R = false;
                a(fragment, this.n, 0, 0, false);
            }
        }
    }

    public void b(Fragment fragment, int i, int i2) {
        if (a) {
            Log.v("FragmentManager", "hide: " + fragment);
        }
        if (fragment.G) {
            return;
        }
        fragment.G = true;
        if (fragment.P != null) {
            Animation animationA = a(fragment, i, true, i2);
            if (animationA != null) {
                fragment.P.startAnimation(animationA);
            }
            fragment.P.setVisibility(8);
        }
        if (fragment.u && fragment.K && fragment.L) {
            this.p = true;
        }
        fragment.b(true);
    }

    void b(b bVar) {
        if (this.i == null) {
            this.i = new ArrayList();
        }
        this.i.add(bVar);
        f();
    }

    public void b(Menu menu) {
        if (this.g == null) {
            return;
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.g.size()) {
                return;
            }
            Fragment fragment = (Fragment) this.g.get(i2);
            if (fragment != null && !fragment.G && fragment.K && fragment.L) {
                fragment.b(menu);
            }
            i = i2 + 1;
        }
    }

    @Override // android.support.v4.app.l
    public boolean b() {
        return e();
    }

    public boolean b(MenuItem menuItem) {
        if (this.g == null) {
            return false;
        }
        for (int i = 0; i < this.g.size(); i++) {
            Fragment fragment = (Fragment) this.g.get(i);
            if (fragment != null && !fragment.G && fragment.S && fragment.b(menuItem)) {
                return true;
            }
        }
        return false;
    }

    void c(Fragment fragment) {
        a(fragment, this.n, 0, 0, false);
    }

    public void c(Fragment fragment, int i, int i2) {
        if (a) {
            Log.v("FragmentManager", "show: " + fragment);
        }
        if (fragment.G) {
            fragment.G = false;
            if (fragment.P != null) {
                Animation animationA = a(fragment, i, true, i2);
                if (animationA != null) {
                    fragment.P.startAnimation(animationA);
                }
                fragment.P.setVisibility(0);
            }
            if (fragment.u && fragment.K && fragment.L) {
                this.p = true;
            }
            fragment.b(false);
        }
    }

    public boolean c() {
        s();
        b();
        return a(this.o.a, (String) null, -1, 0);
    }

    void d() {
        if (this.f == null) {
            return;
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.f.size()) {
                return;
            }
            Fragment fragment = (Fragment) this.f.get(i2);
            if (fragment != null) {
                b(fragment);
            }
            i = i2 + 1;
        }
    }

    void d(Fragment fragment) {
        if (fragment.o >= 0) {
            return;
        }
        if (this.h == null || this.h.size() <= 0) {
            if (this.f == null) {
                this.f = new ArrayList();
            }
            fragment.a(this.f.size());
            this.f.add(fragment);
        } else {
            fragment.a(((Integer) this.h.remove(this.h.size() - 1)).intValue());
            this.f.set(fragment.o, fragment);
        }
        if (a) {
            Log.v("FragmentManager", "Allocated fragment index " + fragment);
        }
    }

    public void d(Fragment fragment, int i, int i2) {
        if (a) {
            Log.v("FragmentManager", "detach: " + fragment);
        }
        if (fragment.H) {
            return;
        }
        fragment.H = true;
        if (fragment.u) {
            if (this.g != null) {
                this.g.remove(fragment);
            }
            if (fragment.K && fragment.L) {
                this.p = true;
            }
            fragment.u = false;
            a(fragment, 1, i, i2, false);
        }
    }

    void e(Fragment fragment) {
        if (fragment.o < 0) {
            return;
        }
        if (a) {
            Log.v("FragmentManager", "Freeing fragment index " + fragment);
        }
        this.f.set(fragment.o, null);
        if (this.h == null) {
            this.h = new ArrayList();
        }
        this.h.add(Integer.valueOf(fragment.o));
        this.o.a(fragment.o);
        fragment.q();
    }

    public void e(Fragment fragment, int i, int i2) {
        if (a) {
            Log.v("FragmentManager", "attach: " + fragment);
        }
        if (fragment.H) {
            fragment.H = false;
            if (fragment.u) {
                return;
            }
            if (this.g == null) {
                this.g = new ArrayList();
            }
            this.g.add(fragment);
            fragment.u = true;
            if (fragment.K && fragment.L) {
                this.p = true;
            }
            a(fragment, this.n, i, i2, false);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x0083, code lost:
    
        r6.e = true;
        r1 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0086, code lost:
    
        if (r1 >= r3) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0088, code lost:
    
        r6.d[r1].run();
        r6.d[r1] = null;
        r1 = r1 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean e() {
        if (this.e) {
            throw new IllegalStateException("Recursive entry to executePendingTransactions");
        }
        if (Looper.myLooper() != this.o.a.getLooper()) {
            throw new IllegalStateException("Must be called from main thread of process");
        }
        boolean z2 = false;
        while (true) {
            synchronized (this) {
                if (this.c == null || this.c.size() == 0) {
                    break;
                }
                int size = this.c.size();
                if (this.d == null || this.d.length < size) {
                    this.d = new Runnable[size];
                }
                this.c.toArray(this.d);
                this.c.clear();
                this.o.a.removeCallbacks(this.w);
            }
            this.e = false;
            z2 = true;
        }
        if (this.t) {
            boolean zA = false;
            for (int i = 0; i < this.f.size(); i++) {
                Fragment fragment = (Fragment) this.f.get(i);
                if (fragment != null && fragment.T != null) {
                    zA |= fragment.T.a();
                }
            }
            if (!zA) {
                this.t = false;
                d();
            }
        }
        return z2;
    }

    void f() {
        if (this.m == null) {
            return;
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.m.size()) {
                return;
            }
            ((m) this.m.get(i2)).a();
            i = i2 + 1;
        }
    }

    void f(Fragment fragment) {
        if (fragment.Q == null) {
            return;
        }
        if (this.v == null) {
            this.v = new SparseArray();
        } else {
            this.v.clear();
        }
        fragment.Q.saveHierarchyState(this.v);
        if (this.v.size() > 0) {
            fragment.n = this.v;
            this.v = null;
        }
    }

    Bundle g(Fragment fragment) {
        Bundle bundle;
        if (this.u == null) {
            this.u = new Bundle();
        }
        fragment.e(this.u);
        if (this.u.isEmpty()) {
            bundle = null;
        } else {
            bundle = this.u;
            this.u = null;
        }
        if (fragment.P != null) {
            f(fragment);
        }
        if (fragment.n != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray("android:view_state", fragment.n);
        }
        if (!fragment.S) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean("android:user_visible_hint", fragment.S);
        }
        return bundle;
    }

    ArrayList g() {
        ArrayList arrayList = null;
        if (this.f != null) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= this.f.size()) {
                    break;
                }
                Fragment fragment = (Fragment) this.f.get(i2);
                if (fragment != null && fragment.I) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(fragment);
                    fragment.J = true;
                    fragment.s = fragment.r != null ? fragment.r.o : -1;
                    if (a) {
                        Log.v("FragmentManager", "retainNonConfig: keeping retained " + fragment);
                    }
                }
                i = i2 + 1;
            }
        }
        return arrayList;
    }

    Parcelable h() {
        int[] iArr;
        int size;
        int size2;
        boolean z2;
        BackStackState[] backStackStateArr = null;
        e();
        if (b) {
            this.q = true;
        }
        if (this.f == null || this.f.size() <= 0) {
            return null;
        }
        int size3 = this.f.size();
        FragmentState[] fragmentStateArr = new FragmentState[size3];
        int i = 0;
        boolean z3 = false;
        while (i < size3) {
            Fragment fragment = (Fragment) this.f.get(i);
            if (fragment == null) {
                z2 = z3;
            } else {
                if (fragment.o < 0) {
                    String str = "Failure saving state: active " + fragment + " has cleared index: " + fragment.o;
                    Log.e("FragmentManager", str);
                    a("  ", (FileDescriptor) null, new PrintWriter(new android.support.v4.c.b("FragmentManager")), new String[0]);
                    throw new IllegalStateException(str);
                }
                FragmentState fragmentState = new FragmentState(fragment);
                fragmentStateArr[i] = fragmentState;
                if (fragment.j <= 0 || fragmentState.j != null) {
                    fragmentState.j = fragment.m;
                } else {
                    fragmentState.j = g(fragment);
                    if (fragment.r != null) {
                        if (fragment.r.o < 0) {
                            String str2 = "Failure saving state: " + fragment + " has target not in fragment manager: " + fragment.r;
                            Log.e("FragmentManager", str2);
                            a("  ", (FileDescriptor) null, new PrintWriter(new android.support.v4.c.b("FragmentManager")), new String[0]);
                            throw new IllegalStateException(str2);
                        }
                        if (fragmentState.j == null) {
                            fragmentState.j = new Bundle();
                        }
                        a(fragmentState.j, "android:target_state", fragment.r);
                        if (fragment.t != 0) {
                            fragmentState.j.putInt("android:target_req_state", fragment.t);
                        }
                    }
                }
                if (a) {
                    Log.v("FragmentManager", "Saved state of " + fragment + ": " + fragmentState.j);
                }
                z2 = true;
            }
            i++;
            z3 = z2;
        }
        if (!z3) {
            if (!a) {
                return null;
            }
            Log.v("FragmentManager", "saveAllState: no fragments!");
            return null;
        }
        if (this.g == null || (size2 = this.g.size()) <= 0) {
            iArr = null;
        } else {
            iArr = new int[size2];
            for (int i2 = 0; i2 < size2; i2++) {
                iArr[i2] = ((Fragment) this.g.get(i2)).o;
                if (iArr[i2] < 0) {
                    String str3 = "Failure saving state: active " + this.g.get(i2) + " has cleared index: " + iArr[i2];
                    Log.e("FragmentManager", str3);
                    a("  ", (FileDescriptor) null, new PrintWriter(new android.support.v4.c.b("FragmentManager")), new String[0]);
                    throw new IllegalStateException(str3);
                }
                if (a) {
                    Log.v("FragmentManager", "saveAllState: adding fragment #" + i2 + ": " + this.g.get(i2));
                }
            }
        }
        if (this.i != null && (size = this.i.size()) > 0) {
            backStackStateArr = new BackStackState[size];
            for (int i3 = 0; i3 < size; i3++) {
                backStackStateArr[i3] = new BackStackState(this, (b) this.i.get(i3));
                if (a) {
                    Log.v("FragmentManager", "saveAllState: adding back stack #" + i3 + ": " + this.i.get(i3));
                }
            }
        }
        FragmentManagerState fragmentManagerState = new FragmentManagerState();
        fragmentManagerState.a = fragmentStateArr;
        fragmentManagerState.b = iArr;
        fragmentManagerState.c = backStackStateArr;
        return fragmentManagerState;
    }

    public void i() {
        this.q = false;
    }

    public void j() {
        this.q = false;
        a(1, false);
    }

    public void k() {
        this.q = false;
        a(2, false);
    }

    public void l() {
        this.q = false;
        a(4, false);
    }

    public void m() {
        this.q = false;
        a(5, false);
    }

    public void n() {
        a(4, false);
    }

    public void o() {
        this.q = true;
        a(3, false);
    }

    public void p() {
        a(2, false);
    }

    public void q() {
        this.r = true;
        e();
        a(0, false);
        this.o = null;
    }

    public void r() {
        if (this.g == null) {
            return;
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.g.size()) {
                return;
            }
            Fragment fragment = (Fragment) this.g.get(i2);
            if (fragment != null) {
                fragment.onLowMemory();
            }
            i = i2 + 1;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        android.support.v4.c.a.a(this.o, sb);
        sb.append("}}");
        return sb.toString();
    }
}
