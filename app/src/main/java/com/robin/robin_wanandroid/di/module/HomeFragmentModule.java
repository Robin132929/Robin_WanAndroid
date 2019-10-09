package com.robin.robin_wanandroid.di.module;

import com.robin.robin_wanandroid.mvp.contract.HomeContract;
import com.robin.robin_wanandroid.mvp.model.HomeModel;
import com.robin.robin_wanandroid.mvp.ui.HomeFragment;

import dagger.Module;
import dagger.Provides;

@Module
abstract class HomeFragmentModule {
    @Provides
    static HomeContract.View provideView(HomeFragment fragment) {
        return fragment;
    }

    @Provides
    static HomeContract.Model provideModel(HomeModel homeModel) {
        return homeModel;
    }
}
