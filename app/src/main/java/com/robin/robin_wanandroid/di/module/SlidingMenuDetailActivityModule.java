package com.robin.robin_wanandroid.di.module;


import com.robin.robin_wanandroid.activity.SlidingMenuDetailActivity;
import com.robin.robin_wanandroid.mvp.contract.SettingContract;
import com.robin.robin_wanandroid.mvp.model.SettingModel;

import dagger.Module;
import dagger.Provides;

@Module
 abstract class SlidingMenuDetailActivityModule {

 @Provides
 static  String provideabc(){
  return "abcqqqqqqqqqq";
 }
 @Provides
 static SettingContract.View provideView(SlidingMenuDetailActivity activity) {
  return activity;
 }
 //    @Binds
//    abstract MainContract.Model bindUserModel(MainModel model);
 @Provides
 static SettingContract.Model provideModel(SettingModel homeModel) {
  return homeModel;
 }
}
