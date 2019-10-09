package com.robin.robin_wanandroid.mvp.model.bean;

public class Tag {
    private String name;
    private String url;

    public Tag(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url == null ? "" : url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
