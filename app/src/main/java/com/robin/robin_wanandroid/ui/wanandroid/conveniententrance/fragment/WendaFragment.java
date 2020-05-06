package com.robin.robin_wanandroid.ui.wanandroid.conveniententrance.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.adapter.wanandroid.WendaListAdapter;
import com.robin.robin_wanandroid.base.RobinBaseFragment;
import com.robin.robin_wanandroid.mvp.contract.ConvenientContract;
import com.robin.robin_wanandroid.mvp.model.bean.wendaBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class WendaFragment extends RobinBaseFragment implements ConvenientContract.View {

    @BindView(R.id.rv_wenda)
    RecyclerView rvWenda;

    private WendaListAdapter wendaListAdapter;
    private List<wendaBean.DataBean.DatasBean> data = new ArrayList<>();

    public WendaFragment() {
        Logger.i("page typ " + this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_wenda_page;
    }

    @Override
    public void initView(@NonNull View view, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        wendaListAdapter = new WendaListAdapter(0, data);
        rvWenda.setLayoutManager(new LinearLayoutManager(getContext()));
        rvWenda.setAdapter(wendaListAdapter);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
    }

    @Override
    protected void initLoadingStatusViewIfNeed() {

    }

    @Override
    protected void onLoadRetry() {

    }

    public String setTitle() {
        return "问答";
    }

    @Override
    public void showWenda(wendaBean.DataBean dataBean) {

    }

    @Override
    public void showReceview() {

    }

    @Override
    public void showSquare() {

    }

    @Override
    public void showTodo() {

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
