
package com.robin.robin_wanandroid.di;

import android.app.Activity;
import android.app.Application;
import android.content.Context;


import com.google.gson.Gson;
import com.robin.rbase.CommonBase.App.BaseApplication;
import com.robin.rbase.CommonBase.Cache.Cache;
import com.robin.rbase.CommonBase.utils.AppManager;
import com.robin.rbase.MVP.di.component.MvpAppComponent;
import com.robin.rbase.MVP.di.module.AppModule;
import com.robin.rbase.MVP.di.module.ClientModule;
import com.robin.rbase.MVP.di.module.GlobalConfigModule;
import com.robin.rbase.MVP.integration.IRepositoryManager;
import com.robin.robin_wanandroid.app.App;
import com.robin.robin_wanandroid.di.module.AllActivitysModule;
import com.robin.robin_wanandroid.di.module.AllFragmentsModule;

import java.io.File;
import java.util.concurrent.ExecutorService;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import okhttp3.OkHttpClient;

/**
 * ================================================
 *
 * ================================================
 */
//@Singleton
//@Component(modules = {AppModule.class, ClientModule.class})
//public interface MyAppComponent {
//    Application application();
//
//    /**
//     * 用于管理网络请求层, 以及数据缓存层
//     *
//     * @return {@link IRepositoryManager}
//     */
//    IRepositoryManager repositoryManager();
//
////    /**
////     * RxJava 错误处理管理类
////     *
////     * @return {@link RxErrorHandler}
////     */
////    RxErrorHandler rxErrorHandler();
//
////    /**
////     * 图片加载管理器, 用于加载图片的管理类, 使用策略者模式, 可在运行时动态替换任何图片加载框架
////     * arms-imageloader-glide 提供 Glide 的策略实现类, 也可以自行实现
////     * 需要在 {@link ConfigModule#applyOptions(Context, GlobalConfigModule.Builder)} 中
////     * 手动注册 {@link BaseImageLoaderStrategy}, {@link ImageLoader} 才能正常使用
////     *
////     * @return
////     */
////    ImageLoader imageLoader();
//
//    /**
//     * 网络请求框架
//     *
//     * @return {@link OkHttpClient}
//     */
//    OkHttpClient okHttpClient();
//
//    /**
//     * Json 序列化库
//     *
//     * @return {@link Gson}
//     */
//    Gson gson();
//
////    /**
////     * 缓存文件根目录 (RxCache 和 Glide 的缓存都已经作为子文件夹放在这个根目录下), 应该将所有缓存都统一放到这个根目录下
////     * 便于管理和清理, 可在 {@link ConfigModule#applyOptions(Context, GlobalConfigModule.Builder)} 种配置
////     *
////     * @return {@link File}
////     */
////    File cacheFile();
////
////    /**
////     * 用来存取一些整个 App 公用的数据, 切勿大量存放大容量数据, 这里的存放的数据和 {@link Application} 的生命周期一致
////     *
////     * @return {@link Cache}
////     */
////    Cache<String, Object> extras();
//
//    /**
//     * 用于创建框架所需缓存对象的工厂
//     *
//     * @return {@link Cache.Factory}
//     */
////    Cache.Factory cacheFactory();
//
//    /**
//     * 返回一个全局公用的线程池,适用于大多数异步需求。
//     * 避免多个线程池创建带来的资源消耗。
//     *
//     * @return {@link ExecutorService}
//     */
//    ExecutorService executorService();
//
//    void inject(AppDelegate delegate);
//
//    @Component.Builder
//    interface Builder {
//        @BindsInstance
//        Builder application(Application application);
//
////        Builder globalConfigModule(GlobalConfigModule globalConfigModule);
//
//        MyAppComponent build();
//    }
//}
@Singleton
@Component(modules = {AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class, AllActivitysModule.class, AppModule.class,
        ClientModule.class, GlobalConfigModule.class
, AllFragmentsModule.class})
public interface MyAppComponent {
    Application application();

    /**
     * 用于管理网络请求层, 以及数据缓存层
     *
     * @return {@link IRepositoryManager}
     */
    IRepositoryManager repositoryManager();

    /**
     * RxJava 错误处理管理类
     *
     * @return {@link RxErrorHandler}
     */
    RxErrorHandler rxErrorHandler();

    /**
     * 图片加载管理器, 用于加载图片的管理类, 使用策略者模式, 可在运行时动态替换任何图片加载框架
     * arms-imageloader-glide 提供 Glide 的策略实现类, 也可以自行实现
     * 需要在 {@link ConfigModule#applyOptions(Context, GlobalConfigModule.Builder)} 中
     * 手动注册 {@link BaseImageLoaderStrategy}, {@link ImageLoader} 才能正常使用
     *
     * @return
     */
//    ImageLoader imageLoader();

    /**
     * 网络请求框架
     *
     * @return {@link OkHttpClient}
     */
    OkHttpClient okHttpClient();

    /**
     * Json 序列化库
     *
     * @return {@link Gson}
     */
    Gson gson();

    /**
     * 缓存文件根目录 (RxCache 和 Glide 的缓存都已经作为子文件夹放在这个根目录下), 应该将所有缓存都统一放到这个根目录下
     * 便于管理和清理, 可在 {@link ConfigModule#applyOptions(Context, GlobalConfigModule.Builder)} 种配置
     *
     * @return {@link File}
     */
    File cacheFile();

    /**
     * 用来存取一些整个 App 公用的数据, 切勿大量存放大容量数据, 这里的存放的数据和 {@link Application} 的生命周期一致
     *
     * @return {@link Cache}
     */
    Cache<String, Object> extras();

    /**
     * 用于创建框架所需缓存对象的工厂
     *
     * @return {@link Cache.Factory}
     */
    Cache.Factory cacheFactory();

    /**
     * 返回一个全局公用的线程池,适用于大多数异步需求。
     * 避免多个线程池创建带来的资源消耗。
     *
     * @return {@link ExecutorService}
     */
    ExecutorService executorService();


    void inject(App delegate);
    @Component.Builder
    interface Builder {

        @BindsInstance
        MyAppComponent.Builder application(Application application);
        MyAppComponent.Builder globalConfigModule(GlobalConfigModule globalConfigModule);

        MyAppComponent build();
    }
}