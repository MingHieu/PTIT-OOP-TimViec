package com.example.timviec;

import android.app.Application;

import com.example.timviec.services.StateManagerService;

public class App extends Application {
    private static App mContext;
    private final StateManagerService stateManager = new StateManagerService();

    public static App getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public StateManagerService getStateManager() {
        return stateManager;
    }

}

