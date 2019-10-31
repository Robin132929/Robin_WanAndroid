package com.robin.robin_wanandroid.di.module;

import com.robin.robin_wanandroid.mvp.contract.wanandroid.WechatContract;
import com.robin.robin_wanandroid.mvp.model.wanandroid.WechatModel;
import com.robin.robin_wanandroid.mvp.ui.WanAndroid.WechatFragment;

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
