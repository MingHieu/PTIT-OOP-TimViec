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

import androidx.cardview.widget.CardView;

import com.example.timviec.App;
import com.example.timviec.R;
import com.example.timviec.Utils;
import com.example.timviec.model.Experience;
import com.example.timviec.services.StateManagerService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;

public class ExperienceScreen extends Utils.BaseActivity {
    private ArrayList<Experience> experienceItems;
    private ExperienceListViewAdapter experienceListViewAdapter;
    private ListView experienceListView;
    private StateManagerService stateManager = App.getContext().getStateManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_screen);

        setUpScreen("Kinh nghiệm");

        experienceItems = new ArrayList<Experience>();
        for (LinkedTreeMap experienceMap : (List<LinkedTreeMap>) stateManager.getUser().getDetail().get("experiences")) {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.toJsonTree(experienceMap).getAsJsonObject();
            Experience experience = gson.fromJson(jsonObject, Experience.class);
            experienceItems.add(experience);
        }

        experienceListView = findViewById(R.id.experience_screen_list);
        experienceListView.setPadding(
                (int) Utils.convertDpToPixel(10, this),
                (int) Utils.convertDpToPixel(20, this),
                (int) Utils.convertDpToPixel(10, this),
                0);
        experienceListView.setDivider(new ColorDrawable(Color.TRANSPARENT));  //hide the divider
        experienceListView.setDividerHeight((int) Utils.convertDpToPixel(20, this));
        experienceListViewAdapter = new ExperienceListViewAdapter(
                experienceItems,
                (int) Utils.convertDpToPixel(10, this),
                Utils.convertDpToPixel(6, this),
                new Intent(ExperienceScreen.this, ExperienceEditScreen.class));
        experienceListView.setAdapter(experienceListViewAdapter);

        findViewById(R.id.experience_screen_add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ExperienceScreen.this, ExperienceEditScreen.class);
                i.putExtra("createNew", true);
                startActivity(i);
            }
        });
    }
}

class ExperienceListViewAdapter extends BaseAdapter {
    private final ArrayList<Experience> listItems;
    private int padding;
    private float radius;
    private Intent intentNavigateTo;

    public ExperienceListViewAdapter(ArrayList<Experience> listItems) {
        this.listItems = listItems;
    }

    public ExperienceListViewAdapter(ArrayList<Experience> listItems, int padding, float radius, Intent intentNavigateTo) {
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
    public Experience getItem(int i) {
        return listItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = view != null ? view : View.inflate(viewGroup.getContext(), R.layout.experience_item, null);
        Experience item = getItem(i);
        ((TextView) itemView.findViewById(R.id.experience_item_name)).setText(item.getName());
        ((TextView) itemView.findViewById(R.id.experience_item_major)).setText(item.getPosition());
        ((TextView) itemView.findViewById(R.id.experience_item_time)).setText(item.getFromDate() + " - " + item.getToDate());
        if (item.getDescription() != null && item.getDescription().length() > 0) {
            ((TextView) itemView.findViewById(R.id.experience_item_description)).setText(item.getDescription());
        } else {
            ((TextView) itemView.findViewById(R.id.experience_item_description)).setVisibility(View.GONE);
        }

        if (padding > 0) {
            itemView.findViewById(R.id.experience_item_wrapper).setPadding(padding, padding, padding, padding);
        }
        if (radius > 0) {
            ((CardView) itemView.findViewById(R.id.experience_item)).setRadius(radius);
        }
        if (intentNavigateTo != null) {
            itemView.findViewById(R.id.experience_item_wrapper).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intentNavigateTo.putExtra("name", item.getName());
                    intentNavigateTo.putExtra("position", item.getPosition());
                    intentNavigateTo.putExtra("from", item.getFromDate());
                    intentNavigateTo.putExtra("to", item.getToDate());
                    intentNavigateTo.putExtra("description", item.getDescription());
                    view.getContext().startActivity(intentNavigateTo);
                }
            });
        }
        return itemView;
    }
}