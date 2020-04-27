package com.robin.robin_wanandroid.di.module;


import com.robin.robin_wanandroid.mvp.contract.common.MainContract;
import com.robin.robin_wanandroid.mvp.model.bean.MainArticleBean;
import com.robin.robin_wanandroid.mvp.model.wanandroid.MainModel;
import com.robin.robin_wanandroid.ui.common.activty.MainActivity;
import com.robin.robin_wanandroid.ui.common.activty.WanAndroidMainActivity;
import com.robin.robin_wanandroid.ui.wanandroid.fragment.WanAndroidMainFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import dagger.Module;
import dagger.Provides;

@Module
abstract class MainActivityModule1 {

    @Provides
    static MainActivity provideView(MainActivity activity) {
        return activity;
    }

}
