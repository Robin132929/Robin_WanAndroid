package com.robin.robin_wanandroid.di.module;

import com.robin.robin_wanandroid.mvp.ui.WanAndroid.HomeFragment;
import com.robin.robin_wanandroid.mvp.ui.WanAndroid.KnowledgeStructureFragment;
import com.robin.robin_wanandroid.mvp.ui.WanAndroid.MainFragment;
import com.robin.robin_wanandroid.mvp.ui.WanAndroid.NavigationFragment;
import com.robin.robin_wanandroid.mvp.ui.WanAndroid.ProjectFragment;
import com.robin.robin_wanandroid.mvp.ui.WanAndroid.WechatFragment;
import com.robin.robin_wanandroid.mvp.ui.gank.GankMainFragment;
import com.robin.robin_wanandroid.mvp.ui.readhub.ReadhubMainFragment;

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

    @ContributesAndroidInjector(modules =  GankAndroidModule.class)
    abstract GankMainFragment contributeGankAndroidInjector();

    @ContributesAndroidInjector(modules =  ReadhubMainFragmentModule.class)
    abstract ReadhubMainFragment contributeReadhubMaintInjector();
}
