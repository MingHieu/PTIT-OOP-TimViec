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
import com.example.timviec.model.Job;
import com.example.timviec.model.User;
import com.example.timviec.services.StateManagerService;
import com.google.gson.Gson;

import java.util.ArrayList;

public class EnterpriseFragment extends Utils.BaseFragment {
    private StateManagerService stateManager = App.getContext().getStateManager();
    private User user = stateManager.getUser();

    ArrayList<Job> jobItems;
    JobListViewAdapter jobListViewAdapter;
    NonScrollListView jobListView;

    private ImageView avatarView;
    private TextView nameView;
    private TextView descriptionView;

    public EnterpriseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        jobItems = new ArrayList<Job>();
        jobItems.add(new Job("Viettel", "Up to 1000$", 5, null, "Trên 1 năm kinh nghiệm", "Lương tháng 13", null, null));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_enterprise, container, false);

        view.findViewById(R.id.fragment_enterprise_infor_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), EnterpriseEditScreen.class);
                startActivity(i);
            }
        });

        view.findViewById(R.id.fragment_enterprise_jobs_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jobItems.add(new Job("Viettel", "Up to 1000$", 5, null, "Trên 1 năm kinh nghiệm", "Lương tháng 13", null, null));
                jobListViewAdapter.notifyDataSetChanged();
                Intent i = new Intent(getActivity(), JobScreen.class);
                i.putExtra("jobItems", new Gson().toJson(jobItems));
                startActivity(i);
            }
        });

        jobListView = view.findViewById(R.id.fragment_enterprise_jobs);
        avatarView = view.findViewById(R.id.fragment_enterprise_avatar);
        nameView = view.findViewById(R.id.fragment_enterprise_name);
        descriptionView = view.findViewById(R.id.fragment_enterprise_description);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        setupView();
    }

    private void setupView() {
        String avatar = user.getDetail().getAvatar();
        if (avatar != null) {
            Utils.setBase64UrlImageView(avatarView, avatar);
        } else {
            avatarView.setImageResource(R.drawable.img_default_user);
        }
        nameView.setText(user.getDetail().getName());
        descriptionView.setText(user.getDetail().getIntroduction());

        jobListViewAdapter = new JobListViewAdapter(jobItems);
        jobListView.setAdapter(jobListViewAdapter);
    }
}