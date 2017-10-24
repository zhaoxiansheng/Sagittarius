package com.example.zy.sagittarius.presenter;

/**
 * Created on 2017/6/23.
 * @author zhaoy
 */

public interface IWelComePresenter {
    /**
     * 获取广告界面图片
     */
    void getBingImg();

    /**
     * 计时跳转
     * @param time 倒计时时间
     */
    void timerTo(int time);

    /**
     * 取消
     */
    void cancel();
}
