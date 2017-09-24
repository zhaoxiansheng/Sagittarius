package com.example.zy.sagittarius.net;

import com.example.zy.sagittarius.bean.HttpConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created ZY on 2017/6/23.
 */

public class HttpControl {

    public static void sendOkHttpRequest(String address, String tag, Callback callback) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .build();
        OkHttpClient client = builder.build();
        Request request = new Request.Builder().url(address)
                .tag(tag)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
