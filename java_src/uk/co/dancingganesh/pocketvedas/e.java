package uk.co.dancingganesh.pocketvedas;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/* loaded from: classes.dex */
class e extends android.support.v4.d.a {
    final /* synthetic */ BookmarksActivity j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(BookmarksActivity bookmarksActivity, Context context, Cursor cursor, int i) {
        super(context, cursor, i);
        this.j = bookmarksActivity;
    }

    @Override // android.support.v4.d.a
    public View a(Context context, Cursor cursor, ViewGroup viewGroup) {
        View viewInflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.bookmark_item, (ViewGroup) null);
        a(viewInflate, context, cursor);
        return viewInflate;
    }

    @Override // android.support.v4.d.a
    public void a(View view, Context context, Cursor cursor) {
        ImageView imageView = (ImageView) view.findViewById(R.id.bookmark_icon);
        if (cursor.getInt(5) != 0) {
            imageView.setImageResource(R.drawable.bookmark_star);
        } else {
            imageView.setImageResource(R.drawable.bookmark_plain);
        }
        ((TextView) view.findViewById(R.id.bookmark_verse)).setText(cursor.getString(2));
        ((TextView) view.findViewById(R.id.bkmk_description)).setText(cursor.getString(3));
    }
}
