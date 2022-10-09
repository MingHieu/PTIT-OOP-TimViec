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
import com.example.timviec.services.StateManagerService;

public class HomeFragment extends Utils.BaseFragment {
    private StateManagerService stateManager = App.getContext().getStateManager();
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

        ((TextView) view.findViewById(R.id.fragment_home_name)).setText((String) stateManager.getUser().getDetail().get("name"));

        return view;
    }
}