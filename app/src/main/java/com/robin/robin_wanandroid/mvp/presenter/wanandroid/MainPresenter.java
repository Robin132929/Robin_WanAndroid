package com.robin.robin_wanandroid.mvp.presenter.wanandroid;

import com.robin.rbase.MVP.MvpBase.BasePresenter;
import com.robin.robin_wanandroid.mvp.contract.wanandroid.MainContract;

import javax.inject.Inject;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

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
