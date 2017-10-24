package com.example.zy.sagittarius.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created on 2017/9/26.
 * 自定义网络拦截器
 *
 * @author zhaoy
 */

public class NetworkInterceptor implements Interceptor {

    private static final int MAX_TRY_COUNT = 5;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        int tryCount = 0;
        while (!response.isSuccessful() && tryCount < MAX_TRY_COUNT) {
            tryCount++;
            response = chain.proceed(request);
        }
        return response;
    }
}
