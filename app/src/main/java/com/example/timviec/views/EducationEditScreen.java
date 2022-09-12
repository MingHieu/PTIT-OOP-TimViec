package com.example.timviec.views;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.timviec.R;
import com.example.timviec.Utils;

public class EducationEditScreen extends Utils.BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_edit_screen);

        setUpScreen("Học vấn");
    }
}