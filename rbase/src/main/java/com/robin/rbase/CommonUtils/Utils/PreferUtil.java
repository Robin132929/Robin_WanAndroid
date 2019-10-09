package com.robin.rbase.CommonUtils.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.robin.rbase.BuildConfig;

public class PreferUtil {

    private static final String PREF_DEFAULT_FILE = "Robin_"+ BuildConfig.APPLICATION_ID;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;


    public static void persist(Context context, String key, Object obj) {
        sharedPreferences = context.getSharedPreferences(PREF_DEFAULT_FILE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        put(key, obj);
        editor.apply();
    }

    public static Object get(Context context, String key, Object defValue) {
        sharedPreferences = context.getSharedPreferences(PREF_DEFAULT_FILE,
                Context.MODE_PRIVATE);
        return getValue(key, defValue);

    }

    private static void put(String key, Object object) {
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
    }

    private static Object getValue(String key, Object defaultObject) {
        if (defaultObject instanceof String) {
            return sharedPreferences.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sharedPreferences.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sharedPreferences.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sharedPreferences.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sharedPreferences.getLong(key, (Long) defaultObject);
        } else {
            return sharedPreferences.getString(key, null);
        }
    }

        public static boolean contains(Context context, String key) {
        try {
            SharedPreferences sp = context.getSharedPreferences(PREF_DEFAULT_FILE, Context.MODE_PRIVATE);
            return sp.contains(key);
        } catch (Exception e) {
            return false;
        }
    }

}
