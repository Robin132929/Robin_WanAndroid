package com.robin.robin_wanandroid.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.robin.rbase.CommonBase.Activity.BaseActivity;
import com.robin.rbase.CommonBase.Cache.Cache;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.mvp.ui.WanAndroid.CollectFragment;

public class SlidingMenuDetailActivity extends BaseActivity {
    public static int TYPE_COLLECT=0x0001;
    private static int TYPE_SETTING=0x0002;
    private static int TYPE_ABOUT=0x0003;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        Fragment fragment=null;
        Intent intent= getIntent();
        int type=intent.getIntExtra("type",0);
        if (type==TYPE_COLLECT){
            fragment=new CollectFragment();
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.slide_menu_detail_content_fl,fragment,"collect");
        ft.commit();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_sliding_menu_detail;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public boolean useFragment() {
        return true;
    }
}
