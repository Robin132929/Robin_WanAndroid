package com.robin.robin_wanandroid.di.module;

import com.robin.robin_wanandroid.mvp.contract.ProjectContract;
import com.robin.robin_wanandroid.mvp.model.ProjectModel;
import com.robin.robin_wanandroid.mvp.ui.ProjectFragment;

import dagger.Module;
import dagger.Provides;

@Module
abstract class ProjectFragmentModule {
    @Provides
    static ProjectContract.View provideView(ProjectFragment fragment) {
        return fragment;
    }

    @Provides
    static  ProjectContract.Model provideModel(ProjectModel Model) {
        return Model;
    }
}
