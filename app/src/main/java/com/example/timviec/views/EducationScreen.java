package com.example.timviec.views;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.timviec.R;
import com.example.timviec.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class EducationScreen extends Utils.BaseActivity {
    ArrayList<EducationItem> educationItems;
    EducationListViewAdapter educationListViewAdapter;
    ListView educationListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_screen);

        setUpScreen("Học vấn");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            educationItems = new Gson().fromJson(extras.getString("educationItems"), new TypeToken<ArrayList<EducationItem>>() {
            }.getType());
        } else {
            educationItems = new ArrayList<EducationItem>();
        }

        educationListView = findViewById(R.id.education_screen_list);
        educationListView.setPadding(
                (int) Utils.convertDpToPixel(10, this),
                (int) Utils.convertDpToPixel(20, this),
                (int) Utils.convertDpToPixel(10, this),
                0);
        educationListView.setDivider(new ColorDrawable(Color.TRANSPARENT));  //hide the divider
        educationListView.setDividerHeight((int) Utils.convertDpToPixel(20, this));
        educationListViewAdapter = new EducationListViewAdapter(
                educationItems,
                (int) Utils.convertDpToPixel(10, this),
                Utils.convertDpToPixel(6, this),
                new Intent(EducationScreen.this, EducationEditScreen.class));
        educationListView.setAdapter(educationListViewAdapter);

        findViewById(R.id.education_screen_add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EducationScreen.this, EducationEditScreen.class);
                startActivity(i);
            }
        });
    }

}