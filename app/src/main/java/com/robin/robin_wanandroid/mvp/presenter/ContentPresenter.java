package com.robin.robin_wanandroid.mvp.presenter;

import com.robin.robin_wanandroid.mvp.contract.ContentContract;
import com.robin.robin_wanandroid.mvp.contract.wanandroid.CollectContract;
import com.robin.robin_wanandroid.mvp.presenter.wanandroid.CommonPresenter;

import javax.inject.Inject;

public class ContentPresenter extends CommonPresenter<ContentContract.Model,ContentContract.View> implements ContentContract.Presenter {
    @Inject
    public ContentPresenter(ContentContract.Model model, ContentContract.View view) {
        super(model, view);
    }
}
