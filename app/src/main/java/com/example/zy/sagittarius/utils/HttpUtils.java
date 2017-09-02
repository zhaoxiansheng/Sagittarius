package com.example.zy.sagittarius.utils;

import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created ZY on 2017/6/23.
 */

public class HttpUtils {

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
