package com.robin.robin_wanandroid.di.module;


import com.robin.robin_wanandroid.activity.ContentActivity;
import com.robin.robin_wanandroid.mvp.contract.ContentContract;
import com.robin.robin_wanandroid.mvp.model.ContentModel;

import dagger.Module;
import dagger.Provides;

@Module
abstract class ContentActivityModule {

    @Provides
    static ContentContract.View provideView(ContentActivity activity) {
        return activity;
    }

    @Provides
    static ContentContract.Model provideModel(ContentModel model) {
        return model;
    }
}
