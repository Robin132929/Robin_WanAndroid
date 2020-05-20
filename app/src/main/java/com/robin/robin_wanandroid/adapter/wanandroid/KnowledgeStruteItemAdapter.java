package com.robin.robin_wanandroid.adapter.wanandroid;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.robin.rbase.CommonUtils.Utils.ContextUtil;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.mvp.model.bean.KnowledgeArticleBean;
import com.robin.robin_wanandroid.util.GlideUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class KnowledgeStruteItemAdapter extends BaseQuickAdapter<KnowledgeArticleBean.DataBean.DatasBean, BaseViewHolder> {

    public KnowledgeStruteItemAdapter(int layoutResId, @Nullable List<KnowledgeArticleBean.DataBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, KnowledgeArticleBean.DataBean.DatasBean item) {
        helper.setText(R.id.tv_article_tag, "1");
        helper.setText(R.id.tv_article_author, item.getAuthor());
        helper.setText(R.id.tv_article_date, item.getNiceDate());
        helper.setText(R.id.tv_article_title, item.getTitle());
        helper.setText(R.id.tv_article_chapterName, item.getChapterName());
        if (item.getEnvelopePic().isEmpty() || (item.getEnvelopePic() == null)) {
            helper.setGone(R.id.iv_article_thumbnail, false);
        }else {
            GlideUtils.showBannerImage(ContextUtil.getAppContext(),helper.getView(R.id.iv_article_thumbnail),item.getEnvelopePic());
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
