package com.example.zy.sagittarius.activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.zy.sagittarius.R;
import com.example.zy.sagittarius.presenter.ILoginPresenter;
import com.example.zy.sagittarius.presenter.LoginPresenter;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created on 2017/9/19.
 * 登陆界面
 *
 * @author zhaoy
 */
public class LoginActivity extends AppCompatActivity implements ILoginView {

    @BindView(R.id.et_login_username)
    EditText etLoginUsername;
    @BindView(R.id.username_wrapper)
    TextInputLayout usernameWrapper;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;
    @BindView(R.id.password_wrapper)
    TextInputLayout passwordWrapper;
    @BindView(R.id.btn_login_login)
    Button btnLoginLogin;
    @BindView(R.id.btn_login_clear)
    Button btnLoginClear;
    @BindView(R.id.progress_login)
    ProgressBar progressLogin;

    ILoginPresenter loginPresenter;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            Transition explode = TransitionInflater.from(this).inflateTransition(android.R.transition.explode);
            getWindow().setEnterTransition(explode);
        }
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loginPresenter = new LoginPresenter(this);
        loginPresenter.setProgressBarVisiblity(View.INVISIBLE);

        sharedPreferences = getSharedPreferences("login", Activity.MODE_PRIVATE);
    }

    @Override
    public void onHideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) Objects.requireNonNull(getSystemService(Context.INPUT_METHOD_SERVICE))).hideSoftInputFromWindow(
                    view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    @Override
    public void onClearText() {
        etLoginUsername.setText("");
        etLoginPassword.setText("");
    }

    @Override
    public void onLoginResult(Boolean result, int code) {
        loginPresenter.setProgressBarVisiblity(View.INVISIBLE);
        btnLoginLogin.setEnabled(true);
        btnLoginClear.setEnabled(true);
        if (result) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name", etLoginUsername.getText().toString());
            editor.putString("pass", etLoginPassword.getText().toString());
            editor.apply();
            usernameWrapper.setErrorEnabled(false);
            passwordWrapper.setErrorEnabled(false);
            HomeActivity.activityIntent(LoginActivity.this);
            finish();
        } else {
            switch (code) {
                case -1:
                    usernameWrapper.setError("用户名不能为空");
                    passwordWrapper.setError("密码不能为空");
                    break;
                case -2:
                    Toast.makeText(LoginActivity.this, "用户名密码错误", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {
        progressLogin.setVisibility(visibility);
    }

    public static void activityIntent(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle());
        } else {
            activity.startActivity(intent);
        }
    }

    @OnClick({R.id.btn_login_login, R.id.btn_login_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login_login:
                loginPresenter.setProgressBarVisiblity(View.VISIBLE);
                btnLoginLogin.setEnabled(false);
                btnLoginClear.setEnabled(false);
                loginPresenter.doLogin(etLoginUsername.getText().toString(), etLoginPassword.getText().toString());
                loginPresenter.onHideKeyboard();
                break;
            case R.id.btn_login_clear:
                loginPresenter.clear();
                break;
            default:
                break;
        }
    }
}
