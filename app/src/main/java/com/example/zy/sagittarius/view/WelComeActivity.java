package com.example.zy.sagittarius.view;

import android.content.Intent;
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
import java.util.TimerTask;

/**
 * Created by ZY on 2017/6/23.
 */

public class WelComeActivity extends AppCompatActivity
    implements IWelComeView, View.OnClickListener {

  private ImageView welcomeImg;
  private TextView timerText;
  IWelComePresenter welComePresenter;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_welcome);

    welcomeImg = (ImageView) findViewById(R.id.welcome_img);
    timerText = (TextView) findViewById(R.id.timer_text);
    timerText.setOnClickListener(this);
    welComePresenter = new WelComePresenter(this, this);
    welComePresenter.getBingImg();
    int timerTime = 3;
    welComePresenter.timerTo(timerTime);
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {
      case R.id.timer_text:
        welComePresenter.cancel();
        Intent intent = new Intent(WelComeActivity.this, HomeActivity.class);
        startActivity(intent);
        break;
    }
  }

  @Override public void getBingImage(final String bingPic) {
    runOnUiThread(new Runnable() {
      @Override public void run() {
        Glide.with(WelComeActivity.this).load(bingPic).into(welcomeImg);
      }
    });
  }

  @Override public void timerToHome(final int time) {
    runOnUiThread(new Runnable() {
      @Override public void run() {
        timerText.setText("跳过(" + time + ")");
        if (time == 0) {
          welComePresenter.cancel();
          Intent intent = new Intent(WelComeActivity.this, HomeActivity.class);
          startActivity(intent);
        }
      }
    });
  }

  @Override public void timerCancel(Timer timer) {
    timer.cancel();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    welComePresenter.cancel();
  }
}
