package com.example.firebaseexample.app.signup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.firebaseexample.R;
import com.example.firebaseexample.common.BaseActivity;
import com.example.firebaseexample.databinding.SignupActivityBinding;
import com.example.firebaseexample.model.User;

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
        getBinding().setViewModel(new User());
    }

    @Override
    public void onClick(View v) {
        if(v.equals(getBinding().btnSignup)){
            User user = getBinding().getViewModel();
            getViewModel().insertUser(user);
            finish();
        }
    }

    private void setListener(){
        getBinding().btnSignup.setOnClickListener(this);
    }
}
