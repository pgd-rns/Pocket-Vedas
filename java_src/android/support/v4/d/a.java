package android.support.v4.d;

import android.content.Context;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.FilterQueryProvider;
import android.widget.Filterable;

/* loaded from: classes.dex */
public abstract class a extends BaseAdapter implements f, Filterable {
    protected boolean a;
    protected boolean b;
    protected Cursor c;
    protected Context d;
    protected int e;
    protected c f;
    protected DataSetObserver g;
    protected e h;
    protected FilterQueryProvider i;

    public a(Context context, Cursor cursor, int i) {
        a(context, cursor, i);
    }

    @Override // android.support.v4.d.f
    public Cursor a() {
        return this.c;
    }

    @Override // android.support.v4.d.f
    public Cursor a(CharSequence charSequence) {
        return this.i != null ? this.i.runQuery(charSequence) : this.c;
    }

    public abstract View a(Context context, Cursor cursor, ViewGroup viewGroup);

    void a(Context context, Cursor cursor, int i) {
        if ((i & 1) == 1) {
            i |= 2;
            this.b = true;
        } else {
            this.b = false;
        }
        boolean z = cursor != null;
        this.c = cursor;
        this.a = z;
        this.d = context;
        this.e = z ? cursor.getColumnIndexOrThrow("_id") : -1;
        if ((i & 2) == 2) {
            this.f = new c(this);
            this.g = new d(this);
        } else {
            this.f = null;
            this.g = null;
        }
        if (z) {
            if (this.f != null) {
                cursor.registerContentObserver(this.f);
            }
            if (this.g != null) {
                cursor.registerDataSetObserver(this.g);
            }
        }
    }

    @Override // android.support.v4.d.f
    public void a(Cursor cursor) {
        Cursor cursorB = b(cursor);
        if (cursorB != null) {
            cursorB.close();
        }
    }

    public abstract void a(View view, Context context, Cursor cursor);

    public Cursor b(Cursor cursor) {
        if (cursor == this.c) {
            return null;
        }
        Cursor cursor2 = this.c;
        if (cursor2 != null) {
            if (this.f != null) {
                cursor2.unregisterContentObserver(this.f);
            }
            if (this.g != null) {
                cursor2.unregisterDataSetObserver(this.g);
            }
        }
        this.c = cursor;
        if (cursor == null) {
            this.e = -1;
            this.a = false;
            notifyDataSetInvalidated();
            return cursor2;
        }
        if (this.f != null) {
            cursor.registerContentObserver(this.f);
        }
        if (this.g != null) {
            cursor.registerDataSetObserver(this.g);
        }
        this.e = cursor.getColumnIndexOrThrow("_id");
        this.a = true;
        notifyDataSetChanged();
        return cursor2;
    }

    public View b(Context context, Cursor cursor, ViewGroup viewGroup) {
        return a(context, cursor, viewGroup);
    }

    protected void b() {
        if (!this.b || this.c == null || this.c.isClosed()) {
            return;
        }
        this.a = this.c.requery();
    }

    @Override // android.support.v4.d.f
    public CharSequence c(Cursor cursor) {
        return cursor == null ? "" : cursor.toString();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (!this.a || this.c == null) {
            return 0;
        }
        return this.c.getCount();
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        if (!this.a) {
            return null;
        }
        this.c.moveToPosition(i);
        if (view == null) {
            view = b(this.d, this.c, viewGroup);
        }
        a(view, this.d, this.c);
        return view;
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        if (this.h == null) {
            this.h = new e(this);
        }
        return this.h;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        if (!this.a || this.c == null) {
            return null;
        }
        this.c.moveToPosition(i);
        return this.c;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        if (this.a && this.c != null && this.c.moveToPosition(i)) {
            return this.c.getLong(this.e);
        }
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (!this.a) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
        if (!this.c.moveToPosition(i)) {
            throw new IllegalStateException("couldn't move cursor to position " + i);
        }
        if (view == null) {
            view = a(this.d, this.c, viewGroup);
        }
        a(view, this.d, this.c);
        return view;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }
}
