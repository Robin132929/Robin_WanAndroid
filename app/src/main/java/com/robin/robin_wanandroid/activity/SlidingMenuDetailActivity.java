package com.robin.robin_wanandroid.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.robin.rbase.CommonBase.Activity.BaseActivity;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.mvp.ui.FootPrintFragment;
import com.robin.robin_wanandroid.mvp.ui.SettingFragment;
import com.robin.robin_wanandroid.mvp.ui.CollectFragment;
import com.robin.robin_wanandroid.util.statusbarUtil.StatusBarUtil;

import java.util.Arrays;

public class SlidingMenuDetailActivity extends MyBaseActivity {
    public static int TYPE_COLLECT=0x0001;
    private static int TYPE_SETTING=0x0002;
    private static int TYPE_ABOUT=0x0003;
    private static int TYPE_FOOTPRINT=0x0004;
    private Toolbar mToolbar;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        int statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        Immersive();
        findViewById(R.id.sliding_ll).setPadding(0, statusBarHeight, 0, 0);
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(R.color.colorPrimary));
        Fragment fragment=null;
        Intent intent= getIntent();
        int type=intent.getIntExtra("type",0);
        Logger.i("type is "+type);
        if (type==TYPE_COLLECT){
            fragment=new CollectFragment();
        }
        if (type==TYPE_SETTING){
            Logger.i("type is "+type);
            fragment=new SettingFragment();
//            fragment= getSupportFragmentManager().getFragmentFactory().instantiate(this.getClassLoader(),SettingFragment.class.getName());
        }
        if (type==TYPE_FOOTPRINT){
            fragment=new FootPrintFragment();
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Logger.i("type is "+fragment.getClass().getSimpleName());

        ft.replace(R.id.slide_menu_detail_content_fl,fragment);
        ft.show(fragment);
        ft.commit();
        mToolbar=findViewById(R.id.general_toolbar);
    }
    private void Immersive() {
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
//        StatusBarUtil.setStatusBarColor(this,R.color.colorPrimary);

        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(this, 0x55000000);
        }
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_sliding_menu_detail;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setSupportActionBar( mToolbar);
        getSupportActionBar().setTitle(R.string.setting);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean useFragment() {
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
