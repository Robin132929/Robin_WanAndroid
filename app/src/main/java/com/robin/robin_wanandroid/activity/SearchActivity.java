package com.robin.robin_wanandroid.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.base.RobinBaseActivity;
import com.robin.robin_wanandroid.mvp.contract.SearchContract;
import com.robin.robin_wanandroid.mvp.model.bean.HotSearchBean;
import com.robin.robin_wanandroid.mvp.presenter.SearchPresenter;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends RobinBaseActivity<SearchPresenter> implements SearchContract.View {
    private List<HotSearchBean>  mHotSearchDatas=new ArrayList<>();
    private Toolbar mToolbar;
    private SearchView mSearchView;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
  System.out.println();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
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
    public void setHotSearchKey(HotSearchBean bean) {

    }
}
