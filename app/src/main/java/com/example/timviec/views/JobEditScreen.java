package com.example.timviec.views;

import android.os.Bundle;

import com.example.timviec.R;
import com.example.timviec.Utils;
import com.example.timviec.components.CustomButton;
import com.example.timviec.model.Job;

public class JobEditScreen extends Utils.BaseActivity {
    private Job mJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_edit_screen);

        setUpScreen("Công việc");

        CustomButton delateButton = findViewById(R.id.job_edit_delete_btn);
        CustomButton approveButton = findViewById(R.id.job_edit_approve_btn);

        Bundle extras = getIntent().getExtras();

        Boolean createNew = extras.getBoolean("ceateNew", false);

        if (extras != null) {
            if (createNew) {
                mJob = new Job();
                delateButton.setButtonText("Huỷ");
                approveButton.setButtonText("Thêm mới");
            } else {
                mJob = new Job(
                        extras.getString("name"),
                        extras.getString("expectSalary"),
                        extras.getInt("quantity"),
                        extras.getString("description"),
                        extras.getString("requirement"),
                        extras.getString("benefit"),
                        extras.getString("creatAt"),
                        extras.getString("expired"));
            }
        }
    }
}