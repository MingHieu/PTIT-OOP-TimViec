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
import com.google.gson.Gson;

import java.util.ArrayList;

public class UserFragment extends Utils.BaseFragment {
    ArrayList<Education> educationItems;
    EducationListViewAdapter educationListViewAdapter;
    NonScrollListView educationListView;

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        educationItems = new ArrayList<Education>();
        educationItems.add(new Education("Học Viện Công Nghệ Bưu Chính Viễn Thông ( Phía Bắc )", "Công nghệ thông tin", "10/2020", "Hiện tại", null));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //  Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        educationListView = view.findViewById(R.id.fragment_user_education);
        educationListViewAdapter = new EducationListViewAdapter(educationItems);
        educationListView.setAdapter(educationListViewAdapter);

        ImageView editButton = view.findViewById(R.id.fragment_user_education_edit);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                educationItems.add(new EducationItem("Học Viện Công Nghệ Bưu Chính Viễn Thông ( Phía Bắc )", "Công nghệ thông tin", "10/2020", "Hiện tại"));
//                educationListViewAdapter.notifyDataSetChanged();
                Intent i = new Intent(getActivity(), EducationScreen.class);
                i.putExtra("educationItems", new Gson().toJson(educationItems));
                startActivity(i);
            }
        });
        return view;
    }
}

