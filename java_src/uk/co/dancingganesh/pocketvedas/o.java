package uk.co.dancingganesh.pocketvedas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.t;
import android.view.ViewGroup;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class o extends t {
    private ArrayList a;
    private int b;
    private String c;
    private Float d;
    private ReadingActivity e;
    private uk.co.dancingganesh.pocketvedas.a.d f;
    private int g;
    private int h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(android.support.v4.app.l lVar, String str, String str2, float f, int i, ReadingActivity readingActivity, Bundle bundle) {
        super(lVar);
        boolean z = false;
        this.c = str2;
        this.d = Float.valueOf(f);
        this.g = i;
        this.e = readingActivity;
        this.a = new ArrayList();
        if (str.endsWith("+")) {
            str = str.substring(0, str.length() - 1);
            z = true;
        }
        this.b = uk.co.dancingganesh.pocketvedas.a.c.a().a(str, this.a);
        this.f = uk.co.dancingganesh.pocketvedas.a.d.a((uk.co.dancingganesh.pocketvedas.a.a) this.a.get(this.a.size() - 1));
        if (!z || this.f == null) {
            return;
        }
        this.b++;
    }

    @Override // android.support.v4.app.t
    public Fragment a(int i) {
        if (i >= this.a.size()) {
            h hVar = new h();
            hVar.a(this.f, String.valueOf(((uk.co.dancingganesh.pocketvedas.a.a) this.a.get(this.a.size() - 1)).d()) + '+');
            return hVar;
        }
        k kVar = new k();
        kVar.a(((uk.co.dancingganesh.pocketvedas.a.a) this.a.get(i)).b(), ((uk.co.dancingganesh.pocketvedas.a.a) this.a.get(i)).d());
        if (this.c != null) {
            kVar.a(this.c);
        }
        if (this.d != null) {
            kVar.a(this.d.floatValue());
            this.d = null;
        }
        return kVar;
    }

    public void a(int i, Intent intent) {
        if (this.g != 0) {
            if (i == 0 || uk.co.dancingganesh.pocketvedas.a.c.a().d(i)) {
                intent.putExtra("uk.co.dancingganesh.pocketvedas.BOOKMARK_ROWID", this.g);
                this.g = 0;
            }
        }
    }

    public void a(int i, String str, float f) {
        if (this.g == 0 || i != this.h) {
            return;
        }
        uk.co.dancingganesh.pocketvedas.a.b.a().a(this.g, str, f);
    }

    @Override // android.support.v4.view.x
    public int b() {
        return (this.f == null ? 0 : 1) + this.a.size();
    }

    @Override // android.support.v4.app.t, android.support.v4.view.x
    public void b(ViewGroup viewGroup, int i, Object obj) {
        super.b(viewGroup, i, obj);
        if (i < this.a.size()) {
            this.h = ((uk.co.dancingganesh.pocketvedas.a.a) this.a.get(i)).b();
            this.e.setTitle(uk.co.dancingganesh.pocketvedas.a.c.a().a(this.h));
        } else if (i == this.a.size()) {
            this.e.setTitle("Pocket Vedas");
            this.h = 0;
        }
    }

    public int c() {
        return this.b;
    }

    public void c(int i) {
        this.g = i;
    }

    public String d() {
        String[] strArrSplit = ((uk.co.dancingganesh.pocketvedas.a.a) this.a.get(0)).d().split("/");
        if (strArrSplit.length <= 2) {
            if (strArrSplit[1].equals("index")) {
                return null;
            }
            return String.valueOf(strArrSplit[0]) + "/index";
        }
        StringBuffer stringBuffer = new StringBuffer(strArrSplit[0]);
        for (int i = 1; i < strArrSplit.length - 1; i++) {
            stringBuffer.append("/").append(strArrSplit[i]);
        }
        return stringBuffer.toString();
    }
}
