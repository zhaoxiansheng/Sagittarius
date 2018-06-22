package com.example.zy.sagittarius.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.zy.sagittarius.R;
import com.example.zy.sagittarius.glideutils.GlideApp;
import com.example.zy.sagittarius.presenter.IWelComePresenter;
import com.example.zy.sagittarius.presenter.WelComePresenter;

import java.util.Timer;

/**
 * Created on 2017/6/23.
 * 欢迎界面
 *
 * @author zhaoy`
 */

public class WelComeActivity extends Activity
        implements IWelComeView, View.OnClickListener {

    private ImageView welcomeImg;
    private TextView timerText;
    IWelComePresenter welComePresenter;
    private SharedPreferences sharedPreferences;
    private String name;
    private String pass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        welcomeImg =  findViewById(R.id.welcome_img);
        timerText =  findViewById(R.id.timer_text);
        timerText.setOnClickListener(this);
        welComePresenter = new WelComePresenter(this, this);
        welComePresenter.getBingImg();
        int timerTime = 3;
        welComePresenter.timerTo(timerTime);
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        name = sharedPreferences.getString("name", null);
        pass = sharedPreferences.getString("pass", null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.timer_text:
                welComePresenter.cancel();
                if (name != null && pass != null) {
                    HomeActivity.activityIntent(WelComeActivity.this);
                    finish();
                } else {
                    LoginActivity.activityIntent(WelComeActivity.this);
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void getBingImage(final String bingPic) {
        if (welcomeImg != null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    GlideApp.with(getApplicationContext())
                            .load(bingPic)
                            .centerCrop()
                            .dontAnimate()
                            .dontTransform()
                            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                            .into(welcomeImg);
                }
            });
        }
    }

    @Override
    public void timerToHome(final int time) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                timerText.setText("跳过(" + time + ")");
                if (time == 0) {
                    welComePresenter.cancel();
                    if (name != null && pass != null) {
                        HomeActivity.activityIntent(WelComeActivity.this);
                        finish();
                    } else {
                        LoginActivity.activityIntent(WelComeActivity.this);
                        finish();
                    }
                }
            }
        });
    }

    @Override
    public void timerCancel(Timer timer) {
        timer.cancel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        welComePresenter.cancel();
    }
}
