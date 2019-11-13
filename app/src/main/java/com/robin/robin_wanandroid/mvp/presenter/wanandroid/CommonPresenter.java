package com.robin.robin_wanandroid.mvp.presenter.wanandroid;

import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.MvpBase.BasePresenter;
import com.robin.rbase.MVP.MvpBase.IModel;
import com.robin.rbase.MVP.MvpBase.IView;
import com.robin.rbase.MVP.utils.RxLifecycleUtils;
import com.robin.robin_wanandroid.app.App;
import com.robin.robin_wanandroid.mvp.contract.wanandroid.CommonContract;
import com.robin.robin_wanandroid.mvp.model.bean.AddCollectBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class CommonPresenter<M extends CommonContract.Model, V extends CommonContract.View> extends BasePresenter<M,V> implements CommonContract.Presenter {

    public CommonPresenter(M model, V view) {
        super(model, view);
    }

    @Override
    public void addCollectArticle(int id) {
     mModel.addCollectArticle(id)
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .compose(RxLifecycleUtils.bindToLifecycle(mView))
             .subscribe(new ErrorHandleSubscriber<AddCollectBean>(App.getmMyAppComponent().rxErrorHandler()) {
                 @Override
                 public void onNext(AddCollectBean addCollectBean) {
                     Logger.i("iscollect :data"+addCollectBean.toString());
                 }
             });
    }

    @Override
    public void cancelCollectArticle(int id) {
    mModel.cancelCollectArticle(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(RxLifecycleUtils.bindToLifecycle(mView))
            .subscribe(new ErrorHandleSubscriber<AddCollectBean>(App.getmMyAppComponent().rxErrorHandler()) {
                @Override
                public void onNext(AddCollectBean addCollectBean) {

                }
            });
    }
}