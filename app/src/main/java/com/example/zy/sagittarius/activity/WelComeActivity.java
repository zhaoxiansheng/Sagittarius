package com.example.zy.sagittarius.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zy.sagittarius.R;
import com.example.zy.sagittarius.presenter.IWelComePresenter;
import com.example.zy.sagittarius.presenter.WelComePresenter;

import java.util.Timer;

/**
 * Created by ZY on 2017/6/23.
 * 欢迎界面
 */

public class WelComeActivity extends AppCompatActivity
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

        welcomeImg = (ImageView) findViewById(R.id.welcome_img);
        timerText = (TextView) findViewById(R.id.timer_text);
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
                    Intent intent = new Intent(WelComeActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(WelComeActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }

    @Override
    public void getBingImage(final String bingPic) {
        if (welcomeImg != null){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Glide.with(getApplicationContext()).load(bingPic).into(welcomeImg);
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
                        Intent intent = new Intent(WelComeActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(WelComeActivity.this, LoginActivity.class);
                        startActivity(intent);
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
