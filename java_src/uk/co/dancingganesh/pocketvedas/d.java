package uk.co.dancingganesh.pocketvedas;

import android.database.Cursor;
import android.os.AsyncTask;
import android.widget.ListView;

/* loaded from: classes.dex */
class d extends AsyncTask {
    final /* synthetic */ BookmarksActivity a;
    private final /* synthetic */ ListView b;

    d(BookmarksActivity bookmarksActivity, ListView listView) {
        this.a = bookmarksActivity;
        this.b = listView;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Cursor doInBackground(String... strArr) {
        return uk.co.dancingganesh.pocketvedas.a.b.a().b();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Cursor cursor) {
        super.onPostExecute(cursor);
        this.a.b.dismiss();
        this.a.setListAdapter(new e(this.a, this.a, cursor, 0));
        this.b.postInvalidate();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onProgressUpdate(Integer... numArr) {
        super.onProgressUpdate(numArr);
        this.a.b.show();
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        super.onPreExecute();
        this.a.b.show();
    }
}
