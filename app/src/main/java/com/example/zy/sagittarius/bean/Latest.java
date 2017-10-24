package com.example.zy.sagittarius.bean;

import java.util.ArrayList;

/**
 * Created on 2017/9/23.
 * 最新消息
 *
 * @author zhaoy
 */

public class Latest {
    private String date;
    private ArrayList<Stories> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<Stories> getStories() {
        return stories;
    }

    public void setStories(ArrayList<Stories> stories) {
        this.stories = stories;
    }
}
