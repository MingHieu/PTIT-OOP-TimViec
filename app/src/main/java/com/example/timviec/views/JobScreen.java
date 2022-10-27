package com.example.timviec.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.example.timviec.App;
import com.example.timviec.R;
import com.example.timviec.Utils;
import com.example.timviec.components.CustomDialog;
import com.example.timviec.components.LoadingDialog;
import com.example.timviec.model.API;
import com.example.timviec.model.Education;
import com.example.timviec.model.Job;
import com.example.timviec.model.User;
import com.example.timviec.services.ApiService;
import com.example.timviec.services.StateManagerService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobScreen extends Utils.BaseActivity {
    ArrayList<Job> jobItems;
    JobListViewAdapter JobListViewAdapter;
    ListView jobListView;
    private StateManagerService stateManager = App.getContext().getStateManager();
    private User user = stateManager.getUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_screen);

        setUpScreen("Công việc");

        jobListView = findViewById(R.id.job_sceen_list);

        findViewById(R.id.job_screen_and_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(JobScreen.this, JobEditScreen.class);
                i.putExtra("createNew", true);
                startActivity(i);
            }
        });

        setupView();
    }

    private void setupView() {
        jobItems = user.getDetail().getJobs();
        JobListViewAdapter = new JobListViewAdapter(jobItems);
        jobListView.setAdapter(JobListViewAdapter);
        jobListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Job item = (Job) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(JobScreen.this, JobEditScreen.class);
                intent.putExtra("job", new Gson().toJson(item));
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK) return;
        Log.i("DebugTag", "OK");

        LoadingDialog loadingDialog = new LoadingDialog(this);
        loadingDialog.show();

        ApiService.apiService.getAllPost(true).enqueue(new Callback<API.getAllPostResponse>() {
            @Override
            public void onResponse(Call<API.getAllPostResponse> call, Response<API.getAllPostResponse> response) {
                loadingDialog.hide();
                if (response.isSuccessful()) {
                    ArrayList<Job> jobs = response.body().getData();
                    user.getDetail().setJobs(jobs);
                    setupView();
                } else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        CustomDialog dialog = new CustomDialog(JobScreen.this, jsonObject.getString("message"), null, CustomDialog.DialogType.ERROR);
                        dialog.show();
                    } catch (Exception e) {
                        CustomDialog dialog = new CustomDialog(JobScreen.this, e.getMessage(), null, null);
                        dialog.show();
                    }
                }
            }

            @Override
            public void onFailure(Call<API.getAllPostResponse> call, Throwable t) {
                loadingDialog.hide();
                Utils.handleFailure(JobScreen.this, t);
            }
        });
    }
}

class JobListViewAdapter extends BaseAdapter {
    private final ArrayList<Job> listItems;
    private Boolean radius = true;

    public JobListViewAdapter(ArrayList<Job> listItems) {
        this.listItems = listItems;
    }

    public JobListViewAdapter(ArrayList<Job> listItems, Boolean radius) {
        this.listItems = listItems;
        this.radius = radius;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int i) {
        return listItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = view != null ? view : View.inflate(viewGroup.getContext(), R.layout.sample_job_card, null);
        Job item = (Job) getItem(i);

        if (item.getCompanyAvatar() != null) {
            Utils.setBase64UrlImageView(itemView.findViewById(R.id.job_card_job_avatar), item.getCompanyAvatar());
        } else {
            ((ImageView) itemView.findViewById(R.id.job_card_job_avatar)).setImageResource(R.drawable.ic_company);
        }
        ((TextView) itemView.findViewById(R.id.job_card_job_name)).setText(item.getName());
        ((TextView) itemView.findViewById(R.id.job_card_job_company)).setText(item.getCompanyName());
        ((TextView) itemView.findViewById(R.id.job_card_job_money)).setText(item.getSalary());
        ((TextView) itemView.findViewById(R.id.job_card_job_address)).setText(item.getAddress());
        ((TextView) itemView.findViewById(R.id.job_card_job_time)).setText("Còn " +
                TimeUnit.DAYS.convert(
                        item.getExpired().getTime() - item.getCreateAt().getTime(),
                        TimeUnit.MILLISECONDS) + " ngày để ứng tuyển");

        if (!radius) {
            CardView cardView = itemView.findViewById(R.id.job_card);
            cardView.setRadius(0);
            cardView.setElevation(0);
        }

        return itemView;
    }
}