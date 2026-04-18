package uk.co.dancingganesh.pocketvedas.a;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class c {
    private static c a = null;
    private SQLiteDatabase b;
    private String[] c = null;
    private Bitmap[] d = null;

    public c(Context context) throws Throwable {
        SQLiteDatabase sQLiteDatabase;
        Cursor cursor;
        boolean z;
        boolean z2;
        Cursor cursorQuery;
        File file = new File(context.getExternalFilesDir(null), "vedabase.db");
        if (file.canRead()) {
            try {
                try {
                    SQLiteDatabase sQLiteDatabaseOpenDatabase = SQLiteDatabase.openDatabase(file.getAbsolutePath(), null, 17);
                    try {
                        cursorQuery = sQLiteDatabaseOpenDatabase.query("VERSION", new String[]{"VERSION"}, null, null, null, null, null);
                    } catch (Throwable th) {
                        sQLiteDatabase = sQLiteDatabaseOpenDatabase;
                        th = th;
                        cursor = null;
                    }
                    try {
                        boolean z3 = !cursorQuery.moveToFirst() ? true : Integer.parseInt(new BufferedReader(new InputStreamReader(context.getAssets().open("raw/dbversion.txt"))).readLine()) > cursorQuery.getInt(0);
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        if (sQLiteDatabaseOpenDatabase != null) {
                            sQLiteDatabaseOpenDatabase.close();
                            z = z3;
                            z2 = false;
                        } else {
                            z = z3;
                            z2 = false;
                        }
                    } catch (Throwable th2) {
                        cursor = cursorQuery;
                        sQLiteDatabase = sQLiteDatabaseOpenDatabase;
                        th = th2;
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (sQLiteDatabase != null) {
                            sQLiteDatabase.close();
                        }
                        throw th;
                    }
                } catch (Exception e) {
                    z = true;
                    z2 = false;
                }
            } catch (Throwable th3) {
                th = th3;
                sQLiteDatabase = null;
                cursor = null;
            }
        } else {
            z2 = true;
            z = false;
        }
        if (z || z2) {
            if (z) {
                file.delete();
            }
            try {
                e.a(new BufferedInputStream(context.getAssets().open("raw/vedabase.db")), new FileOutputStream(file.getAbsolutePath()));
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        }
        this.b = SQLiteDatabase.openDatabase(file.getAbsolutePath(), null, 17);
    }

    /* JADX WARN: Code restructure failed: missing block: B:74:0x01dc, code lost:
    
        throw new java.lang.RuntimeException("Oops");
     */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0223  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private a a(String[] strArr, int i) throws Throwable {
        Cursor cursorQuery;
        Cursor cursorQuery2;
        int i2;
        Cursor cursor;
        String string;
        int i3;
        Cursor cursorQuery3;
        try {
            cursorQuery = this.b.query("BOOK", new String[]{"ROWID"}, "NAME=?", new String[]{strArr[0]}, null, null, null);
        } catch (Throwable th) {
            th = th;
            cursorQuery = null;
        }
        try {
            if (!cursorQuery.moveToFirst()) {
                throw new RuntimeException("Ooops, book=" + strArr[0]);
            }
            int i4 = cursorQuery.getInt(0);
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            StringBuffer stringBuffer = new StringBuffer(strArr[0]);
            int i5 = -1;
            if (i > 2 || (i == 2 && !strArr[1].equals("index"))) {
                try {
                    cursorQuery2 = this.b.query("DIVISION", new String[]{"ROWID"}, "NAME=? AND PARENT=0 AND BOOK=?", new String[]{"index", new StringBuilder().append(i4).toString()}, null, null, null);
                    try {
                        int i6 = cursorQuery2.moveToFirst() ? cursorQuery2.getInt(0) : 0;
                        if (cursorQuery2 != null) {
                            cursorQuery2.close();
                        }
                        i2 = i6;
                    } catch (Throwable th2) {
                        th = th2;
                        if (cursorQuery2 != null) {
                            cursorQuery2.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    cursorQuery2 = null;
                }
            } else {
                i2 = 0;
            }
            if (i > 2 && strArr[i - 1].equals("index")) {
                i--;
            }
            int i7 = 1;
            while (true) {
                int i8 = i7;
                int i9 = i5;
                i5 = i2;
                if (i8 >= i) {
                    return new a(i4, i5, i9, stringBuffer.toString());
                }
                String str = strArr[i8];
                try {
                    Cursor cursorQuery4 = this.b.query("DIVISION", new String[]{"ROWID"}, "NAME=? AND PARENT=? AND BOOK=?", new String[]{str, new StringBuilder().append(i5).toString(), new StringBuilder().append(i4).toString()}, null, null, null);
                    try {
                        if (cursorQuery4.moveToFirst()) {
                            i2 = cursorQuery4.getInt(0);
                            string = str;
                        } else {
                            int i10 = 0;
                            while (i10 < str.length() && Character.isDigit(str.charAt(i10))) {
                                i10++;
                            }
                            if (i10 <= 0 || (i3 = Integer.parseInt(str.substring(0, i10))) == 0) {
                                string = null;
                                i2 = -1;
                            } else {
                                try {
                                    cursorQuery3 = this.b.query("DIVISION", new String[]{"ROWID", "MAX(NAME)"}, "NAME<? AND PARENT=? AND BOOK=?", new String[]{new StringBuilder().append(i3 + 1).toString(), new StringBuilder().append(i5).toString(), new StringBuilder().append(i4).toString()}, null, null, null);
                                    try {
                                        if (cursorQuery3.moveToFirst()) {
                                            int i11 = cursorQuery3.getInt(0);
                                            string = cursorQuery3.getString(1);
                                            int iIndexOf = string.indexOf(45);
                                            if (iIndexOf != -1) {
                                                String strSubstring = string.substring(0, iIndexOf);
                                                String strSubstring2 = string.substring(iIndexOf + 1);
                                                if (Integer.parseInt(strSubstring) > i3 || Integer.parseInt(strSubstring2) < i3) {
                                                    string = null;
                                                    i11 = -1;
                                                }
                                                if (cursorQuery3 != null) {
                                                    cursorQuery3.close();
                                                    i2 = i11;
                                                } else {
                                                    i2 = i11;
                                                }
                                            }
                                        }
                                    } catch (Throwable th4) {
                                        th = th4;
                                        if (cursorQuery3 != null) {
                                            cursorQuery3.close();
                                        }
                                        throw th;
                                    }
                                } catch (Throwable th5) {
                                    th = th5;
                                    cursorQuery3 = null;
                                }
                            }
                        }
                        if (cursorQuery4 != null) {
                            cursorQuery4.close();
                        }
                        if (i2 == -1 || string == null) {
                            break;
                        }
                        stringBuffer.append("/").append(string);
                        i7 = i8 + 1;
                    } catch (Throwable th6) {
                        th = th6;
                        cursor = cursorQuery4;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                } catch (Throwable th7) {
                    th = th7;
                    cursor = null;
                }
            }
        } catch (Throwable th8) {
            th = th8;
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }

    public static c a() {
        return a;
    }

    public static synchronized void a(Context context) {
        if (a == null) {
            a = new c(context);
        }
    }

    public static String b(String str) {
        String[] strArrSplit = str.split("/");
        String str2 = "";
        int length = strArrSplit.length;
        boolean z = true;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return str2;
            }
            String str3 = strArrSplit[i];
            if (!str3.equals("index") || str2.length() == 0) {
                if (str3.charAt(0) < '0' || str3.charAt(0) > '9') {
                    z = false;
                }
                if (str2.length() <= 0) {
                    str2 = str3;
                    length = i;
                } else if (!z) {
                    str2 = String.valueOf(str3) + " " + str2;
                    length = i;
                } else if (i > 0) {
                    str2 = String.valueOf(str3) + "." + str2;
                    length = i;
                } else {
                    str2 = String.valueOf(str3) + "." + str2;
                    length = i;
                }
            } else {
                length = i;
            }
        }
    }

    public int a(String str, ArrayList arrayList) throws Throwable {
        Cursor cursorQuery;
        int size;
        Cursor cursor = null;
        try {
            cursorQuery = this.b.query("REDIRECTION", new String[]{"TARGET"}, "SOURCE=?", new String[]{str}, null, null, null);
        } catch (Throwable th) {
            th = th;
            cursorQuery = null;
        }
        try {
            if (cursorQuery.moveToFirst()) {
                str = cursorQuery.getString(0);
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            String[] strArrSplit = str.split("/");
            if (strArrSplit.length == 2 && strArrSplit[1].equals("index")) {
                b();
                String[] strArr = new String[2];
                strArr[1] = "index";
                size = -1;
                for (int i = 0; i < this.c.length; i++) {
                    strArr[0] = this.c[i];
                    if (strArr[0].equals(strArrSplit[0])) {
                        size = arrayList.size();
                    }
                    arrayList.add(a(strArr, 2));
                }
            } else {
                a aVarA = a(strArrSplit, strArrSplit.length);
                String strSubstring = aVarA.d().substring(0, aVarA.d().lastIndexOf(47) + 1);
                try {
                    Cursor cursorQuery2 = this.b.query("DIVISION", new String[]{"ROWID", "NAME"}, "PARENT=?", new String[]{new StringBuilder().append(aVarA.c()).toString()}, null, null, "SEQUENCE ASC");
                    if (!cursorQuery2.moveToFirst()) {
                        throw new RuntimeException("Ooops");
                    }
                    size = -1;
                    do {
                        int i2 = cursorQuery2.getInt(0);
                        String string = cursorQuery2.getString(1);
                        if (i2 == aVarA.b()) {
                            size = arrayList.size();
                        }
                        arrayList.add(new a(aVarA.a(), i2, aVarA.c(), String.valueOf(strSubstring) + string));
                    } while (cursorQuery2.moveToNext());
                    if (cursorQuery2 != null) {
                        cursorQuery2.close();
                    }
                } catch (Throwable th2) {
                    if (0 != 0) {
                        cursor.close();
                    }
                    throw th2;
                }
            }
            return size;
        } catch (Throwable th3) {
            th = th3;
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }

    public Cursor a(String str) {
        Cursor cursorRawQuery = this.b.rawQuery("SELECT rowid _id, SNIPPET(searching) FROM searching WHERE plain MATCH ?", new String[]{str});
        cursorRawQuery.getCount();
        return cursorRawQuery;
    }

    public String a(int i) throws Throwable {
        Cursor cursorQuery;
        try {
            cursorQuery = this.b.query("VERSE", new String[]{"TITLE"}, "ROWID=?", new String[]{new StringBuilder().append(i).toString()}, null, null, null);
            try {
                if (!cursorQuery.moveToFirst()) {
                    throw new RuntimeException("Ooops: div=" + i);
                }
                String string = cursorQuery.getString(0);
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return string;
            } catch (Throwable th) {
                th = th;
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            cursorQuery = null;
        }
    }

    public String a(int i, String str) throws Throwable {
        Cursor cursorQuery;
        try {
            cursorQuery = this.b.query("VERSE", new String[]{"CONTENT"}, "ROWID=?", new String[]{new StringBuilder().append(i).toString()}, null, null, null);
            try {
                if (!cursorQuery.moveToFirst()) {
                    throw new RuntimeException("Ooops: div=" + i);
                }
                String string = cursorQuery.getString(0);
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return string;
            } catch (Throwable th) {
                th = th;
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            cursorQuery = null;
        }
    }

    public String a(int i, boolean z) throws Throwable {
        Cursor cursor;
        Cursor cursorQuery;
        boolean z2;
        Cursor cursor2 = null;
        int i2 = 0;
        boolean z3 = true;
        String str = "";
        while (true) {
            if (i != 0) {
                try {
                    cursorQuery = this.b.query("DIVISION", new String[]{"NAME", "PARENT", "BOOK"}, "ROWID=?", new String[]{new StringBuilder().append(i).toString()}, null, null, null);
                } catch (Throwable th) {
                    th = th;
                    cursor = null;
                }
                try {
                    if (cursorQuery.moveToFirst()) {
                        String string = cursorQuery.getString(0);
                        i = cursorQuery.getInt(1);
                        int i3 = cursorQuery.getInt(2);
                        cursorQuery.close();
                        if (!string.equals("index") || str.length() == 0) {
                            z2 = (string.charAt(0) < '0' || string.charAt(0) > '9') ? false : z3;
                            if (str.length() > 0) {
                                string = z ? String.valueOf(string) + "/" + str : z2 ? String.valueOf(string) + "." + str : String.valueOf(string) + " " + str;
                            }
                        } else {
                            z2 = z3;
                            string = str;
                        }
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        i2 = i3;
                        z3 = z2;
                        str = string;
                    } else if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    cursor = cursorQuery;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
        }
        try {
            Cursor cursorQuery2 = this.b.query("BOOK", new String[]{"NAME"}, "ROWID=?", new String[]{new StringBuilder().append(i2).toString()}, null, null, null);
            if (!cursorQuery2.moveToFirst()) {
                throw new RuntimeException("Ooops");
            }
            String string2 = cursorQuery2.getString(0);
            String str2 = z ? String.valueOf(string2) + "/" + str : String.valueOf(string2) + " " + str;
            if (cursorQuery2 != null) {
                cursorQuery2.close();
            }
            return str2;
        } catch (Throwable th3) {
            if (0 != 0) {
                cursor2.close();
            }
            throw th3;
        }
    }

    public boolean a(int i, int i2, StringBuffer stringBuffer, StringBuffer stringBuffer2) throws Throwable {
        Cursor cursor;
        Cursor cursorRawQuery;
        try {
            cursorRawQuery = this.b.rawQuery("SELECT MIN(rowid) FROM division WHERE rowid > ? AND book = ? AND parent != ?", new String[]{new StringBuilder().append(i).toString(), new StringBuilder().append(i2).toString(), new StringBuilder().append(i).toString()});
        } catch (Throwable th) {
            th = th;
            cursor = null;
        }
        try {
            if (!cursorRawQuery.moveToFirst()) {
                if (cursorRawQuery == null) {
                    return false;
                }
                cursorRawQuery.close();
                return false;
            }
            int i3 = cursorRawQuery.getInt(0);
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            if (i3 > 0) {
                stringBuffer.append(a(i3));
                stringBuffer2.append(a(i3, true));
            }
            return true;
        } catch (Throwable th2) {
            th = th2;
            cursor = cursorRawQuery;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public int b() throws Throwable {
        Cursor cursorQuery;
        if (this.c == null) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            try {
                cursorQuery = this.b.query("BOOK", new String[]{"NAME", "COVER"}, null, null, null, null, "NAME");
            } catch (Throwable th) {
                th = th;
                cursorQuery = null;
            }
            try {
                if (!cursorQuery.moveToFirst()) {
                    throw new RuntimeException("Ooops");
                }
                do {
                    arrayList.add(cursorQuery.getString(0));
                    byte[] blob = cursorQuery.getBlob(1);
                    arrayList2.add(BitmapFactory.decodeByteArray(blob, 0, blob.length));
                } while (cursorQuery.moveToNext());
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                this.c = (String[]) arrayList.toArray(new String[arrayList.size()]);
                this.d = (Bitmap[]) arrayList2.toArray(new Bitmap[arrayList2.size()]);
            } catch (Throwable th2) {
                th = th2;
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                throw th;
            }
        }
        return this.c.length;
    }

    public Bitmap b(int i) {
        return this.d[i];
    }

    public String c(int i) {
        return this.c[i];
    }

    public boolean d(int i) throws Throwable {
        Cursor cursorQuery;
        try {
            cursorQuery = this.b.query("DIVISION", new String[]{"ROWID"}, "PARENT=?", new String[]{new StringBuilder().append(i).toString()}, null, null, null);
            try {
                if (cursorQuery.moveToFirst()) {
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return true;
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return false;
            } catch (Throwable th) {
                th = th;
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            cursorQuery = null;
        }
    }
}
