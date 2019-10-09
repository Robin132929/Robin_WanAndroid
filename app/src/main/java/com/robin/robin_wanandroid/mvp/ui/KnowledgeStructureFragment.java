package com.robin.robin_wanandroid.mvp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.layoutmanager.FlowLayoutManager;
import com.robin.rbase.CommonUtils.Logger.Logger;
import com.robin.rbase.MVP.MvpBase.BaseMvpFragment;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.adapter.KnowledgeStruteItemAdapter;
import com.robin.robin_wanandroid.adapter.KnowledgeListAdapter;
import com.robin.robin_wanandroid.adapter.KnowledgeListChildAdapter;
import com.robin.robin_wanandroid.mvp.contract.KnowledgeStructureContract;
import com.robin.robin_wanandroid.mvp.model.bean.BannerBean;
import com.robin.robin_wanandroid.mvp.model.bean.KnowledgeArticleBean;
import com.robin.robin_wanandroid.mvp.model.bean.KnowledgeStructureBean;
import com.robin.robin_wanandroid.mvp.presenter.KnowledgeStructurePresenter;
import com.robin.robin_wanandroid.widget.BannerHolderView;
import com.robin.robin_wanandroid.widget.CustomPopupWindow;

import java.util.ArrayList;
import java.util.List;

public class KnowledgeStructureFragment extends BaseMvpFragment<KnowledgeStructurePresenter> implements KnowledgeStructureContract.View {
    RecyclerView rv_list_1st;
    RecyclerView rv_list_2st;
    RecyclerView rv_content;
    ToggleButton btn_list_1st;
    ToggleButton btn_list_2st;
    ToggleButton btn_expand;
    ConvenientBanner<BannerBean.DataBean> convenientBanner;
    CustomPopupWindow mExpandPopupWindow;
    RecyclerView mPopopWindowRecycleView;

    KnowledgeListChildAdapter knowledgeListChildAdapter;
    KnowledgeStruteItemAdapter mKnowledgeStruteItemAdapter;
    KnowledgeListAdapter knowledgeListAdapter;
    KnowledgeListAdapter   knowledgeListAdapter1;

    List<KnowledgeStructureBean.DataBean.ChildrenBean> mChildDataList = new ArrayList<>();
    List<KnowledgeArticleBean.DataBean.DatasBean> mArticleDataList = new ArrayList<>();
    List<KnowledgeStructureBean.DataBean> mStructureDataList = new ArrayList<>();

    private CustomPopupWindow.LayoutGravity layoutGravity;

    public KnowledgeStructureFragment() {
        // Required empty public constructor
    }

    public static KnowledgeStructureFragment newInstance(String param1, String param2) {
        KnowledgeStructureFragment fragment = new KnowledgeStructureFragment();
        return fragment;
    }

    @Override
    public View initView(@NonNull View view, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rv_content = view.findViewById(R.id.rv_list_2);
        rv_list_1st = view.findViewById(R.id.rv_list);
        rv_list_2st = view.findViewById(R.id.rv_list_1);
        btn_list_1st = view.findViewById(R.id.btn_expand);
        btn_list_2st = view.findViewById(R.id.btn_expand_1);
        btn_list_1st.setChecked(false);
        convenientBanner=view.findViewById(R.id.nav_banner);
        View  contentView= LayoutInflater.from(mContext).inflate(R.layout.popup_list, null, false);

        mExpandPopupWindow=CustomPopupWindow.builder()
                .contentView(contentView)
                .isFocus(true)
                .parentView(rv_content)
                .setHeight(500)
                .setWidth(ViewGroup.LayoutParams.MATCH_PARENT)
                .customListener(new CustomPopupWindow.CustomPopupWindowListener() {
                    @Override
                    public void initPopupView(View contentView) {
                        btn_expand=contentView.findViewById(R.id.expand_tb);
                        btn_expand.setChecked(false);
                        btn_expand.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if (isChecked){
                                    mExpandPopupWindow.dismiss();
                                }
                            }
                        });
                     mPopopWindowRecycleView=contentView.findViewById(R.id.data_list);
                     mPopopWindowRecycleView.setLayoutManager(new FlowLayoutManager());
                     knowledgeListAdapter1 = new KnowledgeListAdapter(R.layout.top_nav_item, mStructureDataList);
                     mPopopWindowRecycleView.setAdapter(knowledgeListAdapter1);
                    }
                }).build();
//        btn_list_1st.setBackgroundColor(getResources().getColor(R.color.White));
//        btn_list_2st.setBackgroundColor(getResources().getColor(R.color.White));
        return view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity());
        linearLayoutManager1.setOrientation(RecyclerView.HORIZONTAL);

        rv_list_1st.setLayoutManager(linearLayoutManager);
        rv_list_2st.setLayoutManager(linearLayoutManager1);
        rv_content.setLayoutManager(new LinearLayoutManager(getActivity()));
        layoutGravity=new CustomPopupWindow.LayoutGravity(CustomPopupWindow.LayoutGravity.ALIGN_LEFT| CustomPopupWindow.LayoutGravity.TO_BOTTOM);


        btn_list_1st.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Logger.i(" btn is check "+isChecked);
                if (isChecked){
//                    rv_list_1st.bringToFront();  rv_list_1st.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 800));
//                   rv_list_1st.setLayoutManager(new FlowLayoutManager());
                    knowledgeListAdapter1.notifyDataSetChanged();
                    mExpandPopupWindow.showBashOfAnchor(rv_list_1st,layoutGravity,0,-rv_list_1st.getHeight());

                }else {
                    rv_list_1st.setLayoutManager(linearLayoutManager);

                }
            }
        });
        btn_list_2st.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

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
                mPresenter.requestStructureItem(0,dataBean.getChildren().get(0).getId(),false);
                knowledgeListChildAdapter.notifyDataSetChanged();

                Logger.i("state :"+ view.isClickable()  +" press "+ view.isPressed()
                        +" enable "+ view.isEnabled()+ " focus "+ view.isFocused());

            }
        });

        knowledgeListAdapter1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                KnowledgeStructureBean.DataBean dataBean = (KnowledgeStructureBean.DataBean) adapter.getItem(position);
                Toast.makeText(mContext, "click nav1", Toast.LENGTH_LONG).show();
                mChildDataList.clear();
                mChildDataList.addAll(dataBean.getChildren());
                mPresenter.requestStructureItem(0,dataBean.getChildren().get(0).getId(),false);
                knowledgeListChildAdapter.notifyDataSetChanged();
                mExpandPopupWindow.dismiss();

                mPopopWindowRecycleView.scrollToPosition(position);
                rv_list_1st.scrollToPosition(position);
            }
        });

        knowledgeListChildAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                KnowledgeStructureBean.DataBean.ChildrenBean bean= (KnowledgeStructureBean.DataBean.ChildrenBean) adapter.getItem(position);
                mPresenter.requestStructureItem(0,bean.getId(),false);
            }
        });




        rv_list_1st.setAdapter(knowledgeListAdapter);
        rv_list_2st.setAdapter(knowledgeListChildAdapter);
        rv_content.setAdapter(mKnowledgeStruteItemAdapter);

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_knowledge_structure;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setStructureList1st(KnowledgeStructureBean bean) {
        knowledgeListAdapter.setNewData(bean.getData());
        knowledgeListAdapter1.setNewData(bean.getData());
    }

    @Override
    public void setStructureList2st(List<KnowledgeStructureBean.DataBean> dataBean) {
        mChildDataList.addAll(dataBean.get(0).getChildren());
     knowledgeListChildAdapter.notifyDataSetChanged();
    }

    @Override
    public void setStructureItem(KnowledgeArticleBean bean, int page,boolean refresh) {
        if ((refresh&&bean.getData().getSize()>0)){
            mKnowledgeStruteItemAdapter.addData(bean.getData().getDatas());
            mKnowledgeStruteItemAdapter.loadMoreComplete();
        }else {
            mArticleDataList.clear();
            mArticleDataList.addAll(bean.getData().getDatas());
            mKnowledgeStruteItemAdapter.notifyDataSetChanged();
        }
        if (bean.getData().isOver()){
            mKnowledgeStruteItemAdapter.loadMoreEnd();
        }
        mKnowledgeStruteItemAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (bean.getData().isOver()){
                    mKnowledgeStruteItemAdapter.setEnableLoadMore(false);
//                    mKnowledgeStruteItemAdapter.loadMoreEnd();
                }else {
                    mPresenter.requestStructureItem(bean.getData().getCurPage(), bean.getData().getDatas().get(0).getChapterId(), true);
                }
            }
        },rv_content);
//        final int[] p = {page};
//        mKnowledgeStruteItemAdapter.replaceData(bean.getData().getDatas());
//        Logger.i("knowledge :" + bean.getData().getDatas().get(0).getChapterName());
//        mKnowledgeStruteItemAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//                if (!bean.getData().isOver()) {
//                    mKnowledgeStruteItemAdapter.setNewData(bean.getData().getDatas());
//                    mKnowledgeStruteItemAdapter.loadMoreComplete();
//                    mPresenter.requestStructureItem(++p[0], bean.getData().getDatas().get(0).getChapterId());
//                } else {
//                    mKnowledgeStruteItemAdapter.loadMoreEnd();
//                }
//            }
//        }, rv_content);

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
//    public void onAttach(Context context) {
//        super.onAttach(context);
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view= inflater.inflate(R.layout.fragment_knowledge_structure, container, false);
////        imageView=view.findViewById(R.id.iv_arrow);
//        recyclerView=view.findViewById(R.id.rv_list);
//        recyclerView2=view.findViewById(R.id.rv_list_2);
//        LinearLayoutManager linearLayout2=new LinearLayoutManager(getActivity());
//        linearLayout2.setOrientation(RecyclerView.VERTICAL);
//        recyclerView2.setLayoutManager(linearLayout2);
//        //设置布局管理器
//        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(getActivity());
//        //flexDirection 属性决定主轴的方向（即项目的排列方向）。类似 LinearLayout 的 vertical 和 horizontal。
//        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);//主轴为水平方向，起点在左端。
////        //flexWrap 默认情况下 Flex 跟 LinearLayout 一样，都是不带换行排列的，但是flexWrap属性可以支持换行排列。
////        flexboxLayoutManager.setFlexWrap(FlexWrap.NOWRAP);//按正常方向换行
////        //justifyContent 属性定义了项目在主轴上的对齐方式。
//        flexboxLayoutManager.setJustifyContent(JustifyContent.FLEX_START);//交叉轴的起点对齐。
//        GridLayoutManager f =new GridLayoutManager(getActivity(),4);
//
//
//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
//        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
//        List<String> data =new ArrayList<>();
//        for (int i = 0; i <20 ; i++) {
//            data.add("the item "+i);
//        }
//        KnowledgeListAdapter testAdapter=new KnowledgeListAdapter(R.layout.flow_item,data);
////        imageView.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////
////                recyclerView.postDelayed(new Runnable() {
////                    @Override
////                    public void run() {
////                        recyclerView.setLayoutManager(f);
////
////                    }
////                },1000);
////
////            }
////        });
//        recyclerView.setLayoutManager(linearLayoutManager);
//      recyclerView2.setAdapter(testAdapter);
//        recyclerView.setAdapter(testAdapter);
//
//        return view;
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//    }

}
