package com.example.timviec.views;

import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.timviec.R;
import com.example.timviec.Utils;
import com.example.timviec.components.CustomButton;
import com.example.timviec.components.CustomInput;
import com.example.timviec.model.Education;
import com.example.timviec.model.University;
import com.google.gson.Gson;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class EducationEditScreen extends Utils.BaseActivity {
    private Education mEducation;
    private ArrayList<University> universityArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_edit_screen);

        setUpScreen("Học vấn");

        CustomButton deleteButton = findViewById(R.id.education_edit_delete_btn);
        CustomButton approveButton = findViewById(R.id.education_edit_approve_btn);

        Bundle extras = getIntent().getExtras();

        Boolean createNew = extras.getBoolean("createNew", false);

        if (extras != null) {
            if (createNew) {
                mEducation = new Education();
                deleteButton.setmText("Huỷ");
                approveButton.setmText("Thêm mới");
            } else {
                mEducation = new Education(
                        extras.getString("name"),
                        extras.getString("major"),
                        extras.getString("from"),
                        extras.getString("to"),
                        extras.getString("description"));
            }
        }

        CustomInput nameView = findViewById(R.id.education_edit_name);
        nameView.setValue(mEducation.getName());

        CustomInput majorView = findViewById(R.id.education_edit_major);
        majorView.setValue(mEducation.getMajor());

        CustomInput fromDateView = findViewById(R.id.education_edit_from_date);
        fromDateView.setValue(mEducation.getFromDate());

        CustomInput toDateView = findViewById(R.id.education_edit_to_date);
        toDateView.setValue(mEducation.getToDate());

        CheckBox checkBox = findViewById(R.id.education_edit_checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    toDateView.setVisibility(View.INVISIBLE);
                } else {
                    toDateView.setVisibility(View.VISIBLE);
                }
            }
        });
        if (mEducation.getToDate().equals("Hiện tại")) {
            checkBox.setChecked(true);
            toDateView.setValue("");
        }

        CustomInput detailView = findViewById(R.id.education_edit_description);
        detailView.setValue(mEducation.getDescription());


        deleteButton.setHandleOnClick(new Runnable() {
            @Override
            public void run() {
                if (createNew) {
                    onBackPressed();
                } else {

                }
            }
        });

        approveButton.setHandleOnClick(new Runnable() {
            @Override
            public void run() {
                if (createNew) {

                } else {

                }
            }
        });

        try {
            JsonReader reader = new JsonReader(new FileReader("ViewnamUniversity.json"));
//            universityArrayList = Arrays.asList(new Gson().fromJson(reader, University.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}