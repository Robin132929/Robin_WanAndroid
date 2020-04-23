package com.robin.robin_wanandroid.ui.wanandroid.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.robin.rbase.CommonBase.Fragment.BaseFragment;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.MvpBase.BaseLazyLoadFragment;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.adapter.wanandroid.myViewPagerAdapter;
import com.robin.robin_wanandroid.mvp.contract.common.MainContract;
import com.robin.robin_wanandroid.mvp.presenter.common.MainActivityPresenter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

public class WanAndroidMainFragment extends BaseFragment {
    @BindView(R.id.tl_tabs)
    TabLayout mTabLayout;
    @BindView(R.id.vp_content)
    ViewPager mViewPager;
    //TODO 懒加载
    public WanAndroidMainFragment() {
    }

    public static WanAndroidMainFragment newInstance() {
        WanAndroidMainFragment fragment = new WanAndroidMainFragment();
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

//    @Override
//    public void lazyLoadData() {
//        getActivity().findViewById(R.id.toolbar).setVisibility(View.VISIBLE);
//        Logger.i("view stub is " + getActivity().findViewById(R.id.top_layout));
//        if (getActivity().findViewById(R.id.top_layout) != null) {
//            getActivity().findViewById(R.id.top_layout).setVisibility(View.GONE);
//        }
//        Logger.i("lazy load");
//    }

//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if (!hidden) {
//            getActivity().findViewById(R.id.toolbar).setVisibility(View.VISIBLE);
//            if (getActivity().findViewById(R.id.top_layout) != null) {
//                getActivity().findViewById(R.id.top_layout).setVisibility(View.GONE);
//            }
//        }
//    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.e("fragment  pause "+this.toString());

    }
}
