package com.robin.robin_wanandroid.mvp.presenter.readhub;

import com.robin.rbase.MVP.MvpBase.BasePresenter;
import com.robin.robin_wanandroid.mvp.contract.readhub.ReadhubMainContract;

import javax.inject.Inject;

public class ReadhubMainPresenter extends BasePresenter<ReadhubMainContract.Model,ReadhubMainContract.View>implements ReadhubMainContract.Presenter {
    @Inject
    public ReadhubMainPresenter(ReadhubMainContract.Model model, ReadhubMainContract.View rootView) {
        super(model, rootView);
    }
}
