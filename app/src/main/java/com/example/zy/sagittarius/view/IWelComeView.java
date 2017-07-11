package com.example.zy.sagittarius.view;

import java.util.Timer;

/**
 * Created by ZY on 2017/6/23.
 */

public interface IWelComeView {
    void getBingImage(String bingPic);
    void timerToHome(int time);
    void timerCancel(Timer timer);
}
