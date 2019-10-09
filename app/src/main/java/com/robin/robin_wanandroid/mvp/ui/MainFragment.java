package com.robin.robin_wanandroid.mvp.ui;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.robin.rbase.MVP.MvpBase.BaseMvpFragment;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.adapter.myViewPagerAdapter;
import com.robin.robin_wanandroid.mvp.contract.MainContract;
import com.robin.robin_wanandroid.mvp.model.bean.MainArticleBean;
import com.robin.robin_wanandroid.mvp.model.bean.WeChatBean;
import com.robin.robin_wanandroid.mvp.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class MainFragment extends BaseMvpFragment<MainPresenter> implements MainContract.View {
    TabLayout tabLayout;
    ViewPager viewPager;


    List<Fragment> fragments = new ArrayList<>();
    List<String> titles = new ArrayList<>();

    public MainFragment() {
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public View initView(@NonNull View view, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        tabLayout=view.findViewById(R.id.tl_tabs);
        viewPager=view.findViewById(R.id.vp_content);
        viewPager.setAdapter(new myViewPagerAdapter(new WeChatBean(),getChildFragmentManager(),1));

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.selectTab(tabLayout.getTabAt(1));
        return view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void setHomeArt(MainArticleBean.DataBean dataBean) {

    }

    @Override
    public void showLogoutSuccess(boolean success) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
