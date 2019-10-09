package com.robin.robin_wanandroid.di.module;

import com.robin.robin_wanandroid.mvp.contract.NavgationContract;
import com.robin.robin_wanandroid.mvp.model.NavgationModel;
import com.robin.robin_wanandroid.mvp.ui.NavigationFragment;

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
