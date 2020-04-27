package com.robin.robin_wanandroid.activity.ui;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.robin.rbase.CommonBase.Activity.BaseActivity;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.ui.wanandroid.fragment.WanAndroidMainFragment;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Main5Activity extends BaseActivity {


    @BindView(R.id.fl_content_container)
    FrameLayout flContentContainer;
    //    @BindView(R.id.bottom_navigation_view)
//    BottomNavigationView bottomNavigationView;
//    @BindView(R.id.float_action_btn)
//    FloatingActionButton floatActionBtn;
//    @BindView(R.id.cl_wanandroid_content)
//    CoordinatorLayout clWanandroidContent;


    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fl_content_container, new WanAndroidMainFragment());
        ft.commit();

    }

    @Override
    public int getLayoutId() {
        return R.layout.fl_container;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public boolean useFragment() {
        return true;
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // TODO: add setContentView(...) invocation
//        ButterKnife.bind(this);
//    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // TODO: add setContentView(...) invocation
//        ButterKnife.bind(this);
//    }
}
