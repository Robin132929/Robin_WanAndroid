package com.robin.robin_wanandroid.mvp.presenter.wanandroid;

import com.robin.rbase.MVP.MvpBase.BasePresenter;
import com.robin.rbase.MVP.utils.RxLifecycleUtils;
import com.robin.robin_wanandroid.app.App;
import com.robin.robin_wanandroid.mvp.contract.wanandroid.WanAndroidMainFragmentContract;
import com.robin.robin_wanandroid.mvp.model.bean.BannerBean;
import com.robin.robin_wanandroid.mvp.model.common.DataManager;

import javax.inject.Inject;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class WanAndroidMainFragmentPresenter extends BasePresenter<DataManager, WanAndroidMainFragmentContract.View>
        implements WanAndroidMainFragmentContract.Presenter {
    @Inject
    public WanAndroidMainFragmentPresenter(DataManager model, WanAndroidMainFragmentContract.View rootView) {
        super(model, rootView);
    }

//    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
//    void onStart() {
//        mView.showLoading();
//        getBannerData();
//    }

    @Override
    public void getBannerData() {
        mModel.getBanner().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mView))
                .subscribe(new ErrorHandleSubscriber<BannerBean>(App.getmMyAppComponent().rxErrorHandler()) {
                    @Override
                    public void onNext(BannerBean dataBeans) {
                        mView.hideLoading();
                        mView.showBanner(dataBeans);
                    }
                });
    }
}
