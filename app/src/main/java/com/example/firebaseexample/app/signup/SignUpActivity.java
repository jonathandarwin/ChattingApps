package com.example.firebaseexample.app.signup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.firebaseexample.R;
import com.example.firebaseexample.common.BaseActivity;
import com.example.firebaseexample.databinding.SignupActivityBinding;

public class SignUpActivity extends BaseActivity<SignupActivityBinding,SignUpViewModel>
                implements View.OnClickListener
{

    public SignUpActivity(){
        super(SignUpViewModel.class, R.layout.signup_activity);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListener();
    }

    @Override
    public void onClick(View v) {
        if(v.equals(getBinding().btnSignup)){

        }
    }

    private void setListener(){
        getBinding().btnSignup.setOnClickListener(this);
    }
}
