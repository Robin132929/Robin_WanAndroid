package com.robin.robin_wanandroid.mvp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import q.rorbin.verticaltablayout.VerticalTabLayout;

import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.MvpBase.BaseLazyLoadFragment;
import com.robin.rbase.MVP.MvpBase.BaseMvpFragment;
import com.robin.robin_wanandroid.ContentActivity;
import com.robin.robin_wanandroid.Main4Activity;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.adapter.WechatContentAdapter;
import com.robin.robin_wanandroid.adapter.WechatTitleAdapter;
import com.robin.robin_wanandroid.entity.MyPagerAdapter;
import com.robin.robin_wanandroid.mvp.contract.WechatContract;
import com.robin.robin_wanandroid.mvp.model.bean.WechatContentBean;
import com.robin.robin_wanandroid.mvp.model.bean.WechatTitleBean;
import com.robin.robin_wanandroid.mvp.presenter.WechatPresenter;

import java.util.ArrayList;
import java.util.List;

public class WechatFragment extends BaseLazyLoadFragment<WechatPresenter> implements WechatContract.View {
    //ui
    private RecyclerView title_recycleView;
    private RecyclerView content_recycleView;

    private int ID;
    private int PAGE;

    private List<WechatTitleBean.DataBean> title_list;
    private List<WechatContentBean.DataBean.DatasBean> content_list;

    private WechatTitleAdapter mWechatTitleAdapter;
    private WechatContentAdapter mWechatContentAdapter;

    public WechatFragment() {
        // Required empty public constructor
        title_list = new ArrayList<>();
        content_list = new ArrayList<>();
    }

    public static WechatFragment newInstance() {
        WechatFragment fragment = new WechatFragment();
        return fragment;
    }

    @Override
    public View initView(@NonNull View view, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        title_recycleView = view.findViewById(R.id.title_recycle);
        content_recycleView = view.findViewById(R.id.content_recycle);
        return view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mWechatTitleAdapter = new WechatTitleAdapter(R.layout.wechat_title_item, title_list);
        mWechatTitleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                WechatTitleBean.DataBean bean = (WechatTitleBean.DataBean) adapter.getItem(position);
                mPresenter.requestWechatContent(bean.getId(), 1, false);
            }
        });
        title_recycleView.setLayoutManager(linearLayoutManager);
        title_recycleView.setAdapter(mWechatTitleAdapter);


        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity());
        linearLayoutManager1.setOrientation(RecyclerView.VERTICAL);
        mWechatContentAdapter = new WechatContentAdapter(R.layout.wechat_recycle_item, content_list);
        mWechatContentAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.requestWechatContent(ID, PAGE+1, true);
            }
        }, content_recycleView);
        mWechatContentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            WechatContentBean.DataBean.DatasBean bean= (WechatContentBean.DataBean.DatasBean) adapter.getItem(position);
                ContentActivity.startActivity(mContext,bean.getLink());
            }
        });
        content_recycleView.setLayoutManager(linearLayoutManager1);
        content_recycleView.setAdapter(mWechatContentAdapter);

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_wechat;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setWechatTitle(WechatTitleBean bean) {
        title_list.addAll(bean.getData());
        mWechatTitleAdapter.addData(title_list);
    }

    @Override
    public void setWechatContent(WechatContentBean bean, boolean isRefresh) {
        ID = bean.getData().getDatas().get(0).getChapterId();
        PAGE = bean.getData().getCurPage();
        if (bean.getData().isOver()){
            mWechatContentAdapter.loadMoreEnd();
        }
        if (isRefresh) {
            content_list.addAll(bean.getData().getDatas());
            mWechatContentAdapter.addData(content_list);
            mWechatContentAdapter.loadMoreComplete();
        } else {
            content_list.clear();
            content_list.addAll(bean.getData().getDatas());
            mWechatContentAdapter.addData(content_list);
        }
        Logger.i("siaze : " + bean.getData().getDatas().size());
    }

    @Override
    protected void lazyLoadData() {
        mPresenter.requestWechatTitle();
        mPresenter.requestWechatContent(408,1,false);
    }
}
