package com.example.firebaseexample.repository;

import android.util.Log;

import com.example.firebaseexample.model.Chat;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChatRepository {

    private static ChatRepository instance;
    DatabaseReference reference;

    public static ChatRepository getInstance(){
        if(instance == null){
            instance = new ChatRepository();
        }
        return instance;
    }

    public ChatRepository(){
        reference = FirebaseDatabase.getInstance().getReference();
    }

    public boolean insertChat(Chat chat){
        try{
            reference.child("chat").push().setValue(chat);
        }
        catch(Exception e){
            Log.d("<ERR>", "<ERR> " + e.toString());
            return false;
        }
        return true;
    }
}
