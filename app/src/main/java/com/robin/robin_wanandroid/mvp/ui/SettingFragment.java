package com.robin.robin_wanandroid.mvp.ui;

import android.opengl.Visibility;
import android.os.Bundle;

import com.robin.robin_wanandroid.R;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

public class SettingFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.setting_preferences,rootKey);
        setHasOptionsMenu(true);
        findPreference("auto_nightMode").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                return true;
            }
        });
    }

}
