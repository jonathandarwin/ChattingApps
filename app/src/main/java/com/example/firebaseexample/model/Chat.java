package com.example.firebaseexample.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.firebaseexample.BR;

public class Chat extends BaseObservable {

    public final static int RIGHT = 1;
    public final static int LEFT = 2;

    protected String email;
    protected String name;
    protected String message;

    @Bindable
    public String getEmail() {
        return email;
    }

    public Chat setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
        return this;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public Chat setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
        return this;
    }

    @Bindable
    public String getMessage() {
        return message;
    }

    public Chat setMessage(String message) {
        this.message = message;
        notifyPropertyChanged(BR.message);
        return this;
    }
}
