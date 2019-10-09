package com.robin.robin_wanandroid.mvp.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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
    CardView navlayout;
    RecyclerView recyclerView;
    List<HomeSectionMutipleItem> list = new ArrayList<>();
    LinearLayout linearLayout;
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
//        View view= inflater.inflate(R.layout.fragment_home, container, false);

        LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.banner_view, null);
        convenientBanner = linearLayout.findViewById(R.id.convenient_banner);
        linearLayout.removeView(convenientBanner);

//
//        LinearLayout bannerViewLayout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.banner_view,null);
//        convenientBanner = bannerViewLayout.findViewById(R.id.convenient_banner);
//        bannerViewLayout.removeView(convenientBanner);
//
//       recyclerView=linearLayout.findViewById(R.id.test_recycle);
//        linearLayout.removeView(recyclerView);
//        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(getActivity());
//        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),1);
//        gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
//        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerView.setLayoutManager(gridLayoutManager);
//        HomeNavgationAdapter homeNavgationAdapter=new HomeNavgationAdapter(R.layout.navagtion_recycle_item);
//        homeNavgationAdapter.addData(Arrays.asList(new NavgationBean("a"),new NavgationBean("b"),
//                new NavgationBean("c"),new NavgationBean("d")));
//        recyclerView.setAdapter(homeNavgationAdapter);
//         navlayout= (CardView) LayoutInflater.from(getActivity()).inflate(R.layout.navagtion_recycle_item,null);

//        View BannerView = getLayoutInflater().inflate(R.layout.banner_view, container, false);
//        convenientBanner=BannerView.findViewById(R.id.convenient_banner);

        mRecyclerView = view.findViewById(R.id.home_rv);
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
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
//        ConstraintLayout head_ll= (ConstraintLayout) getLayoutInflater().inflate(R.layout.home_section_head,null);
//        RelativeLayout head_ll= (RelativeLayout) getLayoutInflater().inflate(R.layout.home_section_head,null);
        LinearLayout head_ll = (LinearLayout) getLayoutInflater().inflate(R.layout.home_section_head, null);
        LinearLayout head_ll2 = (LinearLayout) getLayoutInflater().inflate(R.layout.home_head_2_layout, null);

        Button img1 = head_ll.findViewById(R.id.img1);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "aaaaaaaaaa", Toast.LENGTH_LONG).show();
            }
        });

        mHomeAdapter.setHeaderView(head_ll, 1);
        mHomeAdapter.setHeaderView(head_ll2, 2);
        mRecyclerView.setAdapter(mHomeAdapter);

        mHomeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Logger.i("click item " + position);
                MainArticleBean.DataBean.DatasBean data = (MainArticleBean.DataBean.DatasBean) adapter.getItem(position);
//                Intent intent = new Intent(getActivity(), ContentActivity.class);
//                intent.putExtra("url", data.getLink());
                ContentActivity.startActivity(App.getmMyAppComponent().application(),data.getLink());
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

//        progressBar.setVisibility(View.VISIBLE);
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
    public void setArticle(MainArticleBean.DataBean dataBean) {
        index = dataBean.getCurPage();
        Logger.i(dataBean.getDatas().toString());
//        list.add(new HomeSectionMutipleItem(true,"最新文章",true));
//        list.add(new HomeSectionMutipleItem(new HomeData(dataBean.getDatas().get(0),null ,null,null),HomeSectionMutipleItem.CONTENT));
//        list.add(new HomeSectionMutipleItem(new HomeData(dataBean.getDatas().get(1),null ,null,null),HomeSectionMutipleItem.CONTENT));
//        list.add(new HomeSectionMutipleItem(new HomeData(dataBean.getDatas().get(2),null ,null,null),HomeSectionMutipleItem.CONTENT));
//        list.add(new HomeSectionMutipleItem(new HomeData(dataBean.getDatas().get(3),null ,null,null),HomeSectionMutipleItem.CONTENT));

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

                    }
                });
        convenientBanner.startTurning();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img1:
                Toast.makeText(getContext(), "每日问答" , Toast.LENGTH_LONG).show();
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
}
