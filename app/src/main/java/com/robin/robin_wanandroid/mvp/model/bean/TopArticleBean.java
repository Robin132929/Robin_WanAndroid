package com.robin.robin_wanandroid.mvp.model.bean;

import java.util.List;

public class TopArticleBean {

    /**
     * data : [{"apkLink":"","author":"网易","chapterId":361,"chapterName":"课程推荐","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8904,"link":"https://url.163.com/4bj","niceDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1567009489000,"superChapterId":249,"superChapterName":"干货资源","tags":[],"title":"0元领Android职业进阶直播课","type":1,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"xiaoyang","chapterId":440,"chapterName":"官方","collect":false,"courseId":13,"desc":"<p>一般情况，每个 app 会依赖多个 module，一般 module 里面的资源，例如 res中有个 love。<\/p>\r\n<p>对应的在 module中会有个：<\/p>\r\n<pre><code>com.module.R.res.love\r\n<\/code><\/pre><p>app 中也可以使用这个资源，但是包名为：<\/p>\r\n<pre><code>com.app.R.res.love\r\n<\/code><\/pre><p>两者虽然对应一个资源，但是id 值很大概率上是不同的。<\/p>\r\n<p>那么最终打包是如何处理的呢？<\/p>","envelopePic":"","fresh":false,"id":9067,"link":"https://www.wanandroid.com/wenda/show/9067","niceDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1567009487000,"superChapterId":440,"superChapterName":"问答","tags":[{"name":"问答","url":"/article/list/0?cid=440"}],"title":"每日一问 关于 R.java 的生成规则，你知道多少？","type":1,"userId":2,"visible":1,"zan":8},{"apkLink":"","author":"xiaoyang","chapterId":360,"chapterName":"小编发布","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":9016,"link":"https://www.wanandroid.com/blog/show/2661","niceDate":"2019-08-26","origin":"","prefix":"","projectLink":"","publishTime":1566748964000,"superChapterId":298,"superChapterName":"原创文章","tags":[],"title":"玩 Android 更新了两个功能","type":1,"userId":-1,"visible":1,"zan":0}]
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
         * apkLink :
         * author : 网易
         * chapterId : 361
         * chapterName : 课程推荐
         * collect : false
         * courseId : 13
         * desc :
         * envelopePic :
         * fresh : false
         * id : 8904
         * link : https://url.163.com/4bj
         * niceDate : 1天前
         * origin :
         * prefix :
         * projectLink :
         * publishTime : 1567009489000
         * superChapterId : 249
         * superChapterName : 干货资源
         * tags : []
         * title : 0元领Android职业进阶直播课
         * type : 1
         * userId : -1
         * visible : 1
         * zan : 0
         */

        private String apkLink;
        private String author;
        private int chapterId;
        private String chapterName;
        private boolean collect;
        private int courseId;
        private String desc;
        private String envelopePic;
        private boolean fresh;
        private int id;
        private String link;
        private String niceDate;
        private String origin;
        private String prefix;
        private String projectLink;
        private long publishTime;
        private int superChapterId;
        private String superChapterName;
        private String title;
        private int type;
        private int userId;
        private int visible;
        private int zan;
        private List<?> tags;

        public String getApkLink() {
            return apkLink;
        }

        public void setApkLink(String apkLink) {
            this.apkLink = apkLink;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getChapterId() {
            return chapterId;
        }

        public void setChapterId(int chapterId) {
            this.chapterId = chapterId;
        }

        public String getChapterName() {
            return chapterName;
        }

        public void setChapterName(String chapterName) {
            this.chapterName = chapterName;
        }

        public boolean isCollect() {
            return collect;
        }

        public void setCollect(boolean collect) {
            this.collect = collect;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getEnvelopePic() {
            return envelopePic;
        }

        public void setEnvelopePic(String envelopePic) {
            this.envelopePic = envelopePic;
        }

        public boolean isFresh() {
            return fresh;
        }

        public void setFresh(boolean fresh) {
            this.fresh = fresh;
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

        public String getNiceDate() {
            return niceDate;
        }

        public void setNiceDate(String niceDate) {
            this.niceDate = niceDate;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public String getProjectLink() {
            return projectLink;
        }

        public void setProjectLink(String projectLink) {
            this.projectLink = projectLink;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public int getSuperChapterId() {
            return superChapterId;
        }

        public void setSuperChapterId(int superChapterId) {
            this.superChapterId = superChapterId;
        }

        public String getSuperChapterName() {
            return superChapterName;
        }

        public void setSuperChapterName(String superChapterName) {
            this.superChapterName = superChapterName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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

        public int getZan() {
            return zan;
        }

        public void setZan(int zan) {
            this.zan = zan;
        }

        public List<?> getTags() {
            return tags;
        }

        public void setTags(List<?> tags) {
            this.tags = tags;
        }
    }
}
