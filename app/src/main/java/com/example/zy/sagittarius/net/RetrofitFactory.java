package com.example.zy.sagittarius.net;

import com.orhanobut.logger.Logger;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created on 2017/9/21.
 * retrofit单例
 *
 * @author zhaoy
 */

public class RetrofitFactory {

    private static final long TIMEOUT = 30;

    private static OkHttpClient httpClient = new OkHttpClient.Builder()
            .addInterceptor(new NetworkInterceptor())
            .addInterceptor(chain -> {
                Request request = chain.request();

                long t1 = System.nanoTime();

                Response response = chain.proceed(request);

                long t2 = System.nanoTime();

                ResponseBody responseBody = response.body();
                BufferedSource source = null;
                if (responseBody != null) {
                    source = responseBody.source();
                    source.request(Long.MAX_VALUE);
                }
                Buffer buffer = null;
                if (source != null) {
                    buffer = source.buffer();
                }

                String responseBodyString = null;
                if (buffer != null) {
                    responseBodyString = buffer.clone().readString(Charset.forName("UTF-8"));
                }


                Logger.d("url:" + response.request().url()
                        + "\ntime:" + (t2 - t1) / 1e6d
                        + "ms\nbody:" + responseBodyString);
                return response;
            })
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build();

    private static ZhiHuApi retrofitGsonService = new Retrofit.Builder()
            .baseUrl(ZhiHuApi.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build()
            .create(ZhiHuApi.class);

    public static ZhiHuApi getRetrofitGsonService() {
        return retrofitGsonService;
    }

    public static <T> void doHttpRequest(Observable<T> pObservable, BaseObserver<T> baseObserver) {
        Observable<T> observable = pObservable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(baseObserver);
    }
}
