package com.robin.robin_wanandroid.di.module;

import com.robin.rbase.MVP.integration.IRepositoryManager;
import com.robin.rbase.MVP.integration.RepositoryManager;
import com.robin.robin_wanandroid.mvp.model.common.DataManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public  class RobinAppModule {
    @Singleton
    @Provides
    DataManager provider(RepositoryManager repositoryManager){
        return new DataManager(repositoryManager);
    }
}
