//package com.robin.robin_wanandroid.entity;
//
//
//public class HomeSectionMutipleItem extends SectionMultiEntity<HomeData> {
//    public static final int TOP = 1;
//    public static final int NAVIGATION = 2;
//    public static final int CONTENT = 3 ;
//
//    private int itemType;
//    private boolean isRefresh;
//    private HomeData homeData;
//    public HomeSectionMutipleItem(boolean isHeader, String header,boolean isRefresh) {
//        super(isHeader, header);
//        this.isRefresh=isRefresh;
//    }
//
//    public HomeSectionMutipleItem(HomeData homeData, int itemType) {
//        super(homeData);
//        this.itemType = itemType;
//        this.homeData=homeData;
//    }
//
//    public boolean isRefresh() {
//        return isRefresh;
//    }
//
//    public void setRefresh(boolean refresh) {
//        isRefresh = refresh;
//    }
//
//    public HomeData getHomeData() {
//        return homeData;
//    }
//
//    public void setHomeData(HomeData homeData) {
//        this.homeData = homeData;
//    }
//
//    @Override
//    public int getItemType() {
//        return itemType;
//    }
//}
