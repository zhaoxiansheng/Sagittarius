package com.example.zy.sagittarius;

import android.app.Application;
import android.content.Context;
import com.example.zy.sagittarius.utils.CustomCrashHandler;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by ZY on 2017/6/24.
 */

public class MyApplication extends Application {

  public Context context;

  @Override public void onCreate() {
    super.onCreate();

    context = getApplicationContext();
    Logger.addLogAdapter(new AndroidLogAdapter());

    CustomCrashHandler mCustomCrashHandler = CustomCrashHandler.getInstance();
    mCustomCrashHandler.setCustomCrashHanler(getApplicationContext());
  }
}
