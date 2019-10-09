package com.robin.robin_wanandroid.delegate;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.robin.rbase.CommonUtils.Logger.Logger;

public class ActivtyLifeCycleImpl implements Application.ActivityLifecycleCallbacks {
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Logger.i("onActivityCreated1");
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
