package com.example.zy.sagittarius.activity;

import java.util.Timer;

/**
 * Created on 2017/6/23.
 * 欢迎界面接口
 *
 * @author zhaoy
 */

public interface IWelComeView {

    /**
     * 广告页面 图片
     * @param bingPic 路径
     */
    void getBingImage(String bingPic);

    /**
     * 跳转timer
     * @param time 跳转时间
     */
    void timerToHome(int time);

    /**
     * 点击跳过后 取消timer
     * @param timer 计时器
     */
    void timerCancel(Timer timer);
}
