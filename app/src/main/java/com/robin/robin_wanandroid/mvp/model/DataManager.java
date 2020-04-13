package com.robin.robin_wanandroid.mvp.model;

import com.robin.robin_wanandroid.mvp.model.db.DbHelper;
import com.robin.robin_wanandroid.mvp.model.db.DbHelperImpl;
import com.robin.robin_wanandroid.mvp.model.http.HttpHelper;
import com.robin.robin_wanandroid.mvp.model.http.HttpHelperImpl;
import com.robin.robin_wanandroid.mvp.model.pref.PreHelperImpl;
import com.robin.robin_wanandroid.mvp.model.pref.PrefHelper;

public class DataManager implements HttpHelper , DbHelper, PrefHelper {
    private HttpHelper mHttpHelper;
    private DbHelper mDbHelper;
    private PrefHelper mPreferenceHelper;

    public DataManager(HttpHelper mHttpHelper, DbHelper mDbHelper, PrefHelper mPreferenceHelper) {
        this.mHttpHelper = mHttpHelper;
        this.mDbHelper = mDbHelper;
        this.mPreferenceHelper = mPreferenceHelper;
    }

    private static class INSTANCE{
        private static DataManager dataManager=new DataManager(new HttpHelperImpl(),new DbHelperImpl(),new PreHelperImpl());
    }
    public static DataManager getInstance(){
        return INSTANCE.dataManager;
    }
}
