package com.robin.robin_wanandroid.di.module;


import com.robin.robin_wanandroid.ui.wanandroid.home.MainActivity;

import dagger.Module;
import dagger.Provides;

@Module
abstract class MainActivityModule1 {

    @Provides
    static MainActivity provideView(MainActivity activity) {
        return activity;
    }

}
