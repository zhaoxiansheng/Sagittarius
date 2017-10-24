package com.example.zy.sagittarius.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.zy.sagittarius.activity.IWelComeView;
import com.example.zy.sagittarius.net.HttpControl;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created on 2017/6/23.
 *
 * @author zhaoy
 */

public class WelComePresenter implements IWelComePresenter {

    private IWelComeView iWelComeView;
    private Timer timer;
    private int timerTime;
    private Context context;

    public WelComePresenter(Context context, IWelComeView iWelComeView) {
        this.iWelComeView = iWelComeView;
        this.context = context;
    }

    @Override
    public void getBingImg() {
        String requestBingPic = "http://guolin.tech/api/bing_pic";
        HttpControl.sendOkHttpRequest(requestBingPic, "getBingImg", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String bingPic = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(
                        context).edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();
                iWelComeView.getBingImage(bingPic);
            }
        });
    }

    @Override
    public void timerTo(int time) {
        timer = new Timer();
        timerTime = time;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                iWelComeView.timerToHome(timerTime);
                timerTime--;
            }
        }, 1000, 1000);
    }

    @Override
    public void cancel() {
        iWelComeView.timerCancel(timer);
    }
}
