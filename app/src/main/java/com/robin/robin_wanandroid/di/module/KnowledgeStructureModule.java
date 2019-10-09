package com.robin.robin_wanandroid.di.module;

import com.robin.robin_wanandroid.mvp.contract.KnowledgeStructureContract;
import com.robin.robin_wanandroid.mvp.contract.NavgationContract;
import com.robin.robin_wanandroid.mvp.model.KnowledgeStructureModel;
import com.robin.robin_wanandroid.mvp.model.NavgationModel;
import com.robin.robin_wanandroid.mvp.ui.KnowledgeStructureFragment;
import com.robin.robin_wanandroid.mvp.ui.NavigationFragment;

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
