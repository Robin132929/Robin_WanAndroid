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
        tabLayout = view.findViewById(R.id.tl_tabs);
        viewPager = view.findViewById(R.id.vp_content);

        return view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        for (int i = 0; i < 5; i++) {
            if (i == 0) {
                fragments.add(new NavigationFragment());
            } else if (i == 1) {
                fragments.add(HomeFragment.newInstance());
            } else if (i == 2) {
                fragments.add(new KnowledgeStructureFragment());
            } else if (i == 3) {
                fragments.add(new WechatFragment());
            } else {
                fragments.add(new ProjectFragment());
            }
        }

        viewPager.setAdapter(new myViewPagerAdapter(fragments, getChildFragmentManager(), 1));

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
}
