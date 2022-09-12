package com.example.timviec.views;

import android.content.Intent;
import android.os.Bundle;

import com.example.timviec.R;
import com.example.timviec.Utils;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Utils.BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, LoginScreen.class);
                startActivity(i);
            }
        }, 2000);
    }
}