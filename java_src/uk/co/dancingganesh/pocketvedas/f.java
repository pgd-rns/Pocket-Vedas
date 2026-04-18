package uk.co.dancingganesh.pocketvedas;

import android.os.AsyncTask;
import android.widget.GridView;

/* loaded from: classes.dex */
class f extends AsyncTask {
    final /* synthetic */ BooksActivity a;
    private final /* synthetic */ j b;
    private final /* synthetic */ GridView c;

    f(BooksActivity booksActivity, j jVar, GridView gridView) {
        this.a = booksActivity;
        this.b = jVar;
        this.c = gridView;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public String doInBackground(String... strArr) {
        uk.co.dancingganesh.pocketvedas.a.c.a(this.a);
        uk.co.dancingganesh.pocketvedas.a.b.a(this.a);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(String str) {
        super.onPostExecute(str);
        this.a.a.dismiss();
        this.b.notifyDataSetChanged();
        this.c.postInvalidate();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onProgressUpdate(Integer... numArr) {
        super.onProgressUpdate(numArr);
        this.a.a.show();
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        super.onPreExecute();
        this.a.a.show();
    }
}
