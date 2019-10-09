package com.robin.robin_wanandroid.di.module;

import com.robin.robin_wanandroid.mvp.contract.MainContract;
import com.robin.robin_wanandroid.mvp.model.MainModel;
import com.robin.robin_wanandroid.mvp.ui.MainFragment;

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
