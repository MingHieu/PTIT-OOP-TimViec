package com.example.timviec.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.timviec.R;
import com.example.timviec.Utils;
import com.example.timviec.model.Job;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

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
        JobListViewAdapter = new JobListViewAdapter(
                jobItems,
                (int) Utils.convertDpToPixel(10, this),
                Utils.convertDpToPixel(6, this),
                new Intent(JobScreen.this, JobEditScreen.class));
        jobListView.setAdapter(JobListViewAdapter);

        findViewById(R.id.job_screen_and_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(JobScreen.this, JobEditScreen.class);
                i.putExtra("createNew", true);
                startActivity(i);
            }
        });
    }
}

class JobListViewAdapter extends BaseAdapter {
    private final ArrayList<Job> listItems;
    private int padding;
    private float radius;
    private Intent intentNavigateTo;

    public JobListViewAdapter(ArrayList<Job> listItems) {
        this.listItems = listItems;
    }

    public JobListViewAdapter(ArrayList<Job> listItems, int padding, float radius, Intent intentNavigateTo) {
        this.listItems = listItems;
        this.padding = padding;
        this.radius = radius;
        this.intentNavigateTo = intentNavigateTo;
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
        View itemView = view != null ? view : View.inflate(viewGroup.getContext(), R.layout.job_item, null);
        Job item = (Job) getItem(i);
        ((TextView) itemView.findViewById(R.id.job_item_name)).setText(item.getName());
        ((TextView) itemView.findViewById(R.id.job_item_expectSalary)).setText(item.getExpectSalary());
        ((TextView) itemView.findViewById(R.id.job_item_quantity)).setText("" + item.getQuantity());

        if (item.getDescription() != null && item.getDescription().length() > 0) {
            ((TextView) itemView.findViewById(R.id.job_item_description)).setText(item.getDescription());
        } else {
            ((TextView) itemView.findViewById(R.id.job_item_description)).setVisibility(View.GONE);
        }

        if (padding > 0) {
            itemView.findViewById(R.id.job_item_wrapper).setPadding(padding, padding, padding, padding);
        }

        if (radius > 0) {
            ((CardView) itemView.findViewById(R.id.job_item)).setRadius(radius);
        }

        if (intentNavigateTo != null) {
            itemView.findViewById(R.id.job_item_wrapper).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intentNavigateTo.putExtra("name", item.getName());
                    intentNavigateTo.putExtra("expectSalary", item.getExpectSalary());
                    intentNavigateTo.putExtra("quantity", item.getQuantity());
                    intentNavigateTo.putExtra("description", item.getDescription());
                    intentNavigateTo.putExtra("requirement", item.getRequirement());
                    intentNavigateTo.putExtra("benefit", item.getBenefit());
                    intentNavigateTo.putExtra("creatAt", item.getCreateAt());
                    intentNavigateTo.putExtra("expired", item.getExpired());

                    view.getContext().startActivity(intentNavigateTo);
                }
            });
        }


        return itemView;
    }
}