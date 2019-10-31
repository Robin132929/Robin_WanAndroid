package com.robin.robin_wanandroid.adapter.wanandroid;

import android.view.View;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.robin.rbase.CommonUtils.Utils.ContextUtil;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.app.App;
import com.robin.robin_wanandroid.entity.HomeSectionMutipleItem;
import com.robin.robin_wanandroid.mvp.model.bean.BannerBean;
import com.robin.robin_wanandroid.mvp.model.bean.MainArticleBean;
import com.robin.robin_wanandroid.util.GlideUtils;
import com.robin.robin_wanandroid.widget.BannerHolderView;

import java.util.List;

import androidx.annotation.NonNull;

public class HomeAdapter extends BaseQuickAdapter<MainArticleBean.DataBean.DatasBean, BaseViewHolder> {

    public HomeAdapter(int layoutResId) {
        super(layoutResId, null);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MainArticleBean.DataBean.DatasBean item) {
        helper.setText(R.id.tv_article_tag, "1");
        helper.setText(R.id.tv_article_author, item.getAuthor());
        helper.setText(R.id.tv_article_date, item.getNiceDate());
        helper.setText(R.id.tv_article_title, item.getTitle());
        helper.setText(R.id.tv_article_chapterName, item.getChapterName());
        if (item.getEnvelopePic().isEmpty() || (item.getEnvelopePic() == null)) {
            helper.setGone(R.id.iv_article_thumbnail, false);
        }else {
           GlideUtils.showBannerImage(mContext,helper.getView(R.id.iv_article_thumbnail),item.getEnvelopePic());
        }
        if (item.isFresh()){
            helper.getView(R.id.tv_article_fresh).setVisibility(View.VISIBLE);
        }
//        if (item.getVisible()==1){
//            helper.getView(R.id.tv_article_top).setVisibility(View.VISIBLE);
//        }
        if (item.getTags().size()>0){
            TextView tag=helper.getView(R.id.tv_article_tag);
            tag.setText(item.getTags().get(0).getName());
        }
    }
}



//public class HomeAdapter extends BaseSectionMultiItemQuickAdapter<HomeSectionMutipleItem, BaseViewHolder> {
//    /**
//     * Same as QuickAdapter#QuickAdapter(Context,int) but with
//     * some initialization data.
//     *
//     * @param sectionHeadResId The section head layout id for each item
//     * @param data             A new list is created out of this one to avoid mutable list
//     */
//    public HomeAdapter(int sectionHeadResId, List<HomeSectionMutipleItem> data) {
//        super(sectionHeadResId, data);
//        addItemType(HomeSectionMutipleItem.TOP, R.layout.banner_view);
//        addItemType(HomeSectionMutipleItem.NAVIGATION, R.layout.navagtion_recycle_item);
//        addItemType(HomeSectionMutipleItem.CONTENT, R.layout.home_recycle_item);
//
//    }
//
//    @Override
//    protected void convertHead(BaseViewHolder helper, HomeSectionMutipleItem item) {
//        if (item.getItemType() == HomeSectionMutipleItem.CONTENT) {
//            helper.setText(R.id.section_title_tv, "最新文章");
//            helper.setText(R.id.section_refresh_tv, "刷新");
//        }
//    }
//
//    @Override
//    protected void convert(@NonNull BaseViewHolder helper, HomeSectionMutipleItem item) {
//        switch (helper.getItemViewType()) {
//            case HomeSectionMutipleItem.TOP:
//                ConvenientBanner<BannerBean.DataBean> convenientBanner;
//                convenientBanner=(ConvenientBanner<BannerBean.DataBean>)helper.getView(R.id.convenient_banner);
//                        convenientBanner.setPages(new CBViewHolderCreator() {
//                @Override
//                public Holder createHolder(View itemView) {
//                    return new BannerHolderView(itemView, ContextUtil.getAppContext());
//                }
//
//                @Override
//                public int getLayoutId() {
//                    return R.layout.item_banner_view;
//                }
//            },item.getHomeData().getBannerData().getData()).setPageIndicator(new int[]{R.drawable.ic_circle_normal,R.drawable.ic_circle_press})
//                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
//                    .setOnItemClickListener(new com.bigkoo.convenientbanner.listener.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(int position) {
//
//                        }
//                    });
//                convenientBanner.startTurning();
//                break;
//            case HomeSectionMutipleItem.NAVIGATION:
//                helper.setImageResource(R.id.home_nav_img,R.drawable.mianshi);
//                break;
//            case HomeSectionMutipleItem.CONTENT:
//                helper.setText(R.id.tv_article_tag, "1");
//        helper.setText(R.id.tv_article_author, item.getHomeData().getArticleData().getAuthor());
//        helper.setText(R.id.tv_article_date, item.getHomeData().getArticleData().getNiceDate());
//        helper.setText(R.id.tv_article_title, item.getHomeData().getArticleData().getTitle());
//        helper.setText(R.id.tv_article_chapterName, item.getHomeData().getArticleData().getChapterName());
//        if (item.getHomeData().getArticleData().getEnvelopePic().isEmpty() || (item.getHomeData().getArticleData().getEnvelopePic() == null)) {
//            helper.setGone(R.id.iv_article_thumbnail, false);
//        }
//                break;
//            default:
//                break;
//        }
//    }


//}