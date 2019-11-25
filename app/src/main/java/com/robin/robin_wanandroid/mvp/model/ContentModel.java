package com.robin.robin_wanandroid.mvp.model;

import com.robin.rbase.MVP.integration.IRepositoryManager;
import com.robin.robin_wanandroid.mvp.contract.ContentContract;
import com.robin.robin_wanandroid.mvp.model.wanandroid.CommonModel;

import javax.inject.Inject;

public class ContentModel extends CommonModel implements ContentContract.Model {
    @Inject
    public ContentModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }
}
