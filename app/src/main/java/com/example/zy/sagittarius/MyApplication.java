package com.example.zy.sagittarius;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.example.zy.sagittarius.model.IVersion;
import com.example.zy.sagittarius.model.VersionModel;
import com.example.zy.sagittarius.net.RetrofitFactory;
import com.example.zy.sagittarius.net.ZhiHuApi;
import com.example.zy.sagittarius.utils.CustomCrashHandler;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created on 2017/6/24.
 * application
 *
 * @author zhaoy
 */

public class MyApplication extends Application {

    public static Context context;
    public static ZhiHuApi zhiHuApi;
    private IVersion version;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
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
    }

    /**
     * 检查版本
     *
     * @param versionName 当前版本
     */
    private void checkVersion(final String versionName) {
        version = new VersionModel();
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
}
