package com.example.firebaseexample.app.login;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.Nullable;

import com.example.firebaseexample.common.APIHandler;
import com.example.firebaseexample.model.User;
import com.example.firebaseexample.repository.UserRepository;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public class LoginViewModel extends ViewModel {
    private Context context;
    UserRepository repo;

    public LoginViewModel(Context context){
        this.context = context;
        repo = UserRepository.getInstance();
    }

    public LiveData<Boolean> getUser(final User user){
        final MutableLiveData<Boolean> result = new MutableLiveData<>();
        repo.getUser(user, new APIHandler() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                Boolean bool = new Boolean(false);
                for(DataSnapshot item : dataSnapshot.getChildren()){
                    User data = item.getValue(User.class);
                    if(data.getPassword().equals(user.getPassword())){
                        bool = true;
                    }
                    else{
                        bool = false;
                    }
                    break;
                }
                result.postValue(bool);
            }

            @Override
            public void onError(DatabaseError databaseError) {

                result.postValue(false);
            }
        });
        return result;
    }
}
