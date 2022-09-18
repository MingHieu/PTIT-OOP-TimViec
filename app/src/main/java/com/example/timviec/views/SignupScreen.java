package com.example.timviec.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;

import com.example.timviec.R;
import com.example.timviec.components.CustomButton;
import com.example.timviec.components.CustomInput;
import com.example.timviec.router.BottomTab;

public class SignupScreen extends AppCompatActivity {

    private CustomInput mFirstName;
    private CustomInput mLastName;
    private CustomInput mEmail;
    private CustomInput mPassword;
    private CustomInput mRepeatPassword;
    private CheckBox mCheckboxCondition;
    private CustomButton mSignupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);

//        setUpScreen();

        mEmail = findViewById(R.id.sign_up_email);
        mPassword = findViewById(R.id.sign_up_password);
        mRepeatPassword = findViewById(R.id.sign_up_repeat_password);
        mCheckboxCondition = findViewById(R.id.sign_up_checkbox_condition);
        mSignupButton = findViewById(R.id.sign_up_button);

//        mSignupButton.setHandleOnClick(new Runnable() {
//            @Override
//            public void run() {
//                signup();
//            }
//        });

    }

    private void signup() {
        Intent i = new Intent(this, BottomTab.class);
        startActivity(i);
    }

}