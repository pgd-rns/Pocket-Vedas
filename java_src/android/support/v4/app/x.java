package android.support.v4.app;

import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* loaded from: classes.dex */
class x extends v {
    static boolean a = false;
    final android.support.v4.c.c b = new android.support.v4.c.c();
    final android.support.v4.c.c c = new android.support.v4.c.c();
    h d;
    boolean e;
    boolean f;

    x(h hVar, boolean z) {
        this.d = hVar;
        this.e = z;
    }

    void a(h hVar) {
        this.d = hVar;
    }

    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        if (this.b.a() > 0) {
            printWriter.print(str);
            printWriter.println("Active Loaders:");
            String str2 = str + "    ";
            for (int i = 0; i < this.b.a(); i++) {
                y yVar = (y) this.b.f(i);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(this.b.e(i));
                printWriter.print(": ");
                printWriter.println(yVar.toString());
                yVar.a(str2, fileDescriptor, printWriter, strArr);
            }
        }
        if (this.c.a() > 0) {
            printWriter.print(str);
            printWriter.println("Inactive Loaders:");
            String str3 = str + "    ";
            for (int i2 = 0; i2 < this.c.a(); i2++) {
                y yVar2 = (y) this.c.f(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(this.c.e(i2));
                printWriter.print(": ");
                printWriter.println(yVar2.toString());
                yVar2.a(str3, fileDescriptor, printWriter, strArr);
            }
        }
    }

    @Override // android.support.v4.app.v
    public boolean a() {
        int iA = this.b.a();
        boolean z = false;
        for (int i = 0; i < iA; i++) {
            y yVar = (y) this.b.f(i);
            z |= yVar.h && !yVar.f;
        }
        return z;
    }

    void b() {
        if (a) {
            Log.v("LoaderManager", "Starting in " + this);
        }
        if (this.e) {
            RuntimeException runtimeException = new RuntimeException("here");
            runtimeException.fillInStackTrace();
            Log.w("LoaderManager", "Called doStart when already started: " + this, runtimeException);
        } else {
            this.e = true;
            for (int iA = this.b.a() - 1; iA >= 0; iA--) {
                ((y) this.b.f(iA)).a();
            }
        }
    }

    void c() {
        if (a) {
            Log.v("LoaderManager", "Stopping in " + this);
        }
        if (!this.e) {
            RuntimeException runtimeException = new RuntimeException("here");
            runtimeException.fillInStackTrace();
            Log.w("LoaderManager", "Called doStop when not started: " + this, runtimeException);
        } else {
            for (int iA = this.b.a() - 1; iA >= 0; iA--) {
                ((y) this.b.f(iA)).e();
            }
            this.e = false;
        }
    }

    void d() {
        if (a) {
            Log.v("LoaderManager", "Retaining in " + this);
        }
        if (!this.e) {
            RuntimeException runtimeException = new RuntimeException("here");
            runtimeException.fillInStackTrace();
            Log.w("LoaderManager", "Called doRetain when not started: " + this, runtimeException);
        } else {
            this.f = true;
            this.e = false;
            for (int iA = this.b.a() - 1; iA >= 0; iA--) {
                ((y) this.b.f(iA)).b();
            }
        }
    }

    void e() {
        if (this.f) {
            if (a) {
                Log.v("LoaderManager", "Finished Retaining in " + this);
            }
            this.f = false;
            for (int iA = this.b.a() - 1; iA >= 0; iA--) {
                ((y) this.b.f(iA)).c();
            }
        }
    }

    void f() {
        for (int iA = this.b.a() - 1; iA >= 0; iA--) {
            ((y) this.b.f(iA)).k = true;
        }
    }

    void g() {
        for (int iA = this.b.a() - 1; iA >= 0; iA--) {
            ((y) this.b.f(iA)).d();
        }
    }

    void h() {
        if (!this.f) {
            if (a) {
                Log.v("LoaderManager", "Destroying Active in " + this);
            }
            for (int iA = this.b.a() - 1; iA >= 0; iA--) {
                ((y) this.b.f(iA)).f();
            }
        }
        if (a) {
            Log.v("LoaderManager", "Destroying Inactive in " + this);
        }
        for (int iA2 = this.c.a() - 1; iA2 >= 0; iA2--) {
            ((y) this.c.f(iA2)).f();
        }
        this.c.b();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        android.support.v4.c.a.a(this.d, sb);
        sb.append("}}");
        return sb.toString();
    }
}
