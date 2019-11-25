package com.robin.robin_wanandroid.mvp.presenter;

import com.robin.rbase.MVP.MvpBase.BasePresenter;
import com.robin.rbase.MVP.utils.RxLifecycleUtils;
import com.robin.robin_wanandroid.app.App;
import com.robin.robin_wanandroid.mvp.contract.FootPrintContract;
import com.robin.robin_wanandroid.mvp.model.bean.GetCollectBean;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class FootPrintPresenter extends BasePresenter<FootPrintContract.Model,FootPrintContract.View> implements FootPrintContract.Presenter {
   @Inject
    public FootPrintPresenter(FootPrintContract.Model model, FootPrintContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void getFootPrintList(int page, boolean isRefresh) {
        mModel.getFootPrintList(page, isRefresh)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mView))
                .subscribe(new ErrorHandleSubscriber<GetCollectBean>(App.getmMyAppComponent().rxErrorHandler()) {
                    @Override
                    public void onNext(GetCollectBean getCollectBean) {
                        mView.setFootPrintList(getCollectBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                    }
                });
    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onDetach() {

    }
}
