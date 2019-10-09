package com.robin.robin_wanandroid.di.module;


import com.robin.robin_wanandroid.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class AllActivitysModule {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity contributeMainActivitytInjector();


}
