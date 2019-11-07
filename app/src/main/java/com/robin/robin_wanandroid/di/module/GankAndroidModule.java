package com.robin.robin_wanandroid.di.module;


import com.robin.robin_wanandroid.mvp.contract.gank.GankMainContract;
import com.robin.robin_wanandroid.mvp.model.gank.GankMainModel;
import com.robin.robin_wanandroid.mvp.ui.gank.GankMainFragment;

import dagger.Module;
import dagger.Provides;

@Module
abstract class GankAndroidModule {

    @Provides
    static GankMainContract.View provideView(GankMainFragment fragment) {
        return fragment;
    }

    @Provides
   static GankMainContract.Model provideModel(GankMainModel model) {
    return model;
}

}
