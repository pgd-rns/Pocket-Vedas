package android.support.v4.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

/* loaded from: classes.dex */
public class e extends Fragment implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {
    int a = 0;
    int b = 0;
    boolean c = true;
    boolean d = true;
    int e = -1;
    Dialog f;
    boolean g;
    boolean h;
    boolean i;

    public int a() {
        return this.b;
    }

    @Override // android.support.v4.app.Fragment
    public void a(Activity activity) {
        super.a(activity);
        if (this.i) {
            return;
        }
        this.h = false;
    }

    @Override // android.support.v4.app.Fragment
    public void a(Bundle bundle) {
        super.a(bundle);
        this.d = this.E == 0;
        if (bundle != null) {
            this.a = bundle.getInt("android:style", 0);
            this.b = bundle.getInt("android:theme", 0);
            this.c = bundle.getBoolean("android:cancelable", true);
            this.d = bundle.getBoolean("android:showsDialog", this.d);
            this.e = bundle.getInt("android:backStackId", -1);
        }
    }

    public void a(l lVar, String str) {
        this.h = false;
        this.i = true;
        u uVarA = lVar.a();
        uVarA.a(this, str);
        uVarA.a();
    }

    void a(boolean z) {
        if (this.h) {
            return;
        }
        this.h = true;
        this.i = false;
        if (this.f != null) {
            this.f.dismiss();
            this.f = null;
        }
        this.g = true;
        if (this.e >= 0) {
            j().a(this.e, 1);
            this.e = -1;
            return;
        }
        u uVarA = j().a();
        uVarA.a(this);
        if (z) {
            uVarA.b();
        } else {
            uVarA.a();
        }
    }

    @Override // android.support.v4.app.Fragment
    public LayoutInflater b(Bundle bundle) {
        if (!this.d) {
            return super.b(bundle);
        }
        this.f = c(bundle);
        switch (this.a) {
            case 3:
                this.f.getWindow().addFlags(24);
            case 1:
            case 2:
                this.f.requestWindowFeature(1);
                break;
        }
        return this.f != null ? (LayoutInflater) this.f.getContext().getSystemService("layout_inflater") : (LayoutInflater) this.C.getSystemService("layout_inflater");
    }

    @Override // android.support.v4.app.Fragment
    public void b() {
        super.b();
        if (this.i || this.h) {
            return;
        }
        this.h = true;
    }

    public Dialog c(Bundle bundle) {
        return new Dialog(h(), a());
    }

    @Override // android.support.v4.app.Fragment
    public void c() {
        super.c();
        if (this.f != null) {
            this.g = false;
            this.f.show();
        }
    }

    @Override // android.support.v4.app.Fragment
    public void d() {
        super.d();
        if (this.f != null) {
            this.f.hide();
        }
    }

    @Override // android.support.v4.app.Fragment
    public void d(Bundle bundle) {
        Bundle bundle2;
        super.d(bundle);
        if (this.d) {
            View viewM = m();
            if (viewM != null) {
                if (viewM.getParent() != null) {
                    throw new IllegalStateException("DialogFragment can not be attached to a container view");
                }
                this.f.setContentView(viewM);
            }
            this.f.setOwnerActivity(h());
            this.f.setCancelable(this.c);
            this.f.setOnCancelListener(this);
            this.f.setOnDismissListener(this);
            if (bundle == null || (bundle2 = bundle.getBundle("android:savedDialogState")) == null) {
                return;
            }
            this.f.onRestoreInstanceState(bundle2);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void e() {
        super.e();
        if (this.f != null) {
            this.g = true;
            this.f.dismiss();
            this.f = null;
        }
    }

    @Override // android.support.v4.app.Fragment
    public void e(Bundle bundle) {
        Bundle bundleOnSaveInstanceState;
        super.e(bundle);
        if (this.f != null && (bundleOnSaveInstanceState = this.f.onSaveInstanceState()) != null) {
            bundle.putBundle("android:savedDialogState", bundleOnSaveInstanceState);
        }
        if (this.a != 0) {
            bundle.putInt("android:style", this.a);
        }
        if (this.b != 0) {
            bundle.putInt("android:theme", this.b);
        }
        if (!this.c) {
            bundle.putBoolean("android:cancelable", this.c);
        }
        if (!this.d) {
            bundle.putBoolean("android:showsDialog", this.d);
        }
        if (this.e != -1) {
            bundle.putInt("android:backStackId", this.e);
        }
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        if (this.g) {
            return;
        }
        a(true);
    }
}
