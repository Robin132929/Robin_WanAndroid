package com.robin.robin_wanandroid.mvp.presenter;

import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.MvpBase.BasePresenter;
import com.robin.rbase.MVP.utils.RxLifecycleUtils;
import com.robin.robin_wanandroid.app.App;
import com.robin.robin_wanandroid.mvp.contract.FootPrintContract;
import com.robin.robin_wanandroid.mvp.model.bean.FootPrintBean;
import com.robin.robin_wanandroid.mvp.model.bean.GetCollectBean;

import javax.inject.Inject;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class FootPrintPresenter extends BasePresenter<FootPrintContract.Model,FootPrintContract.View> implements FootPrintContract.Presenter {
   @Inject
    public FootPrintPresenter(FootPrintContract.Model model, FootPrintContract.View rootView) {
        super(model, rootView);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {
        //打开 App 时自动加载列表
        getFootPrintList(0,false);
    }

    @Override
    public void getFootPrintList(int page, boolean isRefresh) {
        mModel.getFootPrintList(page, isRefresh)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mView))
                .subscribe(new ErrorHandleSubscriber<FootPrintBean>(App.getmMyAppComponent().rxErrorHandler()) {
                    @Override
                    public void onNext(FootPrintBean getCollectBean) {
                        Logger.i("print foot next ");
                        mView.setFootPrintList(getCollectBean,isRefresh);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                    }
                });
    }

}
