package com.example.timviec.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.timviec.R;
import com.example.timviec.Utils;
import com.example.timviec.model.Job;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class JobScreen extends Utils.BaseActivity {
    ArrayList<Job> jobItems;
    JobListViewAdapter JobListViewAdapter;
    ListView jobListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_screen);

        setUpScreen("Công việc");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jobItems = new Gson().fromJson(extras.getString("jobItems"), new TypeToken<ArrayList<Job>>() {
            }.getType());
        } else {
            jobItems = new ArrayList<Job>();
        }

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