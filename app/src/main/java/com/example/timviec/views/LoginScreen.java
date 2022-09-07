package com.example.timviec.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.example.timviec.R;
import com.example.timviec.components.CustomButton;
import com.example.timviec.router.BottomTab;

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

    private void login (){
        Intent i = new Intent(this,BottomTab.class);
        startActivity(i);
    }
}