package com.robin.robin_wanandroid.mvp.presenter.gank;

import android.os.Build;

import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.MvpBase.BasePresenter;
import com.robin.rbase.MVP.utils.RxLifecycleUtils;
import com.robin.robin_wanandroid.app.App;
import com.robin.robin_wanandroid.mvp.contract.gank.GankMainContract;
import com.robin.robin_wanandroid.mvp.model.bean.GankAndroidBean;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class GankMainPresenter extends BasePresenter<GankMainContract.Model,GankMainContract.View> implements GankMainContract.Presenter {
    @Inject
    public GankMainPresenter(GankMainContract.Model model, GankMainContract.View rootView) {
        super(model, rootView);
    }
//    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE) void onCreate() {
//        //打开 App 时自动加载列表
//        getAndroidData(20,1,false);
//    }

    @Override
    public void getAndroidData(int count, int page,boolean isRefresh) {
        mModel.getAndroidData(count, page, isRefresh)
               .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (page<2) {
                            mView.showLoading();
                        }
                    }
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mView))
                .subscribe(new ErrorHandleSubscriber<GankAndroidBean>(App.getmMyAppComponent().rxErrorHandler()) {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onNext(GankAndroidBean gankAndroidBean) {
                        List<GankAndroidBean.ResultsBean> data = gankAndroidBean.getResults();
                        Logger.i("data size "+data.size());
                        data.forEach(new java.util.function.Consumer<GankAndroidBean.ResultsBean>() {
                            @Override
                            public void accept(GankAndroidBean.ResultsBean resultsBean) {
                                Logger.i(resultsBean.getDesc());
                            }
                        });
                     mView.setAndroidData(gankAndroidBean,isRefresh);
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                        mView.hideLoading();
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        Logger.i("error "+t.getMessage());
                        mView.showError();
                    }
                });
    }
}
