package com.example.timviec.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager2.widget.ViewPager2;

import com.example.timviec.R;
import com.example.timviec.Utils;
import com.example.timviec.components.CustomButton;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HistoryFragment extends Utils.BaseFragment {

    public HistoryFragment() {
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
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        setUpScreen(view, "Lịch sử");

        ((CustomButton) view.findViewById(R.id.fragment_history_button)).setHandleOnClick(new Runnable() {
            @Override
            public void run() {
                BottomNavigationView bottomTab = getActivity().findViewById(R.id.bottom_tab);
                ViewPager2 vp = getActivity().findViewById(R.id.bottom_tab_view_pager);
                vp.setCurrentItem(0);
                bottomTab.setSelectedItemId(R.id.home_screen);
            }
        });

        return view;
    }
}