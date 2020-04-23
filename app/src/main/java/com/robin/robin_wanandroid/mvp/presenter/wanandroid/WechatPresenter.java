package com.robin.robin_wanandroid.mvp.presenter.wanandroid;

import com.robin.rbase.MVP.MvpBase.BasePresenter;
import com.robin.rbase.MVP.utils.RxLifecycleUtils;
import com.robin.robin_wanandroid.app.App;
import com.robin.robin_wanandroid.mvp.contract.wanandroid.WechatContract;
import com.robin.robin_wanandroid.mvp.model.bean.WechatContentBean;
import com.robin.robin_wanandroid.mvp.model.bean.WechatTitleBean;
import com.robin.robin_wanandroid.mvp.model.common.DataManager;

import javax.inject.Inject;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class WechatPresenter extends BasePresenter<DataManager,WechatContract.View> implements WechatContract.Presenter {
    @Inject
    public WechatPresenter(DataManager model, WechatContract.View rootView) {
        super(model, rootView);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onCreate() {
        //打开 App 时自动加载列表
        requestWechatTitle();
        requestWechatContent(408,1,false);
    }

    @Override
    public void requestWechatTitle() {
        mView.showLoading();
        mModel.getWechatTitle().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mView))
                .subscribe(new ErrorHandleSubscriber<WechatTitleBean>(App.getmMyAppComponent().rxErrorHandler()) {
                    @Override
                    public void onNext(WechatTitleBean wechatTitleBean) {
                        mView.hideLoading();
                        mView.setWechatTitle(wechatTitleBean);
                    }
                });

    }

    @Override
    public void requestWechatContent(int id, int page, boolean isRefresh) {
        mView.showLoading();
    mModel.getWechatContent(id, page).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(new Consumer<Disposable>() {
                @Override
                public void accept(Disposable disposable) throws Exception {
//                    mView.showLoading();
                }
            })
            .compose(RxLifecycleUtils.bindToLifecycle(mView))
            .subscribe(new ErrorHandleSubscriber<WechatContentBean>(App.getmMyAppComponent().rxErrorHandler()) {
                @Override
                public void onNext(WechatContentBean wechatContentBean) {
                    mView.setWechatContent(wechatContentBean,isRefresh);
                }

                @Override
                public void onComplete() {
                    super.onComplete();
                    mView.hideLoading();
                }

                @Override
                public void onError(Throwable t) {
                    super.onError(t);
                    mView.showError();
                }
            });
    }
}
