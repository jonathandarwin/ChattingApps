package com.example.firebaseexample.factory;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.firebaseexample.app.login.LoginActivity;
import com.example.firebaseexample.app.login.LoginViewModel;
import com.example.firebaseexample.app.signup.SignUpActivity;
import com.example.firebaseexample.app.signup.SignUpViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private Context context;

    public ViewModelFactory(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(context instanceof LoginActivity){
            return (T) new LoginViewModel(context);
        }
        if(context instanceof SignUpActivity){
            return (T) new SignUpViewModel(context);
        }
        return null;
    }
}
