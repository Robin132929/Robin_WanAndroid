package com.robin.robin_wanandroid.ui.wanandroid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.layoutmanager.FlowLayoutManager;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.adapter.wanandroid.KnowledgeListAdapter;
import com.robin.robin_wanandroid.adapter.wanandroid.KnowledgeListChildAdapter;
import com.robin.robin_wanandroid.adapter.wanandroid.KnowledgeStruteItemAdapter;
import com.robin.robin_wanandroid.base.RobinBaseFragment;
import com.robin.robin_wanandroid.customize_interface.ScrollTopListener;
import com.robin.robin_wanandroid.mvp.contract.wanandroid.KnowledgeStructureContract;
import com.robin.robin_wanandroid.mvp.model.bean.BannerBean;
import com.robin.robin_wanandroid.mvp.model.bean.KnowledgeArticleBean;
import com.robin.robin_wanandroid.mvp.model.bean.KnowledgeStructureBean;
import com.robin.robin_wanandroid.mvp.presenter.wanandroid.KnowledgeStructurePresenter;
import com.robin.robin_wanandroid.ui.content.ContentActivity;
import com.robin.robin_wanandroid.util.loading.Gloading;
import com.robin.robin_wanandroid.widget.BannerHolderView;
import com.robin.robin_wanandroid.widget.CustomPopupWindow;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class KnowledgeStructureFragment extends RobinBaseFragment<KnowledgeStructurePresenter> implements KnowledgeStructureContract.View, ScrollTopListener {
    RecyclerView konwledge_rv;
    RecyclerView konwledge_child_rv;
    RecyclerView konwledge_list_rv;
    ToggleButton tb_show_window;
    ToggleButton tb_show_child_window;
    ToggleButton btn_expand;
    ToggleButton btn_child_expand;
    ConvenientBanner<BannerBean.DataBean> convenientBanner;

    CustomPopupWindow mExpandPopupWindow;
    RecyclerView mPopopWindowRecycleView;
    CustomPopupWindow mChildExpandPopupWindow;
    RecyclerView mChildPopopWindowRecycleView;

    KnowledgeListChildAdapter knowledgeListChildAdapter;
    KnowledgeListChildAdapter knowledgeListChildAdapter_expand;
    KnowledgeStruteItemAdapter mKnowledgeStruteItemAdapter;
    KnowledgeListAdapter knowledgeListAdapter;
    KnowledgeListAdapter knowledgeListAdapter1;

    List<KnowledgeStructureBean.DataBean.ChildrenBean> mChildDataList = new ArrayList<>();
    List<KnowledgeArticleBean.DataBean.DatasBean> mArticleDataList = new ArrayList<>();
    List<KnowledgeStructureBean.DataBean> mStructureDataList = new ArrayList<>();
    @BindView(R.id.ll_konwledge_content)
    LinearLayout llKonwledgeContent;

    private CustomPopupWindow.LayoutGravity layoutGravity;

    public KnowledgeStructureFragment() {
        // Required empty public constructor
    }

    public static KnowledgeStructureFragment newInstance(String param1, String param2) {
        KnowledgeStructureFragment fragment = new KnowledgeStructureFragment();
        return fragment;
    }

    @Override
    public void initView(@NonNull View view, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        konwledge_rv = view.findViewById(R.id.konwledge_rv);
        konwledge_child_rv = view.findViewById(R.id.konwledge_child_rv);
        konwledge_list_rv = view.findViewById(R.id.konwledge_list_rv);

        tb_show_window = view.findViewById(R.id.tb_show_window);
        tb_show_child_window = view.findViewById(R.id.tb_show_child_window);
        convenientBanner = view.findViewById(R.id.nav_banner);

        View contentView = LayoutInflater.from(mContext).inflate(R.layout.popup_list, null, false);

        mExpandPopupWindow = CustomPopupWindow.builder()
                .contentView(contentView)
                .isFocus(true)
                .parentView(konwledge_list_rv)
                .setHeight(500)
                .setWidth(ViewGroup.LayoutParams.MATCH_PARENT)
                .customListener(new CustomPopupWindow.CustomPopupWindowListener() {
                    @Override
                    public void initPopupView(View contentView) {
                        btn_expand = contentView.findViewById(R.id.expand_tb);
                        btn_expand.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if (isChecked) {
                                    mExpandPopupWindow.dismiss();
                                }
                            }
                        });
                        mPopopWindowRecycleView = contentView.findViewById(R.id.data_list);
                        mPopopWindowRecycleView.setLayoutManager(new FlowLayoutManager());
                        knowledgeListAdapter1 = new KnowledgeListAdapter(R.layout.top_nav_item, mStructureDataList);
                        mPopopWindowRecycleView.setAdapter(knowledgeListAdapter1);
                    }
                }).build();
        mExpandPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                btn_expand.setChecked(false);
                tb_show_window.setChecked(false);
            }
        });

        View contentView1 = LayoutInflater.from(mContext).inflate(R.layout.popup_list, null, false);
        mChildExpandPopupWindow = CustomPopupWindow.builder()
                .contentView(contentView1)
                .isFocus(true)
                .parentView(konwledge_child_rv)
                .setHeight(500)
                .setWidth(ViewGroup.LayoutParams.MATCH_PARENT)
                .customListener(new CustomPopupWindow.CustomPopupWindowListener() {
                    @Override
                    public void initPopupView(View contentView) {
                        btn_child_expand = contentView.findViewById(R.id.expand_tb);
                        btn_child_expand.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if (isChecked) {
                                    mChildExpandPopupWindow.dismiss();
                                }
                            }
                        });
                        mChildPopopWindowRecycleView = contentView.findViewById(R.id.data_list);
                        mChildPopopWindowRecycleView.setLayoutManager(new FlowLayoutManager());
                        knowledgeListChildAdapter_expand = new KnowledgeListChildAdapter(R.layout.top_nav_item, mChildDataList);
                        mChildPopopWindowRecycleView.setAdapter(knowledgeListChildAdapter_expand);
                    }
                }).build();

        mChildExpandPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                btn_child_expand.setChecked(false);
                tb_show_child_window.setChecked(false);
            }
        });
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity());
        linearLayoutManager1.setOrientation(RecyclerView.HORIZONTAL);

        konwledge_rv.setLayoutManager(linearLayoutManager);
        konwledge_child_rv.setLayoutManager(linearLayoutManager1);
        konwledge_list_rv.setLayoutManager(new LinearLayoutManager(mContext));

        layoutGravity = new CustomPopupWindow.LayoutGravity(CustomPopupWindow.LayoutGravity.ALIGN_LEFT | CustomPopupWindow.LayoutGravity.TO_BOTTOM);

        tb_show_window.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    knowledgeListAdapter1.notifyDataSetChanged();
                    mExpandPopupWindow.showBashOfAnchor(konwledge_rv, layoutGravity, 0, -konwledge_rv.getHeight());
                } else {
                    konwledge_rv.setLayoutManager(linearLayoutManager);

                }
            }
        });
        tb_show_child_window.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    knowledgeListChildAdapter_expand.notifyDataSetChanged();
                    mChildExpandPopupWindow.showBashOfAnchor(konwledge_child_rv, layoutGravity, 0, -konwledge_child_rv.getHeight());
                } else {
                    konwledge_child_rv.setLayoutManager(linearLayoutManager1);

                }
            }
        });


        knowledgeListAdapter = new KnowledgeListAdapter(R.layout.top_nav_item, mStructureDataList);
        knowledgeListChildAdapter = new KnowledgeListChildAdapter(R.layout.top_nav_item, mChildDataList);
        mKnowledgeStruteItemAdapter = new KnowledgeStruteItemAdapter(R.layout.home_recycle_item, mArticleDataList);

        knowledgeListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                KnowledgeStructureBean.DataBean dataBean = (KnowledgeStructureBean.DataBean) adapter.getItem(position);
                Toast.makeText(mContext, "click nav1", Toast.LENGTH_LONG).show();
                knowledgeListAdapter.setSelectedPosition(position);
                knowledgeListAdapter.notifyItemChanged(position);
                knowledgeListAdapter.notifyItemChanged(knowledgeListAdapter.getLast());
                mChildDataList.clear();
                mChildDataList.addAll(dataBean.getChildren());
                mPresenter.requestStructureItem(0, dataBean.getChildren().get(0).getId(), false);
                knowledgeListChildAdapter.notifyDataSetChanged();

            }
        });

        knowledgeListAdapter1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                KnowledgeStructureBean.DataBean dataBean = (KnowledgeStructureBean.DataBean) adapter.getItem(position);
                Toast.makeText(mContext, "click nav1", Toast.LENGTH_LONG).show();
                mChildDataList.clear();
                mChildDataList.addAll(dataBean.getChildren());
                mPresenter.requestStructureItem(0, dataBean.getChildren().get(0).getId(), false);
                knowledgeListChildAdapter.notifyDataSetChanged();
                mExpandPopupWindow.dismiss();

                mPopopWindowRecycleView.scrollToPosition(position);
                konwledge_rv.scrollToPosition(position);
            }
        });

        knowledgeListChildAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                KnowledgeStructureBean.DataBean.ChildrenBean bean = (KnowledgeStructureBean.DataBean.ChildrenBean) adapter.getItem(position);
                mPresenter.requestStructureItem(0, bean.getId(), false);
            }
        });

        knowledgeListChildAdapter_expand.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                KnowledgeStructureBean.DataBean.ChildrenBean bean = (KnowledgeStructureBean.DataBean.ChildrenBean) adapter.getItem(position);
                mPresenter.requestStructureItem(0, bean.getId(), false);
                mChildExpandPopupWindow.dismiss();
                mChildPopopWindowRecycleView.scrollToPosition(position);
                konwledge_child_rv.scrollToPosition(position);
            }
        });

        mKnowledgeStruteItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                KnowledgeArticleBean.DataBean.DatasBean bean = (KnowledgeArticleBean.DataBean.DatasBean) adapter.getItem(position);
                ContentActivity.startActivity(mContext, bean.getTitle(), bean.getLink());
            }
        });
        konwledge_rv.setAdapter(knowledgeListAdapter);
        konwledge_child_rv.setAdapter(knowledgeListChildAdapter);
        konwledge_list_rv.setAdapter(mKnowledgeStruteItemAdapter);

    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_knowledge_structure;
    }

    @Override
    public void showLoading() {
        Logger.i("konw re show");
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
        knowledgeListAdapter.setNewData(bean.getData());
        knowledgeListAdapter1.setNewData(bean.getData());
    }

    @Override
    public void setStructureList2st(List<KnowledgeStructureBean.DataBean> dataBean) {
        mChildDataList.addAll(dataBean.get(0).getChildren());
        knowledgeListChildAdapter.notifyDataSetChanged();
        knowledgeListChildAdapter_expand.notifyDataSetChanged();
    }

    @Override
    public void setStructureItem(KnowledgeArticleBean bean, int page, boolean refresh) {
        if ((refresh && bean.getData().getSize() > 0)) {
            mKnowledgeStruteItemAdapter.addData(bean.getData().getDatas());
            mKnowledgeStruteItemAdapter.loadMoreComplete();
        } else {
            mArticleDataList.clear();
            mArticleDataList.addAll(bean.getData().getDatas());
            mKnowledgeStruteItemAdapter.notifyDataSetChanged();
        }
        if (bean.getData().isOver()) {
            mKnowledgeStruteItemAdapter.loadMoreEnd();
        }
        mKnowledgeStruteItemAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (bean.getData().isOver()) {
                    mKnowledgeStruteItemAdapter.setEnableLoadMore(false);
                } else {
                    mPresenter.requestStructureItem(bean.getData().getCurPage(), bean.getData().getDatas().get(0).getChapterId(), true);
                }
            }
        }, konwledge_list_rv);

    }

    @Override
    public void setBanner(BannerBean banner) {
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

//    @Override
//    protected void lazyLoadData() {
//        Logger.i(" lazyLoadData:");
//        mPresenter.requestStructureList();
//        mPresenter.requestBanner();
//    }

    @Override
    public void scroll2Top() {
        konwledge_rv.scrollToPosition(0);
    }

    @Override
    protected void initLoadingStatusViewIfNeed() {
        if (mHolder == null) {
            //bind status view to activity root view by default
            mHolder = Gloading.getDefault().cover(llKonwledgeContent).withRetry(new Runnable() {
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
        Logger.e("fragment  pause "+this.toString());

    }
}
