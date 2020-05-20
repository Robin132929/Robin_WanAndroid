package com.robin.robin_wanandroid.adapter.wanandroid;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.robin.robin_wanandroid.R;
import com.robin.robin_wanandroid.adapter.gank.FirstNode;

public class FirstProvider extends BaseNodeProvider {
    @Override
    public int getItemViewType() {
        return 1;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_node_first;
    }

    @Override
    public void convert(BaseViewHolder baseViewHolder, BaseNode baseNode) {
        FirstNode entity = (FirstNode) baseNode;
        baseViewHolder.setText(R.id.title, entity.getTitle());
        baseViewHolder.setImageResource(R.id.iv, R.drawable.down_arrow);

    }
}
