package com.robin.robin_wanandroid.mvp.presenter;

import com.robin.rbase.MVP.MvpBase.BasePresenter;
import com.robin.robin_wanandroid.mvp.contract.SettingContract;

import javax.inject.Inject;

public class SettingPresenter extends BasePresenter<SettingContract.Model,SettingContract.View> implements SettingContract.Presenter {
    @Inject
    public SettingPresenter(SettingContract.Model model, SettingContract.View rootView) {
        super(model, rootView);
    }
}
