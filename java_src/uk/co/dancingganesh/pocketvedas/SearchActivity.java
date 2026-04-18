package uk.co.dancingganesh.pocketvedas;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public class SearchActivity extends ListActivity {
    private static final Map c = new HashMap();
    private ProgressDialog a;
    private String b;

    static {
        c.put((char) 241, 'n');
        c.put((char) 256, 'A');
        c.put((char) 257, 'a');
        c.put((char) 298, 'I');
        c.put((char) 299, 'i');
        c.put((char) 346, 'S');
        c.put((char) 347, 's');
        c.put((char) 362, 'U');
        c.put((char) 363, 'u');
        c.put((char) 8212, '-');
        c.put((char) 7749, 'n');
        c.put((char) 7745, 'm');
        c.put((char) 7745, 'm');
        c.put((char) 7745, 'm');
        c.put((char) 7692, 'D');
        c.put((char) 7770, 'R');
        c.put((char) 7778, 'S');
        c.put((char) 7788, 'T');
        c.put((char) 7693, 'd');
        c.put((char) 7717, 'h');
        c.put((char) 7735, 'l');
        c.put((char) 7747, 'm');
        c.put((char) 7751, 'n');
        c.put((char) 7771, 'r');
        c.put((char) 7779, 's');
        c.put((char) 7789, 't');
    }

    public static String a(String str) {
        Iterator it = c.keySet().iterator();
        while (it.hasNext()) {
            char cCharValue = ((Character) it.next()).charValue();
            str = str.replace(cCharValue, ((Character) c.get(Character.valueOf(cCharValue))).charValue());
        }
        return str;
    }

    public static String a(String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        String upperCase = a(str).toUpperCase();
        String upperCase2 = str2.toUpperCase();
        int length = upperCase2.length();
        int i = 0;
        int length2 = 0;
        int i2 = 0;
        while (true) {
            int iIndexOf = upperCase.indexOf(upperCase2, i2);
            if (iIndexOf == -1) {
                stringBuffer.append(str.substring(length2));
                return stringBuffer.toString();
            }
            String strSubstring = str.substring(length2, (iIndexOf - i2) + length2);
            stringBuffer.append(strSubstring);
            int length3 = length2 + strSubstring.length();
            int i3 = 0;
            int i4 = 0;
            for (int i5 = i2; i5 < iIndexOf; i5++) {
                if (upperCase.charAt(i5) == '<') {
                    i4++;
                } else if (upperCase.charAt(i5) == '>') {
                    i3++;
                }
            }
            boolean z = i4 + i == i3;
            i += i4 - i3;
            if (z) {
                stringBuffer.append("<DIV CLASS=\"highlight\">");
            }
            String strSubstring2 = str.substring(length3, length3 + length);
            stringBuffer.append(strSubstring2);
            if (z) {
                stringBuffer.append("</DIV>");
            }
            length2 = strSubstring2.length() + length3;
            i2 = iIndexOf + length;
        }
    }

    public static String b(String str, String str2) {
        int iIndexOf;
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            if (i < str2.length() && str2.charAt(i) == ' ') {
                i++;
            } else {
                if (i == str2.length()) {
                    break;
                }
                if (str2.charAt(i) != '\"' || (iIndexOf = str2.indexOf(34, i + 1)) == -1) {
                    int iIndexOf2 = str2.indexOf(32, i);
                    if (iIndexOf2 != -1) {
                        int iIndexOf3 = str2.indexOf(42, i);
                        arrayList.add((iIndexOf3 == -1 || iIndexOf3 >= iIndexOf2) ? str2.substring(i, iIndexOf2) : str2.substring(i, iIndexOf3));
                        i = iIndexOf2 + 1;
                    } else {
                        int iIndexOf4 = str2.indexOf(42, i);
                        arrayList.add(iIndexOf4 != -1 ? str2.substring(i, iIndexOf4) : str2.substring(i));
                    }
                } else {
                    arrayList.add(str2.substring(i + 1, iIndexOf));
                    i = iIndexOf + 1;
                }
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            str = a(str, (String) it.next());
        }
        return str;
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        uk.co.dancingganesh.pocketvedas.a.c.a(this);
        uk.co.dancingganesh.pocketvedas.a.b.a(this);
        super.onCreate(bundle);
        setContentView(R.layout.activity_search);
        Intent intent = getIntent();
        if ("android.intent.action.SEARCH".equals(intent.getAction())) {
            this.b = intent.getStringExtra("query");
            this.a = new ProgressDialog(this);
            this.a.setMessage("Searching");
            this.a.setIndeterminate(true);
            this.a.setProgressStyle(0);
            new p(this).execute(a(this.b));
        }
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_search, menu);
        return true;
    }

    @Override // android.app.ListActivity
    public void onListItemClick(ListView listView, View view, int i, long j) {
        int i2 = ((Cursor) getListAdapter().getItem(i)).getInt(0);
        Intent intent = new Intent(this, (Class<?>) ReadingActivity.class);
        intent.putExtra("uk.co.dancingganesh.pocketvedas.BOOK_ADDRESS", uk.co.dancingganesh.pocketvedas.a.c.a().a(i2, true));
        intent.putExtra("uk.co.dancingganesh.pocketvedas.SEARCH_TEXT", this.b);
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
            case R.id.menu_bookmarks /* 2131296273 */:
                startActivity(new Intent(getBaseContext(), (Class<?>) BookmarksActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
