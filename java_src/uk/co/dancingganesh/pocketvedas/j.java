package uk.co.dancingganesh.pocketvedas;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/* loaded from: classes.dex */
public class j extends BaseAdapter {
    private Context a;

    public j(Context context) {
        this.a = context;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        uk.co.dancingganesh.pocketvedas.a.c cVarA = uk.co.dancingganesh.pocketvedas.a.c.a();
        if (cVarA == null) {
            return 0;
        }
        return cVarA.b();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;
        if (view == null) {
            imageView = new ImageView(this.a);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) view;
        }
        imageView.setImageBitmap(uk.co.dancingganesh.pocketvedas.a.c.a().b(i));
        return imageView;
    }
}
