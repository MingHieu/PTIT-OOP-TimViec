package com.example.timviec.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.timviec.R;
import com.example.timviec.components.CustomButton;
import com.example.timviec.model.User;
import com.example.timviec.router.BottomTab;
import com.example.timviec.services.ApiService;
import com.example.timviec.types.LoginBody;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        CustomButton loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setHandleOnClick(new Runnable() {
            @Override
            public void run() {
                login();
            }
        });
    }

    private void login() {
//        Intent i = new Intent(this, BottomTab.class);
//        startActivity(i);
        ApiService.apiService.login(new LoginBody("a", "b")).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.i(null, "Call successful" + response.body().toString());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(null, "Call failed", t);
            }
        });
    }
}