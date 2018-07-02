package com.example.zy.sagittarius.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;

import java.io.InputStream;

public class BitmapTools {

    /**
     * 压缩图片(加载资源图片)
     *
     * @param context    上下文
     * @param recourseId 资源id
     * @param reqWidth   目标view的宽度
     * @param reqHeight  目标view的高度
     * @return 返回一个压缩好的bitmap
     */
    public static Bitmap compress(Context context, int recourseId, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), recourseId, options);
        options.inSampleSize = caculateSampleSize(options, reqWidth, reqHeight);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(context.getResources(), recourseId, options);
    }

    /**
     * 压缩图片(加载本地图片)
     *
     * @param path      文件路径
     * @param reqWidth  目标view的宽度
     * @param reqHeight 目标view的高度
     * @return 返回一个压缩好的bitmap
     */
    public static Bitmap compress(String path, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        options.inSampleSize = caculateSampleSize(options, reqWidth, reqHeight);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }

    /**
     * 压缩图片(加载网络图片)
     *
     * @param inputStream 文件流
     * @param reqWidth    目标view的宽度
     * @param reqHeight   目标view的高度
     * @return 返回一个压缩好的bitmap
     */
    public static Bitmap compress(InputStream inputStream, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, options);
        options.inSampleSize = caculateSampleSize(options, reqWidth, reqHeight);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(inputStream, null, options);
    }

    /**
     * 计算压缩的大小
     *
     * @param options   BitmapFactory.Options
     * @param reqWidth  需要的宽度
     * @param reqHeight 需要的高度
     * @return 压缩的值
     */
    private static int caculateSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int sampleSize = 1;
        int picWidth = options.outWidth;
        int picHeight = options.outHeight;
        if (picWidth > reqWidth || picHeight > reqHeight) {
            int halfPicWidth = picWidth / 2;
            int halfPicHeight = picHeight / 2;
            while (halfPicWidth / sampleSize > reqWidth || halfPicHeight / sampleSize > reqHeight) {
                sampleSize *= 2;
            }
        }
        return sampleSize;
    }
}
