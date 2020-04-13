package com.robin.robin_wanandroid.di.module;

import com.robin.robin_wanandroid.ui.CollectFragment;
import com.robin.robin_wanandroid.ui.FootPrintFragment;
import com.robin.robin_wanandroid.ui.gank.GankMainFragment;
import com.robin.robin_wanandroid.ui.home.fragment.HomeFragment;
import com.robin.robin_wanandroid.ui.home.fragment.KnowledgeStructureFragment;
import com.robin.robin_wanandroid.ui.home.fragment.MainFragment;
import com.robin.robin_wanandroid.ui.home.fragment.NavigationFragment;
import com.robin.robin_wanandroid.ui.home.fragment.ProjectFragment;
import com.robin.robin_wanandroid.ui.home.fragment.WechatFragment;
import com.robin.robin_wanandroid.ui.readhub.ReadhubMainFragment;


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

//    @ContributesAndroidInjector(modules =  SettingFragmentModule.class)
//    abstract SettingFragment contributeSettingFragmnetInjector();

    @ContributesAndroidInjector(modules =  CollectFragmentModule.class)
    abstract CollectFragment contributeCollectMaintInjector();

    @ContributesAndroidInjector(modules =  ReadhubMainFragmentModule.class)
    abstract ReadhubMainFragment contributeReadhubMaintInjector();

    @ContributesAndroidInjector(modules =  FootPrintModule.class)
    abstract FootPrintFragment contributeFootPrintInjector();


}
