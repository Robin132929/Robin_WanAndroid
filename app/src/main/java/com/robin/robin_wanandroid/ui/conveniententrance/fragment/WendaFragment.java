package com.robin.robin_wanandroid.ui.conveniententrance.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.robin.rbase.CommonBase.Fragment.BaseFragment;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.MvpBase.BasePresenter;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.adapter.wanandroid.WendaListAdapter;
import com.robin.robin_wanandroid.customize_interface.PageType;
import com.robin.robin_wanandroid.mvp.model.bean.wendaBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;

public class WendaFragment extends BaseFragment  {

    @BindView(R.id.wenda_rv)
    RecyclerView wendaRv;
    @BindView(R.id.wenda_srl)
    SwipeRefreshLayout wendaSrl;
    private WendaListAdapter wendaListAdapter;
    private List<wendaBean.DataBean.DatasBean> data=new ArrayList<>();

    public WendaFragment() {
        Logger.i("page typ " + this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_wenda_page;
    }

    @Override
    public void initView(@NonNull View view, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      wendaListAdapter=new WendaListAdapter(0,data);
      wendaRv.setLayoutManager(new LinearLayoutManager(getContext()));
      wendaRv.setAdapter(wendaListAdapter);
//        presenter.requestData(0);

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
//          text.setText("aaaaaa");

    }

//    @Override
//    public void setData(@Nullable Object data) {
//      if (data instanceof wendaBean){
//          this.data=((wendaBean) data).getData().getDatas();
//          wendaListAdapter.setNewData(this.data);
//      }
//    }
//
//    @Override
//    public void setPresenter(BasePresenter presenter) {
//        this.presenter= (ConvenientPresenter) presenter;
//    }
//
//    @Override
//    public void initToolbar(ActionBar toolbar) {
//        toolbar.setTitle("问答");
//
//    }
//
//    @Override
//    public void setData(Object data, Object... args) {
//
//    }

}
