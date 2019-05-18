package com.example.firebaseexample.app.signup;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.widget.Toast;

import com.example.firebaseexample.model.User;
import com.example.firebaseexample.repository.UserRepository;

public class SignUpViewModel extends ViewModel {

    private Context context;
    private UserRepository repo;

    public SignUpViewModel(Context context){
        this.context = context;
        repo = UserRepository.getInstance();
    }

    public void insertUser(User user){
        if(repo.insertUser(user)){
            Toast.makeText(context, "Register Success", Toast.LENGTH_SHORT).show();
        }
    }
}
