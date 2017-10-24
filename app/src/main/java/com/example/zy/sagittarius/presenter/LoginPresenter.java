package com.example.zy.sagittarius.presenter;

import android.os.Handler;
import android.os.Looper;

import com.example.zy.sagittarius.activity.ILoginView;
import com.example.zy.sagittarius.model.IUser;
import com.example.zy.sagittarius.model.UserModel;

/**
 * Created on 2017/6/11.
 *
 * @author zhaoy
 */

public class LoginPresenter implements ILoginPresenter {

    private ILoginView iLoginView;
    private IUser user;
    private Handler handler;

    public LoginPresenter(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        initUser();
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void clear() {
        iLoginView.onClearText();
    }

    @Override
    public void doLogin(String name, String passwd) {
        Boolean isLoginSuccess = true;
        final int code = user.checkUserValidity(name, passwd);
        if (code != 0) {
            isLoginSuccess = false;
        }
        final Boolean result = isLoginSuccess;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iLoginView.onLoginResult(result, code);
            }
        }, 1000);
    }

    @Override
    public void setProgressBarVisiblity(int visiblity) {
        iLoginView.onSetProgressBarVisibility(visiblity);
    }

    @Override
    public void onHideKeyboard() {
        iLoginView.onHideKeyboard();
    }

    private void initUser() {
        user = new UserModel("111111", "111111");
    }
}
