package com.robin.robin_wanandroid.mvp.ui.WanAndroid;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.MvpBase.BaseLazyLoadFragment;
import com.robin.rbase.MVP.MvpBase.BaseMvpFragment;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.adapter.wanandroid.CollectListAdapter;
import com.robin.robin_wanandroid.mvp.contract.wanandroid.CollectContract;
import com.robin.robin_wanandroid.mvp.model.bean.GetCollectBean;
import com.robin.robin_wanandroid.mvp.presenter.wanandroid.CollectPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class CollectFragment extends BaseMvpFragment<CollectPresenter> implements CollectContract.View {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private CollectListAdapter mCollectListAdapter;
    private List<GetCollectBean.DataBean.DatasBean> datas=new ArrayList<>();

    public CollectFragment() {
    }

//    @Override
//    protected void lazyLoadData() {
//        Logger.i("lay collect");
//  mPresenter.getCollectList(1,false);
//    }

    @Override
    public View initView(@NonNull View view, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRecyclerView=view.findViewById(R.id.collect_rv);
        mSwipeRefreshLayout=view.findViewById(R.id.collect_srl);
        return view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(linearLayoutManager);
mCollectListAdapter=new CollectListAdapter(R.layout.item_collect_list,datas);
mCollectListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }
});
mRecyclerView.setAdapter(mCollectListAdapter);
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_collect;
    }

    @Override
    public void setCollectList(GetCollectBean bean, boolean isRefresh) {
        Logger.i("data collect"+bean.getData().getDatas().size());

        mCollectListAdapter.setNewData(bean.getData().getDatas());
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

//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        Logger.i("status: chiled"+hidden);
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        Logger.i("status: chiled resume");
//
//    }
}
