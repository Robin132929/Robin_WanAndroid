package com.robin.robin_wanandroid.ui.wanandroid.home.fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.adapter.ImageNetAdapter;
import com.robin.robin_wanandroid.adapter.wanandroid.myViewPagerAdapter;
import com.robin.robin_wanandroid.base.RobinBaseFragment;
import com.robin.robin_wanandroid.mvp.contract.wanandroid.WanAndroidMainFragmentContract;
import com.robin.robin_wanandroid.mvp.model.bean.BannerBean;
import com.robin.robin_wanandroid.mvp.presenter.wanandroid.WanAndroidMainFragmentPresenter;
import com.robin.robin_wanandroid.ui.wanandroid.conveniententrance.ConvenientEntranceActivity;
import com.robin.robin_wanandroid.ui.wanandroid.conveniententrance.fragment.InterviewFragment;
import com.robin.robin_wanandroid.ui.wanandroid.conveniententrance.fragment.SquareFragment;
import com.robin.robin_wanandroid.ui.wanandroid.conveniententrance.fragment.TodoFragment;
import com.robin.robin_wanandroid.ui.wanandroid.conveniententrance.fragment.WendaFragment;
import com.robin.robin_wanandroid.util.loading.Gloading;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.OnClick;

public class WanAndroidMainFragment extends RobinBaseFragment<WanAndroidMainFragmentPresenter>
        implements WanAndroidMainFragmentContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.section_head_ll)
    RelativeLayout sectionHeadLl;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.tl_tabs)
    TabLayout mTabLayout;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.vp_content)
    ViewPager mViewPager;
    @BindView(R.id.cl_wanandroid_content)
    CoordinatorLayout clWanandroidContent;

    private ImageNetAdapter bannerAdapter;

    public WanAndroidMainFragment() {
    }

    public static WanAndroidMainFragment newInstance() {
        WanAndroidMainFragment fragment = new WanAndroidMainFragment();
        return fragment;
    }

    @Override
    public void initView(@NonNull View view, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //初始化toolbar
        if (toolbar != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        }
        setHasOptionsMenu(true);
        banner.setBannerRound2(20.0f);

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mViewPager.setAdapter(new myViewPagerAdapter(getChildFragmentManager()));
        mViewPager.setOffscreenPageLimit(4);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.selectTab(mTabLayout.getTabAt(1));
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_wanandroid;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }


    @Override
    protected void initLoadingStatusViewIfNeed() {
        if (mHolder == null) {
            mHolder = Gloading.getDefault().wrap(clWanandroidContent).withRetry(this::onLoadRetry);
        }
    }

    @Override
    protected void onLoadRetry() {
      //TODO 重试
    }

    @Override
    public void showBanner(BannerBean bannerBean) {
        if (bannerAdapter == null) {
            bannerAdapter = new ImageNetAdapter(bannerBean.getData());
            banner.setAdapter(bannerAdapter);
            banner.setIndicator(new CircleIndicator(mContext));
            return;
        }
        banner.setDatas(bannerBean.getData());
    }

    @Override
    public void showLoading() {
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

    @OnClick({R.id.bt_daily, R.id.bt_review, R.id.bt_score, R.id.bt_more, R.id.search_btn})
    public void onBtClick(View view) {
        switch (view.getId()) {
            case R.id.bt_daily:
                ConvenientEntranceActivity.onStartActivty(WendaFragment.class,mContext);
                break;
            case R.id.bt_review:
                ConvenientEntranceActivity.onStartActivty(InterviewFragment.class,mContext);
                break;
            case R.id.bt_score:
                ConvenientEntranceActivity.onStartActivty(SquareFragment.class,mContext);
                break;
            case R.id.bt_more:
                ConvenientEntranceActivity.onStartActivty(TodoFragment.class,mContext);
                break;
            case R.id.search_btn:
                break;
            default:
                break;
        }
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        showLoading();
        mPresenter.getBannerData();
    }
}
