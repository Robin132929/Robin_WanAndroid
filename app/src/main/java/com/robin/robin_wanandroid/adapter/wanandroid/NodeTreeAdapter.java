package com.robin.robin_wanandroid.adapter.wanandroid;

import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.robin.robin_wanandroid.adapter.gank.FirstNode;
import com.robin.robin_wanandroid.adapter.gank.SecondNode;

import java.util.List;

public class NodeTreeAdapter extends BaseNodeAdapter {

    public NodeTreeAdapter() {
        super();
        addNodeProvider(new FirstProvider());
        addNodeProvider(new SecondProvider());
    }

    @Override
    protected int getItemType(List<? extends BaseNode> list, int i) {
        BaseNode node = list.get(i);
        if (node instanceof FirstNode) {
            return 1;
        } else if (node instanceof SecondNode) {
            return 2;
        }
        return -1;
    }
}
