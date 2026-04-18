package uk.co.dancingganesh.pocketvedas;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.SearchView;

/* loaded from: classes.dex */
public class BooksActivity extends Activity {
    private ProgressDialog a;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a = new ProgressDialog(this);
        this.a.setMessage("Unpacking books");
        this.a.setIndeterminate(true);
        this.a.setProgressStyle(0);
        setContentView(R.layout.activity_books);
        GridView gridView = (GridView) findViewById(R.id.gridView1);
        j jVar = new j(this);
        new f(this, jVar, gridView).execute("");
        gridView.setBackgroundColor(-12566464);
        gridView.setAdapter((ListAdapter) jVar);
        gridView.setOnItemClickListener(new g(this));
    }

    @Override // android.app.Activity
    @SuppressLint({"NewApi"})
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_books, menu);
        if (Build.VERSION.SDK_INT < 11) {
            return true;
        }
        ((SearchView) menu.findItem(R.id.menu_search).getActionView()).setSearchableInfo(((SearchManager) getSystemService("search")).getSearchableInfo(getComponentName()));
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_settings /* 2131296271 */:
                startActivity(new Intent(getBaseContext(), (Class<?>) SettingsActivity.class));
                return true;
            case R.id.menu_about /* 2131296272 */:
                startActivity(new Intent(getBaseContext(), (Class<?>) AboutActivity.class));
                return true;
            case R.id.menu_bookmarks /* 2131296273 */:
                startActivity(new Intent(getBaseContext(), (Class<?>) BookmarksActivity.class));
                return true;
            case R.id.menu_search /* 2131296274 */:
                return onSearchRequested();
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
