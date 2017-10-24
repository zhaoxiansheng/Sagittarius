package com.example.zy.sagittarius.net;

import com.example.zy.sagittarius.bean.Latest;
import com.example.zy.sagittarius.model.Themes;
import com.example.zy.sagittarius.model.VersionModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created on 2017/9/19.
 * 知乎api
 *
 * @author zhaoy
 */

public interface ZhiHuApi {

    String BASE_URL = "https://news-at.zhihu.com/api/4/";

    /**
     * 版本查询
     *
     * @param versionCode 版本号
     * @return 版本信息
     */
    @GET("version/android/{versionCode}")
    Observable<VersionModel> checkVersion(@Path("versionCode") String versionCode);

    /**
     * 主题查询
     *
     * @return 主题对象
     */
    @GET("themes")
    Observable<Themes> getTopicList();

    /**
     * Latest列表的获取
     *
     * @return latest列表
     */
    @GET("news/latest")
    Observable<Latest> getLatestList();
}
