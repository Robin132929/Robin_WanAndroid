package com.robin.robin_wanandroid.mvp.model.readhub;

import com.robin.rbase.MVP.MvpBase.BaseModel;
import com.robin.rbase.MVP.integration.IRepositoryManager;
import com.robin.robin_wanandroid.mvp.contract.readhub.ReadhubMainContract;

import javax.inject.Inject;

public class ReadhubMainModel extends BaseModel implements ReadhubMainContract.Model {
    @Inject
    public ReadhubMainModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }
}
