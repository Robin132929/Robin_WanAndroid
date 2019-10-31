package com.robin.robin_wanandroid.mvp.ui.gank;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.robin.common_customize_ui.multis_status_view.MultiStateView;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.MvpBase.BaseLazyLoadFragment;
import com.robin.rbase.MVP.MvpBase.BaseMvpFragment;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.adapter.gank.GankAndroidAdapter;
import com.robin.robin_wanandroid.mvp.contract.gank.GankMainContract;
import com.robin.robin_wanandroid.mvp.model.bean.GankAndroidBean;
import com.robin.robin_wanandroid.mvp.presenter.gank.GankMainPresenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GankMainFragment extends BaseLazyLoadFragment<GankMainPresenter> implements GankMainContract.View {
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private MultiStateView mMultiStateView;

    private GankAndroidAdapter mGankAndroidAdapter;
    private List<GankAndroidBean.ResultsBean> data = new ArrayList<>();
    private static int page = 1;

    public GankMainFragment() {
    }

    @Override
    protected void lazyLoadData() {
        Logger.i("lazy load gank");
        mPresenter.getAndroidData(20, 1, false);
        getActivity().findViewById(R.id.toolbar).setVisibility(View.GONE);
        Activity activity=getActivity();
       View view= activity.findViewById(R.id.view_stub);
       view.setVisibility(View.VISIBLE);
       activity.findViewById(R.id.top_layout).setVisibility(View.VISIBLE);
        Logger.i("activty :"+getActivity().getClass().getSimpleName()+" toolbar :");
    }

    @Override
    public View initView(@NonNull View view, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRecyclerView = view.findViewById(R.id.gank_android_rv);
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        mMultiStateView = view.findViewById(R.id.gank_android_fragment_msv);
        return view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mGankAndroidAdapter = new GankAndroidAdapter(R.layout.item_gank_android_layout, data);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mGankAndroidAdapter);
        mGankAndroidAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                Logger.i("page is "+page);
                mPresenter.getAndroidData(20, page, false);
            }
        }, mRecyclerView);

        mGankAndroidAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getAndroidData(20, 1, true);
                page=1;
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_gankandroid;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void setAndroidData(GankAndroidBean bean, boolean isRefresh) {
        List<GankAndroidBean.ResultsBean> data = bean.getResults();
        Logger.i("data size " + data.size());
        data.forEach(new Consumer<GankAndroidBean.ResultsBean>() {
            @Override
            public void accept(GankAndroidBean.ResultsBean resultsBean) {
                Logger.i(resultsBean.getDesc());
            }
        });

        if (data == null && data.size() == 0) {
            return;
        }
        if (isRefresh) {
            mGankAndroidAdapter.setNewData(data);
            page = 1;
            return;
        }
        if (data.size() < 20) {
            mGankAndroidAdapter.addData(data);
            page++;
            mGankAndroidAdapter.loadMoreEnd();
        } else {
            mGankAndroidAdapter.addData(data);
            page++;
            mGankAndroidAdapter.loadMoreComplete();
        }
        Logger.i("page is set "+page);

    }

    @Override
    public void showLoading() {
        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
    }

    @Override
    public void hideLoading() {
        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
    }

    @Override
    public void showError() {
        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            getActivity().findViewById(R.id.toolbar).setVisibility(View.GONE);
            Activity activity=getActivity();

            View view= activity.findViewById(R.id.view_stub);
            if (view!=null) {
                view.setVisibility(View.VISIBLE);
            }
            activity.findViewById(R.id.top_layout).setVisibility(View.VISIBLE);
            Logger.i("activty :"+getActivity().getClass().getSimpleName()+" toolbar :");
        }

    }
}
