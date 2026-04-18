package android.support.v4.app;

import android.os.Bundle;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;

/* loaded from: classes.dex */
final class y implements android.support.v4.a.b {
    final int a;
    final Bundle b;
    w c;
    android.support.v4.a.a d;
    boolean e;
    boolean f;
    Object g;
    boolean h;
    boolean i;
    boolean j;
    boolean k;
    boolean l;
    boolean m;
    y n;
    final /* synthetic */ x o;

    void a() {
        if (this.i && this.j) {
            this.h = true;
            return;
        }
        if (this.h) {
            return;
        }
        this.h = true;
        if (x.a) {
            Log.v("LoaderManager", "  Starting: " + this);
        }
        if (this.d == null && this.c != null) {
            this.d = this.c.a(this.a, this.b);
        }
        if (this.d != null) {
            if (this.d.getClass().isMemberClass() && !Modifier.isStatic(this.d.getClass().getModifiers())) {
                throw new IllegalArgumentException("Object returned from onCreateLoader must not be a non-static inner member class: " + this.d);
            }
            if (!this.m) {
                this.d.a(this.a, this);
                this.m = true;
            }
            this.d.a();
        }
    }

    void a(android.support.v4.a.a aVar, Object obj) {
        String str;
        if (this.c != null) {
            if (this.o.d != null) {
                String str2 = this.o.d.b.s;
                this.o.d.b.s = "onLoadFinished";
                str = str2;
            } else {
                str = null;
            }
            try {
                if (x.a) {
                    Log.v("LoaderManager", "  onLoadFinished in " + aVar + ": " + aVar.a(obj));
                }
                this.c.a(aVar, obj);
                this.f = true;
            } finally {
                if (this.o.d != null) {
                    this.o.d.b.s = str;
                }
            }
        }
    }

    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mId=");
        printWriter.print(this.a);
        printWriter.print(" mArgs=");
        printWriter.println(this.b);
        printWriter.print(str);
        printWriter.print("mCallbacks=");
        printWriter.println(this.c);
        printWriter.print(str);
        printWriter.print("mLoader=");
        printWriter.println(this.d);
        if (this.d != null) {
            this.d.a(str + "  ", fileDescriptor, printWriter, strArr);
        }
        if (this.e || this.f) {
            printWriter.print(str);
            printWriter.print("mHaveData=");
            printWriter.print(this.e);
            printWriter.print("  mDeliveredData=");
            printWriter.println(this.f);
            printWriter.print(str);
            printWriter.print("mData=");
            printWriter.println(this.g);
        }
        printWriter.print(str);
        printWriter.print("mStarted=");
        printWriter.print(this.h);
        printWriter.print(" mReportNextStart=");
        printWriter.print(this.k);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.l);
        printWriter.print(str);
        printWriter.print("mRetaining=");
        printWriter.print(this.i);
        printWriter.print(" mRetainingStarted=");
        printWriter.print(this.j);
        printWriter.print(" mListenerRegistered=");
        printWriter.println(this.m);
        if (this.n != null) {
            printWriter.print(str);
            printWriter.println("Pending Loader ");
            printWriter.print(this.n);
            printWriter.println(":");
            this.n.a(str + "  ", fileDescriptor, printWriter, strArr);
        }
    }

    void b() {
        if (x.a) {
            Log.v("LoaderManager", "  Retaining: " + this);
        }
        this.i = true;
        this.j = this.h;
        this.h = false;
        this.c = null;
    }

    void c() {
        if (this.i) {
            if (x.a) {
                Log.v("LoaderManager", "  Finished Retaining: " + this);
            }
            this.i = false;
            if (this.h != this.j && !this.h) {
                e();
            }
        }
        if (this.h && this.e && !this.k) {
            a(this.d, this.g);
        }
    }

    void d() {
        if (this.h && this.k) {
            this.k = false;
            if (this.e) {
                a(this.d, this.g);
            }
        }
    }

    void e() {
        if (x.a) {
            Log.v("LoaderManager", "  Stopping: " + this);
        }
        this.h = false;
        if (this.i || this.d == null || !this.m) {
            return;
        }
        this.m = false;
        this.d.a((android.support.v4.a.b) this);
        this.d.c();
    }

    void f() {
        String str;
        if (x.a) {
            Log.v("LoaderManager", "  Destroying: " + this);
        }
        this.l = true;
        boolean z = this.f;
        this.f = false;
        if (this.c != null && this.d != null && this.e && z) {
            if (x.a) {
                Log.v("LoaderManager", "  Reseting: " + this);
            }
            if (this.o.d != null) {
                String str2 = this.o.d.b.s;
                this.o.d.b.s = "onLoaderReset";
                str = str2;
            } else {
                str = null;
            }
            try {
                this.c.a(this.d);
            } finally {
                if (this.o.d != null) {
                    this.o.d.b.s = str;
                }
            }
        }
        this.c = null;
        this.g = null;
        this.e = false;
        if (this.d != null) {
            if (this.m) {
                this.m = false;
                this.d.a((android.support.v4.a.b) this);
            }
            this.d.e();
        }
        if (this.n != null) {
            this.n.f();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append("LoaderInfo{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" #");
        sb.append(this.a);
        sb.append(" : ");
        android.support.v4.c.a.a(this.d, sb);
        sb.append("}}");
        return sb.toString();
    }
}
