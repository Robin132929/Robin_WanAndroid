package com.robin.robin_wanandroid.delegate;

import android.app.Application;
import android.content.Context;

import com.robin.rbase.CommonBase.App.ConfigModule;
import com.robin.rbase.CommonBase.Lifecycle.FragmentLifecycle;
import com.robin.rbase.CommonBase.delegate.AppLifecycles;
import com.robin.rbase.MVP.di.module.GlobalConfigModule;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

public class GlobalConfiguration implements ConfigModule {
    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlobalConfigModule.Builder builder) {
        builder.baseurl("https://www.wanandroid.com/")
                .build();
    }

    @Override
    public void injectAppLifecycle(@NonNull Context context, @NonNull List<AppLifecycles> lifecycles) {
     lifecycles.add(new AppLifecyclesImpl());
    }

    @Override
    public void injectActivityLifecycle(@NonNull Context context, @NonNull List<Application.ActivityLifecycleCallbacks> lifecycles) {
lifecycles.add(new ActivtyLifeCycleImpl());
    }

    @Override
    public void injectFragmentLifecycle(@NonNull Context context, @NonNull List<FragmentManager.FragmentLifecycleCallbacks> lifecycles) {
    }
}
