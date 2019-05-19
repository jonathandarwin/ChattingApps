package com.example.firebaseexample.app.chat;

import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.firebaseexample.model.Chat;
import com.example.firebaseexample.repository.ChatRepository;

public class ChatViewModel extends ViewModel {

    private ChatRepository repo;
    private Context context;

    public ChatViewModel(Context context){
        this.context = context;
        repo = ChatRepository.getInstance();
    }

    public boolean insertChat(Chat chat){
        if(repo.insertChat(chat)){
            return true;
        }
        return false;
    }
}
