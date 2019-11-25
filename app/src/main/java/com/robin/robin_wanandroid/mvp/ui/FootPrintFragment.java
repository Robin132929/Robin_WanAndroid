package com.robin.robin_wanandroid.mvp.ui;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.robin.rbase.MVP.MvpBase.BaseMvpFragment;
import com.robin.robin_wanandroid.customize_interface.ScrollTopListener;
import com.robin.robin_wanandroid.mvp.contract.FootPrintContract;
import com.robin.robin_wanandroid.mvp.model.bean.AddCollectBean;
import com.robin.robin_wanandroid.mvp.model.bean.GetCollectBean;
import com.robin.robin_wanandroid.mvp.presenter.FootPrintPresenter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FootPrintFragment extends BaseMvpFragment<FootPrintPresenter> implements ScrollTopListener, FootPrintContract.View {
    @Override
    public View initView(@NonNull View view, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void scroll2Top() {

    }

    @Override
    public void setFootPrintList(GetCollectBean bean) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {

    }
}
