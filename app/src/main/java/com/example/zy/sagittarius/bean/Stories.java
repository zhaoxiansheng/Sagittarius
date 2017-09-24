package com.example.zy.sagittarius.bean;


import com.google.gson.annotations.SerializedName;

/**
 * Created by zhaoy on 2017/9/23.
 * 最新消息的列表
 */

public class Stories {

    private String[] images;
    private String type;
    private String id;
    @SerializedName("ga_prefix")
    private String prefix;
    private String title;

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
