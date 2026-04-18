package android.support.v4.view;

import android.R;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* loaded from: classes.dex */
public class ViewPager extends ViewGroup {
    private static final int[] a = {R.attr.layout_gravity};
    private static final Comparator b = new at();
    private static final Interpolator c = new au();
    private boolean A;
    private int B;
    private int C;
    private int D;
    private float E;
    private float F;
    private float G;
    private int H;
    private VelocityTracker I;
    private int J;
    private int K;
    private int L;
    private int M;
    private boolean N;
    private android.support.v4.d.g O;
    private android.support.v4.d.g P;
    private boolean Q;
    private boolean R;
    private boolean S;
    private int T;
    private ba U;
    private ba V;
    private az W;
    private int Z;
    private final ArrayList d;
    private final aw e;
    private final Rect f;
    private x g;
    private int h;
    private int i;
    private Parcelable j;
    private ClassLoader k;
    private Scroller l;
    private bb m;
    private int n;
    private Drawable o;
    private int p;
    private int q;
    private float r;
    private float s;
    private int t;
    private int u;
    private boolean v;
    private boolean w;
    private boolean x;
    private int y;
    private boolean z;

    public class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator CREATOR = android.support.v4.b.a.a(new bc());
        int a;
        Parcelable b;
        ClassLoader c;

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel);
            classLoader = classLoader == null ? getClass().getClassLoader() : classLoader;
            this.a = parcel.readInt();
            this.b = parcel.readParcelable(classLoader);
            this.c = classLoader;
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.a + "}";
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a);
            parcel.writeParcelable(this.b, i);
        }
    }

    public ViewPager(Context context) {
        super(context);
        this.d = new ArrayList();
        this.e = new aw();
        this.f = new Rect();
        this.i = -1;
        this.j = null;
        this.k = null;
        this.r = -3.4028235E38f;
        this.s = Float.MAX_VALUE;
        this.y = 1;
        this.H = -1;
        this.Q = true;
        this.R = false;
        this.Z = 0;
        a();
    }

    public ViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = new ArrayList();
        this.e = new aw();
        this.f = new Rect();
        this.i = -1;
        this.j = null;
        this.k = null;
        this.r = -3.4028235E38f;
        this.s = Float.MAX_VALUE;
        this.y = 1;
        this.H = -1;
        this.Q = true;
        this.R = false;
        this.Z = 0;
        a();
    }

    private int a(int i, float f, int i2, int i3) {
        if (Math.abs(i3) <= this.L || Math.abs(i2) <= this.J) {
            i = (int) (i + f + 0.5f);
        } else if (i2 <= 0) {
            i++;
        }
        if (this.d.size() <= 0) {
            return i;
        }
        return Math.max(((aw) this.d.get(0)).b, Math.min(i, ((aw) this.d.get(this.d.size() - 1)).b));
    }

    private Rect a(Rect rect, View view) {
        Rect rect2 = rect == null ? new Rect() : rect;
        if (view == null) {
            rect2.set(0, 0, 0, 0);
            return rect2;
        }
        rect2.left = view.getLeft();
        rect2.right = view.getRight();
        rect2.top = view.getTop();
        rect2.bottom = view.getBottom();
        ViewParent parent = view.getParent();
        while ((parent instanceof ViewGroup) && parent != this) {
            ViewGroup viewGroup = (ViewGroup) parent;
            rect2.left += viewGroup.getLeft();
            rect2.right += viewGroup.getRight();
            rect2.top += viewGroup.getTop();
            rect2.bottom += viewGroup.getBottom();
            parent = viewGroup.getParent();
        }
        return rect2;
    }

    private void a(int i, int i2, int i3, int i4) {
        if (i2 > 0 && !this.d.isEmpty()) {
            int scrollX = (int) ((i + i3) * (getScrollX() / (i2 + i4)));
            scrollTo(scrollX, getScrollY());
            if (this.l.isFinished()) {
                return;
            }
            this.l.startScroll(scrollX, 0, (int) (b(this.h).e * i), 0, this.l.getDuration() - this.l.timePassed());
            return;
        }
        aw awVarB = b(this.h);
        int iMin = (int) ((awVarB != null ? Math.min(awVarB.e, this.s) : 0.0f) * i);
        if (iMin != getScrollX()) {
            h();
            scrollTo(iMin, getScrollY());
        }
    }

    private void a(aw awVar, int i, aw awVar2) {
        aw awVar3;
        aw awVar4;
        int iB = this.g.b();
        int width = getWidth();
        float f = width > 0 ? this.n / width : 0.0f;
        if (awVar2 != null) {
            int i2 = awVar2.b;
            if (i2 < awVar.b) {
                float fB = awVar2.e + awVar2.d + f;
                int i3 = i2 + 1;
                int i4 = 0;
                while (i3 <= awVar.b && i4 < this.d.size()) {
                    Object obj = this.d.get(i4);
                    while (true) {
                        awVar4 = (aw) obj;
                        if (i3 <= awVar4.b || i4 >= this.d.size() - 1) {
                            break;
                        }
                        i4++;
                        obj = this.d.get(i4);
                    }
                    while (i3 < awVar4.b) {
                        fB += this.g.b(i3) + f;
                        i3++;
                    }
                    awVar4.e = fB;
                    fB += awVar4.d + f;
                    i3++;
                }
            } else if (i2 > awVar.b) {
                int size = this.d.size() - 1;
                float fB2 = awVar2.e;
                int i5 = i2 - 1;
                while (i5 >= awVar.b && size >= 0) {
                    Object obj2 = this.d.get(size);
                    while (true) {
                        awVar3 = (aw) obj2;
                        if (i5 >= awVar3.b || size <= 0) {
                            break;
                        }
                        size--;
                        obj2 = this.d.get(size);
                    }
                    while (i5 > awVar3.b) {
                        fB2 -= this.g.b(i5) + f;
                        i5--;
                    }
                    fB2 -= awVar3.d + f;
                    awVar3.e = fB2;
                    i5--;
                }
            }
        }
        int size2 = this.d.size();
        float f2 = awVar.e;
        int i6 = awVar.b - 1;
        this.r = awVar.b == 0 ? awVar.e : -3.4028235E38f;
        this.s = awVar.b == iB + (-1) ? (awVar.e + awVar.d) - 1.0f : Float.MAX_VALUE;
        for (int i7 = i - 1; i7 >= 0; i7--) {
            aw awVar5 = (aw) this.d.get(i7);
            float fB3 = f2;
            while (i6 > awVar5.b) {
                fB3 -= this.g.b(i6) + f;
                i6--;
            }
            f2 = fB3 - (awVar5.d + f);
            awVar5.e = f2;
            if (awVar5.b == 0) {
                this.r = f2;
            }
            i6--;
        }
        float f3 = awVar.e + awVar.d + f;
        int i8 = awVar.b + 1;
        for (int i9 = i + 1; i9 < size2; i9++) {
            aw awVar6 = (aw) this.d.get(i9);
            float fB4 = f3;
            while (i8 < awVar6.b) {
                fB4 = this.g.b(i8) + f + fB4;
                i8++;
            }
            if (awVar6.b == iB - 1) {
                this.s = (awVar6.d + fB4) - 1.0f;
            }
            awVar6.e = fB4;
            f3 = fB4 + awVar6.d + f;
            i8++;
        }
        this.R = false;
    }

    private void a(MotionEvent motionEvent) {
        int iA = s.a(motionEvent);
        if (s.b(motionEvent, iA) == this.H) {
            int i = iA == 0 ? 1 : 0;
            this.F = s.c(motionEvent, i);
            this.H = s.b(motionEvent, i);
            if (this.I != null) {
                this.I.clear();
            }
        }
    }

    private boolean a(float f, float f2) {
        return (f < ((float) this.C) && f2 > 0.0f) || (f > ((float) (getWidth() - this.C)) && f2 < 0.0f);
    }

    private boolean b(float f) {
        boolean z;
        float f2;
        boolean z2 = true;
        float f3 = this.F - f;
        this.F = f;
        float scrollX = getScrollX() + f3;
        int width = getWidth();
        float f4 = width * this.r;
        float f5 = width * this.s;
        aw awVar = (aw) this.d.get(0);
        aw awVar2 = (aw) this.d.get(this.d.size() - 1);
        if (awVar.b != 0) {
            f4 = awVar.e * width;
            z = false;
        } else {
            z = true;
        }
        if (awVar2.b != this.g.b() - 1) {
            f2 = awVar2.e * width;
            z2 = false;
        } else {
            f2 = f5;
        }
        if (scrollX < f4) {
            if (z) {
                zA = this.O.a(Math.abs(f4 - scrollX) / width);
            }
        } else if (scrollX > f2) {
            zA = z2 ? this.P.a(Math.abs(scrollX - f2) / width) : false;
            f4 = f2;
        } else {
            f4 = scrollX;
        }
        this.F += f4 - ((int) f4);
        scrollTo((int) f4, getScrollY());
        d((int) f4);
        return zA;
    }

    private boolean d(int i) {
        if (this.d.size() == 0) {
            this.S = false;
            a(0, 0.0f, 0);
            if (this.S) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        aw awVarI = i();
        int width = getWidth();
        int i2 = this.n + width;
        int i3 = awVarI.b;
        float f = ((i / width) - awVarI.e) / (awVarI.d + (this.n / width));
        this.S = false;
        a(i3, f, (int) (i2 * f));
        if (this.S) {
            return true;
        }
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }

    private void g() {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= getChildCount()) {
                return;
            }
            if (!((ax) getChildAt(i2).getLayoutParams()).a) {
                removeViewAt(i2);
                i2--;
            }
            i = i2 + 1;
        }
    }

    private void h() {
        boolean z = this.Z == 2;
        if (z) {
            setScrollingCacheEnabled(false);
            this.l.abortAnimation();
            int scrollX = getScrollX();
            int scrollY = getScrollY();
            int currX = this.l.getCurrX();
            int currY = this.l.getCurrY();
            if (scrollX != currX || scrollY != currY) {
                scrollTo(currX, currY);
            }
            setScrollState(0);
        }
        this.x = false;
        boolean z2 = z;
        for (int i = 0; i < this.d.size(); i++) {
            aw awVar = (aw) this.d.get(i);
            if (awVar.c) {
                awVar.c = false;
                z2 = true;
            }
        }
        if (z2) {
            c();
        }
    }

    private aw i() {
        int i;
        aw awVar;
        int width = getWidth();
        float scrollX = width > 0 ? getScrollX() / width : 0.0f;
        float f = width > 0 ? this.n / width : 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        int i2 = -1;
        int i3 = 0;
        boolean z = true;
        aw awVar2 = null;
        while (i3 < this.d.size()) {
            aw awVar3 = (aw) this.d.get(i3);
            if (z || awVar3.b == i2 + 1) {
                i = i3;
                awVar = awVar3;
            } else {
                aw awVar4 = this.e;
                awVar4.e = f2 + f3 + f;
                awVar4.b = i2 + 1;
                awVar4.d = this.g.b(awVar4.b);
                i = i3 - 1;
                awVar = awVar4;
            }
            float f4 = awVar.e;
            float f5 = awVar.d + f4 + f;
            if (!z && scrollX < f4) {
                return awVar2;
            }
            if (scrollX < f5 || i == this.d.size() - 1) {
                return awVar;
            }
            f3 = f4;
            i2 = awVar.b;
            z = false;
            f2 = awVar.d;
            awVar2 = awVar;
            i3 = i + 1;
        }
        return awVar2;
    }

    private void j() {
        this.z = false;
        this.A = false;
        if (this.I != null) {
            this.I.recycle();
            this.I = null;
        }
    }

    private void setScrollState(int i) {
        if (this.Z == i) {
            return;
        }
        this.Z = i;
        if (this.U != null) {
            this.U.b(i);
        }
    }

    private void setScrollingCacheEnabled(boolean z) {
        if (this.w != z) {
            this.w = z;
        }
    }

    float a(float f) {
        return (float) Math.sin((float) ((f - 0.5f) * 0.4712389167638204d));
    }

    aw a(int i, int i2) {
        aw awVar = new aw();
        awVar.b = i;
        awVar.a = this.g.a((ViewGroup) this, i);
        awVar.d = this.g.b(i);
        if (i2 < 0 || i2 >= this.d.size()) {
            this.d.add(awVar);
        } else {
            this.d.add(i2, awVar);
        }
        return awVar;
    }

    aw a(View view) {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.d.size()) {
                return null;
            }
            aw awVar = (aw) this.d.get(i2);
            if (this.g.a(view, awVar.a)) {
                return awVar;
            }
            i = i2 + 1;
        }
    }

    void a() {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context context = getContext();
        this.l = new Scroller(context, c);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.D = ao.a(viewConfiguration);
        this.J = viewConfiguration.getScaledMinimumFlingVelocity();
        this.K = viewConfiguration.getScaledMaximumFlingVelocity();
        this.O = new android.support.v4.d.g(context);
        this.P = new android.support.v4.d.g(context);
        float f = context.getResources().getDisplayMetrics().density;
        this.L = (int) (25.0f * f);
        this.M = (int) (2.0f * f);
        this.B = (int) (f * 16.0f);
        ad.a(this, new ay(this));
        if (ad.c(this) == 0) {
            ad.b(this, 1);
        }
    }

    void a(int i) {
        aw awVar;
        int i2;
        aw awVar2;
        aw awVarA;
        if (this.h != i) {
            aw awVarB = b(this.h);
            this.h = i;
            awVar = awVarB;
        } else {
            awVar = null;
        }
        if (this.g == null || this.x || getWindowToken() == null) {
            return;
        }
        this.g.a((ViewGroup) this);
        int i3 = this.y;
        int iMax = Math.max(0, this.h - i3);
        int iB = this.g.b();
        int iMin = Math.min(iB - 1, i3 + this.h);
        int i4 = 0;
        while (true) {
            i2 = i4;
            if (i2 >= this.d.size()) {
                break;
            }
            awVar2 = (aw) this.d.get(i2);
            if (awVar2.b >= this.h) {
                if (awVar2.b != this.h) {
                    break;
                }
            } else {
                i4 = i2 + 1;
            }
        }
        awVar2 = null;
        aw awVarA2 = (awVar2 != null || iB <= 0) ? awVar2 : a(this.h, i2);
        if (awVarA2 != null) {
            int i5 = i2 - 1;
            aw awVar3 = i5 >= 0 ? (aw) this.d.get(i5) : null;
            float f = 2.0f - awVarA2.d;
            float f2 = 0.0f;
            int i6 = i2;
            int i7 = i5;
            for (int i8 = this.h - 1; i8 >= 0; i8--) {
                if (f2 >= f && i8 < iMax) {
                    if (awVar3 == null) {
                        break;
                    }
                    if (i8 == awVar3.b && !awVar3.c) {
                        this.d.remove(i7);
                        this.g.a((ViewGroup) this, i8, awVar3.a);
                        i7--;
                        i6--;
                        awVar3 = i7 >= 0 ? (aw) this.d.get(i7) : null;
                    }
                } else if (awVar3 == null || i8 != awVar3.b) {
                    f2 += a(i8, i7 + 1).d;
                    i6++;
                    awVar3 = i7 >= 0 ? (aw) this.d.get(i7) : null;
                } else {
                    f2 += awVar3.d;
                    i7--;
                    awVar3 = i7 >= 0 ? (aw) this.d.get(i7) : null;
                }
            }
            float f3 = awVarA2.d;
            int i9 = i6 + 1;
            if (f3 < 2.0f) {
                aw awVar4 = i9 < this.d.size() ? (aw) this.d.get(i9) : null;
                float f4 = f3;
                int i10 = i9;
                int i11 = this.h + 1;
                while (i11 < iB) {
                    if (f4 >= 2.0f && i11 > iMin) {
                        if (awVar4 == null) {
                            break;
                        }
                        if (i11 == awVar4.b && !awVar4.c) {
                            this.d.remove(i10);
                            this.g.a((ViewGroup) this, i11, awVar4.a);
                            awVar4 = i10 < this.d.size() ? (aw) this.d.get(i10) : null;
                        }
                    } else if (awVar4 == null || i11 != awVar4.b) {
                        aw awVarA3 = a(i11, i10);
                        i10++;
                        f4 += awVarA3.d;
                        awVar4 = i10 < this.d.size() ? (aw) this.d.get(i10) : null;
                    } else {
                        f4 += awVar4.d;
                        i10++;
                        awVar4 = i10 < this.d.size() ? (aw) this.d.get(i10) : null;
                    }
                    i11++;
                    awVar4 = awVar4;
                    f4 = f4;
                }
            }
            a(awVarA2, i6, awVar);
        }
        this.g.b((ViewGroup) this, this.h, awVarA2 != null ? awVarA2.a : null);
        this.g.b((ViewGroup) this);
        int childCount = getChildCount();
        for (int i12 = 0; i12 < childCount; i12++) {
            View childAt = getChildAt(i12);
            ax axVar = (ax) childAt.getLayoutParams();
            if (!axVar.a && axVar.c == 0.0f && (awVarA = a(childAt)) != null) {
                axVar.c = awVarA.d;
            }
        }
        if (hasFocus()) {
            View viewFindFocus = findFocus();
            aw awVarB2 = viewFindFocus != null ? b(viewFindFocus) : null;
            if (awVarB2 == null || awVarB2.b != this.h) {
                for (int i13 = 0; i13 < getChildCount(); i13++) {
                    View childAt2 = getChildAt(i13);
                    aw awVarA4 = a(childAt2);
                    if (awVarA4 != null && awVarA4.b == this.h && childAt2.requestFocus(2)) {
                        return;
                    }
                }
            }
        }
    }

    protected void a(int i, float f, int i2) {
        int measuredWidth;
        int i3;
        int i4;
        if (this.T > 0) {
            int scrollX = getScrollX();
            int paddingLeft = getPaddingLeft();
            int paddingRight = getPaddingRight();
            int width = getWidth();
            int childCount = getChildCount();
            int i5 = 0;
            while (i5 < childCount) {
                View childAt = getChildAt(i5);
                ax axVar = (ax) childAt.getLayoutParams();
                if (axVar.a) {
                    switch (axVar.b & 7) {
                        case 1:
                            measuredWidth = Math.max((width - childAt.getMeasuredWidth()) / 2, paddingLeft);
                            int i6 = paddingRight;
                            i3 = paddingLeft;
                            i4 = i6;
                            break;
                        case 2:
                        case 4:
                        default:
                            measuredWidth = paddingLeft;
                            int i7 = paddingRight;
                            i3 = paddingLeft;
                            i4 = i7;
                            break;
                        case 3:
                            int width2 = childAt.getWidth() + paddingLeft;
                            int i8 = paddingLeft;
                            i4 = paddingRight;
                            i3 = width2;
                            measuredWidth = i8;
                            break;
                        case 5:
                            measuredWidth = (width - paddingRight) - childAt.getMeasuredWidth();
                            int measuredWidth2 = paddingRight + childAt.getMeasuredWidth();
                            i3 = paddingLeft;
                            i4 = measuredWidth2;
                            break;
                    }
                    int left = (measuredWidth + scrollX) - childAt.getLeft();
                    if (left != 0) {
                        childAt.offsetLeftAndRight(left);
                    }
                } else {
                    int i9 = paddingRight;
                    i3 = paddingLeft;
                    i4 = i9;
                }
                i5++;
                int i10 = i4;
                paddingLeft = i3;
                paddingRight = i10;
            }
        }
        if (this.U != null) {
            this.U.a(i, f, i2);
        }
        if (this.V != null) {
            this.V.a(i, f, i2);
        }
        this.S = true;
    }

    void a(int i, int i2, int i3) {
        int iAbs;
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int i4 = i - scrollX;
        int i5 = i2 - scrollY;
        if (i4 == 0 && i5 == 0) {
            h();
            c();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int width = getWidth();
        int i6 = width / 2;
        float fA = (i6 * a(Math.min(1.0f, (Math.abs(i4) * 1.0f) / width))) + i6;
        int iAbs2 = Math.abs(i3);
        if (iAbs2 > 0) {
            iAbs = Math.round(1000.0f * Math.abs(fA / iAbs2)) * 4;
        } else {
            iAbs = (int) (((Math.abs(i4) / ((width * this.g.b(this.h)) + this.n)) + 1.0f) * 100.0f);
        }
        this.l.startScroll(scrollX, scrollY, i4, i5, Math.min(iAbs, 600));
        ad.b(this);
    }

    public void a(int i, boolean z) {
        this.x = false;
        a(i, z, false);
    }

    void a(int i, boolean z, boolean z2) {
        a(i, z, z2, 0);
    }

    void a(int i, boolean z, boolean z2, int i2) {
        if (this.g == null || this.g.b() <= 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        if (!z2 && this.h == i && this.d.size() != 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        if (i < 0) {
            i = 0;
        } else if (i >= this.g.b()) {
            i = this.g.b() - 1;
        }
        int i3 = this.y;
        if (i > this.h + i3 || i < this.h - i3) {
            for (int i4 = 0; i4 < this.d.size(); i4++) {
                ((aw) this.d.get(i4)).c = true;
            }
        }
        boolean z3 = this.h != i;
        a(i);
        aw awVarB = b(i);
        int iMax = awVarB != null ? (int) (Math.max(this.r, Math.min(awVarB.e, this.s)) * getWidth()) : 0;
        if (z) {
            a(iMax, 0, i2);
            if (z3 && this.U != null) {
                this.U.a(i);
            }
            if (!z3 || this.V == null) {
                return;
            }
            this.V.a(i);
            return;
        }
        if (z3 && this.U != null) {
            this.U.a(i);
        }
        if (z3 && this.V != null) {
            this.V.a(i);
        }
        h();
        scrollTo(iMax, 0);
    }

    public boolean a(KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0) {
            return false;
        }
        switch (keyEvent.getKeyCode()) {
            case 21:
                break;
            case 22:
                break;
            case 61:
                if (Build.VERSION.SDK_INT >= 11) {
                    if (!n.a(keyEvent)) {
                        if (n.a(keyEvent, 1)) {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                break;
        }
        return false;
    }

    protected boolean a(View view, boolean z, int i, int i2, int i3) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                if (i2 + scrollX >= childAt.getLeft() && i2 + scrollX < childAt.getRight() && i3 + scrollY >= childAt.getTop() && i3 + scrollY < childAt.getBottom() && a(childAt, true, i, (i2 + scrollX) - childAt.getLeft(), (i3 + scrollY) - childAt.getTop())) {
                    return true;
                }
            }
        }
        return z && ad.a(view, -i);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList arrayList, int i, int i2) {
        aw awVarA;
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i3 = 0; i3 < getChildCount(); i3++) {
                View childAt = getChildAt(i3);
                if (childAt.getVisibility() == 0 && (awVarA = a(childAt)) != null && awVarA.b == this.h) {
                    childAt.addFocusables(arrayList, i, i2);
                }
            }
        }
        if ((descendantFocusability != 262144 || size == arrayList.size()) && isFocusable()) {
            if (((i2 & 1) == 1 && isInTouchMode() && !isFocusableInTouchMode()) || arrayList == null) {
                return;
            }
            arrayList.add(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addTouchables(ArrayList arrayList) {
        aw awVarA;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 0 && (awVarA = a(childAt)) != null && awVarA.b == this.h) {
                childAt.addTouchables(arrayList);
            }
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        ViewGroup.LayoutParams layoutParamsGenerateLayoutParams = !checkLayoutParams(layoutParams) ? generateLayoutParams(layoutParams) : layoutParams;
        ax axVar = (ax) layoutParamsGenerateLayoutParams;
        axVar.a |= view instanceof av;
        if (!this.v) {
            super.addView(view, i, layoutParamsGenerateLayoutParams);
        } else {
            if (axVar != null && axVar.a) {
                throw new IllegalStateException("Cannot add pager decor view during layout");
            }
            axVar.d = true;
            addViewInLayout(view, i, layoutParamsGenerateLayoutParams);
        }
    }

    aw b(int i) {
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= this.d.size()) {
                return null;
            }
            aw awVar = (aw) this.d.get(i3);
            if (awVar.b == i) {
                return awVar;
            }
            i2 = i3 + 1;
        }
    }

    aw b(View view) {
        while (true) {
            Object parent = view.getParent();
            if (parent == this) {
                return a(view);
            }
            if (parent == null || !(parent instanceof View)) {
                break;
            }
            view = (View) parent;
        }
        return null;
    }

    void b() {
        int i;
        boolean z;
        int iMax;
        boolean z2;
        boolean z3 = this.d.size() < (this.y * 2) + 1 && this.d.size() < this.g.b();
        boolean z4 = false;
        int i2 = this.h;
        boolean z5 = z3;
        int i3 = 0;
        while (i3 < this.d.size()) {
            aw awVar = (aw) this.d.get(i3);
            int iA = this.g.a(awVar.a);
            if (iA == -1) {
                i = i3;
                z = z4;
                iMax = i2;
                z2 = z5;
            } else if (iA == -2) {
                this.d.remove(i3);
                int i4 = i3 - 1;
                if (!z4) {
                    this.g.a((ViewGroup) this);
                    z4 = true;
                }
                this.g.a((ViewGroup) this, awVar.b, awVar.a);
                if (this.h == awVar.b) {
                    i = i4;
                    z = z4;
                    iMax = Math.max(0, Math.min(this.h, this.g.b() - 1));
                    z2 = true;
                } else {
                    i = i4;
                    z = z4;
                    iMax = i2;
                    z2 = true;
                }
            } else if (awVar.b != iA) {
                if (awVar.b == this.h) {
                    i2 = iA;
                }
                awVar.b = iA;
                i = i3;
                z = z4;
                iMax = i2;
                z2 = true;
            } else {
                i = i3;
                z = z4;
                iMax = i2;
                z2 = z5;
            }
            z5 = z2;
            i2 = iMax;
            z4 = z;
            i3 = i + 1;
        }
        if (z4) {
            this.g.b((ViewGroup) this);
        }
        Collections.sort(this.d, b);
        if (z5) {
            int childCount = getChildCount();
            for (int i5 = 0; i5 < childCount; i5++) {
                ax axVar = (ax) getChildAt(i5).getLayoutParams();
                if (!axVar.a) {
                    axVar.c = 0.0f;
                }
            }
            a(i2, false, true);
            requestLayout();
        }
    }

    void c() {
        a(this.h);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0075  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean c(int i) {
        boolean zD;
        View viewFindFocus = findFocus();
        if (viewFindFocus == this) {
            viewFindFocus = null;
        }
        View viewFindNextFocus = FocusFinder.getInstance().findNextFocus(this, viewFindFocus, i);
        if (viewFindNextFocus == null || viewFindNextFocus == viewFindFocus) {
            zD = (i == 17 || i == 1) ? d() : (i == 66 || i == 2) ? e() : false;
        } else if (i == 17) {
            zD = (viewFindFocus == null || a(this.f, viewFindNextFocus).left < a(this.f, viewFindFocus).left) ? viewFindNextFocus.requestFocus() : d();
        } else if (i == 66) {
            zD = (viewFindFocus == null || a(this.f, viewFindNextFocus).left > a(this.f, viewFindFocus).left) ? viewFindNextFocus.requestFocus() : e();
        }
        if (zD) {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i));
        }
        return zD;
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof ax) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.l.isFinished() || !this.l.computeScrollOffset()) {
            h();
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.l.getCurrX();
        int currY = this.l.getCurrY();
        if (scrollX != currX || scrollY != currY) {
            scrollTo(currX, currY);
            if (!d(currX)) {
                this.l.abortAnimation();
                scrollTo(0, currY);
            }
        }
        ad.b(this);
    }

    boolean d() {
        if (this.h <= 0) {
            return false;
        }
        a(this.h - 1, true);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || a(keyEvent);
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        aw awVarA;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 0 && (awVarA = a(childAt)) != null && awVarA.b == this.h && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        boolean zA = false;
        int iA = ad.a(this);
        if (iA == 0 || (iA == 1 && this.g != null && this.g.b() > 1)) {
            if (!this.O.a()) {
                int iSave = canvas.save();
                int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                int width = getWidth();
                canvas.rotate(270.0f);
                canvas.translate((-height) + getPaddingTop(), this.r * width);
                this.O.a(height, width);
                zA = false | this.O.a(canvas);
                canvas.restoreToCount(iSave);
            }
            if (!this.P.a()) {
                int iSave2 = canvas.save();
                int width2 = getWidth();
                int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(90.0f);
                canvas.translate(-getPaddingTop(), (-(this.s + 1.0f)) * width2);
                this.P.a(height2, width2);
                zA |= this.P.a(canvas);
                canvas.restoreToCount(iSave2);
            }
        } else {
            this.O.b();
            this.P.b();
        }
        if (zA) {
            ad.b(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.o;
        if (drawable == null || !drawable.isStateful()) {
            return;
        }
        drawable.setState(getDrawableState());
    }

    boolean e() {
        if (this.g == null || this.h >= this.g.b() - 1) {
            return false;
        }
        a(this.h + 1, true);
        return true;
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ax();
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ax(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return generateDefaultLayoutParams();
    }

    public x getAdapter() {
        return this.g;
    }

    public int getCurrentItem() {
        return this.h;
    }

    public int getOffscreenPageLimit() {
        return this.y;
    }

    public int getPageMargin() {
        return this.n;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.Q = true;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        float f;
        super.onDraw(canvas);
        if (this.n <= 0 || this.o == null || this.d.size() <= 0 || this.g == null) {
            return;
        }
        int scrollX = getScrollX();
        int width = getWidth();
        float f2 = this.n / width;
        aw awVar = (aw) this.d.get(0);
        float f3 = awVar.e;
        int size = this.d.size();
        int i = awVar.b;
        int i2 = ((aw) this.d.get(size - 1)).b;
        int i3 = 0;
        for (int i4 = i; i4 < i2; i4++) {
            while (i4 > awVar.b && i3 < size) {
                i3++;
                awVar = (aw) this.d.get(i3);
            }
            if (i4 == awVar.b) {
                f = (awVar.e + awVar.d) * width;
                f3 = awVar.e + awVar.d + f2;
            } else {
                float fB = this.g.b(i4);
                f = (f3 + fB) * width;
                f3 += fB + f2;
            }
            if (this.n + f > scrollX) {
                this.o.setBounds((int) f, this.p, (int) (this.n + f + 0.5f), this.q);
                this.o.draw(canvas);
            }
            if (f > scrollX + width) {
                return;
            }
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            this.z = false;
            this.A = false;
            this.H = -1;
            if (this.I == null) {
                return false;
            }
            this.I.recycle();
            this.I = null;
            return false;
        }
        if (action != 0) {
            if (this.z) {
                return true;
            }
            if (this.A) {
                return false;
            }
        }
        switch (action) {
            case 0:
                float x = motionEvent.getX();
                this.E = x;
                this.F = x;
                this.G = motionEvent.getY();
                this.H = s.b(motionEvent, 0);
                this.A = false;
                this.l.computeScrollOffset();
                if (this.Z == 2 && Math.abs(this.l.getFinalX() - this.l.getCurrX()) > this.M) {
                    this.l.abortAnimation();
                    this.x = false;
                    c();
                    this.z = true;
                    setScrollState(1);
                    break;
                } else {
                    h();
                    this.z = false;
                    break;
                }
            case 2:
                int i = this.H;
                if (i != -1) {
                    int iA = s.a(motionEvent, i);
                    float fC = s.c(motionEvent, iA);
                    float f = fC - this.F;
                    float fAbs = Math.abs(f);
                    float fD = s.d(motionEvent, iA);
                    float fAbs2 = Math.abs(fD - this.G);
                    if (f != 0.0f && !a(this.F, f) && a(this, false, (int) f, (int) fC, (int) fD)) {
                        this.F = fC;
                        this.E = fC;
                        this.G = fD;
                        this.A = true;
                        return false;
                    }
                    if (fAbs > this.D && fAbs > fAbs2) {
                        this.z = true;
                        setScrollState(1);
                        this.F = f > 0.0f ? this.E + this.D : this.E - this.D;
                        setScrollingCacheEnabled(true);
                    } else if (fAbs2 > this.D) {
                        this.A = true;
                    }
                    if (this.z && b(fC)) {
                        ad.b(this);
                        break;
                    }
                }
                break;
            case 6:
                a(motionEvent);
                break;
        }
        if (this.I == null) {
            this.I = VelocityTracker.obtain();
        }
        this.I.addMovement(motionEvent);
        return this.z;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x013b  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        aw awVarA;
        int i5;
        int i6;
        int iMax;
        int measuredHeight;
        int i7;
        int i8;
        this.v = true;
        c();
        this.v = false;
        int childCount = getChildCount();
        int i9 = i3 - i;
        int i10 = i4 - i2;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int scrollX = getScrollX();
        int i11 = 0;
        int i12 = 0;
        while (i12 < childCount) {
            View childAt = getChildAt(i12);
            if (childAt.getVisibility() != 8) {
                ax axVar = (ax) childAt.getLayoutParams();
                if (axVar.a) {
                    int i13 = axVar.b & 7;
                    int i14 = axVar.b & 112;
                    switch (i13) {
                        case 1:
                            iMax = Math.max((i9 - childAt.getMeasuredWidth()) / 2, paddingLeft);
                            break;
                        case 2:
                        case 4:
                        default:
                            iMax = paddingLeft;
                            break;
                        case 3:
                            iMax = paddingLeft;
                            paddingLeft = childAt.getMeasuredWidth() + paddingLeft;
                            break;
                        case 5:
                            int measuredWidth = (i9 - paddingRight) - childAt.getMeasuredWidth();
                            paddingRight += childAt.getMeasuredWidth();
                            iMax = measuredWidth;
                            break;
                    }
                    switch (i14) {
                        case 16:
                            measuredHeight = Math.max((i10 - childAt.getMeasuredHeight()) / 2, paddingTop);
                            int i15 = paddingBottom;
                            i7 = paddingTop;
                            i8 = i15;
                            break;
                        case 48:
                            int measuredHeight2 = childAt.getMeasuredHeight() + paddingTop;
                            int i16 = paddingTop;
                            i8 = paddingBottom;
                            i7 = measuredHeight2;
                            measuredHeight = i16;
                            break;
                        case 80:
                            measuredHeight = (i10 - paddingBottom) - childAt.getMeasuredHeight();
                            int measuredHeight3 = paddingBottom + childAt.getMeasuredHeight();
                            i7 = paddingTop;
                            i8 = measuredHeight3;
                            break;
                        default:
                            measuredHeight = paddingTop;
                            int i17 = paddingBottom;
                            i7 = paddingTop;
                            i8 = i17;
                            break;
                    }
                    int i18 = iMax + scrollX;
                    childAt.layout(i18, measuredHeight, childAt.getMeasuredWidth() + i18, childAt.getMeasuredHeight() + measuredHeight);
                    i5 = i11 + 1;
                    i6 = i7;
                    paddingBottom = i8;
                } else {
                    i5 = i11;
                    i6 = paddingTop;
                }
            }
            i12++;
            paddingLeft = paddingLeft;
            paddingRight = paddingRight;
            paddingTop = i6;
            i11 = i5;
        }
        for (int i19 = 0; i19 < childCount; i19++) {
            View childAt2 = getChildAt(i19);
            if (childAt2.getVisibility() != 8) {
                ax axVar2 = (ax) childAt2.getLayoutParams();
                if (!axVar2.a && (awVarA = a(childAt2)) != null) {
                    int i20 = ((int) (awVarA.e * i9)) + paddingLeft;
                    if (axVar2.d) {
                        axVar2.d = false;
                        childAt2.measure(View.MeasureSpec.makeMeasureSpec((int) (axVar2.c * ((i9 - paddingLeft) - paddingRight)), 1073741824), View.MeasureSpec.makeMeasureSpec((i10 - paddingTop) - paddingBottom, 1073741824));
                    }
                    childAt2.layout(i20, paddingTop, childAt2.getMeasuredWidth() + i20, childAt2.getMeasuredHeight() + paddingTop);
                }
            }
        }
        this.p = paddingTop;
        this.q = i10 - paddingBottom;
        this.T = i11;
        this.Q = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x010a A[PHI: r1
      0x010a: PHI (r1v18 int) = (r1v17 int), (r1v20 int) binds: [B:28:0x0088, B:30:0x008f] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void onMeasure(int i, int i2) {
        ax axVar;
        ax axVar2;
        int i3;
        int i4;
        setMeasuredDimension(getDefaultSize(0, i), getDefaultSize(0, i2));
        int measuredWidth = getMeasuredWidth();
        this.C = Math.min(measuredWidth / 10, this.B);
        int paddingLeft = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8 && (axVar2 = (ax) childAt.getLayoutParams()) != null && axVar2.a) {
                int i6 = axVar2.b & 7;
                int i7 = axVar2.b & 112;
                int i8 = Integer.MIN_VALUE;
                int i9 = Integer.MIN_VALUE;
                boolean z = i7 == 48 || i7 == 80;
                boolean z2 = i6 == 3 || i6 == 5;
                if (z) {
                    i8 = 1073741824;
                } else if (z2) {
                    i9 = 1073741824;
                }
                if (axVar2.width != -2) {
                    i3 = 1073741824;
                    i4 = axVar2.width != -1 ? axVar2.width : paddingLeft;
                } else {
                    i3 = i8;
                    i4 = paddingLeft;
                }
                if (axVar2.height != -2) {
                    i9 = 1073741824;
                    int i10 = axVar2.height != -1 ? axVar2.height : measuredHeight;
                    childAt.measure(View.MeasureSpec.makeMeasureSpec(i4, i3), View.MeasureSpec.makeMeasureSpec(i10, i9));
                    if (z) {
                        measuredHeight -= childAt.getMeasuredHeight();
                    } else if (z2) {
                        paddingLeft -= childAt.getMeasuredWidth();
                    }
                }
            }
        }
        this.t = View.MeasureSpec.makeMeasureSpec(paddingLeft, 1073741824);
        this.u = View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
        this.v = true;
        c();
        this.v = false;
        int childCount2 = getChildCount();
        for (int i11 = 0; i11 < childCount2; i11++) {
            View childAt2 = getChildAt(i11);
            if (childAt2.getVisibility() != 8 && ((axVar = (ax) childAt2.getLayoutParams()) == null || !axVar.a)) {
                childAt2.measure(View.MeasureSpec.makeMeasureSpec((int) (axVar.c * paddingLeft), 1073741824), this.u);
            }
        }
    }

    @Override // android.view.ViewGroup
    protected boolean onRequestFocusInDescendants(int i, Rect rect) {
        int i2;
        aw awVarA;
        int i3 = -1;
        int childCount = getChildCount();
        if ((i & 2) != 0) {
            i3 = 1;
            i2 = 0;
        } else {
            i2 = childCount - 1;
            childCount = -1;
        }
        while (i2 != childCount) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (awVarA = a(childAt)) != null && awVarA.b == this.h && childAt.requestFocus(i, rect)) {
                return true;
            }
            i2 += i3;
        }
        return false;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (this.g != null) {
            this.g.a(savedState.b, savedState.c);
            a(savedState.a, false, true);
        } else {
            this.i = savedState.a;
            this.j = savedState.b;
            this.k = savedState.c;
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = this.h;
        if (this.g != null) {
            savedState.b = this.g.a();
        }
        return savedState;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3) {
            a(i, i3, this.n, this.n);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean zC = false;
        if (this.N) {
            return true;
        }
        if (motionEvent.getAction() == 0 && motionEvent.getEdgeFlags() != 0) {
            return false;
        }
        if (this.g == null || this.g.b() == 0) {
            return false;
        }
        if (this.I == null) {
            this.I = VelocityTracker.obtain();
        }
        this.I.addMovement(motionEvent);
        switch (motionEvent.getAction() & 255) {
            case 0:
                this.l.abortAnimation();
                this.x = false;
                c();
                this.z = true;
                setScrollState(1);
                float x = motionEvent.getX();
                this.E = x;
                this.F = x;
                this.H = s.b(motionEvent, 0);
                break;
            case 1:
                if (this.z) {
                    VelocityTracker velocityTracker = this.I;
                    velocityTracker.computeCurrentVelocity(1000, this.K);
                    int iA = (int) y.a(velocityTracker, this.H);
                    this.x = true;
                    int width = getWidth();
                    int scrollX = getScrollX();
                    aw awVarI = i();
                    a(a(awVarI.b, ((scrollX / width) - awVarI.e) / awVarI.d, iA, (int) (s.c(motionEvent, s.a(motionEvent, this.H)) - this.E)), true, true, iA);
                    this.H = -1;
                    j();
                    zC = this.P.c() | this.O.c();
                    break;
                }
                break;
            case 2:
                if (!this.z) {
                    int iA2 = s.a(motionEvent, this.H);
                    float fC = s.c(motionEvent, iA2);
                    float fAbs = Math.abs(fC - this.F);
                    float fAbs2 = Math.abs(s.d(motionEvent, iA2) - this.G);
                    if (fAbs > this.D && fAbs > fAbs2) {
                        this.z = true;
                        this.F = fC - this.E > 0.0f ? this.E + this.D : this.E - this.D;
                        setScrollState(1);
                        setScrollingCacheEnabled(true);
                    }
                }
                if (this.z) {
                    zC = false | b(s.c(motionEvent, s.a(motionEvent, this.H)));
                    break;
                }
                break;
            case 3:
                if (this.z) {
                    a(this.h, true, true);
                    this.H = -1;
                    j();
                    zC = this.P.c() | this.O.c();
                    break;
                }
                break;
            case 5:
                int iA3 = s.a(motionEvent);
                this.F = s.c(motionEvent, iA3);
                this.H = s.b(motionEvent, iA3);
                break;
            case 6:
                a(motionEvent);
                this.F = s.c(motionEvent, s.a(motionEvent, this.H));
                break;
        }
        if (zC) {
            ad.b(this);
        }
        return true;
    }

    public void setAdapter(x xVar) {
        if (this.g != null) {
            this.g.b(this.m);
            this.g.a((ViewGroup) this);
            for (int i = 0; i < this.d.size(); i++) {
                aw awVar = (aw) this.d.get(i);
                this.g.a((ViewGroup) this, awVar.b, awVar.a);
            }
            this.g.b((ViewGroup) this);
            this.d.clear();
            g();
            this.h = 0;
            scrollTo(0, 0);
        }
        x xVar2 = this.g;
        this.g = xVar;
        if (this.g != null) {
            if (this.m == null) {
                this.m = new bb(this, null);
            }
            this.g.a((DataSetObserver) this.m);
            this.x = false;
            this.Q = true;
            if (this.i >= 0) {
                this.g.a(this.j, this.k);
                a(this.i, false, true);
                this.i = -1;
                this.j = null;
                this.k = null;
            } else {
                c();
            }
        }
        if (this.W == null || xVar2 == xVar) {
            return;
        }
        this.W.a(xVar2, xVar);
    }

    public void setCurrentItem(int i) {
        this.x = false;
        a(i, !this.Q, false);
    }

    public void setOffscreenPageLimit(int i) {
        if (i < 1) {
            Log.w("ViewPager", "Requested offscreen page limit " + i + " too small; defaulting to 1");
            i = 1;
        }
        if (i != this.y) {
            this.y = i;
            c();
        }
    }

    void setOnAdapterChangeListener(az azVar) {
        this.W = azVar;
    }

    public void setOnPageChangeListener(ba baVar) {
        this.U = baVar;
    }

    public void setPageMargin(int i) {
        int i2 = this.n;
        this.n = i;
        int width = getWidth();
        a(width, width, i, i2);
        requestLayout();
    }

    public void setPageMarginDrawable(int i) {
        setPageMarginDrawable(getContext().getResources().getDrawable(i));
    }

    public void setPageMarginDrawable(Drawable drawable) {
        this.o = drawable;
        if (drawable != null) {
            refreshDrawableState();
        }
        setWillNotDraw(drawable == null);
        invalidate();
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.o;
    }
}
