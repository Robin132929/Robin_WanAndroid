package com.robin.robin_wanandroid.ui.readhub;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.robin.rbase.MVP.MvpBase.BaseLazyLoadFragment;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.adapter.readhub.ReadhubViewPagerAdapter;
import com.robin.robin_wanandroid.mvp.contract.readhub.ReadhubMainContract;
import com.robin.robin_wanandroid.mvp.presenter.readhub.ReadhubMainPresenter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class ReadhubMainFragment extends BaseLazyLoadFragment<ReadhubMainPresenter> implements ReadhubMainContract.View {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public ReadhubMainFragment() {
    }

    @Override
    protected void lazyLoadData() {

    }

    @Override
    public void initView(@NonNull View view, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        tabLayout = view.findViewById(R.id.tl_tabs);
        viewPager = view.findViewById(R.id.vp_content);

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        viewPager.setAdapter(new ReadhubViewPagerAdapter(getChildFragmentManager()));
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.selectTab(tabLayout.getTabAt(1));
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_readhub_main;
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
