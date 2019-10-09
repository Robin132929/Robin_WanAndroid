package com.robin.robin_wanandroid.di.module;

import com.robin.robin_wanandroid.mvp.ui.HomeFragment;
import com.robin.robin_wanandroid.mvp.ui.KnowledgeStructureFragment;
import com.robin.robin_wanandroid.mvp.ui.MainFragment;
import com.robin.robin_wanandroid.mvp.ui.NavigationFragment;
import com.robin.robin_wanandroid.mvp.ui.ProjectFragment;
import com.robin.robin_wanandroid.mvp.ui.WechatFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AllFragmentsModule {
    @ContributesAndroidInjector(modules = HomeFragmentModule.class)
    abstract HomeFragment contributeHometInjector();

    @ContributesAndroidInjector(modules = NavgationFragmentModule.class)
    abstract NavigationFragment contributeNavtInjector();

    @ContributesAndroidInjector(modules =  MainFragmentModule.class)
    abstract MainFragment contributeMaintInjector();

    @ContributesAndroidInjector(modules =  KnowledgeStructureModule.class)
    abstract KnowledgeStructureFragment contributeKnowledgeStructureInjector();

    @ContributesAndroidInjector(modules =  WechatFragmentModule.class)
    abstract WechatFragment contributeWechatInjector();

    @ContributesAndroidInjector(modules =  ProjectFragmentModule.class)
    abstract ProjectFragment contributeProjectInjector();
}
