package com.example.timviec.views;

import android.content.Intent;
import android.os.Bundle;

import com.example.timviec.R;
import com.example.timviec.Utils;
import com.example.timviec.components.CustomButton;
import com.example.timviec.components.CustomInput;
import com.example.timviec.router.BottomTab;

public class SignupEnterpriseScreen extends Utils.BaseActivity {
    private CustomInput mName;
    private CustomInput mAddress;
    private CustomInput mEmail;
    private CustomInput mPassword;
    private CustomInput mRepeatPassword;
    private CustomButton mSignupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_enterprise_screen);

        setUpScreen();
        mName = findViewById(R.id.sign_up_enterprise_name);
        mAddress = findViewById(R.id.sign_up_enterprise_address);
        mEmail = findViewById(R.id.sign_up_enterprise_email);
        mPassword = findViewById(R.id.sign_up_enterprise_password);
        mRepeatPassword = findViewById(R.id.sign_up_enterprise_repeat_password);

        mSignupButton = findViewById(R.id.sign_up_enterprise_button);
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