package android.support.v4.c;

/* loaded from: classes.dex */
public class c {
    private static final Object a = new Object();
    private boolean b;
    private int[] c;
    private Object[] d;
    private int e;

    public c() {
        this(10);
    }

    public c(int i) {
        this.b = false;
        int iH = h(i);
        this.c = new int[iH];
        this.d = new Object[iH];
        this.e = 0;
    }

    private static int a(int[] iArr, int i, int i2, int i3) {
        int i4 = i - 1;
        int i5 = i + i2;
        while (i5 - i4 > 1) {
            int i6 = (i5 + i4) / 2;
            if (iArr[i6] < i3) {
                i4 = i6;
            } else {
                i5 = i6;
            }
        }
        return i5 == i + i2 ? (i + i2) ^ (-1) : iArr[i5] != i3 ? i5 ^ (-1) : i5;
    }

    private void c() {
        int i = this.e;
        int[] iArr = this.c;
        Object[] objArr = this.d;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != a) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    objArr[i2] = obj;
                }
                i2++;
            }
        }
        this.b = false;
        this.e = i2;
    }

    static int g(int i) {
        for (int i2 = 4; i2 < 32; i2++) {
            if (i <= (1 << i2) - 12) {
                return (1 << i2) - 12;
            }
        }
        return i;
    }

    static int h(int i) {
        return g(i * 4) / 4;
    }

    public int a() {
        if (this.b) {
            c();
        }
        return this.e;
    }

    public Object a(int i) {
        return a(i, null);
    }

    public Object a(int i, Object obj) {
        int iA = a(this.c, 0, this.e, i);
        return (iA < 0 || this.d[iA] == a) ? obj : this.d[iA];
    }

    public void b() {
        int i = this.e;
        Object[] objArr = this.d;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.e = 0;
        this.b = false;
    }

    public void b(int i) {
        int iA = a(this.c, 0, this.e, i);
        if (iA < 0 || this.d[iA] == a) {
            return;
        }
        this.d[iA] = a;
        this.b = true;
    }

    public void b(int i, Object obj) {
        int iA = a(this.c, 0, this.e, i);
        if (iA >= 0) {
            this.d[iA] = obj;
            return;
        }
        int iA2 = iA ^ (-1);
        if (iA2 < this.e && this.d[iA2] == a) {
            this.c[iA2] = i;
            this.d[iA2] = obj;
            return;
        }
        if (this.b && this.e >= this.c.length) {
            c();
            iA2 = a(this.c, 0, this.e, i) ^ (-1);
        }
        if (this.e >= this.c.length) {
            int iH = h(this.e + 1);
            int[] iArr = new int[iH];
            Object[] objArr = new Object[iH];
            System.arraycopy(this.c, 0, iArr, 0, this.c.length);
            System.arraycopy(this.d, 0, objArr, 0, this.d.length);
            this.c = iArr;
            this.d = objArr;
        }
        if (this.e - iA2 != 0) {
            System.arraycopy(this.c, iA2, this.c, iA2 + 1, this.e - iA2);
            System.arraycopy(this.d, iA2, this.d, iA2 + 1, this.e - iA2);
        }
        this.c[iA2] = i;
        this.d[iA2] = obj;
        this.e++;
    }

    public void c(int i) {
        b(i);
    }

    public void d(int i) {
        if (this.d[i] != a) {
            this.d[i] = a;
            this.b = true;
        }
    }

    public int e(int i) {
        if (this.b) {
            c();
        }
        return this.c[i];
    }

    public Object f(int i) {
        if (this.b) {
            c();
        }
        return this.d[i];
    }
}
