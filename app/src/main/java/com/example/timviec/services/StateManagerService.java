package com.example.timviec.services;

import com.example.timviec.model.User;

public class StateManagerService {
    private User user;

     public StateManagerService() {
        user = new User();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
