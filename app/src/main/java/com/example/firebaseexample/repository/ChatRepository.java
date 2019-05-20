package com.example.firebaseexample.repository;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.firebaseexample.common.APIHandler;
import com.example.firebaseexample.model.Chat;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

    public void observeData(final APIHandler listener){
        ValueEventListener chatListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listener.onSuccess(dataSnapshot);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onError(databaseError);
            }
        };
        reference.child("chat").addValueEventListener(chatListener);
    }
}
