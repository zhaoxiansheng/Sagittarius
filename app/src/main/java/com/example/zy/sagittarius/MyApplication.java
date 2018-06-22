package com.example.zy.sagittarius;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.example.zy.sagittarius.model.IVersion;
import com.example.zy.sagittarius.model.VersionModel;
import com.example.zy.sagittarius.net.RetrofitFactory;
import com.example.zy.sagittarius.net.ZhiHuApi;
import com.example.zy.sagittarius.utils.CustomCrashHandler;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.lang.ref.WeakReference;

/**
 * Created on 2017/6/24.
 * application
 *
 * @author zhaoy
 */

public class MyApplication extends Application {

    private static WeakReference<Context> context;
    public static ZhiHuApi zhiHuApi;

    @Override
    public void onCreate() {
        super.onCreate();

        context = new WeakReference<>(getApplicationContext());
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });

        CustomCrashHandler mCustomCrashHandler = CustomCrashHandler.getInstance();
        mCustomCrashHandler.setCustomCrashHanler(getApplicationContext());

        zhiHuApi = RetrofitFactory.getRetrofitGsonService();
        PackageInfo packageInfo = getVersion();
        checkVersion(packageInfo.versionName);

        registerActivityLifecycleCallbacks(new MyActivityLifecycle());
    }

    public static Context getContext() {
        return context.get();
    }

    /**
     * 检查版本
     *
     * @param versionName 当前版本
     */
    private void checkVersion(final String versionName) {
        IVersion version = new VersionModel();
        version.checkVersion(versionName);
    }

    /**
     * 获取版本号和版本名
     *
     * @return 当前应用的版本号
     */
    public PackageInfo getVersion() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            return info;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // TODO: 2018/6/22 封装数据统计 
    private static class MyActivityLifecycle implements ActivityLifecycleCallbacks{
        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    }
}
