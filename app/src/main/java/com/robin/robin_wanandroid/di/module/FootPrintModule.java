package com.robin.robin_wanandroid.di.module;


import com.robin.robin_wanandroid.mvp.contract.FootPrintContract;
import com.robin.robin_wanandroid.mvp.model.FootPrintModel;
import com.robin.robin_wanandroid.ui.wanandroid.history.FootPrintFragment;

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
