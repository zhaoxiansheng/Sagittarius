package com.example.zy.sagittarius.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.example.zy.sagittarius.MyApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created  on 2017/7/3.
 * 自定义系统的Crash捕捉类，用Toast替换系统的对话框
 * 将软件版本信息，设备信息，出错信息保存在sd卡中，你可以上传到服务器中
 *
 * @author zhaoy
 */
public final class CustomCrashHandler implements UncaughtExceptionHandler {
    /**
     * tag
     */
    private static final String TAG = "CustomCrashHandler";
    /**
     * 上下文
     */
    private Context mContext;
    private static final String SDCARD_ROOT = Environment.getExternalStorageDirectory().toString();
    /**
     * 私有化静态当前对象，
     */
    private static WeakReference<CustomCrashHandler> mInstance;

    private CustomCrashHandler() {
    }

    /**
     * 单例模式，保证只有一个CustomCrashHandler实例存在
     *
     * @return 返回Custom实例
     */
    public static CustomCrashHandler getInstance() {
        if (mInstance == null){
            synchronized (CustomCrashHandler.class){
                if (mInstance == null){
                    mInstance = new WeakReference<>(new CustomCrashHandler());
                }
            }
        }
        return mInstance.get();
    }

    /**
     * 异常发生时，系统回调的函数，我们在这里处理一些操作
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        //将一些信息保存到SDcard中
        saveInfoToSD(mContext, ex);

        //提示用户程序即将退出
        showToast(mContext);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    /**
     * 为我们的应用程序设置自定义Crash处理
     */
    public void setCustomCrashHandler(Context context) {
        mContext = context;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 显示提示信息，需要在线程中显示Toast
     */
    private void showToast(final Context context) {
        MyApplication.threadPoolExecutor.execute(() -> {
            Looper.prepare();
            Toast.makeText(context, "很抱歉，程序遭遇异常，即将退出！", Toast.LENGTH_LONG).show();
            Looper.loop();
        });
    }

    /**
     * 获取一些简单的信息,软件版本，手机版本，型号等信息存放在HashMap中
     */
    private HashMap<String, String> obtainSimpleInfo(Context context) {
        HashMap<String, String> map = new HashMap<>();
        PackageManager mPackageManager = context.getPackageManager();
        PackageInfo mPackageInfo = null;
        try {
            mPackageInfo =
                    mPackageManager.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }

        if (mPackageInfo != null) {
            map.put("versionName", mPackageInfo.versionName);
            map.put("versionCode", "" + mPackageInfo.versionCode);

            map.put("MODEL", "" + Build.MODEL);
            map.put("SDK_INT", "" + Build.VERSION.SDK_INT);
            map.put("PRODUCT", "" + Build.PRODUCT);
        }

        return map;
    }

    /**
     * 获取系统未捕捉的错误信息
     */
    private String obtainExceptionInfo(Throwable throwable) {
        StringWriter mStringWriter = new StringWriter();
        PrintWriter mPrintWriter = new PrintWriter(mStringWriter);
        throwable.printStackTrace(mPrintWriter);
        mPrintWriter.close();

        Log.e(TAG, mStringWriter.toString());
        return mStringWriter.toString();
    }

    /**
     * 保存获取的 软件信息，设备信息和出错信息保存在SDcard中
     * @param context 上下文
     * @param ex 异常
     * @return 文件名字
     */
    private String saveInfoToSD(Context context, Throwable ex) {
        String fileName = null;
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, String> entry : obtainSimpleInfo(context).entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key).append(" = ").append(value).append("\n");
        }

        sb.append(obtainExceptionInfo(ex));

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File dir = new File(SDCARD_ROOT + File.separator + "crash" + File.separator);
            if (!dir.exists()) {
                dir.mkdir();
            }

            try {
                fileName = dir.toString() + File.separator + parseTime(System.currentTimeMillis()) + ".log";
                FileOutputStream fos = new FileOutputStream(fileName);
                fos.write(sb.toString().getBytes());
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return fileName;
    }

    /**
     * 将毫秒数转换成yyyy-MM-dd-HH-mm-ss的格式
     */
    private String parseTime(long milliseconds) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.CHINA);
        return format.format(new Date(milliseconds));
    }
}
