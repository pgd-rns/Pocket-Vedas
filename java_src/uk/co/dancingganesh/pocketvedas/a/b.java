package uk.co.dancingganesh.pocketvedas.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import uk.co.dancingganesh.pocketvedas.BookmarksActivity;

/* loaded from: classes.dex */
public class b {
    private static b a = null;
    private SQLiteDatabase b;

    public b(Context context) {
        File file = new File(context.getExternalFilesDir(null), "bookmarks.db");
        if (!file.canRead()) {
            try {
                e.a(new BufferedInputStream(context.getAssets().open("raw/bookmarks.db")), new FileOutputStream(file.getAbsolutePath()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        this.b = SQLiteDatabase.openDatabase(file.getAbsolutePath(), null, 16);
    }

    public static b a() {
        return a;
    }

    public static synchronized void a(Context context) {
        if (a == null) {
            a = new b(context);
        }
    }

    public long a(String str, String str2, boolean z, float f) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("path", str);
        contentValues.put("name", c.b(str));
        contentValues.put("description", str2);
        contentValues.put("smart", Boolean.valueOf(z));
        contentValues.put("offset", Float.valueOf(f));
        long jInsert = this.b.insert("bookmark", null, contentValues);
        BookmarksActivity.a();
        return jInsert;
    }

    public void a(int i) {
        this.b.delete("BOOKMARK", "ROWID = ?", new String[]{new StringBuilder().append(i).toString()});
        BookmarksActivity.a();
    }

    public void a(long j, String str, float f) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("path", str);
        contentValues.put("name", c.b(str));
        contentValues.put("offset", Float.valueOf(f));
        this.b.update("bookmark", contentValues, "ROWID = ?", new String[]{new StringBuilder().append(j).toString()});
        BookmarksActivity.a();
    }

    public Cursor b() {
        Cursor cursorRawQuery = this.b.rawQuery("SELECT rowid _id, PATH, NAME, DESCRIPTION, OFFSET, SMART FROM BOOKMARK", null);
        cursorRawQuery.getCount();
        return cursorRawQuery;
    }
}
