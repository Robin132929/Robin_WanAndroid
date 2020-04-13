package com.robin.robin_wanandroid.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robin.rbase.MVP.MvpBase.BaseLazyLoadFragment;
import com.robin.rbase.MVP.MvpBase.BaseMvpFragment;
import com.robin.rbase.MVP.MvpBase.BasePresenter;
import com.robin.rbase.MVP.MvpBase.IPresenter;

import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseSimpleFragment<P extends BasePresenter> extends BaseLazyLoadFragment<P> {
    private Unbinder unBinder;

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
////        View view = inflater.inflate(getLayoutId(), container, false);
////        unBinder = ButterKnife.bind(this, view);
//        return view;
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unBinder != null && unBinder != Unbinder.EMPTY) {
            unBinder.unbind();
            unBinder = null;
        }
    }
}
