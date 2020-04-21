package com.robin.robin_wanandroid.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import io.reactivex.functions.Consumer;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.base.RobinBaseActivity;
import com.robin.robin_wanandroid.mvp.contract.SettingContract;
import com.robin.robin_wanandroid.mvp.presenter.SettingPresenter;
import com.robin.robin_wanandroid.ui.FootPrintFragment;
import com.robin.robin_wanandroid.ui.SettingFragment;
import com.robin.robin_wanandroid.ui.CollectFragment;
import com.robin.robin_wanandroid.rx.ColorEvent;
import com.robin.robin_wanandroid.rx.RxBus;

public class SlidingMenuDetailActivity extends RobinBaseActivity<SettingPresenter> implements SettingContract.View {
    public static int TYPE_COLLECT=0x0001;
    private static int TYPE_SETTING=0x0002;
    private static int TYPE_ABOUT=0x0003;
    private static int TYPE_FOOTPRINT=0x0004;
    private Toolbar mToolbar;


    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        Logger.i("page type "+this);
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

    @Override
    public int getLayoutId() {
        return R.layout.activity_sliding_menu_detail;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setSupportActionBar( mToolbar);
        getSupportActionBar().setTitle(R.string.setting);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RxBus.getInstance().toObservable(this, ColorEvent.class).subscribe(new Consumer<ColorEvent>() {
            @Override
            public void accept(ColorEvent colorEvent) throws Exception {

//               int color= Color.valueOf(colorEvent.colorInt);
                initColor(getResources().getColor(colorEvent.colorInt));
            }
        });
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

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void initColor(int color) {
        super.initColor(color);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.i("page type "+this);

    }
}
