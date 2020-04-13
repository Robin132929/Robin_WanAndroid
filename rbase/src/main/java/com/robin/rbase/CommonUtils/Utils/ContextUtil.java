package com.robin.rbase.CommonUtils.Utils;

import android.app.Application;
import android.content.Context;

import com.robin.rbase.CommonBase.delegate.App;
import com.robin.rbase.CommonUtils.Logger.Logger;

public class ContextUtil {

    private static Context globalAppContext;

    public synchronized static void init(Context context) {
        if (context != null) {
            if (context instanceof Application) {
                globalAppContext = context;
            } else {
                globalAppContext = context.getApplicationContext();
            }
        }
    }

    public synchronized static Context getAppContext() {
        if (globalAppContext == null) {
            Logger.i("globalAppContext is null");
        }
        return globalAppContext;
    }

}
