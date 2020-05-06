package com.robin.robin_wanandroid.mvp.contract;

import com.robin.rbase.MVP.MvpBase.IPresenter;
import com.robin.rbase.MVP.MvpBase.IView;
import com.robin.robin_wanandroid.mvp.model.bean.wendaBean;

public interface ConvenientContract{

    interface View extends IView {
        void showWenda(wendaBean.DataBean dataBean);
        void showReceview();
        void showSquare();
        void showTodo();
    }

    interface Presenter extends IPresenter {
        //问答
        void requestWenda(int page);
        //面试集锦
        void requestReceview();
        //广场
        void requestSquare();
        //待办清单
        void requestTodo();
    }
}
