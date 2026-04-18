package android.support.v4.d;

import android.database.Cursor;
import android.widget.Filter;

/* loaded from: classes.dex */
class e extends Filter {
    f a;

    e(f fVar) {
        this.a = fVar;
    }

    @Override // android.widget.Filter
    public CharSequence convertResultToString(Object obj) {
        return this.a.c((Cursor) obj);
    }

    @Override // android.widget.Filter
    protected Filter.FilterResults performFiltering(CharSequence charSequence) {
        Cursor cursorA = this.a.a(charSequence);
        Filter.FilterResults filterResults = new Filter.FilterResults();
        if (cursorA != null) {
            filterResults.count = cursorA.getCount();
            filterResults.values = cursorA;
        } else {
            filterResults.count = 0;
            filterResults.values = null;
        }
        return filterResults;
    }

    @Override // android.widget.Filter
    protected void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
        Cursor cursorA = this.a.a();
        if (filterResults.values == null || filterResults.values == cursorA) {
            return;
        }
        this.a.a((Cursor) filterResults.values);
    }
}
