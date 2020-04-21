package com.robin.robin_wanandroid.app;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.robin.rbase.CommonBase.App.BaseApplication;
import com.robin.rbase.CommonBase.App.ConfigModule;
import com.robin.rbase.CommonBase.Cache.IntelligentCache;
import com.robin.rbase.CommonUtils.Utils.ContextUtil;
import com.robin.rbase.MVP.di.module.GlobalConfigModule;
import com.robin.robin_wanandroid.ui.login.LoginActivity;
import com.robin.robin_wanandroid.delegate.GlobalConfiguration;
import com.robin.robin_wanandroid.di.DaggerMyAppComponent;
import com.robin.robin_wanandroid.di.MyAppComponent;
import com.robin.robin_wanandroid.util.Login.LoginHelper;
import com.robin.robin_wanandroid.util.Login.RLogin;
import com.robin.robin_wanandroid.util.loading.Gloading;
import com.robin.robin_wanandroid.util.loading.GloadingAdapter;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

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
        //??? 操作什么作用
        mMyAppComponent.extras().put(IntelligentCache.getKeyOfKeep(ConfigModule.class.getName()), getmAppDelegate());
        ContextUtil.init(this);

        LoginHelper.getInstance().init(this,new RLogin() {
            @Override
            public boolean login(Context context) {
                Intent intent = new Intent(context, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                LoginActivity.startLogin(context, intent);
                return true;
            }
        });

        Gloading.initDefault(new GloadingAdapter());
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
