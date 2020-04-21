package com.robin.robin_wanandroid.ui;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.exoplayer2.C;
import com.robin.rbase.CommonBase.Fragment.BaseFragment;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.MvpBase.BaseMvpFragment;
import com.robin.rbase.MVP.MvpBase.BasePresenter;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.customize_interface.PageType;
import com.robin.robin_wanandroid.ui.content.ContentActivity;
import com.robin.robin_wanandroid.adapter.wanandroid.CollectListAdapter;
import com.robin.robin_wanandroid.app.App;
import com.robin.robin_wanandroid.mvp.contract.wanandroid.CollectContract;
import com.robin.robin_wanandroid.mvp.model.bean.GetCollectBean;
import com.robin.robin_wanandroid.mvp.presenter.wanandroid.CollectPresenter;
import com.robin.robin_wanandroid.rx.RefreshHomeEvent;
import com.robin.robin_wanandroid.rx.RxBus;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class CollectFragment extends BaseMvpFragment<CollectPresenter> implements CollectContract.View {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private CollectListAdapter mCollectListAdapter;
    private List<GetCollectBean.DataBean.DatasBean> datas = new ArrayList<>();
    private static int page_index=0;

    public CollectFragment() {
        Logger.i("page type "+this);
    }

    @Override
    public void initView(@NonNull View view, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRecyclerView = view.findViewById(R.id.collect_rv);
        mSwipeRefreshLayout = view.findViewById(R.id.collect_srl);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getCollectList(0, true);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mCollectListAdapter = new CollectListAdapter(R.layout.item_collect_list, datas);
        mCollectListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                GetCollectBean.DataBean.DatasBean bean= (GetCollectBean.DataBean.DatasBean) adapter.getItem(position);
                ContentActivity.startActivity(App.getmMyAppComponent().application(), bean.getTitle(),bean.getLink());
            }
        });
        mCollectListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.getCollectList(page_index,false);
            }
        }, mRecyclerView);
        mCollectListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                GetCollectBean.DataBean.DatasBean bean= (GetCollectBean.DataBean.DatasBean) adapter.getItem(position);
                if (view.getId()==R.id.iv_like){
                    adapter.remove(position);
                    mPresenter.removeCollectState(bean.getId(),bean.getOriginId());
                }
            }
        });
        mRecyclerView.setAdapter(mCollectListAdapter);

    }



    @Override
    public int getLayoutId() {
        return R.layout.fragment_collect;
    }

    @Override
    public void setCollectList(GetCollectBean bean, boolean isRefresh) {
        Logger.i("data collect" + bean.getData().getDatas().size());
        page_index=bean.getData().getCurPage();
        //refresh为true 代表该次请求是下拉刷新
        if (isRefresh) {
            mCollectListAdapter.replaceData(bean.getData().getDatas());
        } else {
            mCollectListAdapter.addData(bean.getData().getDatas());
        }
        if (bean.getData().getDatas().size() < bean.getData().getSize()) {
            mCollectListAdapter.loadMoreEnd(true);
        } else {
            mCollectListAdapter.loadMoreComplete();
        }
    }

    @Override
    public void removeCollectState(boolean success) {
        if (success){
            Logger.i("refreshhome ");
            RxBus.getInstance().post(new RefreshHomeEvent(success));
        }
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
