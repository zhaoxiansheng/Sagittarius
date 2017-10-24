package com.example.zy.sagittarius.presenter;

/**
 * Created on 2017/9/24.
 *
 * @author zhaoy
 */

public interface IHomePresenter {
    /**
     * 获取主题列表接口
     */
    void getTopic();

    /**
     * 获取latest列表接口
     */
    void getLatest();
}
