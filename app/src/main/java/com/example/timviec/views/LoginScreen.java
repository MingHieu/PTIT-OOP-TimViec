package com.example.timviec.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.timviec.R;
import com.example.timviec.Utils;
import com.example.timviec.components.CustomButton;
import com.example.timviec.components.CustomInput;
import com.example.timviec.router.BottomTab;

public class LoginScreen extends Utils.BaseActivity {
    private CustomInput mUsernameInput;
    private CustomInput mPasswordInput;
    private CustomButton mLoginButton;
    private TextView mSignupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        setUpScreen();

        mUsernameInput = findViewById(R.id.login_screen_username_input);
        mPasswordInput = findViewById(R.id.login_screen_password_input);
        mLoginButton = findViewById(R.id.login_screen_login_btn);
        mSignupButton = findViewById(R.id.login_screen_signup_btn);

        mLoginButton.setHandleOnClick(new Runnable() {
            @Override
            public void run() {
                login();
            }
        });

        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSignup();
            }
        });
    }

    private void login() {
        Intent i = new Intent(this, BottomTab.class);
        startActivity(i);


//        ApiService.apiService.hello().enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                Log.i(null, "onResponse: " + response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                Log.i(null, "onFailure: " + t.getMessage());
//            }
//        });
    }

    private void goToSignup() {
        Bundle extras = getIntent().getExtras();
        int roleId = extras.getInt("roleId");
        if (roleId == 1) {
            Intent i = new Intent(this, SignupUserScreen.class);
            startActivity(i);
        }
        if (roleId == 2) {
            Intent i = new Intent(this, SignupEnterpriseScreen.class);
            startActivity(i);
        }
    }
}