package com.example.timviec.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.example.timviec.R;
import com.example.timviec.Utils;
import com.example.timviec.components.CustomButton;
import com.example.timviec.components.CustomInput;
import com.example.timviec.router.BottomTab;

public class SignupScreen extends Utils.BaseActivity {

    private CustomInput mFirstName;
    private CustomInput mLastName;
    private CustomInput mEmail;
    private CustomInput mPassword;
    private CustomInput mRepeatPassword;
    private CheckBox mCheckboxCondition;
    private CustomButton mSignupButton;
    private LinearLayout mNameWrapper;
    private CustomInput mEnterpriseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);

        setUpScreen();

        Bundle extras = getIntent().getExtras();
        int roleId = extras.getInt("roleId");

        mFirstName = findViewById(R.id.sign_up_first_name);
        mLastName = findViewById(R.id.sign_up_last_name);
        mEmail = findViewById(R.id.sign_up_email);
        mPassword = findViewById(R.id.sign_up_password);
        mRepeatPassword = findViewById(R.id.sign_up_repeat_password);
        mCheckboxCondition = findViewById(R.id.sign_up_checkbox_condition);
        mSignupButton = findViewById(R.id.sign_up_button);
        mNameWrapper = findViewById(R.id.sign_up_name_wrapper);
        mEnterpriseName = findViewById(R.id.sign_up_enterprise_name);

        switch (roleId){
            case 1:
                mEnterpriseName.setVisibility(View.GONE);
                break;
            case 2:
                mNameWrapper.setVisibility(View.GONE);
                break;
        }

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