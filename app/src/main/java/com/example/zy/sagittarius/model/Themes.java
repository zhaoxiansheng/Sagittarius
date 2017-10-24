package com.example.zy.sagittarius.model;

import com.example.zy.sagittarius.bean.Others;
import com.example.zy.sagittarius.bean.Subscribed;

import java.util.ArrayList;

/**
 * Created on 2017/9/19.
 * 主题日报列表查询
 *
 * @author zhaoy
 */

public class Themes {

    private String limit;

    private ArrayList<Subscribed> subscribed;

    private ArrayList<Others> others;

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public ArrayList<Subscribed> getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(ArrayList<Subscribed> subscribed) {
        this.subscribed = subscribed;
    }

    public ArrayList<Others> getOthers() {
        return others;
    }

    public void setOthers(ArrayList<Others> others) {
        this.others = others;
    }
}
