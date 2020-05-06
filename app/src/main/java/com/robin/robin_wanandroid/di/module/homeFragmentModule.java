package com.robin.robin_wanandroid.di.module;

import com.robin.robin_wanandroid.mvp.contract.wanandroid.HomeContract;
import com.robin.robin_wanandroid.ui.wanandroid.home.fragment.WanAndroidHomeFragment;

import dagger.Module;
import dagger.Provides;

@Module
abstract class homeFragmentModule {
    @Provides
    static HomeContract.View provideView(WanAndroidHomeFragment fragment) {
        return fragment;
    }
}
