package com.example.timviec.views;

import android.content.Intent;
import android.os.Bundle;

import com.example.timviec.R;
import com.example.timviec.Utils;
import com.example.timviec.components.CustomButton;
import com.example.timviec.components.CustomInput;
import com.example.timviec.router.BottomTab;

public class SignupUserScreen extends Utils.BaseActivity {

    private CustomInput mName;
    private CustomInput mEmail;
    private CustomInput mPassword;
    private CustomInput mRepeatPassword;
    private CustomButton mSignupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_user_screen);

        setUpScreen();

        mName = findViewById(R.id.sign_up_user_name);
        mEmail = findViewById(R.id.sign_up_user_email);
        mPassword = findViewById(R.id.sign_up_user_password);
        mRepeatPassword = findViewById(R.id.sign_up_user_repeat_password);

        mSignupButton = findViewById(R.id.sign_up_user_button);
        mSignupButton.setHandleOnClick(new Runnable() {
            @Override
            public void run() {
                signup();
            }
        });

    }

    private void signup() {
        Intent i = new Intent(this, BottomTab.class);
        startActivity(i);
    }
}