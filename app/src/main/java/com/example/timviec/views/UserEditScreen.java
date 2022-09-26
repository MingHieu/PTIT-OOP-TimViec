package com.example.timviec.views;

import android.os.Bundle;
import android.widget.RadioGroup;

import com.example.timviec.R;
import com.example.timviec.Utils;
import com.example.timviec.components.CustomButton;
import com.example.timviec.components.CustomInput;

public class UserEditScreen extends Utils.BaseActivity {
    private CustomInput mName;
    private CustomInput mDob;
    private RadioGroup mGender;
    private CustomInput mAddress;
    private CustomInput mPhoneNumber;
    private CustomInput mDescription;
    private CustomButton mCancelButton;
    private CustomButton mUpdateButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit_screen);

        mName = findViewById(R.id.edit_user_name);
        mDob = findViewById(R.id.edit_user_dob);
        mGender = findViewById(R.id.edit_user_gender);
        mAddress = findViewById(R.id.edit_user_address);
        mPhoneNumber = findViewById(R.id.edit_user_phone_number);
        mDescription = findViewById(R.id.edit_user_description);
        mCancelButton = findViewById(R.id.edit_user_cancel_btn);
        mUpdateButton = findViewById(R.id.edit_user_approve_btn);

        mCancelButton.setHandleOnClick(new Runnable() {
            @Override
            public void run() {
                onBackPressed();
            }
        });

        mUpdateButton.setHandleOnClick(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}