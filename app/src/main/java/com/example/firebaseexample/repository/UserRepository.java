package com.example.firebaseexample.repository;

import android.content.Context;

import com.example.firebaseexample.model.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseError;
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

    public boolean insertUser(User user){
        try{
            reference.child("user").push().setValue(user);
        }
        catch(Exception e){
            return false;
        }
        return true;
    }
}
