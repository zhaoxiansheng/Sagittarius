package com.example.zy.sagittarius.activity;

import com.example.zy.sagittarius.bean.Latest;
import com.example.zy.sagittarius.model.Themes;

/**
 * Created on 2017/9/24.
 *
 * @author zhaoy
 */

public interface IHomeView {
    /**
     * topic list
     *
     * @param themes themes
     */
    void getTopicList(Themes themes);

    /**
     * latest list
     *
     * @param latest latest
     */
    void getLatestList(Latest latest);
}
