package com.robin.robin_wanandroid.delegate;

import android.app.Application;
import android.content.Context;

import com.robin.rbase.BuildConfig;
import com.robin.rbase.CommonBase.App.ConfigModule;
import com.robin.rbase.CommonBase.delegate.AppLifecycles;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.di.module.ClientModule;
import com.robin.rbase.MVP.di.module.GlobalConfigModule;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

public class GlobalConfiguration implements ConfigModule {
    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlobalConfigModule.Builder builder) {
        builder.baseurl("https://www.wanandroid.com/")
                .globalHttpHandler(new GlobaHttpHandlerImpl())
                .responseErrorListener(new ResponseErrorListenerImpl())
                .okhttpConfiguration(new ClientModule.OkhttpConfiguration() {
                    @Override
                    public void configOkhttp(@NonNull Context context, @NonNull OkHttpClient.Builder builder) {

                        RetrofitUrlManager.getInstance().with(builder);
                        RetrofitUrlManager.getInstance().putDomain("gank", "http://gank.io/");
                        RetrofitUrlManager.getInstance().putDomain("readhub", "https://api.readhub.cn/");

                    }
                })

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
    lifecycles.add(new FragmentLifecycleCallbacksImpl());
    }
}
