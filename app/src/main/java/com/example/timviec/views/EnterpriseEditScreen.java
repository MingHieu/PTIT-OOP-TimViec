package com.example.timviec.views;

import android.os.Bundle;

import com.example.timviec.R;
import com.example.timviec.Utils;
import com.example.timviec.components.CustomButton;
import com.example.timviec.components.CustomInput;

public class EnterpriseEditScreen extends Utils.BaseActivity {
    private CustomInput mName;
    private CustomInput mAddress;
    private CustomInput mPhoneNumber;
    private CustomInput mDescription;
    private CustomButton mCancelButton;
    private CustomButton mUpdateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise_edit_screen);

        mName = findViewById(R.id.edit_entreprise_name);
        mAddress = findViewById(R.id.edit_entreprise_address);
        mPhoneNumber = findViewById(R.id.edit_entreprise_phone_number);
        mDescription = findViewById(R.id.edit_entreprise_description);
        mCancelButton = findViewById(R.id.edit_entreprise_cancel_btn);
        mUpdateButton = findViewById(R.id.edit_entreprise_approve_btn);

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