package com.example.zy.sagittarius.activity;

import android.content.Context;
import java.util.Timer;

/**
 * Created by ZY on 2017/6/23.
 */

public interface IWelComeView {
    void getBingImage(Context context,String bingPic);
    void timerToHome(int time);
    void timerCancel(Timer timer);
}
