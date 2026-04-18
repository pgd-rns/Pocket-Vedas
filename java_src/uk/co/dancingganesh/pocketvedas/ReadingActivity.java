package uk.co.dancingganesh.pocketvedas;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

/* loaded from: classes.dex */
public class ReadingActivity extends android.support.v4.app.h {
    private o m;
    private ViewPager n;

    public o f() {
        return this.m;
    }

    @Override // android.support.v4.app.h, android.app.Activity
    @SuppressLint({"NewApi"})
    public void onCreate(Bundle bundle) {
        uk.co.dancingganesh.pocketvedas.a.c.a(this);
        uk.co.dancingganesh.pocketvedas.a.b.a(this);
        super.onCreate(bundle);
        setContentView(R.layout.activity_reading);
        this.n = (ViewPager) findViewById(R.id.pager);
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("uk.co.dancingganesh.pocketvedas.BOOK_ADDRESS");
        if (stringExtra == null) {
            stringExtra = intent.getData().toString();
            if (stringExtra.startsWith("veda:")) {
                stringExtra = stringExtra.substring(5);
                if (stringExtra.startsWith("//")) {
                    stringExtra = stringExtra.substring(2);
                }
            }
        }
        this.m = new o(e(), stringExtra, intent.getStringExtra("uk.co.dancingganesh.pocketvedas.SEARCH_TEXT"), intent.getFloatExtra("uk.co.dancingganesh.pocketvedas.BOOKMARK_OFFSET", 0.0f), intent.getIntExtra("uk.co.dancingganesh.pocketvedas.BOOKMARK_ROWID", 0), this, bundle);
        this.n.setAdapter(this.m);
        this.n.setCurrentItem(this.m.c());
        if (Build.VERSION.SDK_INT >= 11) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override // android.app.Activity
    @SuppressLint({"NewApi"})
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_reading, menu);
        if (Build.VERSION.SDK_INT < 11) {
            return true;
        }
        ((SearchView) menu.findItem(R.id.menu_search).getActionView()).setSearchableInfo(((SearchManager) getSystemService("search")).getSearchableInfo(getComponentName()));
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intent intent;
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                String strD = this.m.d();
                if (strD != null) {
                    intent = new Intent(this, (Class<?>) ReadingActivity.class);
                    intent.putExtra("uk.co.dancingganesh.pocketvedas.BOOK_ADDRESS", strD);
                } else {
                    intent = new Intent(this, (Class<?>) BooksActivity.class);
                }
                startActivity(intent);
                finish();
                return true;
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
