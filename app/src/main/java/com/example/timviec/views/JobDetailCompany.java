package com.example.timviec.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.timviec.R;
import com.example.timviec.Utils;
import com.example.timviec.components.NonScrollListView;
import com.example.timviec.model.Job;
import com.example.timviec.model.User;

import java.util.ArrayList;

public class JobDetailCompany extends Utils.BaseFragment {

    private User.UserDetail enterprise;
    private ArrayList<Job> relatedJob;

    public JobDetailCompany() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_job_detail_company, container, false);

        enterprise = ((JobDetailScreen) getActivity()).enterprise;
        relatedJob = ((JobDetailScreen) getActivity()).relatedJob;

        ((TextView) view.findViewById(R.id.job_detail_company_name)).setText(enterprise.getName());
        ((TextView) view.findViewById(R.id.job_detail_company_address)).setText(enterprise.getAddress());
        ((TextView) view.findViewById(R.id.job_detail_company_email)).setText(enterprise.getEmail());
        ((TextView) view.findViewById(R.id.job_detail_company_introduction)).setText(enterprise.getIntroduction());
        NonScrollListView jobListView = view.findViewById(R.id.job_detail_company_job_list);
        JobListViewAdapter jobListViewAdapter = new JobListViewAdapter(relatedJob);
        jobListView.setAdapter(jobListViewAdapter);
        jobListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Job item = (Job) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(getActivity(), JobDetailScreen.class);
                intent.putExtra("jobId", item.getId());
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
}