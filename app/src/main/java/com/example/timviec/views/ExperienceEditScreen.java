package com.example.timviec.views;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.timviec.R;
import com.example.timviec.Utils;
import com.example.timviec.components.CustomButton;
import com.example.timviec.components.CustomInput;
import com.example.timviec.model.Experience;

public class ExperienceEditScreen extends Utils.BaseActivity {
    private Experience mExperience;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_edit_screen);

        setUpScreen("Kinh nghiệm");

        CustomButton deleteButton = findViewById(R.id.experience_edit_delete_btn);
        CustomButton approveButton = findViewById(R.id.experience_edit_approve_btn);

        Bundle extras = getIntent().getExtras();

        Boolean createNew = extras.getBoolean("createNew", false);

        if (extras != null) {
            if (createNew) {
                mExperience = new Experience();
                deleteButton.setmText("Huỷ");
                approveButton.setmText("Thêm mới");
            } else {
                mExperience = new Experience(
                        extras.getString("name"),
                        extras.getString("position"),
                        extras.getString("from"),
                        extras.getString("to"),
                        extras.getString("description"));
            }
        }

        CustomInput nameView = findViewById(R.id.experience_edit_name);
        nameView.setValue(mExperience.getName());

        CustomInput majorView = findViewById(R.id.experience_edit_major);
        majorView.setValue(mExperience.getPosition());

        CustomInput fromDateView = findViewById(R.id.experience_edit_from_date);
        fromDateView.setValue(mExperience.getFromDate());

        CustomInput toDateView = findViewById(R.id.experience_edit_to_date);
        toDateView.setValue(mExperience.getToDate());

        CheckBox checkBox = findViewById(R.id.experience_edit_checkbox);
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
        if (mExperience.getToDate().equals("Hiện tại")) {
            checkBox.setChecked(true);
            toDateView.setValue("");
        }

        CustomInput detailView = findViewById(R.id.experience_edit_description);
        detailView.setValue(mExperience.getDescription());


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
    }
}