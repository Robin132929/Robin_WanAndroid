package com.robin.robin_wanandroid.ui.home.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import io.reactivex.functions.Consumer;

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
import com.robin.common_customize_ui.multis_status_view.MultiStateView;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.MvpBase.BaseLazyLoadFragment;
import com.robin.robin_wanandroid.contanst.Constant;
import com.robin.robin_wanandroid.ui.content.ContentActivity;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.ui.conveniententrance.activity.ConvenientEntranceActivity;
import com.robin.robin_wanandroid.adapter.wanandroid.HomeAdapter;
import com.robin.robin_wanandroid.annotation.CheckLogin;
import com.robin.robin_wanandroid.app.App;
import com.robin.robin_wanandroid.customize_interface.ScrollTopListener;
import com.robin.robin_wanandroid.mvp.contract.wanandroid.HomeContract;
import com.robin.robin_wanandroid.mvp.model.bean.BannerBean;
import com.robin.robin_wanandroid.mvp.model.bean.MainArticleBean;
import com.robin.robin_wanandroid.mvp.presenter.wanandroid.HomePresenter;
import com.robin.robin_wanandroid.rx.FootPrintEvent;
import com.robin.robin_wanandroid.rx.RxBus;
import com.robin.robin_wanandroid.widget.BannerHolderView;
import com.robin.robin_wanandroid.widget.CustomPopupWindow;

//TODO 懒加载 已实现2019-10-23
public class HomeFragment extends BaseLazyLoadFragment<HomePresenter> implements HomeContract.View, View.OnClickListener, ScrollTopListener {
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
    private CustomPopupWindow loadingView;
    private BaseQuickAdapter mHomeAdapter;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private MultiStateView mMultiStateView;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }


    @Override
    public void initView(@NonNull View view, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.banner_view, null);
        convenientBanner = linearLayout.findViewById(R.id.convenient_banner);
        linearLayout.removeView(convenientBanner);

        mRecyclerView = view.findViewById(R.id.home_rv);
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        mMultiStateView=view.findViewById(R.id.home_fragment_msv);
        head_LinearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.home_section_head, null);
        head_LinearLayout_1 = (LinearLayout) getLayoutInflater().inflate(R.layout.home_head_2_layout, null);
        img1 = head_LinearLayout.findViewById(R.id.img1);
        img2 = head_LinearLayout.findViewById(R.id.img2);
        img3 = head_LinearLayout.findViewById(R.id.img3);
        img4 = head_LinearLayout.findViewById(R.id.img4);
        img5 = head_LinearLayout.findViewById(R.id.img5);

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

        RxBusSubscriber();


        mRecyclerView.setAdapter(mHomeAdapter);

        mHomeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MainArticleBean.DataBean.DatasBean data = (MainArticleBean.DataBean.DatasBean) adapter.getItem(position);
                ContentActivity.startActivity(App.getmMyAppComponent().application(),data.getTitle(), data.getLink());
            }
        });
        mHomeAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Logger.i("iscollect : click ");
                MainArticleBean.DataBean.DatasBean bean= (MainArticleBean.DataBean.DatasBean) adapter.getItem(position);
                if (view.getId()==R.id.iv_like){
                        collectAction(position, bean);
                }
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
                mPresenter.requestArticle(0, false);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }
    @CheckLogin(action = CheckLogin.Action.JUMP)
    private void collectAction(int position, MainArticleBean.DataBean.DatasBean bean) {
        bean.setCollect(!bean.isCollect());
        mHomeAdapter.setData(position,bean);
        Logger.i("iscollect "+bean.isCollect());
        if (bean.isCollect()) {
            mPresenter.addCollectArticle(bean.getId());
        } else {
            mPresenter.cancelCollectArticle(bean.getId());
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void showLoading() {
       Logger.e("multistatusview :"+(mMultiStateView==null));
        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
    }

    @Override
    public void hideLoading() {

        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
    }

    @Override
    public void showError() {
        if (mMultiStateView.getViewState()==MultiStateView.VIEW_STATE_ERROR){
            return;
        }
        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);

    }

//    @Override
//    public void scollerToTop() {
//        if (linearLayoutManager.findFirstVisibleItemPosition() > 20) {
//            mRecyclerView.scrollToPosition(0);
//        } else {
//            mRecyclerView.smoothScrollToPosition(0);
//        }
//
//    }

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
                        ContentActivity.startActivity(App.getmMyAppComponent().application(),banner.getData().get(position).getTitle(), banner.getData().get(position).getUrl());
                    }
                });
        convenientBanner.startTurning();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img1:
                ConvenientEntranceActivity.onStartActivty(Constant.WEN_DA,getContext());
                Toast.makeText(getContext(), "每日问答", Toast.LENGTH_LONG).show();
                break;
            case R.id.img2:
                Toast.makeText(getContext(), "面试集锦", Toast.LENGTH_LONG).show();
                break;
            case R.id.img3:
                Toast.makeText(getContext(), "常用网址", Toast.LENGTH_LONG).show();
                break;
            case R.id.img4:
                Toast.makeText(getContext(), "积分墙", Toast.LENGTH_LONG).show();
                break;
            case R.id.img5:
                Toast.makeText(getContext(), "更多", Toast.LENGTH_LONG).show();
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

    @Override
    public void lazyLoadData() {
        mPresenter.requestBanner(false);
        mPresenter.requestTopArticle();
        mPresenter.requestArticle(0,false);
    }

    @Override
    public void scroll2Top() {
        if (linearLayoutManager.findFirstVisibleItemPosition() > 20) {
            mRecyclerView.scrollToPosition(0);
        } else {
            mRecyclerView.smoothScrollToPosition(0);
        }
    }

    public void RxBusSubscriber(){
        RxBus.getInstance().toObservable(this, FootPrintEvent.class).subscribe(new Consumer<FootPrintEvent>() {
            @Override
            public void accept(FootPrintEvent footPrintEvent) throws Exception {
                Logger.i(" rxbus is get the event");
                mPresenter.addFootPrint(footPrintEvent.title,footPrintEvent.url);
            }
        });

    }

}
