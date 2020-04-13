package com.robin.robin_wanandroid.di.module;


import com.robin.robin_wanandroid.ui.login.LoginActivity;
import com.robin.robin_wanandroid.mvp.contract.wanandroid.LoginContract;
import com.robin.robin_wanandroid.mvp.model.wanandroid.LoginModel;

import dagger.Module;
import dagger.Provides;

@Module
abstract class LoginActivityModule {

    @Provides
    static LoginContract.View provideView(LoginActivity activity) {
        return activity;
    }

    @Provides
    static LoginContract.Model provideModel(LoginModel model) {
        return model;
    }
}
