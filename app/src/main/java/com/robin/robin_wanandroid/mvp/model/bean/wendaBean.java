package com.robin.robin_wanandroid.mvp.model.bean;

import java.util.List;

public class wendaBean {

    /**
     * data : {"curPage":1,"datas":[{"apkLink":"","audit":1,"author":"xiaoyang","canEdit":false,"chapterId":440,"chapterName":"官方","collect":false,"courseId":13,"desc":"<p>技术的演进都是有原因的，带着好奇的思路，所以今天的问题是：<\/p> <ol> <li>Android v1 v2 v3 签名机制是什么样子的，或者说各自是如何保证 apk 的防篡改的？<\/li> <li>v1 -&gt; v2 -&gt; v3 主要是为了解决什么问题，或者带来了什么好处？<\/li> <li>对于快速的多渠道打包，针对 v1,v2,v3 都可以怎么做，有哪些开源库可以考虑？<\/li> <\/ol>","descMd":"","envelopePic":"","fresh":false,"id":10669,"link":"https://www.wanandroid.com/wenda/show/10669","niceDate":"2019-12-09 23:52","niceShareDate":"2019-12-04 00:17","origin":"","prefix":"","projectLink":"","publishTime":1575906771000,"selfVisible":0,"shareDate":1575389866000,"shareUser":"鸿洋","superChapterId":440,"superChapterName":"问答","tags":[{"name":"问答","url":"/article/list/0?cid=440"}],"title":"每日一问 Android 签名机制 v1 v2 v3 ， 卧槽都 v3 了？","type":0,"userId":2,"visible":1,"zan":9}],"offset":0,"over":false,"pageCount":4,"size":21,"total":77}
     * errorCode : 0
     * errorMsg :
     */

    private DataBean data;
    private int errorCode;
    private String errorMsg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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

    public static class DataBean {
        /**
         * curPage : 1
         * datas : [{"apkLink":"","audit":1,"author":"xiaoyang","canEdit":false,"chapterId":440,"chapterName":"官方","collect":false,"courseId":13,"desc":"<p>技术的演进都是有原因的，带着好奇的思路，所以今天的问题是：<\/p> <ol> <li>Android v1 v2 v3 签名机制是什么样子的，或者说各自是如何保证 apk 的防篡改的？<\/li> <li>v1 -&gt; v2 -&gt; v3 主要是为了解决什么问题，或者带来了什么好处？<\/li> <li>对于快速的多渠道打包，针对 v1,v2,v3 都可以怎么做，有哪些开源库可以考虑？<\/li> <\/ol>","descMd":"","envelopePic":"","fresh":false,"id":10669,"link":"https://www.wanandroid.com/wenda/show/10669","niceDate":"2019-12-09 23:52","niceShareDate":"2019-12-04 00:17","origin":"","prefix":"","projectLink":"","publishTime":1575906771000,"selfVisible":0,"shareDate":1575389866000,"shareUser":"鸿洋","superChapterId":440,"superChapterName":"问答","tags":[{"name":"问答","url":"/article/list/0?cid=440"}],"title":"每日一问 Android 签名机制 v1 v2 v3 ， 卧槽都 v3 了？","type":0,"userId":2,"visible":1,"zan":9}]
         * offset : 0
         * over : false
         * pageCount : 4
         * size : 21
         * total : 77
         */

        private int curPage;
        private int offset;
        private boolean over;
        private int pageCount;
        private int size;
        private int total;
        private List<DatasBean> datas;

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public boolean isOver() {
            return over;
        }

        public void setOver(boolean over) {
            this.over = over;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        public static class DatasBean {
            /**
             * apkLink :
             * audit : 1
             * author : xiaoyang
             * canEdit : false
             * chapterId : 440
             * chapterName : 官方
             * collect : false
             * courseId : 13
             * desc : <p>技术的演进都是有原因的，带着好奇的思路，所以今天的问题是：</p> <ol> <li>Android v1 v2 v3 签名机制是什么样子的，或者说各自是如何保证 apk 的防篡改的？</li> <li>v1 -&gt; v2 -&gt; v3 主要是为了解决什么问题，或者带来了什么好处？</li> <li>对于快速的多渠道打包，针对 v1,v2,v3 都可以怎么做，有哪些开源库可以考虑？</li> </ol>
             * descMd :
             * envelopePic :
             * fresh : false
             * id : 10669
             * link : https://www.wanandroid.com/wenda/show/10669
             * niceDate : 2019-12-09 23:52
             * niceShareDate : 2019-12-04 00:17
             * origin :
             * prefix :
             * projectLink :
             * publishTime : 1575906771000
             * selfVisible : 0
             * shareDate : 1575389866000
             * shareUser : 鸿洋
             * superChapterId : 440
             * superChapterName : 问答
             * tags : [{"name":"问答","url":"/article/list/0?cid=440"}]
             * title : 每日一问 Android 签名机制 v1 v2 v3 ， 卧槽都 v3 了？
             * type : 0
             * userId : 2
             * visible : 1
             * zan : 9
             */

            private String apkLink;
            private int audit;
            private String author;
            private boolean canEdit;
            private int chapterId;
            private String chapterName;
            private boolean collect;
            private int courseId;
            private String desc;
            private String descMd;
            private String envelopePic;
            private boolean fresh;
            private int id;
            private String link;
            private String niceDate;
            private String niceShareDate;
            private String origin;
            private String prefix;
            private String projectLink;
            private long publishTime;
            private int selfVisible;
            private long shareDate;
            private String shareUser;
            private int superChapterId;
            private String superChapterName;
            private String title;
            private int type;
            private int userId;
            private int visible;
            private int zan;
            private List<TagsBean> tags;

            public String getApkLink() {
                return apkLink;
            }

            public void setApkLink(String apkLink) {
                this.apkLink = apkLink;
            }

            public int getAudit() {
                return audit;
            }

            public void setAudit(int audit) {
                this.audit = audit;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public boolean isCanEdit() {
                return canEdit;
            }

            public void setCanEdit(boolean canEdit) {
                this.canEdit = canEdit;
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

            public String getDescMd() {
                return descMd;
            }

            public void setDescMd(String descMd) {
                this.descMd = descMd;
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

            public String getNiceShareDate() {
                return niceShareDate;
            }

            public void setNiceShareDate(String niceShareDate) {
                this.niceShareDate = niceShareDate;
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

            public int getSelfVisible() {
                return selfVisible;
            }

            public void setSelfVisible(int selfVisible) {
                this.selfVisible = selfVisible;
            }

            public long getShareDate() {
                return shareDate;
            }

            public void setShareDate(long shareDate) {
                this.shareDate = shareDate;
            }

            public String getShareUser() {
                return shareUser;
            }

            public void setShareUser(String shareUser) {
                this.shareUser = shareUser;
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

            public List<TagsBean> getTags() {
                return tags;
            }

            public void setTags(List<TagsBean> tags) {
                this.tags = tags;
            }

            public static class TagsBean {
                /**
                 * name : 问答
                 * url : /article/list/0?cid=440
                 */

                private String name;
                private String url;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }
        }
    }
}
