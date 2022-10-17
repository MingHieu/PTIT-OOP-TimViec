package com.example.timviec.views;


import android.os.Bundle;
import android.widget.RatingBar;

import com.example.timviec.R;
import com.example.timviec.Utils;
import com.example.timviec.components.CustomButton;
import com.example.timviec.components.CustomInput;
import com.example.timviec.model.Skill;


public class SkillEditScreen extends Utils.BaseActivity {

    private Skill mSkill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill_edit_screen);

        setUpScreen("Kỹ năng");

        CustomButton deleteButton = findViewById(R.id.skill_edit_delete_btn);
        CustomButton approveButton = findViewById(R.id.skill_edit_approve_btn);

        Bundle extras = getIntent().getExtras();

        Boolean createNew = extras.getBoolean("createNew", false);

        if (extras != null) {
            if (createNew) {
                mSkill = new Skill();
                deleteButton.setmText("Huỷ");
                approveButton.setmText("Thêm mới");
            } else {
                mSkill = new Skill(
                        extras.getString("name"),
                        extras.getInt("rating"),
                        extras.getString("description"));
            }
        }

        CustomInput nameView = findViewById(R.id.skill_edit_name);
        nameView.setValue(mSkill.getName());

        RatingBar rateView = findViewById(R.id.skill_edit_rate);
        rateView.setRating(mSkill.getRating());
        rateView.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                mSkill.setRating((int) v);
            }
        });

        CustomInput detailView = findViewById(R.id.skill_edit_description);
        detailView.setValue(mSkill.getDescription());

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