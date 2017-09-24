package com.example.zy.sagittarius.activity;

import com.example.zy.sagittarius.bean.Latest;
import com.example.zy.sagittarius.model.Themes;

/**
 * Created by zhaoy on 2017/9/24.
 */

public interface IHomeView {
    void getTopicList(Themes themes);
    void getLatestList(Latest latest);
}
