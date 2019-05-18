package com.example.firebaseexample.app.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.firebaseexample.R;
import com.example.firebaseexample.app.signup.SignUpActivity;
import com.example.firebaseexample.common.BaseActivity;
import com.example.firebaseexample.databinding.LoginActivityBinding;

public class LoginActivity extends BaseActivity<LoginActivityBinding, LoginViewModel>
            implements View.OnClickListener{

    public LoginActivity(){
        super(LoginViewModel.class, R.layout.login_activity);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListener();
    }

    private void setListener(){
        getBinding().btnLogin.setOnClickListener(this);
        getBinding().txtSignup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(getBinding().btnLogin)){

        }
        else if(v.equals(getBinding().txtSignup)){
            gotoSignupIntent();
        }
    }

    protected void gotoSignupIntent(){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}
