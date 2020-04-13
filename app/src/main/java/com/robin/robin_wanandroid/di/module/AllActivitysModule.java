package com.robin.robin_wanandroid.di.module;


import com.robin.robin_wanandroid.ui.content.ContentActivity;
import com.robin.robin_wanandroid.ui.conveniententrance.activity.ConvenientEntranceActivity;
import com.robin.robin_wanandroid.ui.login.LoginActivity;
import com.robin.robin_wanandroid.ui.home.activty.MainActivity;
import com.robin.robin_wanandroid.activity.SlidingMenuDetailActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class AllActivitysModule {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity contributeMainActivitytInjector();

    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity contributeLoginActivitytInjector();

    @ContributesAndroidInjector(modules = ContentActivityModule.class)
    abstract ContentActivity contributeContentActivitytInjector();

    @ContributesAndroidInjector(modules = SlidingMenuDetailActivityModule.class)
    abstract SlidingMenuDetailActivity contributeSlidingMenuDetailActivitytInjector();

//    @ContributesAndroidInjector(modules = HomeConvenientButtonContentActivityModule.class)
//    abstract ConvenientEntranceActivity contributeHomeConvenientButtonContentActivityInjector();
}
