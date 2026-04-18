package uk.co.dancingganesh.pocketvedas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes.dex */
public class a extends android.support.v4.app.e {
    private String W;
    private float X;
    private o Y;

    public void a(String str, float f) {
        this.W = str;
        this.X = f;
    }

    public void a(o oVar) {
        this.Y = oVar;
    }

    @Override // android.support.v4.app.e
    public Dialog c(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(h());
        View viewInflate = h().getLayoutInflater().inflate(R.layout.dialog_add_bookmark, (ViewGroup) null);
        builder.setView(viewInflate);
        builder.setTitle(R.string.add_bookmark);
        builder.setPositiveButton(R.string.ok, new b(this, viewInflate));
        builder.setNegativeButton(R.string.cancel, new c(this));
        return builder.create();
    }
}
