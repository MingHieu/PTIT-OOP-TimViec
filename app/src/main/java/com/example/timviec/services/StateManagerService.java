package com.example.timviec.services;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.timviec.model.User;

public class StateManagerService {
    private MutableLiveData<User> user = new MutableLiveData<>();
    private MutableLiveData<String> authToken = new MutableLiveData<>();

    public StateManagerService() {
        user.setValue(new User(10, null, null, null));
        authToken.setValue("");
    }

    public User getUser() {
        return user.getValue();
    }

    public void setUser(User user) {
        this.user.setValue(user);
    }

    public void setOnChangeUser(Context context, Runnable runnable) {
        user.observe((LifecycleOwner) context, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                runnable.run();
            }
        });
    }

    public String getAuthToken() {
        return authToken.getValue();
    }

    public void setAuthToken(String authToken) {
        this.authToken.setValue(authToken);
    }

    public void setOnChangeAuthToken(Context context, Runnable runnable) {
        authToken.observe((LifecycleOwner) context, new Observer<String>() {
            @Override
            public void onChanged(String authToken) {
                runnable.run();
            }
        });
    }
}
