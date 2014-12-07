package com.cs420.unrmaps;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.cs420.unrmaps.R;

public class SettingsFragment extends PreferenceFragment {
    public SettingsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }
}
