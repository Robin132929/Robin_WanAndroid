package com.robin.robin_wanandroid.di.module;

import com.robin.robin_wanandroid.mvp.contract.wanandroid.NavgationContract;
import com.robin.robin_wanandroid.mvp.model.wanandroid.NavgationModel;
import com.robin.robin_wanandroid.mvp.ui.WanAndroid.NavigationFragment;

import dagger.Module;
import dagger.Provides;
@Module
abstract class NavgationFragmentModule {
    @Provides
    static NavgationContract.View provideView(NavigationFragment fragment) {
        return fragment;
    }

    @Provides
    static NavgationContract.Model provideModel(NavgationModel homeModel) {
        return homeModel;
    }
}
