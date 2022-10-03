package com.example.timviec.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.timviec.R;
import com.example.timviec.Utils;
import com.example.timviec.components.CustomButton;

public class LogoutScreen extends Utils.BaseActivity {
    private CustomButton mCancelButton;
    private CustomButton mLogoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout_screen);

        setUpScreen();

        mCancelButton = findViewById(R.id.logout_screen_btn_cancel);
        mLogoutButton = findViewById(R.id.logout_screen_btn_approve);

    }
}