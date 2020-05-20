package com.robin.robin_wanandroid.ui.wanandroid.explore;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;
import com.robin.rbase.CommonUtils.ArmsUtils;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.CommonUtils.Utils.Utils;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.adapter.wanandroid.KnowledgeListAdapter;
import com.robin.robin_wanandroid.adapter.wanandroid.KnowledgeListChildAdapter;
import com.robin.robin_wanandroid.adapter.wanandroid.KnowledgeStruteItemAdapter;
import com.robin.robin_wanandroid.app.App;
import com.robin.robin_wanandroid.base.RobinBaseFragment;
import com.robin.robin_wanandroid.customize_interface.ScrollTopListener;
import com.robin.robin_wanandroid.mvp.contract.wanandroid.KnowledgeStructureContract;
import com.robin.robin_wanandroid.mvp.model.bean.BannerBean;
import com.robin.robin_wanandroid.mvp.model.bean.KnowledgeArticleBean;
import com.robin.robin_wanandroid.mvp.model.bean.KnowledgeStructureBean;
import com.robin.robin_wanandroid.mvp.presenter.wanandroid.KnowledgeStructurePresenter;
import com.robin.robin_wanandroid.ui.content.ContentActivity;
import com.robin.robin_wanandroid.util.loading.Gloading;
import com.robin.robin_wanandroid.widget.BaseBottomSheetDialog;
import com.robin.robin_wanandroid.widget.CustomPopupWindow;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

import static com.google.android.material.bottomsheet.BottomSheetBehavior.PEEK_HEIGHT_AUTO;

public class KnowledgeStructureFragment extends RobinBaseFragment<KnowledgeStructurePresenter> implements KnowledgeStructureContract.View, ScrollTopListener {

    @BindView(R.id.tb_show_detail)
    ImageView tbShowDetail;
    @BindView(R.id.rv_konwledge_content)
    RecyclerView rvKonwledgeContent;
    @BindView(R.id.rl_konwledge_content)
    RelativeLayout rlKonwledgeContent;
    @BindView(R.id.flex_konwledge_sub_title)
    RecyclerView flexKonwledgeSubTitle;
    @BindView(R.id.rv_konwledge_title)
    TabLayout rvKonwledgeTitle;

    private KnowledgeStruteItemAdapter mKnowledgeStruteItemAdapter;
    private KnowledgeListChildAdapter knowledgeListChildAdapter;
    private KnowledgeListAdapter knowledgeListAdapter;
    private List<KnowledgeStructureBean.DataBean.ChildrenBean> mChildDataList = new ArrayList<>();
    private List<KnowledgeArticleBean.DataBean.DatasBean> mArticleDataList = new ArrayList<>();
    private List<KnowledgeStructureBean.DataBean> mStructureDataList = new ArrayList<>();
    private BottomSheetBehavior sheetBehavior;
    private BottomSheetDialog bottomSheetDialog;
    private int curChapterId = -1;
    private int curPage = -1;
    private int mSelect = -1;

    public KnowledgeStructureFragment() {
        // Required empty public constructor
    }

    public static KnowledgeStructureFragment newInstance(String param1, String param2) {
        KnowledgeStructureFragment fragment = new KnowledgeStructureFragment();
        return fragment;
    }

    @Override
    public void initView(@NonNull View view, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //初始化tab 设置监听
        rvKonwledgeTitle.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //对应一级条目选中后加载二级条目以及二级条目对应文章
                if (mStructureDataList.size() != 0) {
                    setStructureList2st(mStructureDataList.get(tab.getPosition()));
                }

                mPresenter.requestStructureItem(0, mStructureDataList.get(tab.getPosition()).getChildren().get(0).getId(), true);
                Logger.i("requestStructureItem2222");

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //初始化二级条目显示recycleview
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(getActivity(), FlexDirection.ROW, FlexWrap.WRAP);
        flexKonwledgeSubTitle.setLayoutManager(flexboxLayoutManager);
        knowledgeListChildAdapter = new KnowledgeListChildAdapter(R.layout.top_nav_item, mChildDataList);
        knowledgeListChildAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                KnowledgeStructureBean.DataBean.ChildrenBean bean = (KnowledgeStructureBean.DataBean.ChildrenBean) adapter.getItem(position);
                GradientDrawable drawable = (GradientDrawable) view.getBackground();
                BaseViewHolder hodler = (BaseViewHolder) flexKonwledgeSubTitle.findViewHolderForLayoutPosition(mSelect);
                KnowledgeStructureBean.DataBean.ChildrenBean lastselectBean=  ( (KnowledgeStructureBean.DataBean.ChildrenBean) adapter.getItem(mSelect));
                if (lastselectBean!=null){
                    lastselectBean.setSelect(false);
                }
                if (hodler != null) {
                    ((GradientDrawable) hodler.getView(R.id.show_text).getBackground()).setColor(mContext.getResources().getColor(R.color.Grey200));
                } else {
                    adapter.notifyItemChanged(mSelect);
                }
                mSelect=position;
                bean.setSelect(true);
                    drawable.setColor(mContext.getResources().getColor(R.color.Red));

                mPresenter.requestStructureItem(0, bean.getId(), true);
                curPage=-1;
                Logger.i("requestStructureItem11111");
            }
        });
        flexKonwledgeSubTitle.setAdapter(knowledgeListChildAdapter);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        //初始化条目下文章recycleview
        mKnowledgeStruteItemAdapter = new KnowledgeStruteItemAdapter(R.layout.home_recycle_item, mArticleDataList);
        mKnowledgeStruteItemAdapter.setOnItemClickListener((adapter, view, position) -> {
            KnowledgeArticleBean.DataBean.DatasBean data =
                    (KnowledgeArticleBean.DataBean.DatasBean) adapter.getItem(position);
            ContentActivity.startActivity(
                    App.getmMyAppComponent().application(), data.getTitle(), data.getLink());
        });
//        mKnowledgeStruteItemAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//                    mPresenter.requestStructureItem(curPage++,curChapterId, true);
//                Logger.i("requestStructureItem333333");
//
//            }
//        }, rvKonwledgeContent);

        rvKonwledgeContent.setLayoutManager(new LinearLayoutManager(mContext));
        rvKonwledgeContent.setAdapter(mKnowledgeStruteItemAdapter);
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_knowledge_structure;
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
    public void setStructureList1st(KnowledgeStructureBean bean) {
        Logger.i("KnowledgeStructureBean size is :" + bean.getData().size());
        mStructureDataList.addAll(bean.getData());
        for (KnowledgeStructureBean.DataBean datum : bean.getData()) {
            rvKonwledgeTitle.addTab(rvKonwledgeTitle.newTab().setText(datum.getName()));
        }
    }

    @Override
    public void setStructureList2st(KnowledgeStructureBean.DataBean dataBean) {
        mChildDataList.clear();
        mChildDataList.addAll(dataBean.getChildren());
        knowledgeListChildAdapter.notifyDataSetChanged();
    }

    @Override
    public void setStructureItem(KnowledgeArticleBean bean, int page, boolean refresh) {
        //todo crash bug
        if (bean.getData().isOver()) {
//            mKnowledgeStruteItemAdapter.loadMoreEnd();
        }
        int newChapterId = bean.getData().getDatas().get(0).getChapterId();
        curPage = bean.getData().getCurPage();
        if (curChapterId != newChapterId) {
            curChapterId = newChapterId;
            mArticleDataList.clear();
            mArticleDataList.addAll(bean.getData().getDatas());
            mKnowledgeStruteItemAdapter.notifyDataSetChanged();
            return;
        }

        mArticleDataList.addAll(bean.getData().getDatas());
        mKnowledgeStruteItemAdapter.notifyDataSetChanged();


    }

    @Override
    public void scroll2Top() {
        rvKonwledgeContent.scrollToPosition(0);
    }

    @Override
    protected void initLoadingStatusViewIfNeed() {
        if (mHolder == null) {
            //bind status view to activity root view by default
            mHolder = Gloading.getDefault().cover(rlKonwledgeContent).withRetry(this::onLoadRetry);
        }
    }

    @Override
    protected void onLoadRetry() {
        mPresenter.requestStructureList();
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        mPresenter.requestStructureList();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @OnClick(R.id.tb_show_detail)
    public void setTbShowDetail() {
        Logger.i("tb_show_detail ");
        View view = View.inflate(mContext, R.layout.dialog_bottomsheet, null);
        RecyclerView recyclerView = view.findViewById(R.id.rv_konwledge_top_detail);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 4);
        recyclerView.setLayoutManager(gridLayoutManager);
//            ImageView click=view.findViewById(R.id.iv_konwledge_top_detail);
//            click.setOnClickListener(v -> bottomSheetDialog.dismiss());
        knowledgeListAdapter = new KnowledgeListAdapter(R.layout.top_nav_item, mStructureDataList);
        recyclerView.setAdapter(knowledgeListAdapter);
        bottomSheetDialog = new BaseBottomSheetDialog(mContext);

        bottomSheetDialog.setContentView(view);
        sheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        sheetBehavior.setPeekHeight(ArmsUtils.getScreenHeidth(mContext));

        bottomSheetDialog.show();

    }
}
