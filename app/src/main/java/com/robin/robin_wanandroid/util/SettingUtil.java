package com.robin.robin_wanandroid.util;

import android.content.SharedPreferences;

import com.robin.robin_wanandroid.app.App;

import androidx.preference.PreferenceManager;

public class SettingUtil {
    private static SharedPreferences setting = PreferenceManager.getDefaultSharedPreferences(App.getmMyAppComponent().application());

    public static boolean isNoPhoto(){
        return setting.getBoolean("switch_noPhotoMode",false);
    }
}
