package com.example.firebaseexample.app.chat;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.firebaseexample.R;
import com.example.firebaseexample.databinding.ChatItemLeftBinding;
import com.example.firebaseexample.databinding.ChatItemRightBinding;
import com.example.firebaseexample.model.Chat;
import com.example.firebaseexample.model.SESSION;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<Chat> listChat;

    public ChatAdapter(Context context, List<Chat> listChat){
        this.context = context;
        this.listChat = listChat;
    }


    public class RightViewHolder extends RecyclerView.ViewHolder{
        ChatItemRightBinding binding;
        public RightViewHolder(ChatItemRightBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public class LeftViewHolder extends RecyclerView.ViewHolder{
        ChatItemLeftBinding binding;
        public LeftViewHolder(ChatItemLeftBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(listChat.get(i).getEmail().equals(SESSION.email)){
            ChatItemRightBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.chat_item_right, viewGroup, false);
            return new RightViewHolder(binding);
        }
        else {
            ChatItemLeftBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.chat_item_left, viewGroup, false);
            return new LeftViewHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Chat chat = listChat.get(i);
        if(viewHolder instanceof RightViewHolder){
            ((RightViewHolder) viewHolder).binding.setViewModel(chat);
        }
        else if (viewHolder instanceof LeftViewHolder){
            ((LeftViewHolder) viewHolder).binding.setViewModel(chat);
        }
    }

    @Override
    public int getItemCount() {
        return listChat.size();
    }
}
