package uk.co.dancingganesh.pocketvedas;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

/* loaded from: classes.dex */
public class SettingsActivity extends PreferenceActivity {
    private int a;
    private boolean b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;
    private boolean g;

    @Override // android.preference.PreferenceActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT < 19) {
            addPreferencesFromResource(R.xml.oldpreferences);
        } else {
            addPreferencesFromResource(R.xml.preferences);
        }
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.a = defaultSharedPreferences.getInt("pref_zoom", 133);
        this.b = defaultSharedPreferences.getBoolean("pref_text", true);
        this.c = defaultSharedPreferences.getBoolean("pref_synonyms", true);
        this.d = defaultSharedPreferences.getBoolean("pref_translation", true);
        this.e = defaultSharedPreferences.getBoolean("pref_purport", true);
        this.f = defaultSharedPreferences.getBoolean("pref_reverse", true);
        this.g = defaultSharedPreferences.getBoolean("pref_keep_awake", false);
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    public void onStop() throws Resources.NotFoundException {
        super.onStop();
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int i = this.a;
        if (Build.VERSION.SDK_INT >= 19) {
            i = defaultSharedPreferences.getInt("pref_zoom", 133);
        }
        boolean z = defaultSharedPreferences.getBoolean("pref_text", true);
        boolean z2 = defaultSharedPreferences.getBoolean("pref_synonyms", true);
        boolean z3 = defaultSharedPreferences.getBoolean("pref_translation", true);
        boolean z4 = defaultSharedPreferences.getBoolean("pref_purport", true);
        boolean z5 = defaultSharedPreferences.getBoolean("pref_reverse", true);
        boolean z6 = defaultSharedPreferences.getBoolean("pref_keep_awake", false);
        if (this.a == i && this.b == z && this.c == z2 && this.d == z3 && this.e == z4 && this.f == z5 && this.g == z6) {
            return;
        }
        k.a((k) null);
    }
}
