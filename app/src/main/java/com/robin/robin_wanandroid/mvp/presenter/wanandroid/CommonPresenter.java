package com.robin.robin_wanandroid.mvp.presenter.wanandroid;

import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.MvpBase.BaseModel;
import com.robin.rbase.MVP.MvpBase.BasePresenter;
import com.robin.rbase.MVP.MvpBase.IModel;
import com.robin.rbase.MVP.MvpBase.IView;
import com.robin.rbase.MVP.utils.RxLifecycleUtils;
import com.robin.robin_wanandroid.app.App;
import com.robin.robin_wanandroid.mvp.contract.wanandroid.CommonContract;
import com.robin.robin_wanandroid.mvp.model.bean.AddCollectBean;
import com.robin.robin_wanandroid.mvp.model.common.DataManager;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class CommonPresenter< V extends CommonContract.View> extends BasePresenter<DataManager,V> implements CommonContract.Presenter {
    public CommonPresenter(DataManager model, V view) {
        super(model, view);
    }

    public CommonPresenter(V rootView) {
        super(rootView);
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

    @Override
    public void addFootPrint(String name, String link) {
        Logger.i("add footprint presenter"+name+link);

        mModel.addFootPrint(name,link)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mView))
                .subscribe(new ErrorHandleSubscriber<AddCollectBean>(App.getmMyAppComponent().rxErrorHandler()) {
                    @Override
                    public void onNext(AddCollectBean addCollectBean) {
                        Logger.i("add footprint next"+addCollectBean.toString());

                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        Logger.i("add footprint next error"+t.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                        Logger.i("add footprint next complete");

                    }
                });
    }
}
