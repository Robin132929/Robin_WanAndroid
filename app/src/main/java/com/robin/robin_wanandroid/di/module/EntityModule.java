package com.robin.robin_wanandroid.di.module;


import com.robin.robin_wanandroid.ui.wanandroid.explore.NavigationFragment;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class EntityModule {
    @Provides
    static NavigationFragment provideNavigationFragment(){
        return null;
    }
}
