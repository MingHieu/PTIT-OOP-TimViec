package com.example.timviec;

import android.app.Application;

import com.example.timviec.services.StateManagerService;

public class App extends Application {
    private StateManagerService stateManager = new StateManagerService();

    public StateManagerService getStateManager() {
        return stateManager;
    }
}

