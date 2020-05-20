package com.robin.robin_wanandroid.ui.wanandroid.explore;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.adapter.wanandroid.WeChatVP2Adapter;
import com.robin.robin_wanandroid.adapter.wanandroid.WechatContentAdapter;
import com.robin.robin_wanandroid.adapter.wanandroid.WechatTitleAdapter;
import com.robin.robin_wanandroid.base.RobinBaseFragment;
import com.robin.robin_wanandroid.customize_interface.ScrollTopListener;
import com.robin.robin_wanandroid.mvp.contract.wanandroid.WechatContract;
import com.robin.robin_wanandroid.mvp.model.bean.WechatContentBean;
import com.robin.robin_wanandroid.mvp.model.bean.WechatTitleBean;
import com.robin.robin_wanandroid.mvp.presenter.wanandroid.WechatPresenter;
import com.robin.robin_wanandroid.ui.content.ContentActivity;
import com.robin.robin_wanandroid.util.loading.Gloading;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import butterknife.BindView;

public class WechatFragment extends RobinBaseFragment<WechatPresenter>
        implements WechatContract.View, ScrollTopListener {
    //ui
    @BindView(R.id.ll_wechat_content)
    LinearLayout llWechatContent;
    @BindView(R.id.tl_title)
    TabLayout tlTitle;
    @BindView(R.id.vp2_content)
    ViewPager2 vp2Content;

    private WeChatVP2Adapter weChatVP2Adapter;

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
    public void initView(@NonNull View view, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
//        mWechatTitleAdapter = new WechatTitleAdapter(R.layout.wechat_title_item, title_list);
//        mWechatTitleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                WechatTitleBean.DataBean bean = (WechatTitleBean.DataBean) adapter.getItem(position);
//                mPresenter.requestWechatContent(bean.getId(), 1, false);
//            }
//        });
//        title_recycleView.setLayoutManager(linearLayoutManager);
//        title_recycleView.setAdapter(mWechatTitleAdapter);
//
//
//        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity());
//        linearLayoutManager1.setOrientation(RecyclerView.VERTICAL);
//        mWechatContentAdapter = new WechatContentAdapter(R.layout.wechat_recycle_item, content_list);
//        mWechatContentAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//                mPresenter.requestWechatContent(ID, PAGE + 1, true);
//            }
//        }, content_recycleView);
//        mWechatContentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                WechatContentBean.DataBean.DatasBean bean = (WechatContentBean.DataBean.DatasBean) adapter.getItem(position);
//                ContentActivity.startActivity(mContext, bean.getTitle(), bean.getLink());
//            }
//        });
//        content_recycleView.setLayoutManager(linearLayoutManager1);
//        content_recycleView.setAdapter(mWechatContentAdapter);


    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_wechat;
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

    @Override
    public void setWechatTitle(WechatTitleBean bean) {
        title_list.addAll(bean.getData());
        weChatVP2Adapter=new WeChatVP2Adapter(this,14);
        vp2Content.setAdapter(weChatVP2Adapter);

        new TabLayoutMediator(tlTitle,vp2Content,((tab, position) -> {
            tab.setText(title_list.get(position).getName()+" "+position );
        })).attach();
//        mWechatTitleAdapter.addData(title_list);
    }

    @Override
    public void setWechatContent(WechatContentBean bean, boolean isRefresh) {
//        ID = bean.getData().getDatas().get(0).getChapterId();
//        PAGE = bean.getData().getCurPage();
//        if (bean.getData().isOver()) {
//            mWechatContentAdapter.loadMoreEnd();
//        }
//        if (isRefresh) {
//            content_list.addAll(bean.getData().getDatas());
//            mWechatContentAdapter.addData(content_list);
//            mWechatContentAdapter.loadMoreComplete();
//        } else {
//            content_list.clear();
//            content_list.addAll(bean.getData().getDatas());
//            mWechatContentAdapter.addData(content_list);
//        }
        Logger.i("siaze : " + bean.getData().getDatas().size());
    }

//    @Override
//    protected void lazyLoadData() {
//        mPresenter.requestWechatTitle();
//        mPresenter.requestWechatContent(408,1,false);
//    }

    @Override
    public void scroll2Top() {
//        content_recycleView.scrollToPosition(0);
    }

    @Override
    protected void initLoadingStatusViewIfNeed() {
        if (mHolder == null) {
            //bind status view to activity root view by default
            mHolder = Gloading.getDefault().wrap(vp2Content).withRetry(new Runnable() {
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

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        mPresenter.requestWechatTitle();

        mPresenter.requestWechatContent(408,1,false);
    }
}
