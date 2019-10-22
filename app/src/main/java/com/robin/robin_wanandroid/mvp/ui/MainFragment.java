package com.robin.robin_wanandroid.mvp.ui;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.MvpBase.BaseLazyLoadFragment;
import com.robin.rbase.MVP.MvpBase.BaseMvpFragment;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.adapter.myViewPagerAdapter;
import com.robin.robin_wanandroid.mvp.contract.MainContract;
import com.robin.robin_wanandroid.mvp.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class MainFragment extends BaseLazyLoadFragment<MainPresenter> implements MainContract.View {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public MainFragment() {
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public View initView(@NonNull View view, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        tabLayout = view.findViewById(R.id.tl_tabs);
        viewPager = view.findViewById(R.id.vp_content);

        return view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        viewPager.setAdapter(new myViewPagerAdapter(getChildFragmentManager()));
        viewPager.setOffscreenPageLimit(4);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.selectTab(tabLayout.getTabAt(1));

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Logger.i("visable load");

    }

    @Override
    protected void lazyLoadData() {
        Logger.i("lazy load");
    }
}
