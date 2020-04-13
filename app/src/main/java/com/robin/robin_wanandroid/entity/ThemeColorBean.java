package com.robin.robin_wanandroid.entity;

import androidx.annotation.ColorInt;

public class ThemeColorBean {
    private boolean isSelect;
    private int selectIndex=-1;
    @ColorInt
   private Integer colorInt=0;
    private String colorName="";

    public ThemeColorBean(boolean isSelect, int selectIndex, int colorInt, String colorName) {
        this.isSelect = isSelect;
        this.selectIndex = selectIndex;
        this.colorInt = colorInt;
        this.colorName = colorName;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public int getSelectIndex() {
        return selectIndex;
    }

    public void setSelectIndex(int selectIndex) {
        this.selectIndex = selectIndex;
    }

    public int getColorInt() {
        return colorInt;
    }

    public void setColorInt(int colorInt) {
        this.colorInt = colorInt;
    }

    public String getColorName() {
        return colorName == null ? "" : colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }
}
