package com.robin.robin_wanandroid.mvp.model.bean;

import java.util.List;

public class FootPrintBean {

    /**
     * data : [{"desc":"","icon":"","id":3656,"link":"https://mp.weixin.qq.com/s/H3oPDFS_OxMdzgxviSonBg","name":"Flutter App 软件调试指南 | 开发者说&amp;middot;DTalk","order":0,"userId":33387,"visible":1},{"desc":"","icon":"","id":3657,"link":"https://mp.weixin.qq.com/s/H3oPDFS_OxMdzgxviSonBg","name":"Flutter App 软件调试指南 | 开发者说&amp;middot;DTalk","order":0,"userId":33387,"visible":1},{"desc":"","icon":"","id":3658,"link":"https://juejin.im/post/5dd7c4dfe51d45230479be8c","name":"Jetpack架构组件 &amp;mdash; LiveData与ViewModel入坑详解 ","order":0,"userId":33387,"visible":1},{"desc":"","icon":"","id":3659,"link":"https://www.wanandroid.com/blog/show/2708","name":"android-developer-roadmap-cn","order":0,"userId":33387,"visible":1},{"desc":"","icon":"","id":3660,"link":"https://www.wanandroid.com/blog/show/2707","name":"RxHttp  OkHttp+RxJava 一条链发送请求，一行代码实现任意类中自动关闭请求，新一代Http请求神器","order":0,"userId":33387,"visible":1},{"desc":"","icon":"","id":3661,"link":"https://juejin.im/post/5dd499a6f265da0bf21126cc#heading-13","name":"「Android10源码分析」为什么复杂布局会产生卡顿？-- LayoutInflater详解","order":0,"userId":33387,"visible":1},{"desc":"","icon":"","id":3662,"link":"https://juejin.im/post/5dd499a6f265da0bf21126cc#heading-13","name":"「Android10源码分析」为什么复杂布局会产生卡顿？-- LayoutInflater详解","order":0,"userId":33387,"visible":1},{"desc":"","icon":"","id":3663,"link":"https://mp.weixin.qq.com/s/gop8vNaK8LFADRQijAjgrA","name":"9102年了，再谈屏幕适配！","order":0,"userId":33387,"visible":1},{"desc":"","icon":"","id":3664,"link":"https://juejin.im/post/5dd22ecd5188252a091e9b47","name":"从零开始仿写一个抖音App&amp;mdash;&amp;mdash;视频编辑SDK开发(一)","order":0,"userId":33387,"visible":1}]
     * errorCode : 0
     * errorMsg :
     */

    private int errorCode;
    private String errorMsg;
    private List<DataBean> data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * desc :
         * icon :
         * id : 3656
         * link : https://mp.weixin.qq.com/s/H3oPDFS_OxMdzgxviSonBg
         * name : Flutter App 软件调试指南 | 开发者说&amp;middot;DTalk
         * order : 0
         * userId : 33387
         * visible : 1
         */

        private String desc;
        private String icon;
        private int id;
        private String link;
        private String name;
        private int order;
        private int userId;
        private int visible;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getVisible() {
            return visible;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }
    }
}
