package com.robin.robin_wanandroid.mvp.model.bean;

import java.util.List;

public class GetCollectBean {

    /**
     * data : {"curPage":1,"datas":[{"author":"xiaoyang","chapterId":360,"chapterName":"小编发布","courseId":13,"desc":"","envelopePic":"","id":97247,"link":"https://wanandroid.com/blog/show/2701","niceDate":"1天前","origin":"","originId":9988,"publishTime":1572852595000,"title":"不好意思哈，很多群可能要被迫解散了，建立了一个新家园！","userId":33387,"visible":0,"zan":0},{"author":"网易","chapterId":361,"chapterName":"课程推荐","courseId":13,"desc":"","envelopePic":"","id":97246,"link":"https://url.163.com/4bj","niceDate":"1天前","origin":"","originId":8904,"publishTime":1572852593000,"title":"利用OpenCV对图片进行处理，快速实战识别图文信息","userId":33387,"visible":0,"zan":0},{"author":"xiaoyang","chapterId":440,"chapterName":"官方","courseId":13,"desc":"<p>在早期，非常多博客在讲解和控件交互的时候，只会关注：<\/p> <p>ACTION_DOWN , ACTION_MOVE , ACTION_UP, ACTION_CANCEL<\/p> <p>这样的控件在一个手指交互的时候基本没有问题，但是一旦两个手指甚至多指操作，一个支持上下滑动的控件就会有跳跃感。<\/p> <p>那么今天的问题是：<\/p> <ol> <li>支持多个手指以上的操作，还应该关注哪些事件？<\/li> <li>Google 官方的控件，比如 ScrollView，ViewPager 这些都是支持多指操作的，那么多个手指时，如何判断哪一个是 active pointer（需要考虑一个接一个按下；一个接一个抬起）。<\/li> <li>一个未支持多指的控件，如何快速的支持？<\/li> <\/ol> <p>以上问题，知道任意一个都可以回答。<\/p> <blockquote> <p>另外，我们的问答数量已经突破了 50+，现在已经独立为 tab 啦，抬头即可看间。<br>本站始终追求非常高质量的提问，保证大多数问题能寻找答案的伙伴有所收获，么么哒，这个问题我觉得可以挂 5 天。<\/p> <\/blockquote>","envelopePic":"","id":97245,"link":"https://www.wanandroid.com/wenda/show/10049","niceDate":"1天前","origin":"","originId":10049,"publishTime":1572852592000,"title":"每日一问 你那么多手指在触摸屏幕，你叫我怎么选？","userId":33387,"visible":0,"zan":0}],"offset":0,"over":true,"pageCount":1,"size":20,"total":3}
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
         * datas : [{"author":"xiaoyang","chapterId":360,"chapterName":"小编发布","courseId":13,"desc":"","envelopePic":"","id":97247,"link":"https://wanandroid.com/blog/show/2701","niceDate":"1天前","origin":"","originId":9988,"publishTime":1572852595000,"title":"不好意思哈，很多群可能要被迫解散了，建立了一个新家园！","userId":33387,"visible":0,"zan":0},{"author":"网易","chapterId":361,"chapterName":"课程推荐","courseId":13,"desc":"","envelopePic":"","id":97246,"link":"https://url.163.com/4bj","niceDate":"1天前","origin":"","originId":8904,"publishTime":1572852593000,"title":"利用OpenCV对图片进行处理，快速实战识别图文信息","userId":33387,"visible":0,"zan":0},{"author":"xiaoyang","chapterId":440,"chapterName":"官方","courseId":13,"desc":"<p>在早期，非常多博客在讲解和控件交互的时候，只会关注：<\/p> <p>ACTION_DOWN , ACTION_MOVE , ACTION_UP, ACTION_CANCEL<\/p> <p>这样的控件在一个手指交互的时候基本没有问题，但是一旦两个手指甚至多指操作，一个支持上下滑动的控件就会有跳跃感。<\/p> <p>那么今天的问题是：<\/p> <ol> <li>支持多个手指以上的操作，还应该关注哪些事件？<\/li> <li>Google 官方的控件，比如 ScrollView，ViewPager 这些都是支持多指操作的，那么多个手指时，如何判断哪一个是 active pointer（需要考虑一个接一个按下；一个接一个抬起）。<\/li> <li>一个未支持多指的控件，如何快速的支持？<\/li> <\/ol> <p>以上问题，知道任意一个都可以回答。<\/p> <blockquote> <p>另外，我们的问答数量已经突破了 50+，现在已经独立为 tab 啦，抬头即可看间。<br>本站始终追求非常高质量的提问，保证大多数问题能寻找答案的伙伴有所收获，么么哒，这个问题我觉得可以挂 5 天。<\/p> <\/blockquote>","envelopePic":"","id":97245,"link":"https://www.wanandroid.com/wenda/show/10049","niceDate":"1天前","origin":"","originId":10049,"publishTime":1572852592000,"title":"每日一问 你那么多手指在触摸屏幕，你叫我怎么选？","userId":33387,"visible":0,"zan":0}]
         * offset : 0
         * over : true
         * pageCount : 1
         * size : 20
         * total : 3
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
             * author : xiaoyang
             * chapterId : 360
             * chapterName : 小编发布
             * courseId : 13
             * desc :
             * envelopePic :
             * id : 97247
             * link : https://wanandroid.com/blog/show/2701
             * niceDate : 1天前
             * origin :
             * originId : 9988
             * publishTime : 1572852595000
             * title : 不好意思哈，很多群可能要被迫解散了，建立了一个新家园！
             * userId : 33387
             * visible : 0
             * zan : 0
             */

            private String author;
            private int chapterId;
            private String chapterName;
            private int courseId;
            private String desc;
            private String envelopePic;
            private int id;
            private String link;
            private String niceDate;
            private String origin;
            private int originId;
            private long publishTime;
            private String title;
            private int userId;
            private int visible;
            private int zan;

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

            public int getOriginId() {
                return originId;
            }

            public void setOriginId(int originId) {
                this.originId = originId;
            }

            public long getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(long publishTime) {
                this.publishTime = publishTime;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
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
        }
    }
}
