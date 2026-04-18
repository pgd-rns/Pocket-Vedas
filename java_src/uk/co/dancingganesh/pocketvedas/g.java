package uk.co.dancingganesh.pocketvedas;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

/* loaded from: classes.dex */
class g implements AdapterView.OnItemClickListener {
    final /* synthetic */ BooksActivity a;

    g(BooksActivity booksActivity) {
        this.a = booksActivity;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        Intent intent = new Intent(this.a, (Class<?>) ReadingActivity.class);
        intent.putExtra("uk.co.dancingganesh.pocketvedas.BOOK_ADDRESS", String.valueOf(uk.co.dancingganesh.pocketvedas.a.c.a().c(i)) + "/index");
        this.a.startActivity(intent);
    }
}
