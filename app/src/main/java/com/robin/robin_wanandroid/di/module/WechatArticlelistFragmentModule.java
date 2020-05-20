package com.robin.robin_wanandroid.di.module;

import com.robin.robin_wanandroid.ui.wanandroid.explore.WechatArticleListFragment;

import dagger.Module;
import dagger.Provides;

@Module
abstract class WechatArticlelistFragmentModule {

        @Provides
        static WechatArticleListFragment provideView(WechatArticleListFragment fragment) {
            return fragment;
        }
}
