package com.example.zy.sagittarius;

import android.app.Application;
import android.content.Context;

import com.example.zy.sagittarius.bean.Constants;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;

/**
 * Created by ZY on 2017/6/24.
 */

public class MyApplication extends Application {

  public Context context;

  @Override public void onCreate() {
    super.onCreate();

    context = getApplicationContext();
    WbSdk.install(context,
        new AuthInfo(context, Constants.APP_KEY,
            Constants.REDIRECT_URL, Constants.REDIRECT_URL));
    Logger.addLogAdapter(new AndroidLogAdapter());
  }
}
