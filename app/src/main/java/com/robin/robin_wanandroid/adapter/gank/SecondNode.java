package com.robin.robin_wanandroid.adapter.gank;

import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;

import java.util.List;

import androidx.annotation.Nullable;

public class SecondNode extends BaseExpandNode {

    private List<BaseNode> childNode;
    private String title;

    public SecondNode(List<BaseNode> childNode, String title) {
        this.childNode = childNode;
        this.title = title;

        setExpanded(false);
    }

    public String getTitle() {
        return title;
    }

    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return childNode;
    }
}
