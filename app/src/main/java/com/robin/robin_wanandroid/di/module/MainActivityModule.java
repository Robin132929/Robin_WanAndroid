package com.robin.robin_wanandroid.di.module;

import android.app.Application;
import android.content.Context;


import com.robin.rbase.CommonBase.App.BaseApplication;
import com.robin.rbase.MVP.integration.IRepositoryManager;
import com.robin.rbase.MVP.integration.RepositoryManager;
import com.robin.robin_wanandroid.MainActivity;
import com.robin.robin_wanandroid.mvp.contract.MainContract;
import com.robin.robin_wanandroid.mvp.model.MainModel;
import com.robin.robin_wanandroid.mvp.model.bean.MainArticleBean;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.listener.ResponseErrorListener;

@Module
abstract class MainActivityModule {

    @Provides
    static  String provideabc(){
        return "abcqqqqqqqqqq";
    }

    @Provides
    static MainArticleBean provideDatebean(){
        return new MainArticleBean();

    }

    @Provides
    static MainContract.View provideView(MainActivity activity) {
        return activity;
    }
//    @Binds
//    abstract MainContract.Model bindUserModel(MainModel model);
    @Provides
static MainContract.Model provideModel(MainModel homeModel) {
    return homeModel;
}
//
//    @Binds
//    abstract IRepositoryManager bindRepositoryManagers(RepositoryManager repositoryManager);
////    @Provides
//    static RxErrorHandler provideRxError(){
//        return  RxErrorHandler
//                .builder()
//                .with()
//                .responseErrorListener(new ResponseErrorListener() {
//                    @Override
//                    public void handleResponseError(Context context, Throwable t) {
//
//                    }
//                })
//                .build();
//    }
//    @Binds
//    @Named("ActivityLifecycle")
//    abstract Application.ActivityLifecycleCallbacks bindActivityLifecycle(abc activityLifecycle);
//
//    @Binds
//    @Named("ActivityLifecycleForRxLifecycle")
//    abstract Application.ActivityLifecycleCallbacks bindActivityLifecycleForRxLifecycle(abcd activityLifecycleForRxLifecycle);

}
