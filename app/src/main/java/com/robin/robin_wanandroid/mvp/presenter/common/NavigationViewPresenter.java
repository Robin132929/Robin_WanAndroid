package com.robin.robin_wanandroid.mvp.presenter.common;

import com.robin.rbase.MVP.MvpBase.BasePresenter;
import com.robin.robin_wanandroid.mvp.contract.common.NavigationViewContract;
import com.robin.robin_wanandroid.mvp.model.bean.GetCollectBean;
import com.robin.robin_wanandroid.mvp.model.common.DataManager;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class NavigationViewPresenter extends BasePresenter<DataManager,NavigationViewContract.View> implements NavigationViewContract.Presenter {

    public NavigationViewPresenter(DataManager model, NavigationViewContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void getCollectList(int page, boolean isRefresh) {
     mModel.getCollectList(page)
             .subscribeOn(Schedulers.io())
             .doOnSubscribe(disposable -> {

             }).subscribeOn(AndroidSchedulers.mainThread())
             .subscribe(new ErrorHandleSubscriber<GetCollectBean>(RxErrorHandler.builder().build()) {
                 @Override
                 public void onNext(GetCollectBean getCollectBean) {
                     mView.showCollectList();
                 }
             });
    }

    @Override
    public void getBrowsingHistory(int page, boolean isRefresh) {

    }

}
