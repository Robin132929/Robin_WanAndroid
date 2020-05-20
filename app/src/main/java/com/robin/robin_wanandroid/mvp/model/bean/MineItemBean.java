package com.robin.robin_wanandroid.mvp.model.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

public class MineItemBean implements SectionEntity {
    private int icon;
    private String title;
    private int arr;
    public boolean isHeader;

    public MineItemBean(int icon, String title, int arr,boolean isHeader) {
        this.isHeader=isHeader;
        this.icon = icon;
        this.title = title;
        this.arr = arr;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getArr() {
        return arr;
    }

    public void setArr(int arr) {
        this.arr = arr;
    }

    @Override
    public boolean isHeader() {
        return false;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
