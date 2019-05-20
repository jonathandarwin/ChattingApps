package com.example.firebaseexample.app.chat;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import com.example.firebaseexample.common.APIHandler;
import com.example.firebaseexample.model.Chat;
import com.example.firebaseexample.model.SESSION;
import com.example.firebaseexample.repository.ChatRepository;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import java.util.ArrayList;
import java.util.List;

public class ChatViewModel extends ViewModel {

    private ChatRepository repo;
    private Context context;

    public ChatViewModel(Context context){
        this.context = context;
        repo = ChatRepository.getInstance();
    }

    public boolean insertChat(Chat chat){
        chat.setEmail(SESSION.email);
        chat.setName(SESSION.name);
        if(repo.insertChat(chat)){
            return true;
        }
        return false;
    }

    public LiveData<List<Chat>> observeMessage(){
        final MutableLiveData<List<Chat>> result = new MutableLiveData<>();
        repo.observeData(new APIHandler() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                List<Chat> listChat = new ArrayList<>();
                int i = 1;
                for(DataSnapshot item : dataSnapshot.getChildren()){
                    Chat chat = item.getValue(Chat.class);
                    listChat.add(chat);
                }
                result.postValue(listChat);
            }

            @Override
            public void onError(DatabaseError databaseError) {
                result.postValue(new ArrayList<Chat>());
            }
        });
        return result;
    }
}
