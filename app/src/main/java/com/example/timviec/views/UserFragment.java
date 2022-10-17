package com.example.timviec.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.timviec.App;
import com.example.timviec.R;
import com.example.timviec.Utils;
import com.example.timviec.components.NonScrollListView;
import com.example.timviec.model.Education;
import com.example.timviec.model.Experience;
import com.example.timviec.model.Skill;
import com.example.timviec.model.User;
import com.example.timviec.services.StateManagerService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;

public class UserFragment extends Utils.BaseFragment {
    private ArrayList<Education> educationItems;
    private EducationListViewAdapter educationListViewAdapter;
    private NonScrollListView educationListView;

    private ArrayList<Experience> experienceItems;
    private ExperienceListViewAdapter experienceListViewAdapter;
    private NonScrollListView experienceListView;

    private ArrayList<Skill> skillItems;
    private SkillListViewAdapter skillListViewAdapter;
    private NonScrollListView skillListView;

    private StateManagerService stateManager = App.getContext().getStateManager();
    private User user = stateManager.getUser();

    private ImageView avatarView;
    private TextView nameView;
    private TextView descriptionView;


    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //  Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        view.findViewById(R.id.fragment_user_infor_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), UserEditScreen.class);
                startActivity(i);
            }
        });

        view.findViewById(R.id.fragment_user_education_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), EducationScreen.class);
                startActivity(i);
            }
        });

        view.findViewById(R.id.fragment_user_experience_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ExperienceScreen.class);
                startActivity(i);
            }
        });

        view.findViewById(R.id.fragment_user_skill_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), SkillScreen.class);
                startActivity(i);
            }
        });

        avatarView = view.findViewById(R.id.fragment_user_avatar);
        nameView = view.findViewById(R.id.fragment_user_name);
        descriptionView = view.findViewById(R.id.fragment_user_description);
        educationListView = view.findViewById(R.id.fragment_user_education);
        experienceListView = view.findViewById(R.id.fragment_user_experience);
        skillListView = view.findViewById(R.id.fragment_user_skill);

        setupView();

        return view;
    }

    private void setupView() {
        if ((String) user.getDetail().get("avatar") != null) {
            Utils.setBase64UrlImageView(avatarView, (String) stateManager.getUser().getDetail().get("avatar"));
        } else {
            avatarView.setImageResource(R.drawable.img_default_user);
        }
        nameView.setText((String) user.getDetail().get("name"));
        descriptionView.setText((String) user.getDetail().get("introduction"));

        educationItems = new ArrayList<Education>();
        for (LinkedTreeMap educationMap : (List<LinkedTreeMap>) user.getDetail().get("educations")) {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.toJsonTree(educationMap).getAsJsonObject();
            Education education = gson.fromJson(jsonObject, Education.class);
            educationItems.add(education);
        }
        educationListViewAdapter = new EducationListViewAdapter(educationItems);
        educationListView.setAdapter(educationListViewAdapter);

        experienceItems = new ArrayList<Experience>();
        for (LinkedTreeMap experienceMap : (List<LinkedTreeMap>) user.getDetail().get("experiences")) {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.toJsonTree(experienceMap).getAsJsonObject();
            Experience experience = gson.fromJson(jsonObject, Experience.class);
            experienceItems.add(experience);
        }
        experienceListViewAdapter = new ExperienceListViewAdapter(experienceItems);
        experienceListView.setAdapter(experienceListViewAdapter);

        skillItems = new ArrayList<Skill>();
        for (LinkedTreeMap skillMap : (List<LinkedTreeMap>) user.getDetail().get("skills")) {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.toJsonTree(skillMap).getAsJsonObject();
            Skill skill = gson.fromJson(jsonObject, Skill.class);
            skillItems.add(skill);
        }
        skillListViewAdapter = new SkillListViewAdapter(skillItems);
        skillListView.setAdapter(skillListViewAdapter);
    }
}

