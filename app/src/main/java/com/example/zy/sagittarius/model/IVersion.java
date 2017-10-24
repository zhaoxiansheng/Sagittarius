package com.example.zy.sagittarius.model;

/**
 * Created on 2017/9/21.
 *
 * @author zhaoy
 */

public interface IVersion {

    /**
     * 版本接口返回的值
     *
     * @return 状态
     */
    String getStatus();

    /**
     * 版本接口返回的值
     *
     * @return latest
     */
    String getLatest();

    /**
     * 更新地址
     *
     * @return 需要更新时的更新地址
     */
    String getUrl();

    /**
     * 版本更新信息，都更新了什么
     *
     * @return 更新信息
     */
    String getMsg();

    /**
     * 检查版本
     * @param versionName 当前版本号
     */
    void checkVersion(String versionName);
}
