package com.robin.robin_wanandroid.di.module;

import com.robin.robin_wanandroid.ui.wanandroid.explore.ExploreFragment;

import dagger.Module;
import dagger.Provides;

@Module
abstract class WanAndroidExploreFragmentModule {

        @Provides
        static ExploreFragment provideView(ExploreFragment fragment) {
            return fragment;
        }
}
