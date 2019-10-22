package com.robin.robin_wanandroid.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.MvpBase.BasePresenter;
import com.robin.rbase.MVP.lifecycle.ActivityLifecycleable;
import com.robin.rbase.MVP.utils.RxLifecycleUtils;
import com.robin.robin_wanandroid.app.App;
import com.robin.robin_wanandroid.delegate.AppLifecyclesImpl;
import com.robin.robin_wanandroid.mvp.contract.MainContract;
import com.robin.robin_wanandroid.mvp.model.bean.MainArticleBean;
import com.trello.rxlifecycle2.android.ActivityEvent;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.listener.ResponseErrorListener;

public class MainPresenter extends BasePresenter<MainContract.Model,MainContract.View>implements MainContract.Presenter {

     @Inject
    public MainPresenter(MainContract.View view,MainContract.Model model) {
        super(model,view);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {
//        //打开 App 时自动加载列表
//        Observable.just(1).compose(RxLifecycleUtils.bindToLifecycle(mView))
//                .subscribe(new ErrorHandleSubscriber<Integer>(App.getmMyAppComponent().rxErrorHandler()) {
//                    @Override
//                    public void onNext(Integer integer) {
//                       Logger.i("regist");
//                    }
//                });
    }
//
//    public MainArticleBean load(int page) {
//         mModel.getMainArticle(page).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .compose(RxLifecycleUtils.bindToLifecycle(mView)).subscribe(new ErrorHandleSubscriber<MainArticleBean>(App.getmMyAppComponent().rxErrorHandler()) {
//                    @Override
//                    public void onNext(MainArticleBean dataBeans) {
//                        Logger.i("test mian :"+dataBeans.toString());
//                        mView.setHomeArt(dataBeans.getData());
//                    }
//
//             @Override
//             public void onError(Throwable t) {
//                 super.onError(t);
//             }
//         });
//     }
//
//    @Override
//    public void logout() {
//
//    }
}
