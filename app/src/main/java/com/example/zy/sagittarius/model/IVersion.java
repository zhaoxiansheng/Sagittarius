package com.example.zy.sagittarius.model;

/**
 * Created by zhaoy on 2017/9/21.
 */

public interface IVersion {

    String getStatus();
    String getLatest();
    String getUrl();
    String getMsg();
    void checkVersion(String versionName);
}
