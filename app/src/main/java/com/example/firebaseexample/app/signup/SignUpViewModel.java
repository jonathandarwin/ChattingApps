package com.example.firebaseexample.app.signup;

import android.arch.lifecycle.ViewModel;

import com.example.firebaseexample.model.User;
import com.example.firebaseexample.repository.UserRepository;

public class SignUpViewModel extends ViewModel {

    private UserRepository repo;

    public SignUpViewModel(){
        repo = UserRepository.getInstance();
    }

    public void insertUser(User user){

    }
}
