package com.robin.rbase.CommonBase.App;

import android.app.Application;
import android.content.Context;
import com.robin.rbase.CommonBase.delegate.Impl.AppDelegate;
import com.robin.rbase.CommonBase.delegate.AppLifecycles;
import com.robin.rbase.MVP.di.module.GlobalConfigModule;

import java.util.List;


/**
 * ================================================
 * 基类Application 可根据需求继承该类并扩展
 * ================================================
 */
public class BaseApplication extends Application  {
    private AppLifecycles mAppDelegate;
    /**
     * 这里会在 {@link BaseApplication#onCreate} 之前被调用,可以做一些较早的初始化
     * 常用于 MultiDex 以及插件化框架的初始化
     *
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        if (mAppDelegate == null)
            this.mAppDelegate = new AppDelegate(base);
        this.mAppDelegate.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (mAppDelegate != null)
            this.mAppDelegate.onCreate(this);
    }

    /**
     * 在模拟环境中程序终止时会被调用
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mAppDelegate != null)
            this.mAppDelegate.onTerminate(this);
    }

    public static GlobalConfigModule getGlobalConfigModule(Context context, List<ConfigModule> modules) {
        GlobalConfigModule.Builder builder = GlobalConfigModule
                .builder();

        //遍历 ConfigModule 集合, 给全局配置 GlobalConfigModule 添加参数
        for (ConfigModule module : modules) {
            module.applyOptions(context, builder);
        }

        return builder.build();
    }
}
