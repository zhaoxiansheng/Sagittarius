package com.example.zy.sagittarius.presenter;

/**
 * Created on 2017/6/11.
 *
 * @author zhaoy
 */

public interface ILoginPresenter {
    /**
     * 清除用户名密码
     */
    void clear();

    /**
     * 登陆接口
     *
     * @param name   用户名
     * @param passwd 密码
     */
    void doLogin(String name, String passwd);

    /**
     * 登陆时是否展示progressbar
     *
     * @param visiblity 是否展示
     */
    void setProgressBarVisiblity(int visiblity);

    /**
     * 是否隐藏软件盘
     */
    void onHideKeyboard();
}
