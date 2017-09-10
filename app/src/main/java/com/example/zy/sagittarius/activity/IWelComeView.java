package com.example.zy.sagittarius.activity;

import android.app.Activity;
import android.content.Context;
import java.util.Timer;

/**
 * Created by ZY on 2017/6/23.
 * 欢迎界面接口
 */

public interface IWelComeView {
    void getBingImage(String bingPic);
    void timerToHome(int time);
    void timerCancel(Timer timer);
}
