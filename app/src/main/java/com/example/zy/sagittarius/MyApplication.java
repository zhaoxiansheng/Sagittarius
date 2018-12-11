package com.example.zy.sagittarius;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.example.zy.sagittarius.net.RetrofitFactory;
import com.example.zy.sagittarius.net.ZhiHuApi;
import com.example.zy.sagittarius.utils.CustomCrashHandler;
import com.example.zy.sagittarius.utils.ThreadPoolUtils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.lang.ref.WeakReference;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created on 2017/6/24.
 * application
 *
 * @author zhaoy
 */

public class MyApplication extends Application {

    private static WeakReference<Context> context;

    public static ZhiHuApi zhiHuApi;

    public static ThreadPoolExecutor threadPoolExecutor;

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

        int corePoolSize = Runtime.getRuntime().availableProcessors();
        LinkedBlockingDeque<Runnable> workQueue = new LinkedBlockingDeque<>();
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
        threadPoolExecutor = ThreadPoolUtils.getInstance(corePoolSize, 2 * corePoolSize + 1, 30,
                TimeUnit.SECONDS, workQueue, threadFactory, handler);

        CustomCrashHandler mCustomCrashHandler = CustomCrashHandler.getInstance();
        mCustomCrashHandler.setCustomCrashHandler(getContext());

        zhiHuApi = RetrofitFactory.getRetrofitGsonService();
        PackageInfo packageInfo = getVersion();

        registerActivityLifecycleCallbacks(new MyActivityLifecycle());
    }

    public static Context getContext() {
        return context.get();
    }

    /**
     * 获取版本号和版本名
     *
     * @return 当前应用的版本号
     */
    public PackageInfo getVersion() {
        try {
            PackageManager manager = this.getPackageManager();
            return manager.getPackageInfo(this.getPackageName(), 0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // TODO: 2018/6/22 封装数据统计 
    private static class MyActivityLifecycle implements ActivityLifecycleCallbacks {
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
