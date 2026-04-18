package uk.co.dancingganesh.pocketvedas;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.Iterator;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public class BookmarksActivity extends ListActivity {
    private static WeakHashMap a = new WeakHashMap();
    private ProgressDialog b;

    public BookmarksActivity() {
        a.put(this, false);
    }

    public static void a() {
        Iterator it = a.keySet().iterator();
        while (it.hasNext()) {
            ((e) ((ListView) ((BookmarksActivity) it.next()).findViewById(android.R.id.list)).getAdapter()).a(uk.co.dancingganesh.pocketvedas.a.b.a().b());
        }
    }

    @Override // android.app.Activity
    public boolean onContextItemSelected(MenuItem menuItem) {
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();
        switch (menuItem.getItemId()) {
            case R.id.remove_item /* 2131296276 */:
                uk.co.dancingganesh.pocketvedas.a.b.a().a(((Cursor) getListAdapter().getItem(adapterContextMenuInfo.position)).getInt(0));
                return true;
            default:
                return false;
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        uk.co.dancingganesh.pocketvedas.a.c.a(this);
        uk.co.dancingganesh.pocketvedas.a.b.a(this);
        super.onCreate(bundle);
        setContentView(R.layout.activity_bookmarks);
        ListView listView = (ListView) findViewById(android.R.id.list);
        registerForContextMenu(listView);
        this.b = new ProgressDialog(this);
        this.b.setMessage("Loading");
        this.b.setIndeterminate(true);
        this.b.setProgressStyle(0);
        new d(this, listView).execute("");
    }

    @Override // android.app.Activity, android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
        getMenuInflater().inflate(R.menu.bookmark_list_menu, contextMenu);
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_bookmarks, menu);
        return true;
    }

    @Override // android.app.ListActivity
    public void onListItemClick(ListView listView, View view, int i, long j) {
        Cursor cursor = (Cursor) getListAdapter().getItem(i);
        String string = cursor.getString(1);
        Float fValueOf = Float.valueOf(cursor.getFloat(4));
        Intent intent = new Intent(this, (Class<?>) ReadingActivity.class);
        intent.putExtra("uk.co.dancingganesh.pocketvedas.BOOK_ADDRESS", string);
        intent.putExtra("uk.co.dancingganesh.pocketvedas.BOOKMARK_OFFSET", fValueOf);
        if (cursor.getInt(5) != 0) {
            intent.putExtra("uk.co.dancingganesh.pocketvedas.BOOKMARK_ROWID", cursor.getInt(0));
        }
        startActivity(intent);
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
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
