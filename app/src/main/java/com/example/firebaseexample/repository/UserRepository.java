package com.example.firebaseexample.repository;

import com.example.firebaseexample.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserRepository {

    private static UserRepository instance;
    DatabaseReference reference;

    public UserRepository(){
        reference = FirebaseDatabase.getInstance().getReference();
    }

    public static UserRepository getInstance(){
        if(instance == null){
            instance = new UserRepository();
        }
        return instance;
    }

    public void insertUser(User user){
        reference.child("users").setValue(user);
    }
}
