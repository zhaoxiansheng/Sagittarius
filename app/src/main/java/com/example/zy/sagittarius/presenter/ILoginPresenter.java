package com.example.zy.sagittarius.presenter;

/**
 * Created by ZY on 2017/6/11.
 */

public interface ILoginPresenter {
    void clear();
    void doLogin(String name, String passwd);
    void setProgressBarVisiblity(int visiblity);
    void onHideKeyboard();
}
