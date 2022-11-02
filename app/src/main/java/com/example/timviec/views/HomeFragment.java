package com.example.timviec.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.timviec.App;
import com.example.timviec.R;
import com.example.timviec.Utils;
import com.example.timviec.components.NonScrollListView;
import com.example.timviec.model.Job;
import com.example.timviec.model.User;
import com.example.timviec.services.StateManagerService;

import java.util.ArrayList;

public class HomeFragment extends Utils.BaseFragment {
    ArrayList<Job> jobItems;
    JobListViewAdapter JobListViewAdapter;
    private StateManagerService stateManager = App.getContext().getStateManager();
    private User user = stateManager.getUser();
    private ImageView mAvatar;
    private TextView mName;
    private NonScrollListView jobListView;
    private ScrollView scrollView;
    private SwipeRefreshLayout refreshLayout;
    private boolean loadingMore = false;
    private boolean eod = false;
    private ViewTreeObserver.OnScrollChangedListener detectScrollToEnd;
    private String avatar;
    private String name;


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
        mName = view.findViewById(R.id.fragment_home_name);
        jobListView = view.findViewById(R.id.fragment_home_list);
        scrollView = view.findViewById(R.id.fragment_home_loadmore);
        refreshLayout = view.findViewById(R.id.fragment_home_refresh);

        mAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), SettingScreen.class);
                startActivity(i);
            }
        });

        view.findViewById(R.id.fragment_home_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), SearchScreen.class);
                startActivity(i);
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(null, "Refreshing...");
                resetData();
            }
        });

        detectScrollToEnd = new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                if (!scrollView.canScrollVertically(1) && !loadingMore && !eod) {
                    // bottom of scroll view
                    Log.i(null, "Load more...");
                    loadingMore = true;
                    getMoreData();
                }
            }
        };
        scrollView.getViewTreeObserver().addOnScrollChangedListener(detectScrollToEnd);

        jobItems = user.getDetail().getJobs();
        JobListViewAdapter = new JobListViewAdapter(jobItems);
        jobListView.setAdapter(JobListViewAdapter);
        jobListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Job item = (Job) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(getActivity(), JobDetailScreen.class);
                intent.putExtra("jobId", item.getId());
                startActivity(intent);
            }
        });

        setAvatar();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (user.getDetail().getAvatar() != null && !user.getDetail().getAvatar().equals(avatar)) {
            setAvatar();
        }

        if (!user.getDetail().getName().equals(name)) {
            setName();
        }
    }

    private void setAvatar() {
        avatar = user.getDetail().getAvatar();
        if (avatar != null) {
            Utils.setBase64UrlImageView(mAvatar, avatar);
        } else {
            mAvatar.setImageResource(R.drawable.img_default_user);
        }
    }

    private void setName() {
        name = user.getDetail().getName();
        mName.setText(name);
    }

    private void resetData() {
    }

    private void getMoreData() {
    }
}