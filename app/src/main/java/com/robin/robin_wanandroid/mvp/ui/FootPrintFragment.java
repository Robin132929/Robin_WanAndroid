package com.robin.robin_wanandroid.mvp.ui;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.MvpBase.BaseMvpFragment;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.activity.ContentActivity;
import com.robin.robin_wanandroid.adapter.wanandroid.CollectListAdapter;
import com.robin.robin_wanandroid.adapter.wanandroid.FootListAdapter;
import com.robin.robin_wanandroid.app.App;
import com.robin.robin_wanandroid.customize_interface.ScrollTopListener;
import com.robin.robin_wanandroid.mvp.contract.FootPrintContract;
import com.robin.robin_wanandroid.mvp.model.bean.AddCollectBean;
import com.robin.robin_wanandroid.mvp.model.bean.FootPrintBean;
import com.robin.robin_wanandroid.mvp.model.bean.GetCollectBean;
import com.robin.robin_wanandroid.mvp.presenter.FootPrintPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class FootPrintFragment extends BaseMvpFragment<FootPrintPresenter> implements ScrollTopListener, FootPrintContract.View {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private FootListAdapter mFootListAdapter;
    private List<FootPrintBean.DataBean> datas = new ArrayList<>();

    public FootPrintFragment() {
    }

    @Override
    public View initView(@NonNull View view, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRecyclerView = view.findViewById(R.id.collect_rv);
        mSwipeRefreshLayout = view.findViewById(R.id.collect_srl);
        return view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mFootListAdapter = new FootListAdapter(R.layout.item_collect_list, datas);
        mFootListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                GetCollectBean.DataBean.DatasBean bean= (GetCollectBean.DataBean.DatasBean) adapter.getItem(position);
                ContentActivity.startActivity(App.getmMyAppComponent().application(), bean.getTitle(),bean.getLink());
            }
        });
        mFootListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
            }
        }, mRecyclerView);
        mFootListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        mRecyclerView.setAdapter(mFootListAdapter);
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_collect;
    }

    @Override
    public void scroll2Top() {

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
        Logger.i("print foot result is "+bean.getData().size());
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
