package com.example.firebaseexample.app.login;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.example.firebaseexample.R;
import com.example.firebaseexample.app.chat.ChatActivity;
import com.example.firebaseexample.app.signup.SignUpActivity;
import com.example.firebaseexample.common.BaseActivity;
import com.example.firebaseexample.databinding.LoginActivityBinding;
import com.example.firebaseexample.model.User;

public class LoginActivity extends BaseActivity<LoginActivityBinding, LoginViewModel>
            implements View.OnClickListener{

    public LoginActivity(){
        super(LoginViewModel.class, R.layout.login_activity);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListener();
        getBinding().setViewModel(new User());
    }

    private void setListener(){
        getBinding().btnLogin.setOnClickListener(this);
        getBinding().txtSignup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(getBinding().btnLogin)){
            User user = getBinding().getViewModel();
            getViewModel().getUser(user).observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(@Nullable Boolean aBoolean) {
                    if(aBoolean){
                        gotoIntent(ChatActivity.class, true);
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Incorrect email / password", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else if(v.equals(getBinding().txtSignup)){
            gotoIntent(SignUpActivity.class, false);
        }
    }

    protected void gotoIntent(Class classIntent, boolean isFinish){
        Intent intent = new Intent(this, classIntent);
        startActivity(intent);
        if(isFinish){
            finish();
        }
    }
}
