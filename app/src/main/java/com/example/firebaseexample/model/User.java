package com.example.firebaseexample.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.firebaseexample.BR;

public class User extends BaseObservable {
    protected String email;
    protected String password;
    protected String name;

    @Bindable
    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
        return this;
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
        return this;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
        return this;
    }
}
