package com.example.timviec.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.timviec.R;
import com.example.timviec.Utils;

public class HomeFragment extends Utils.BaseFragment {
    private ImageView mAvatar;
    private TextView mName;

    public HomeFragment() {
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mAvatar = view.findViewById(R.id.fragment_home_avatar);
        mAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), SettingScreen.class);
                startActivity(i);
            }
        });

        return view;
    }
}