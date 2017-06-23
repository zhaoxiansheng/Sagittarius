package com.example.zy.sagittarius.view;

/**
 * Created by ZY on 2017/6/11.
 */

public interface ILoginView {
    void onClearText();
    void onLoginResult(Boolean result, int code);
    void onSetProgressBarVisibility(int visibility);
    void onHideKeyboard();
}
