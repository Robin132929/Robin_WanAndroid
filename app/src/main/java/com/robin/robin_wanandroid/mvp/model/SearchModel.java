package com.robin.robin_wanandroid.mvp.model;

import com.robin.rbase.MVP.MvpBase.BaseModel;
import com.robin.rbase.MVP.integration.IRepositoryManager;
import com.robin.robin_wanandroid.mvp.contract.SearchContract;
import com.robin.robin_wanandroid.mvp.model.bean.HotSearchBean;

import javax.inject.Inject;

import io.reactivex.Observable;

public class SearchModel extends BaseModel implements SearchContract.Model {
    @Inject
    public SearchModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<HotSearchBean> getHotSearchKey() {
        return null;
    }
}
