package com.robin.robin_wanandroid.ui.wanandroid.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.adapter.ImageNetAdapter;
import com.robin.robin_wanandroid.adapter.wanandroid.HomeAdapter;
import com.robin.robin_wanandroid.annotation.CheckLogin;
import com.robin.robin_wanandroid.app.App;
import com.robin.robin_wanandroid.base.RobinBaseFragment;
import com.robin.robin_wanandroid.contanst.Constant;
import com.robin.robin_wanandroid.customize_interface.ScrollTopListener;
import com.robin.robin_wanandroid.mvp.contract.wanandroid.HomeContract;
import com.robin.robin_wanandroid.mvp.model.bean.BannerBean;
import com.robin.robin_wanandroid.mvp.model.bean.MainArticleBean;
import com.robin.robin_wanandroid.mvp.presenter.wanandroid.HomePresenter;
import com.robin.robin_wanandroid.rx.FootPrintEvent;
import com.robin.robin_wanandroid.rx.RxBus;
import com.robin.robin_wanandroid.ui.content.ContentActivity;
import com.robin.robin_wanandroid.ui.conveniententrance.activity.ConvenientEntranceActivity;
import com.robin.robin_wanandroid.util.loading.Gloading;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import io.reactivex.functions.Consumer;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

//TODO 懒加载
public class WanAndroidHomeFragment extends RobinBaseFragment<HomePresenter> implements HomeContract.View, View.OnClickListener, ScrollTopListener {
    private static int index = 0;
    private LinearLayoutManager linearLayoutManager;
    private ConvenientBanner<BannerBean.DataBean> convenientBanner;
    private LinearLayout head_LinearLayout;
    private LinearLayout head_LinearLayout_1;
    @BindView(R.id.home_rv)
    RecyclerView mRecyclerView;

    private BaseQuickAdapter mHomeAdapter;

    public WanAndroidHomeFragment() {
        // Required empty public constructor
    }

    public static WanAndroidHomeFragment newInstance() {
        WanAndroidHomeFragment fragment = new WanAndroidHomeFragment();
        return fragment;
    }

    @Override
    public void initView(@NonNull View view, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mHomeAdapter = new HomeAdapter(R.layout.home_recycle_item);

        LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.banner_view, null);
//        convenientBanner = linearLayout.findViewById(R.id.convenient_banner);
//        linearLayout.removeView(convenientBanner);
//        head_LinearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.home_section_head, null);
        head_LinearLayout_1 = (LinearLayout) getLayoutInflater().inflate(R.layout.home_head_2_layout, null);
        TextView tv=head_LinearLayout_1.findViewById(R.id.section_title_tv);
        Button bt=head_LinearLayout_1.findViewById(R.id.section_refresh_tv);
        head_LinearLayout_1.removeView(tv);
        head_LinearLayout_1.removeView(bt);
        mHomeAdapter.addHeaderView(tv,0,LinearLayout.HORIZONTAL);
        mHomeAdapter.addHeaderView(bt,1,LinearLayout.HORIZONTAL);
        mHomeAdapter.getHeaderLayout().setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT));

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mHomeAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
//        mHomeAdapter.setHeaderView(convenientBanner);
//        mHomeAdapter.setHeaderView(head_LinearLayout, 1);
//        mHomeAdapter.setHeaderView(head_LinearLayout_1, 2);


        RxBusSubscriber();

        mRecyclerView.setAdapter(mHomeAdapter);

        mHomeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MainArticleBean.DataBean.DatasBean data = (MainArticleBean.DataBean.DatasBean) adapter.getItem(position);
                ContentActivity.startActivity(App.getmMyAppComponent().application(), data.getTitle(), data.getLink());
            }
        });
        mHomeAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Logger.i("iscollect : click ");
                MainArticleBean.DataBean.DatasBean bean = (MainArticleBean.DataBean.DatasBean) adapter.getItem(position);
                if (view.getId() == R.id.iv_like) {
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
//
//        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                mPresenter.requestArticle(0, false);
//                mSwipeRefreshLayout.setRefreshing(false);
//            }
//        });
        mPresenter.requestBanner(false);
//        requestTopArticle();
        mPresenter.requestArticle(0, false);
    }

    @CheckLogin(action = CheckLogin.Action.JUMP)
    private void collectAction(int position, MainArticleBean.DataBean.DatasBean bean) {
        bean.setCollect(!bean.isCollect());
        mHomeAdapter.setData(position, bean);
        Logger.i("iscollect " + bean.isCollect());
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
        Logger.e("gload multistatusview :");
        showLoadingView();
    }

    @Override
    public void hideLoading() {
        showLoadSuccess();
    }

    @Override
    public void showError() {
        showLoadFailed();
    }

    @Override
    public void setArticle(MainArticleBean.DataBean dataBean, boolean isRefresh) {
        index = dataBean.getCurPage();
        if (isRefresh && dataBean.isOver()) {
            mHomeAdapter.loadMoreEnd();
        }
        if (!isRefresh) {
            mHomeAdapter.setNewData(dataBean.getDatas());
        }
        mHomeAdapter.addData(dataBean.getDatas());
        mHomeAdapter.loadMoreComplete();
    }

    @Override
    public void setBanner(BannerBean bannerbean, boolean isrefresh) {
        Logger.i( "setBanner: "+bannerbean.getData().get(0).getImagePath());
        ImageNetAdapter adapter = new ImageNetAdapter(bannerbean.getData());
//        banner.setAdapter(adapter);
//        banner.setIndicator(new CircleIndicator(mContext));
//        convenientBanner.setPages(new CBViewHolderCreator() {
//            @Override
//            public Holder createHolder(View itemView) {
//                return new BannerHolderView(itemView, getContext());
//            }
//
//            @Override
//            public int getLayoutId() {
//                return R.layout.item_banner_view;
//            }
//        }, banner.getData()).setPageIndicator(new int[]{R.drawable.ic_circle_normal, R.drawable.ic_circle_press})
//                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
//                .setOnItemClickListener(new OnItemClickListener() {
//                    @Override
//                    public void onItemClick(int position) {
//                        ContentActivity.startActivity(App.getmMyAppComponent().application(), banner.getData().get(position).getTitle(), banner.getData().get(position).getUrl());
//                    }
//                });
//        convenientBanner.startTurning();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img1:
                ConvenientEntranceActivity.onStartActivty(Constant.WEN_DA, getContext());
                Toast.makeText(getContext(), "每日问答", Toast.LENGTH_LONG).show();
                break;
            case R.id.img2:
                Toast.makeText(getContext(), "面试集锦", Toast.LENGTH_LONG).show();
                break;
//            case R.id.img3:
//                Toast.makeText(getContext(), "常用网址", Toast.LENGTH_LONG).show();
//                break;
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
//        mHomeAdapter.setNewData(null);
    }

//    @Override
//    public void lazyLoadData() {
//        Logger.i("aaaa lazy");
//        mPresenter.requestBanner(false);
//        mPresenter.requestTopArticle();
//        mPresenter.requestArticle(0,false);
//    }

    @Override
    public void scroll2Top() {
        if (linearLayoutManager.findFirstVisibleItemPosition() > 20) {
            mRecyclerView.scrollToPosition(0);
        } else {
            mRecyclerView.smoothScrollToPosition(0);
        }
    }

    public void RxBusSubscriber() {
        RxBus.getInstance().toObservable(this, FootPrintEvent.class).subscribe(new Consumer<FootPrintEvent>() {
            @Override
            public void accept(FootPrintEvent footPrintEvent) throws Exception {
                Logger.i(" rxbus is get the event");
                mPresenter.addFootPrint(footPrintEvent.title, footPrintEvent.url);
            }
        });

    }

    @Override
    protected void initLoadingStatusViewIfNeed() {
        if (mHolder == null) {
            //bind status view to activity root view by default
            mHolder = Gloading.getDefault().cover(mRecyclerView).withRetry(new Runnable() {
                @Override
                public void run() {
                    onLoadRetry();
                }
            });
        }
    }

    @Override
    protected void onLoadRetry() {

    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.e("fragment  pause " + this.toString());

    }
}
