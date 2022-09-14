package com.example.timviec.views;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.example.timviec.R;
import com.example.timviec.Utils;
import com.example.timviec.model.Education;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class EducationScreen extends Utils.BaseActivity {
    ArrayList<Education> educationItems;
    EducationListViewAdapter educationListViewAdapter;
    ListView educationListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_screen);

        setUpScreen("Học vấn");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            educationItems = new Gson().fromJson(extras.getString("educationItems"), new TypeToken<ArrayList<Education>>() {
            }.getType());
        } else {
            educationItems = new ArrayList<Education>();
        }

        educationListView = findViewById(R.id.education_screen_list);
        educationListView.setPadding(
                (int) Utils.convertDpToPixel(10, this),
                (int) Utils.convertDpToPixel(20, this),
                (int) Utils.convertDpToPixel(10, this),
                0);
        educationListView.setDivider(new ColorDrawable(Color.TRANSPARENT));  //hide the divider
        educationListView.setDividerHeight((int) Utils.convertDpToPixel(20, this));
        educationListViewAdapter = new EducationListViewAdapter(
                educationItems,
                (int) Utils.convertDpToPixel(10, this),
                Utils.convertDpToPixel(6, this),
                new Intent(EducationScreen.this, EducationEditScreen.class));
        educationListView.setAdapter(educationListViewAdapter);

        findViewById(R.id.education_screen_add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EducationScreen.this, EducationEditScreen.class);
                i.putExtra("createNew", true);
                startActivity(i);
            }
        });
    }
}

class EducationListViewAdapter extends BaseAdapter {
    private final ArrayList<Education> listItems;
    private int padding;
    private float radius;
    private Intent intentNavigateTo;

    public EducationListViewAdapter(ArrayList<Education> listItems) {
        this.listItems = listItems;
    }

    public EducationListViewAdapter(ArrayList<Education> listItems, @Nullable int padding, @Nullable float radius, @Nullable Intent intentNavigateTo) {
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
    public Education getItem(int i) {
        return listItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = view != null ? view : View.inflate(viewGroup.getContext(), R.layout.education_item, null);
        Education item = getItem(i);
        ((TextView) itemView.findViewById(R.id.education_item_name)).setText(item.getName());
        ((TextView) itemView.findViewById(R.id.education_item_major)).setText(item.getMajor());
        ((TextView) itemView.findViewById(R.id.education_item_time)).setText(item.getFrom() + " - " + item.getTo());

        if (padding > 0) {
            itemView.findViewById(R.id.education_item_wrapper).setPadding(padding, padding, padding, padding);
        }
        if (radius > 0) {
            ((CardView) itemView.findViewById(R.id.education_item)).setRadius(radius);
        }
        if (intentNavigateTo != null) {
            itemView.findViewById(R.id.education_item_wrapper).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intentNavigateTo.putExtra("name", item.getName());
                    intentNavigateTo.putExtra("major", item.getMajor());
                    intentNavigateTo.putExtra("from", item.getFrom());
                    intentNavigateTo.putExtra("to", item.getTo());
                    view.getContext().startActivity(intentNavigateTo);
                }
            });
        }
        return itemView;
    }
}