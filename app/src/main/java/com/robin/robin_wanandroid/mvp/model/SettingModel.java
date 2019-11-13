package com.robin.robin_wanandroid.mvp.model;

import com.robin.rbase.MVP.MvpBase.BaseModel;
import com.robin.rbase.MVP.integration.IRepositoryManager;
import com.robin.robin_wanandroid.mvp.contract.SettingContract;

import javax.inject.Inject;

public class SettingModel extends BaseModel implements SettingContract.Model {
    @Inject
    public SettingModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }
}
