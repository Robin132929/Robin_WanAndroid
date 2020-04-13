package com.robin.robin_wanandroid.di.module;

import com.robin.robin_wanandroid.mvp.contract.wanandroid.HomeContract;
import com.robin.robin_wanandroid.mvp.model.wanandroid.HomeModel;
import com.robin.robin_wanandroid.ui.home.fragment.HomeFragment;

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
