package com.example.timviec.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.timviec.R;
import com.example.timviec.Utils;
import com.example.timviec.components.NonScrollListView;
import com.example.timviec.model.Education;
import com.example.timviec.model.Experience;
import com.example.timviec.model.Skill;
import com.google.gson.Gson;

import java.util.ArrayList;

public class UserFragment extends Utils.BaseFragment {
    ArrayList<Education> educationItems;
    EducationListViewAdapter educationListViewAdapter;
    NonScrollListView educationListView;

    ArrayList<Experience> experienceItems;
    ExperienceListViewAdapter experienceListViewAdapter;
    NonScrollListView experienceListView;

    ArrayList<Skill> skillItems;
    SkillListViewAdapter skillListViewAdapter;
    NonScrollListView skillListView;

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        educationItems = new ArrayList<Education>();
        educationItems.add(new Education("Học Viện Công Nghệ Bưu Chính Viễn Thông ( Phía Bắc )", "Công nghệ thông tin", "10/2020", "Hiện tại", null));

        experienceItems = new ArrayList<Experience>();
        experienceItems.add(new Experience("MindX Technology School", "Giảng viên", "05/2021", "Hiện tại", null));

        skillItems = new ArrayList<Skill>();
        skillItems.add(new Skill("Github, Gitlab", 3, null));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //  Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        ImageView userEditButton = view.findViewById(R.id.fragment_user_infor_edit);
        userEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), UserEditScreen.class);
                startActivity(i);
            }
        });

        educationListView = view.findViewById(R.id.fragment_user_education);
        educationListViewAdapter = new EducationListViewAdapter(educationItems);
        educationListView.setAdapter(educationListViewAdapter);

        ImageView educationEditButton = view.findViewById(R.id.fragment_user_education_edit);
        educationEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                educationItems.add(new EducationItem("Học Viện Công Nghệ Bưu Chính Viễn Thông ( Phía Bắc )", "Công nghệ thông tin", "10/2020", "Hiện tại"));
//                educationListViewAdapter.notifyDataSetChanged();
                Intent i = new Intent(getActivity(), EducationScreen.class);
                i.putExtra("educationItems", new Gson().toJson(educationItems));
                startActivity(i);
            }
        });

        experienceListView = view.findViewById(R.id.fragment_user_experience);
        experienceListViewAdapter = new ExperienceListViewAdapter(experienceItems);
        experienceListView.setAdapter(experienceListViewAdapter);

        ImageView experienceEditButton = view.findViewById(R.id.fragment_user_experience_edit);
        experienceEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ExpeirenceScreen.class);
                startActivity(i);
            }
        });

        skillListView = view.findViewById(R.id.fragment_user_skill);
        skillListViewAdapter = new SkillListViewAdapter(skillItems);
        skillListView.setAdapter(skillListViewAdapter);

        ImageView skillEditButton = view.findViewById(R.id.fragment_user_skill_edit);
        skillEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), SkillScreen.class);
                startActivity(i);
            }
        });

        return view;
    }
}

