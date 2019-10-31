package com.robin.robin_wanandroid.di.module;

import com.robin.robin_wanandroid.mvp.contract.readhub.ReadhubMainContract;
import com.robin.robin_wanandroid.mvp.contract.wanandroid.MainContract;
import com.robin.robin_wanandroid.mvp.model.readhub.ReadhubMainModel;
import com.robin.robin_wanandroid.mvp.model.wanandroid.MainModel;
import com.robin.robin_wanandroid.mvp.ui.WanAndroid.MainFragment;
import com.robin.robin_wanandroid.mvp.ui.readhub.ReadhubMainFragment;

import dagger.Module;
import dagger.Provides;

@Module
abstract class ReadhubMainFragmentModule {
    @Provides
    static ReadhubMainContract.View provideView(ReadhubMainFragment fragment) {
        return fragment;
    }

    @Provides
    static ReadhubMainContract.Model provideModel(ReadhubMainModel homeModel) {
        return homeModel;
    }
}
