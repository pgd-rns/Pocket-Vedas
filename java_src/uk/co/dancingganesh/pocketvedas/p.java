package uk.co.dancingganesh.pocketvedas;

import android.database.Cursor;
import android.os.AsyncTask;
import android.widget.ListView;

/* loaded from: classes.dex */
class p extends AsyncTask {
    final /* synthetic */ SearchActivity a;

    p(SearchActivity searchActivity) {
        this.a = searchActivity;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Cursor doInBackground(String... strArr) {
        return uk.co.dancingganesh.pocketvedas.a.c.a().a(strArr[0]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Cursor cursor) {
        super.onPostExecute(cursor);
        this.a.a.dismiss();
        this.a.setListAdapter(new q(this.a, this.a, cursor, 0));
        ((ListView) this.a.findViewById(android.R.id.list)).postInvalidate();
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
