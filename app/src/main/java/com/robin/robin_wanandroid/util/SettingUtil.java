package com.robin.robin_wanandroid.util;

import android.content.SharedPreferences;

import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.app.App;

import androidx.preference.PreferenceManager;

public class SettingUtil {
    private static SharedPreferences setting = PreferenceManager.getDefaultSharedPreferences(App.getmMyAppComponent().application());

    public static boolean isNoPhoto(){
        return setting.getBoolean("switch_noPhotoMode",false);
    }

    public static boolean isAutoNightMode(){
        return setting.getBoolean("auto_nightMode",false);
    }

    public static int getColor(){
        return  setting.getInt("theme_color", R.color.TURQUOISE);
    }

    public static void setColor(int color){
        setting.edit().putInt("theme_color",color).apply();
    }
}
