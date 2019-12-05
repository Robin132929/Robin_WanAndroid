package com.robin.robin_wanandroid.mvp.presenter;

import com.robin.rbase.MVP.MvpBase.BasePresenter;
import com.robin.robin_wanandroid.mvp.contract.SearchContract;

import javax.inject.Inject;

public class SearchPresenter extends BasePresenter<SearchContract.Model,SearchContract.View> implements SearchContract.Presenter {
    @Inject
    public SearchPresenter(SearchContract.Model model, SearchContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void getHotSearchKey() {

    }
}
