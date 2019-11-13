package com.robin.robin_wanandroid.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.robin.rbase.CommonBase.Activity.BaseActivity;
import com.robin.rbase.CommonBase.Cache.Cache;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.mvp.ui.SettingFragment;
import com.robin.robin_wanandroid.mvp.ui.WanAndroid.CollectFragment;

public class SlidingMenuDetailActivity extends BaseActivity {
    public static int TYPE_COLLECT=0x0001;
    private static int TYPE_SETTING=0x0002;
    private static int TYPE_ABOUT=0x0003;
    private Toolbar mToolbar;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
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
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Logger.i("type is "+fragment.getClass().getSimpleName());

        ft.replace(R.id.slide_menu_detail_content_fl,fragment);
        ft.show(fragment);
        ft.commit();
        mToolbar=findViewById(R.id.general_toolbar);
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
