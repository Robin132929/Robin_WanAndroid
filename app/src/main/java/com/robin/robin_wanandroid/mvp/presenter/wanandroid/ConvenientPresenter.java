package com.robin.robin_wanandroid.mvp.presenter.wanandroid;

import com.robin.rbase.MVP.MvpBase.BasePresenter;
import com.robin.rbase.MVP.utils.RxLifecycleUtils;
import com.robin.robin_wanandroid.app.App;
import com.robin.robin_wanandroid.mvp.contract.ConvenientContract;
import com.robin.robin_wanandroid.mvp.model.bean.wendaBean;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

//public class ConvenientPresenter extends BasePresenter<ConvenientContract.Model,ConvenientContract.View> implements ConvenientContract.Presenter {
//
//    @Inject
//    public ConvenientPresenter(ConvenientContract.Model model, ConvenientContract.View rootView) {
//        super(model, rootView);
//    }
//
//    public void request(String name,Object a,Class<?>... parameterTypes){
//        getClass().getDeclaredMethod(name,parameterTypes).invoke(this,a);
//    }
//
//
//    @Override
//    public  void requestData(int page) {
//        mModel.requestWenda(page)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .compose(RxLifecycleUtils.bindToLifecycle(mView))
//                .subscribe(new ErrorHandleSubscriber<wendaBean>(App.getmMyAppComponent().rxErrorHandler()) {
//                    @Override
//                    public void onNext(wendaBean wendaBean) {
//                      mView.setData(wendaBean);
//                    }
//                });
//    }
//}
