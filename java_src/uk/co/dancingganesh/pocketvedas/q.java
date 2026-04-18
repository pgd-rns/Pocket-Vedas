package uk.co.dancingganesh.pocketvedas;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/* loaded from: classes.dex */
class q extends android.support.v4.d.a {
    final /* synthetic */ SearchActivity j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q(SearchActivity searchActivity, Context context, Cursor cursor, int i) {
        super(context, cursor, i);
        this.j = searchActivity;
    }

    @Override // android.support.v4.d.a
    public View a(Context context, Cursor cursor, ViewGroup viewGroup) {
        View viewInflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.search_item, (ViewGroup) null);
        a(viewInflate, context, cursor);
        return viewInflate;
    }

    @Override // android.support.v4.d.a
    public void a(View view, Context context, Cursor cursor) {
        int i = 0;
        TextView textView = (TextView) view.findViewById(R.id.verse_name);
        int i2 = cursor.getInt(0);
        String string = cursor.getString(1);
        textView.setText(uk.co.dancingganesh.pocketvedas.a.c.a().a(i2, false));
        TextView textView2 = (TextView) view.findViewById(R.id.match_found);
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            int iIndexOf = string.indexOf("<b>", i);
            if (iIndexOf == -1) {
                break;
            }
            stringBuffer.append(string.substring(i, iIndexOf));
            int iIndexOf2 = string.indexOf("</b>", iIndexOf);
            stringBuffer.append(string.substring(iIndexOf + 3, iIndexOf2).toUpperCase());
            i = iIndexOf2 + 4;
        }
        if (i < string.length()) {
            stringBuffer.append(string.substring(i));
        }
        textView2.setText(stringBuffer.toString());
    }
}
