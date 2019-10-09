package com.robin.robin_wanandroid.delegate;

import android.app.Application;
import android.content.Context;

import com.robin.rbase.CommonBase.delegate.AppLifecycles;

import androidx.annotation.NonNull;

public class AppLifecyclesImpl implements AppLifecycles {
   public static Application mApplication;

    @Override
    public void attachBaseContext(@NonNull Context base) {

    }

    @Override
    public void onCreate(@NonNull Application application) {
   mApplication=application;
    }

    @Override
    public void onTerminate(@NonNull Application application) {

    }
    public static Application getapp(){
        return mApplication;
    }
}
