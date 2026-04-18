package uk.co.dancingganesh.pocketvedas;

import android.content.DialogInterface;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

/* loaded from: classes.dex */
class b implements DialogInterface.OnClickListener {
    final /* synthetic */ a a;
    private final /* synthetic */ View b;

    b(a aVar, View view) {
        this.a = aVar;
        this.b = view;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        EditText editText = (EditText) this.b.findViewById(R.id.bookmark_description);
        CheckBox checkBox = (CheckBox) this.b.findViewById(R.id.bookmark_dynamic);
        long jA = uk.co.dancingganesh.pocketvedas.a.b.a().a(this.a.W, editText.getText().toString(), checkBox.isChecked(), this.a.X);
        if (checkBox.isChecked()) {
            System.out.println("Track current page for bookmark " + jA);
            this.a.Y.c((int) jA);
        }
    }
}
