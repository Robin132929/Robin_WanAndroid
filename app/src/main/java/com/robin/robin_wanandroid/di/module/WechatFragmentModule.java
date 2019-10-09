package com.robin.robin_wanandroid.di.module;

import com.robin.robin_wanandroid.mvp.contract.WechatContract;
import com.robin.robin_wanandroid.mvp.model.WechatModel;
import com.robin.robin_wanandroid.mvp.ui.WechatFragment;

import dagger.Module;
import dagger.Provides;

@Module
abstract class WechatFragmentModule {
    @Provides
    static WechatContract.View provideView(WechatFragment fragment) {
        return fragment;
    }

    @Provides
    static  WechatContract.Model provideModel(WechatModel Model) {
        return Model;
    }
}
