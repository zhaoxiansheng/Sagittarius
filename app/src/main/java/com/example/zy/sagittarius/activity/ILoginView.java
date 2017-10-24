package com.example.zy.sagittarius.activity;

/**
 * Created on 2017/6/11.
 *
 * @author zhaoy
 */

public interface ILoginView {

    /**
     * 清除用户名密码
     */
    void onClearText();

    /**
     * 登陆结果
     *
     * @param result 是否成功
     * @param code   返回码
     */
    void onLoginResult(Boolean result, int code);

    /**
     * 设置是否显示progressbar
     *
     * @param visibility 是否显示
     */
    void onSetProgressBarVisibility(int visibility);

    /**
     * 隐藏软键盘
     */
    void onHideKeyboard();
}
