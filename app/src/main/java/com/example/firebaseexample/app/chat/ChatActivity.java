package com.example.firebaseexample.app.chat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.firebaseexample.R;
import com.example.firebaseexample.common.BaseActivity;
import com.example.firebaseexample.databinding.ChatActivityBinding;
import com.example.firebaseexample.model.Chat;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends BaseActivity<ChatActivityBinding, ChatViewModel>
            implements View.OnClickListener{

    List<Chat> listChat;
    ChatAdapter adapter;

    public ChatActivity(){
        super(ChatViewModel.class, R.layout.chat_activity);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAdapter;
        setListener();
        getBinding().setViewModel(new Chat());
    }

    @Override
    public void onClick(View v) {
        if(v.equals(getBinding().btnSend)){
            Chat chat = getBinding().getViewModel();
            if(getViewModel().insertChat(chat)){
                
            }
            else{
                Toast.makeText(this, "Error. Please try again", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setListener(){
        getBinding().btnSend.setOnClickListener(this);
    }

    private void initAdapter(){
        listChat = new ArrayList<>();
        adapter = new ChatAdapter(this, listChat);
        getBinding().recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getBinding().recyclerView.setHasFixedSize(true);
        getBinding().recyclerView.setAdapter(adapter);
    }
}