package com.robin.robin_wanandroid.di.module;


import com.robin.robin_wanandroid.activity.LoginActivity;
import com.robin.robin_wanandroid.activity.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class AllActivitysModule {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity contributeMainActivitytInjector();

    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity contributeLoginActivitytInjector();

}
