package com.robin.robin_wanandroid.ui.home.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.MvpBase.BaseLazyLoadFragment;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.adapter.wanandroid.myViewPagerAdapter;
import com.robin.robin_wanandroid.mvp.contract.wanandroid.MainContract;
import com.robin.robin_wanandroid.mvp.presenter.wanandroid.MainPresenter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

public class MainFragment extends BaseLazyLoadFragment<MainPresenter> implements MainContract.View {
    @BindView(R.id.tl_tabs)
    TabLayout mTabLayout;
    @BindView(R.id.vp_content)
    ViewPager mViewPager;


    public MainFragment() {
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void initView(@NonNull View view, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mViewPager.setAdapter(new myViewPagerAdapter(getChildFragmentManager()));
        mViewPager.setOffscreenPageLimit(4);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.selectTab(mTabLayout.getTabAt(1));

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Logger.i("visable load");

    }

    @Override
    public void lazyLoadData() {
        getActivity().findViewById(R.id.toolbar).setVisibility(View.VISIBLE);
        Logger.i("view stub is " + getActivity().findViewById(R.id.top_layout));
        if (getActivity().findViewById(R.id.top_layout) != null) {
            getActivity().findViewById(R.id.top_layout).setVisibility(View.GONE);
        }
        Logger.i("lazy load");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            getActivity().findViewById(R.id.toolbar).setVisibility(View.VISIBLE);
            if (getActivity().findViewById(R.id.top_layout) != null) {
                getActivity().findViewById(R.id.top_layout).setVisibility(View.GONE);
            }
        }
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

    @Override
    public void showError() {

    }
}
