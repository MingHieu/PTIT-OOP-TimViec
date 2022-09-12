package com.example.timviec.views;

import android.content.Intent;
import android.os.Bundle;

import com.example.timviec.R;
import com.example.timviec.Utils;
import com.example.timviec.components.CustomButton;
import com.example.timviec.components.CustomInput;
import com.example.timviec.router.BottomTab;

public class LoginScreen extends Utils.BaseActivity {
    private CustomInput mUsernameInput;
    private CustomInput mPasswordInput;
    private CustomButton mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        mUsernameInput = findViewById(R.id.login_screen_username_input);
        mPasswordInput = findViewById(R.id.login_screen_password_input);
        mLoginButton = findViewById(R.id.loginBtn);

        mLoginButton.setHandleOnClick(new Runnable() {
            @Override
            public void run() {
                login();
            }
        });
    }

    private void login() {
        Intent i = new Intent(this, BottomTab.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

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

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}