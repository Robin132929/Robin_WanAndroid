package com.robin.robin_wanandroid.base;

import android.os.Bundle;

import com.robin.rbase.MVP.MvpBase.BaseMvpActivity;

public abstract class RobinBaseActivity extends BaseMvpActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        AndroidInjection.inject(this);  //一处声明，处处依赖注入
        super.onCreate(savedInstanceState);
    }

}
