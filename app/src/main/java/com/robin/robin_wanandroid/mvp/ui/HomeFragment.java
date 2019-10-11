package com.robin.robin_wanandroid.mvp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.MvpBase.BaseMvpFragment;
import com.robin.robin_wanandroid.ContentActivity;
import com.robin.robin_wanandroid.ItemFragment;
import com.robin.robin_wanandroid.MyItemRecyclerViewAdapter;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.adapter.HomeAdapter;
import com.robin.robin_wanandroid.adapter.HomeNavgationAdapter;
import com.robin.robin_wanandroid.app.App;
import com.robin.robin_wanandroid.dummy.DummyContent;
import com.robin.robin_wanandroid.entity.HomeData;
import com.robin.robin_wanandroid.entity.HomeSectionMutipleItem;
import com.robin.robin_wanandroid.mvp.contract.HomeContract;
import com.robin.robin_wanandroid.mvp.model.bean.BannerBean;
import com.robin.robin_wanandroid.mvp.model.bean.MainArticleBean;
import com.robin.robin_wanandroid.mvp.model.bean.NavgationBean;
import com.robin.robin_wanandroid.mvp.presenter.HomePresenter;
import com.robin.robin_wanandroid.widget.BannerHolderView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends BaseMvpFragment<HomePresenter> implements HomeContract.View, View.OnClickListener {
    private static int index = 0;
    LinearLayoutManager linearLayoutManager;
    ConvenientBanner<BannerBean.DataBean> convenientBanner;

    LinearLayout head_LinearLayout;
    LinearLayout head_LinearLayout_1;
    Button img1;
    Button img2;
    Button img3;
    Button img4;
    Button img5;

    private BaseQuickAdapter mHomeAdapter;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }


    @Override
    public View initView(@NonNull View view, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.banner_view, null);
        convenientBanner = linearLayout.findViewById(R.id.convenient_banner);
        linearLayout.removeView(convenientBanner);

        mRecyclerView = view.findViewById(R.id.home_rv);
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);

        head_LinearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.home_section_head, null);
        head_LinearLayout_1 = (LinearLayout) getLayoutInflater().inflate(R.layout.home_head_2_layout, null);
        img1 = head_LinearLayout.findViewById(R.id.img1);
        img2 = head_LinearLayout.findViewById(R.id.img2);
        img3 = head_LinearLayout.findViewById(R.id.img3);
        img4 = head_LinearLayout.findViewById(R.id.img4);
        img5 = head_LinearLayout.findViewById(R.id.img5);
        showLoading();
        return view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mHomeAdapter = new HomeAdapter(R.layout.home_recycle_item);
        mHomeAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        mHomeAdapter.setHeaderView(convenientBanner);
        mHomeAdapter.setHeaderView(head_LinearLayout, 1);
        mHomeAdapter.setHeaderView(head_LinearLayout_1, 2);

        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);
        img5.setOnClickListener(this);


        mRecyclerView.setAdapter(mHomeAdapter);

        mHomeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MainArticleBean.DataBean.DatasBean data = (MainArticleBean.DataBean.DatasBean) adapter.getItem(position);
                ContentActivity.startActivity(App.getmMyAppComponent().application(), data.getLink());
            }
        });
        mHomeAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.requestArticle(index++, true);
            }
        }, mRecyclerView);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.requestArticle(index, true);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void scollerToTop() {
        if (linearLayoutManager.findFirstVisibleItemPosition() > 20) {
            mRecyclerView.scrollToPosition(0);
        } else {
            mRecyclerView.smoothScrollToPosition(0);
        }

    }

    @Override
    public void setArticle(MainArticleBean.DataBean dataBean,boolean isRefresh) {
        index = dataBean.getCurPage();
        if (isRefresh&&dataBean.isOver()){
            mHomeAdapter.loadMoreEnd();
        }
        if (!isRefresh){
            mHomeAdapter.setNewData(dataBean.getDatas());
        }
        mHomeAdapter.addData(dataBean.getDatas());
        mHomeAdapter.loadMoreComplete();
    }

    @Override
    public void setBanner(BannerBean banner, boolean isrefresh) {

        convenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Holder createHolder(View itemView) {
                return new BannerHolderView(itemView, getContext());
            }

            @Override
            public int getLayoutId() {
                return R.layout.item_banner_view;
            }
        }, banner.getData()).setPageIndicator(new int[]{R.drawable.ic_circle_normal, R.drawable.ic_circle_press})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        ContentActivity.startActivity(App.getmMyAppComponent().application(), banner.getData().get(position).getUrl());
                    }
                });
        convenientBanner.startTurning();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img1:
                Toast.makeText(getContext(), "每日问答", Toast.LENGTH_LONG).show();
                break;
            case R.id.img2:
                Toast.makeText(getContext(), "面试集锦", Toast.LENGTH_LONG).show();
                break;
            case R.id.img3:
                Toast.makeText(getContext(), "收藏", Toast.LENGTH_LONG).show();
                break;
            case R.id.img4:
                Toast.makeText(getContext(), "积分墙", Toast.LENGTH_LONG).show();
                break;
            case R.id.img5:
                Toast.makeText(getContext(), "正在建设中...", Toast.LENGTH_LONG).show();
                break;
            default:
                break;

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHomeAdapter.setNewData(null);
    }
}
