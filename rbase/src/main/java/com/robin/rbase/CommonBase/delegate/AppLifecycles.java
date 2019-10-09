package com.robin.rbase.CommonBase.delegate;

import android.app.Application;
import android.content.Context;

import com.robin.rbase.CommonBase.delegate.Impl.AppDelegate;

import androidx.annotation.NonNull;

/**
 * ================================================
 * 用于代理 {@link Application} 的生命周期
 *
 * @see AppDelegate
 * ================================================
 */
public interface AppLifecycles {
    void attachBaseContext(@NonNull Context base);

    void onCreate(@NonNull Application application);

    void onTerminate(@NonNull Application application);
}
