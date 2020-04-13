package com.robin.robin_wanandroid.di.module;

import com.robin.robin_wanandroid.mvp.contract.wanandroid.CollectContract;
import com.robin.robin_wanandroid.mvp.model.wanandroid.CollectModel;
import com.robin.robin_wanandroid.ui.CollectFragment;

import dagger.Module;
import dagger.Provides;

@Module
abstract class CollectFragmentModule {
    @Provides
    static CollectContract.View provideView(CollectFragment fragment) {
        return fragment;
    }

    @Provides
    static CollectContract.Model provideModel(CollectModel homeModel) {
        return homeModel;
    }
}
