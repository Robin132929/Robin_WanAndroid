package com.robin.robin_wanandroid.di.module;

import com.robin.robin_wanandroid.mvp.contract.wanandroid.MainContract;
import com.robin.robin_wanandroid.mvp.model.wanandroid.MainModel;
import com.robin.robin_wanandroid.mvp.ui.WanAndroid.MainFragment;

import dagger.Module;
import dagger.Provides;

@Module
abstract class MainFragmentModule {
    @Provides
    static MainContract.View provideView(MainFragment fragment) {
        return fragment;
    }

    @Provides
    static MainContract.Model provideModel(MainModel homeModel) {
        return homeModel;
    }
}
