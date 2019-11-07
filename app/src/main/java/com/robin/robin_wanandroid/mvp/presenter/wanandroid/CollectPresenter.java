package com.robin.robin_wanandroid.mvp.presenter.wanandroid;

import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.utils.RxLifecycleUtils;
import com.robin.robin_wanandroid.app.App;
import com.robin.robin_wanandroid.mvp.contract.wanandroid.CollectContract;
import com.robin.robin_wanandroid.mvp.model.bean.GetCollectBean;

import javax.inject.Inject;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class CollectPresenter extends CommonPresenter<CollectContract.Model,CollectContract.View> implements CollectContract.Presenter {
   @Inject
    public CollectPresenter(CollectContract.Model model, CollectContract.View view) {
        super(model, view);
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {
        //打开 App 时自动加载列表
     getCollectList(0,false);
    }
    @Override
    public void getCollectList(int page, boolean isRefresh) {
     mModel.getCollectList(page, isRefresh)
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .compose(RxLifecycleUtils.bindToLifecycle(mView))
             .subscribe(new ErrorHandleSubscriber<GetCollectBean>(App.getmMyAppComponent().rxErrorHandler()) {
                 @Override
                 public void onNext(GetCollectBean getCollectBean) {
                     Logger.i("data collect"+getCollectBean.getErrorMsg());
                     mView.setCollectList(getCollectBean,isRefresh);
                 }
             });
    }
}
