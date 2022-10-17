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
import com.example.timviec.model.Job;
import com.google.gson.Gson;

import java.util.ArrayList;

public class EnterpriseFragment extends Utils.BaseFragment {
    ArrayList<Job> jobItems;
    JobListViewAdapter jobListViewAdapter;
    NonScrollListView jobListView;

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
        ImageView enterpriseEditButton = view.findViewById(R.id.fragment_enterprise_infor_edit);
        enterpriseEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), EnterpriseEditScreen.class);
                startActivity(i);
            }
        });

        jobListView = view.findViewById(R.id.fragment_enterprise_jobs);
        jobListViewAdapter = new JobListViewAdapter(jobItems);
        jobListView.setAdapter(jobListViewAdapter);

        ImageView jobEditButton = view.findViewById(R.id.fragment_enterprise_jobs_edit);
        jobEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jobItems.add(new Job("Viettel", "Up to 1000$", 5, null, "Trên 1 năm kinh nghiệm", "Lương tháng 13", null, null));
                jobListViewAdapter.notifyDataSetChanged();
                Intent i = new Intent(getActivity(), JobScreen.class);
                i.putExtra("jobItems", new Gson().toJson(jobItems));
                startActivity(i);
            }
        });


        return view;
    }
}