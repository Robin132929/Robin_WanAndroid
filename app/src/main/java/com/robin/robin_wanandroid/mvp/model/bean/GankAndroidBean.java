package com.robin.robin_wanandroid.mvp.model.bean;

import java.util.ArrayList;
import java.util.List;

public class GankAndroidBean {

    /**
     * error : false
     * results : [{"_id":"5db0ff5b9d21226808a3e9a8","createdAt":"2019-10-24T01:33:15.558Z","desc":"webview封装库，极大提高开发效率","images":["http://img.gank.io/cc45d086-526a-40e2-8b16-402dbd39f0a4","http://img.gank.io/e86728de-36ba-4b18-8058-cb6af71647cd","http://img.gank.io/d2d096c3-44c6-49f7-b69b-3a20177b48a2","http://img.gank.io/ef1577e2-4d9f-43ed-a5c0-4c2cffe4b3cd","http://img.gank.io/4577d7bd-d7b9-4eac-b2ef-fbdec7dbc538","http://img.gank.io/abfa62d1-67d0-4663-a19e-fd0f42d966b3","http://img.gank.io/d1c7dd0f-3638-4b1e-9186-ca7737d1f644"],"publishedAt":"2019-10-24T01:34:26.445Z","source":"web","type":"Android","url":"https://github.com/yangchong211/YCWebView/blob/master/README.md","used":true,"who":"潇湘剑雨"},{"_id":"5dad77709d212207e200f06a","createdAt":"2019-10-21T17:16:32.290Z","desc":"一篇文章为你科普跨平台开发，完善 Flutter 的 Widget 体系。","publishedAt":"2019-10-24T01:31:34.800Z","source":"web","type":"Android","url":"https://juejin.im/post/5dac428af265da5ba838f476","used":true,"who":"潇湘剑雨"},{"_id":"5da7c3969d21226808a3e9a2","createdAt":"2019-10-17T01:27:50.273Z","desc":"安卓端高仿小红书图片剪裁框架+微信图片选择器+超高清大图预览+图片自定义比例剪裁，支持UI自定义、支持跨进程回调","publishedAt":"2019-10-18T05:14:31.834Z","source":"web","type":"Android","url":"https://github.com/yangpeixing/YImagePicker","used":true,"who":"潇湘剑雨"},{"_id":"5da131d99d212267f5ba605f","createdAt":"2019-10-12T01:52:25.308Z","desc":"Go 和 Android 集成实战","publishedAt":"2019-10-15T00:55:32.770Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s/VX5-_4IpLyhHe80w7-4MRA","used":true,"who":"潇湘剑雨"},{"_id":"5d9fcc199d212267f5ba605e","createdAt":"2019-10-11T00:26:01.498Z","desc":"还在用 android.support？谷歌强推 AndroidX 啦！","publishedAt":"2019-10-11T00:59:47.707Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s/36frfcZE3CLWKqTJ-wcRmQ","used":true,"who":"潇湘剑雨"},{"_id":"5d906d219d212267f5ba605b","createdAt":"2019-09-29T08:36:49.904Z","desc":"Flutter 上滑动特效的深度解析","publishedAt":"2019-10-09T01:07:33.80Z","source":"web","type":"Android","url":"https://juejin.im/post/5d9067026fb9a04df00ece14","used":true,"who":"潇湘剑雨"},{"_id":"5d8d5a1d9d2122688d07a75d","createdAt":"2019-09-27T00:38:53.969Z","desc":"学会这3个Android Studio操作，保你月薪double","publishedAt":"2019-09-27T01:07:19.58Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s/flvC4b2kW3731eP9jsI8zA","used":true,"who":"潇湘剑雨"},{"_id":"5d885cea9d21226808a3e98e","createdAt":"2019-09-23T05:49:30.103Z","desc":"基于腾讯x5封源库，提高webView开发效率，大概要节约你百分之六十的时间成本。","publishedAt":"2019-09-23T05:49:44.533Z","source":"web","type":"Android","url":"https://github.com/yangchong211/YCWebView","used":true,"who":"潇湘剑雨"},{"_id":"5d817c759d2122031f053291","createdAt":"2019-09-18T00:38:13.545Z","desc":"加载那么多小姐姐的脉脉，怎么没OOM？","publishedAt":"2019-09-18T01:12:37.561Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s/iQvqU5KcNsFWBBMXUqL6Vw","used":true,"who":"潇湘剑雨"},{"_id":"5d731dfb9d2122279ba69b62","createdAt":"2019-09-07T03:03:23.186Z","desc":"一位练习时长两年半的安卓练习生根据鸿神提供的WanAndroid开放Api来制作的产品级App","publishedAt":"2019-09-08T13:43:26.439Z","source":"web","type":"Android","url":"https://github.com/hegaojian/WanAndroid","used":true,"who":"潇湘剑雨"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 5db0ff5b9d21226808a3e9a8
         * createdAt : 2019-10-24T01:33:15.558Z
         * desc : webview封装库，极大提高开发效率
         * images : ["http://img.gank.io/cc45d086-526a-40e2-8b16-402dbd39f0a4","http://img.gank.io/e86728de-36ba-4b18-8058-cb6af71647cd","http://img.gank.io/d2d096c3-44c6-49f7-b69b-3a20177b48a2","http://img.gank.io/ef1577e2-4d9f-43ed-a5c0-4c2cffe4b3cd","http://img.gank.io/4577d7bd-d7b9-4eac-b2ef-fbdec7dbc538","http://img.gank.io/abfa62d1-67d0-4663-a19e-fd0f42d966b3","http://img.gank.io/d1c7dd0f-3638-4b1e-9186-ca7737d1f644"]
         * publishedAt : 2019-10-24T01:34:26.445Z
         * source : web
         * type : Android
         * url : https://github.com/yangchong211/YCWebView/blob/master/README.md
         * used : true
         * who : 潇湘剑雨
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images=new ArrayList<>();

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
