package com.robin.robin_wanandroid.mvp.model.wanandroid;

import com.robin.rbase.MVP.MvpBase.BaseModel;
import com.robin.rbase.MVP.integration.IRepositoryManager;
import com.robin.robin_wanandroid.mvp.contract.wanandroid.LoginContract;
import com.robin.robin_wanandroid.mvp.model.bean.LoginDataBean;
import com.robin.robin_wanandroid.mvp.model.service.Api;

import javax.inject.Inject;

import io.reactivex.Observable;

public class LoginModel extends BaseModel implements LoginContract.Model {
    @Inject
    public LoginModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<LoginDataBean> login(String name, String password) {
        return Observable.just(mRepositoryManager.obtainRetrofitService(Api.class).getLoginData(name,password))
                .flatMap((Observable<LoginDataBean> loginDataBeanObservable) -> loginDataBeanObservable);
    }
}
