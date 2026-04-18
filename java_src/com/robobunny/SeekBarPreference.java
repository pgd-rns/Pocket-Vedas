package com.robobunny;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.Preference;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import uk.co.dancingganesh.pocketvedas.R;

/* loaded from: classes.dex */
public class SeekBarPreference extends Preference implements SeekBar.OnSeekBarChangeListener {
    private final String a;
    private int b;
    private int c;
    private int d;
    private int e;
    private String f;
    private String g;
    private SeekBar h;
    private TextView i;

    public SeekBarPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = getClass().getName();
        this.b = 100;
        this.c = 0;
        this.d = 1;
        this.f = "";
        this.g = "";
        a(context, attributeSet);
    }

    public SeekBarPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = getClass().getName();
        this.b = 100;
        this.c = 0;
        this.d = 1;
        this.f = "";
        this.g = "";
        a(context, attributeSet);
    }

    private String a(AttributeSet attributeSet, String str, String str2, String str3) {
        String attributeValue = attributeSet.getAttributeValue(str, str2);
        return attributeValue == null ? str3 : attributeValue;
    }

    private void a(Context context, AttributeSet attributeSet) {
        a(attributeSet);
        setWidgetLayoutResource(R.layout.seek_bar_preference);
    }

    private void a(AttributeSet attributeSet) {
        this.b = attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "max", 100);
        this.c = attributeSet.getAttributeIntValue("http://robobunny.com", "min", 0);
        this.f = a(attributeSet, "http://robobunny.com", "unitsLeft", "");
        this.g = a(attributeSet, "http://robobunny.com", "unitsRight", a(attributeSet, "http://robobunny.com", "units", ""));
        try {
            String attributeValue = attributeSet.getAttributeValue("http://robobunny.com", "interval");
            if (attributeValue != null) {
                this.d = Integer.parseInt(attributeValue);
            }
        } catch (Exception e) {
            Log.e(this.a, "Invalid interval value", e);
        }
    }

    protected void a(View view) {
        try {
            this.i = (TextView) view.findViewById(R.id.seekBarPrefValue);
            this.i.setText(String.valueOf(this.e));
            this.i.setMinimumWidth(30);
            this.h.setProgress(this.e - this.c);
            ((TextView) view.findViewById(R.id.seekBarPrefUnitsRight)).setText(this.g);
            ((TextView) view.findViewById(R.id.seekBarPrefUnitsLeft)).setText(this.f);
        } catch (Exception e) {
            Log.e(this.a, "Error updating seek bar preference", e);
        }
    }

    @Override // android.preference.Preference
    public void onBindView(View view) {
        super.onBindView(view);
        try {
            this.h = (SeekBar) view.findViewById(R.id.seekBarPrefSeekBar);
            this.h.setMax(this.b - this.c);
            this.h.setOnSeekBarChangeListener(this);
        } catch (Exception e) {
            Log.e(this.a, "Error binding view: " + e.toString());
        }
        if (view != null && !view.isEnabled()) {
            this.h.setEnabled(false);
        }
        a(view);
    }

    @Override // android.preference.Preference
    protected View onCreateView(ViewGroup viewGroup) {
        View viewOnCreateView = super.onCreateView(viewGroup);
        ((LinearLayout) viewOnCreateView).setOrientation(1);
        return viewOnCreateView;
    }

    @Override // android.preference.Preference
    public void onDependencyChanged(Preference preference, boolean z) {
        super.onDependencyChanged(preference, z);
        if (this.h != null) {
            this.h.setEnabled(!z);
        }
    }

    @Override // android.preference.Preference
    protected Object onGetDefaultValue(TypedArray typedArray, int i) {
        return Integer.valueOf(typedArray.getInt(i, 50));
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        int iRound = this.c + i;
        if (iRound > this.b) {
            iRound = this.b;
        } else if (iRound < this.c) {
            iRound = this.c;
        } else if (this.d != 1 && iRound % this.d != 0) {
            iRound = Math.round(iRound / this.d) * this.d;
        }
        if (!callChangeListener(Integer.valueOf(iRound))) {
            seekBar.setProgress(this.e - this.c);
            return;
        }
        this.e = iRound;
        this.i.setText(String.valueOf(iRound));
        persistInt(iRound);
    }

    @Override // android.preference.Preference
    protected void onSetInitialValue(boolean z, Object obj) {
        int iIntValue;
        if (z) {
            this.e = getPersistedInt(this.e);
            return;
        }
        try {
            iIntValue = ((Integer) obj).intValue();
        } catch (Exception e) {
            Log.e(this.a, "Invalid default value: " + obj.toString());
            iIntValue = 0;
        }
        persistInt(iIntValue);
        this.e = iIntValue;
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
        notifyChanged();
    }

    @Override // android.preference.Preference
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.h.setEnabled(z);
    }
}
