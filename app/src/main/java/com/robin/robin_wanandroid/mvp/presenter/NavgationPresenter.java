package com.robin.robin_wanandroid.mvp.presenter;

import android.content.Context;

import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.MvpBase.BasePresenter;
import com.robin.rbase.MVP.utils.RxLifecycleUtils;
import com.robin.robin_wanandroid.app.App;
import com.robin.robin_wanandroid.delegate.AppLifecyclesImpl;
import com.robin.robin_wanandroid.mvp.contract.NavgationContract;
import com.robin.robin_wanandroid.mvp.model.NavgationModel;
import com.robin.robin_wanandroid.mvp.model.bean.NavgationBean;

import javax.inject.Inject;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.listener.ResponseErrorListener;

public class NavgationPresenter extends BasePresenter<NavgationContract.Model,NavgationContract.View> implements NavgationContract.Presenter {
    RxErrorHandler mErrorHandler=RxErrorHandler
            .builder()
            .with(AppLifecyclesImpl.getapp())
            .responseErrorListener(new ResponseErrorListener() {
                @Override
                public void handleResponseError(Context context, Throwable t) {

                }
            })
            .build();

    @Inject
    public NavgationPresenter(NavgationContract.Model model, NavgationContract.View rootView) {
        super(model, rootView);
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {
        //打开 App 时自动加载列表
       requestNavgationData();
    }

    @Override
    public void requestNavgationData() {
     mModel.requestNavgationData().subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .compose(RxLifecycleUtils.bindToLifecycle(mView)).subscribe(new ErrorHandleSubscriber<NavgationBean>(App.getmMyAppComponent().rxErrorHandler()) {
         @Override
         public void onNext(NavgationBean navgationBean) {
             Logger.i("test : "+App.getmMyAppComponent().rxErrorHandler());
             mView.setNavgationData(navgationBean);
         }

         @Override
         public void onError(Throwable t) {
             super.onError(t);
             mView.showLoading();
         }
     });
    }

}
