package com.example.timviec.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.timviec.R;
import com.example.timviec.Utils;
import com.example.timviec.components.NonScrollListView;
import com.example.timviec.model.Job;

import java.util.ArrayList;
import java.util.Date;

public class JobDetailCompany extends Utils.BaseFragment {

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

        ((TextView) view.findViewById(R.id.job_detail_company_name)).setText("");
        ((TextView) view.findViewById(R.id.job_detail_company_address)).setText("");
        ((TextView) view.findViewById(R.id.job_detail_company_email)).setText("");
        ((TextView) view.findViewById(R.id.job_detail_company_introduction)).setText("");
        NonScrollListView jobListView = view.findViewById(R.id.job_detail_company_job_list);
        ArrayList<Job> jobs = new ArrayList<>();
        jobs.add(new Job("", "VNPT", "Lập trình viên Java", "Up to 1000$", "Full time",
                5, null, "Trên 1 năm kinh nghiệm", "Giám đốc",
                "Hà Nội", null, null, null, new Date(), new Date()));
        jobs.add(new Job("", "VNPT", "Lập trình viên Java", "Up to 1000$", "Full time",
                5, null, "Trên 1 năm kinh nghiệm", "Giám đốc",
                "Hà Nội", null, null, null, new Date(), new Date()));
        JobListViewAdapter jobListViewAdapter = new JobListViewAdapter(jobs);
        jobListView.setAdapter(jobListViewAdapter);

        return view;
    }
}