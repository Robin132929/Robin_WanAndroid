package com.robin.robin_wanandroid.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.robin.rbase.CommonBase.App.BaseApplication;
import com.robin.rbase.CommonBase.App.ConfigModule;
import com.robin.rbase.CommonBase.Cache.IntelligentCache;
import com.robin.rbase.CommonBase.utils.ManifestParser;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.CommonUtils.Utils.ContextUtil;
import com.robin.rbase.MVP.di.module.GlobalConfigModule;
import com.robin.robin_wanandroid.delegate.GlobalConfiguration;
import com.robin.robin_wanandroid.di.DaggerMyAppComponent;
import com.robin.robin_wanandroid.di.MyAppComponent;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.platform.Platform;


public class App extends BaseApplication implements HasAndroidInjector{
    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;
    private static MyAppComponent mMyAppComponent;

    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public void onCreate() {

        super.onCreate();
        mMyAppComponent.extras().put(IntelligentCache.getKeyOfKeep(ConfigModule.class.getName()), getmAppDelegate());
        Logger.i("extra : "+ mMyAppComponent.extras().toString()+"  "+System.currentTimeMillis());
        ContextUtil.init(this);
    }
    @Override
    protected void attachBaseContext(Context base) {

       mMyAppComponent = DaggerMyAppComponent.builder().
               application(this)
               .globalConfigModule(getGlobalConfigModule(this,new GlobalConfiguration()))
               .build();
       mMyAppComponent.inject(this);
        super.attachBaseContext(base);
    }

    public static MyAppComponent getmMyAppComponent(){
        if (mMyAppComponent!=null){
            return mMyAppComponent;
        }
        return null;
    }
    public static GlobalConfigModule getGlobalConfigModule(Context context, ConfigModule modules) {
        GlobalConfigModule.Builder builder = GlobalConfigModule
                .builder();

        //遍历 ConfigModule 集合, 给全局配置 GlobalConfigModule 添加参数
//        for (ConfigModule module : modules) {
            modules.applyOptions(context, builder);
//        }

        return builder.build();
    }
}
