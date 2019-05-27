package com.example.firebaseexample.app.chat;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.firebaseexample.common.APIHandler;
import com.example.firebaseexample.common.Singleton;
import com.example.firebaseexample.model.Chat;
import com.example.firebaseexample.model.SESSION;
import com.example.firebaseexample.repository.ChatRepository;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import java.util.ArrayList;
import java.util.List;

public class ChatViewModel extends ViewModel {

    private ChatRepository repo;
    private Context context;
    final private String serverKey = "key=" + "AAAATMnF4pI:APA91bHf6iFgtNGHDLJbcdr2rbKrufIbfMkCkQ045xR3GXp709W1g5YRJCpQTkZoLLLCfBzuZ1uOmqzcel8fetFfVX_arQ6RsNVyV0itWaR_4S-nwuBlRo8sra6QIbQ98rCifipGGwdn";
    final private String FCM_API = "https://fcm.googleapis.com/fcm/send";
    final private String contentType = "application/json";

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

    public void sendNotification(Chat chat){
        String topic = "/topics/getNotification";
        JSONObject notification = new JSONObject();
        JSONObject notificationBody = new JSONObject();
        try{
            notificationBody.put("email", SESSION.email);
            notificationBody.put("name", SESSION.name);
            notificationBody.put("message", chat.getMessage());

            notification.put("to", topic);
            notification.put("data", notificationBody);
        }catch (Exception e) {
            Log.d("<ERR>", "sendNotification ga masuk");
        }
        requestNotification(notification);
    }

    private void requestNotification(JSONObject notification) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(FCM_API, notification,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("<REQ>", "onResponse: " + response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("<ERR>", "onErrorResponse: Didn't work");
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", serverKey);
                params.put("Content-Type", contentType);
                return params;
            }
        };
        Singleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }
}
