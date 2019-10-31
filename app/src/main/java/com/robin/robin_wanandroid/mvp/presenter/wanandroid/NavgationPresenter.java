package com.robin.robin_wanandroid.mvp.presenter.wanandroid;

import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.MvpBase.BasePresenter;
import com.robin.rbase.MVP.utils.RxLifecycleUtils;
import com.robin.robin_wanandroid.app.App;
import com.robin.robin_wanandroid.mvp.contract.wanandroid.NavgationContract;
import com.robin.robin_wanandroid.mvp.model.bean.NavgationBean;

import javax.inject.Inject;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class NavgationPresenter extends BasePresenter<NavgationContract.Model,NavgationContract.View> implements NavgationContract.Presenter {

    @Inject
    public NavgationPresenter(NavgationContract.Model model, NavgationContract.View rootView) {
        super(model, rootView);
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {
        //打开 App 时自动加载列表
//       requestNavgationData();
    }

    @Override
    public void requestNavgationData() {
     mModel.requestNavgationData().subscribeOn(Schedulers.io())
             .doOnSubscribe(new Consumer<Disposable>() {
                 @Override
                 public void accept(Disposable disposable) throws Exception {
                     mView.showLoading();
                 }
             })
             .doFinally(new Action() {
                 @Override
                 public void run() throws Exception {
//                     mView.hideLoading();
                 }
             })
             .observeOn(AndroidSchedulers.mainThread())
             .compose(RxLifecycleUtils.bindToLifecycle(mView)).subscribe(new ErrorHandleSubscriber<NavgationBean>(App.getmMyAppComponent().rxErrorHandler()) {
         @Override
         public void onNext(NavgationBean navgationBean) {
             Logger.i("test : "+App.getmMyAppComponent().rxErrorHandler());
             mView.setNavgationData(navgationBean);
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
