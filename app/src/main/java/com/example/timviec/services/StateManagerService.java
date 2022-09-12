package com.example.timviec.services;

import com.example.timviec.model.User;

public class StateManagerService {
    private User user;
    private String authToken;

    public StateManagerService() {
        user = new User();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
