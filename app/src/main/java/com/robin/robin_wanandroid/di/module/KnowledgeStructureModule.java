package com.robin.robin_wanandroid.di.module;

import com.robin.robin_wanandroid.mvp.contract.wanandroid.KnowledgeStructureContract;
import com.robin.robin_wanandroid.mvp.model.wanandroid.KnowledgeStructureModel;
import com.robin.robin_wanandroid.mvp.ui.WanAndroid.KnowledgeStructureFragment;

import dagger.Module;
import dagger.Provides;

@Module
abstract class KnowledgeStructureModule {
    @Provides
    static KnowledgeStructureContract.View provideView(KnowledgeStructureFragment fragment) {
        return fragment;
    }

    @Provides
    static  KnowledgeStructureContract.Model provideModel(KnowledgeStructureModel homeModel) {
        return homeModel;
    }
}
