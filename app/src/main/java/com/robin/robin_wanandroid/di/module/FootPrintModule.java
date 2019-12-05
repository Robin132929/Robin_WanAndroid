package com.robin.robin_wanandroid.di.module;


import com.robin.robin_wanandroid.mvp.contract.FootPrintContract;
import com.robin.robin_wanandroid.mvp.contract.gank.GankMainContract;
import com.robin.robin_wanandroid.mvp.model.FootPrintModel;
import com.robin.robin_wanandroid.mvp.model.gank.GankMainModel;
import com.robin.robin_wanandroid.mvp.ui.FootPrintFragment;
import com.robin.robin_wanandroid.mvp.ui.gank.GankMainFragment;
import com.robin.robin_wanandroid.rx.FootPrintEvent;

import dagger.Module;
import dagger.Provides;

@Module
abstract class FootPrintModule {

    @Provides
    static FootPrintContract.View provideView(FootPrintFragment fragment) {
        return fragment;
    }

    @Provides
   static FootPrintContract.Model provideModel(FootPrintModel model) {
    return model;
}

}
