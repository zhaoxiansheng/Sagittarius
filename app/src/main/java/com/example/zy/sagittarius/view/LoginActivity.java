package com.example.zy.sagittarius.view;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.zy.sagittarius.R;
import com.example.zy.sagittarius.presenter.ILoginPresenter;
import com.example.zy.sagittarius.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements ILoginView, View.OnClickListener {

  private EditText editUser;
  private EditText editPass;
  private Button btnLogin;
  private Button btnClear;
  ILoginPresenter loginPresenter;
  private ProgressBar progressBar;
  private TextInputLayout userWrapper;
  private TextInputLayout passwordWrapper;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    //find view
    editUser = (EditText) this.findViewById(R.id.et_login_username);
    editPass = (EditText) this.findViewById(R.id.et_login_password);
    btnLogin = (Button) this.findViewById(R.id.btn_login_login);
    btnClear = (Button) this.findViewById(R.id.btn_login_clear);
    progressBar = (ProgressBar) this.findViewById(R.id.progress_login);

    btnLogin.setOnClickListener(this);
    btnClear.setOnClickListener(this);

    loginPresenter = new LoginPresenter(this);
    loginPresenter.setProgressBarVisiblity(View.INVISIBLE);

    userWrapper = (TextInputLayout) findViewById(R.id.username_wrapper);
    passwordWrapper = (TextInputLayout) findViewById(R.id.password_wrapper);
  }

  @Override public void onHideKeyboard() {
    View view = getCurrentFocus();
    if (view != null) {
      ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
          view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {
      case R.id.btn_login_clear:
        loginPresenter.clear();
        break;
      case R.id.btn_login_login:
        loginPresenter.setProgressBarVisiblity(View.VISIBLE);
        btnLogin.setEnabled(false);
        btnClear.setEnabled(false);
        loginPresenter.doLogin(editUser.getText().toString(), editPass.getText().toString());
        loginPresenter.onHideKeyboard();
        break;
      default:
        break;
    }
  }

  @Override public void onClearText() {
    editUser.setText("");
    editPass.setText("");
  }

  @Override public void onLoginResult(Boolean result, int code) {
    loginPresenter.setProgressBarVisiblity(View.INVISIBLE);
    btnLogin.setEnabled(true);
    btnClear.setEnabled(true);
    if (result) {
      startActivity(new Intent(this, WelComeActivity.class));
      userWrapper.setErrorEnabled(false);
      passwordWrapper.setErrorEnabled(false);
    } else {
      switch (code) {
        case -1:
          userWrapper.setError("用户名不能为空");
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

  @Override public void onSetProgressBarVisibility(int visibility) {
    progressBar.setVisibility(visibility);
  }
}
