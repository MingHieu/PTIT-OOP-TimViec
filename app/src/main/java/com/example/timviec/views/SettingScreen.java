package com.example.timviec.views;

import android.content.Intent;
import android.os.Bundle;

import com.example.timviec.R;
import com.example.timviec.Utils;
import com.example.timviec.components.CustomButton;

public class SettingScreen extends Utils.BaseActivity {
    private CustomButton mLogoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_screen);

        setUpScreen("Cài đặt");

        mLogoutButton = findViewById(R.id.setting_logout_button);
        mLogoutButton.setHandleOnClick(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SettingScreen.this,SelectRoleScreen.class);
                startActivity(i);
                finish();
            }
        });
    }
}