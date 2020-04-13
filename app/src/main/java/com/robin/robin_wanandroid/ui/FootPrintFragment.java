package com.robin.robin_wanandroid.ui;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.robin.rbase.CommonBase.Fragment.BaseFragment;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.MvpBase.BasePresenter;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.adapter.wanandroid.FootListAdapter;
import com.robin.robin_wanandroid.app.App;
import com.robin.robin_wanandroid.customize_interface.PageType;
import com.robin.robin_wanandroid.customize_interface.ScrollTopListener;
import com.robin.robin_wanandroid.mvp.contract.FootPrintContract;
import com.robin.robin_wanandroid.mvp.model.bean.FootPrintBean;
import com.robin.robin_wanandroid.mvp.model.bean.GetCollectBean;
import com.robin.robin_wanandroid.mvp.presenter.wanandroid.ConvenientPresenter;
import com.robin.robin_wanandroid.ui.content.ContentActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;

public class FootPrintFragment extends BaseFragment implements ScrollTopListener, FootPrintContract.View, PageType {
    @BindView(R.id.collect_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.collect_srl)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private FootListAdapter mFootListAdapter;
    private List<FootPrintBean.DataBean> datas = new ArrayList<>();
    ConvenientPresenter mPresenter;

    public FootPrintFragment() {

    }

    @Override
    public void initView(@NonNull View view, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });


    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mFootListAdapter = new FootListAdapter(R.layout.item_collect_list, datas);
        mFootListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                GetCollectBean.DataBean.DatasBean bean = (GetCollectBean.DataBean.DatasBean) adapter.getItem(position);
                ContentActivity.startActivity(App.getmMyAppComponent().application(), bean.getTitle(), bean.getLink());
            }
        });
        mFootListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
            }
        }, mRecyclerView);
        mRecyclerView.setAdapter(mFootListAdapter);
//        mPresenter.
    }


    @Override
    public void initToolbar(ActionBar toolbar) {
        toolbar.setTitle("足迹");
    }

    @Override
    public void setData(Object data,Object... args) {

    }


    @Override
    public void setPresenter(BasePresenter presenter) {
     this.mPresenter= (ConvenientPresenter) presenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_collect;
    }

    @Override
    public void scroll2Top() {
        mRecyclerView.smoothScrollToPosition(0);
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

    @Override
    public void setFootPrintList(FootPrintBean bean, boolean isRefresh) {
        Logger.i("print foot result is " + bean.getData().size());
        //refresh为true 代表该次请求是下拉刷新
        if (isRefresh) {
            mFootListAdapter.replaceData(bean.getData());
        } else {
            mFootListAdapter.addData(bean.getData());
        }
//        if (bean.getData().getDatas().size() < bean.getData().getSize()) {
//            mCollectListAdapter.loadMoreEnd(true);
//        } else {
//            mCollectListAdapter.loadMoreComplete();
//        }
    }
}
