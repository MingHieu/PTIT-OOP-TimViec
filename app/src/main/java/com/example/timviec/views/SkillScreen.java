package com.example.timviec.views;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.timviec.App;
import com.example.timviec.R;
import com.example.timviec.Utils;
import com.example.timviec.model.Skill;
import com.example.timviec.services.StateManagerService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;

public class SkillScreen extends Utils.BaseActivity {

    private ArrayList<Skill> skillItems;
    private SkillListViewAdapter skillListViewAdapter;
    private ListView skillListView;
    private StateManagerService stateManager = App.getContext().getStateManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill_screen);

        setUpScreen("Kỹ năng");

        skillItems = new ArrayList<Skill>();
        for (LinkedTreeMap skillMap : (List<LinkedTreeMap>) stateManager.getUser().getDetail().get("skills")) {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.toJsonTree(skillMap).getAsJsonObject();
            Skill skill = gson.fromJson(jsonObject, Skill.class);
            skillItems.add(skill);
        }

        skillListView = findViewById(R.id.skill_screen_list);
        skillListView.setPadding(
                (int) Utils.convertDpToPixel(10, this),
                (int) Utils.convertDpToPixel(20, this),
                (int) Utils.convertDpToPixel(10, this),
                0);
        skillListView.setDivider(new ColorDrawable(Color.TRANSPARENT));  //hide the divider
        skillListView.setDividerHeight((int) Utils.convertDpToPixel(20, this));
        skillListViewAdapter = new SkillListViewAdapter(
                skillItems,
                (int) Utils.convertDpToPixel(10, this),
                Utils.convertDpToPixel(6, this),
                new Intent(SkillScreen.this, SkillEditScreen.class));
        skillListView.setAdapter(skillListViewAdapter);

        findViewById(R.id.skill_screen_add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SkillScreen.this, SkillEditScreen.class);
                i.putExtra("createNew", true);
                startActivity(i);
            }
        });
    }
}

class SkillListViewAdapter extends BaseAdapter {
    private final ArrayList<Skill> listItems;
    private int padding;
    private float radius;
    private Intent intentNavigateTo;

    public SkillListViewAdapter(ArrayList<Skill> listItems) {
        this.listItems = listItems;
    }

    public SkillListViewAdapter(ArrayList<Skill> listItems, int padding, float radius, Intent intentNavigateTo) {
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
    public Skill getItem(int i) {
        return listItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = view != null ? view : View.inflate(viewGroup.getContext(), R.layout.skill_item, null);
        Skill item = getItem(i);

        ((TextView) itemView.findViewById(R.id.skill_item_name)).setText(item.getName());
        ((RatingBar) itemView.findViewById(R.id.skill_item_rate)).setRating(item.getRating());

        if (item.getDescription() != null && item.getDescription().length() > 0) {
            ((TextView) itemView.findViewById(R.id.skill_item_description)).setText(item.getDescription());
        } else {
            ((TextView) itemView.findViewById(R.id.skill_item_description)).setVisibility(View.GONE);
        }

        if (padding > 0) {
            itemView.findViewById(R.id.skill_item_wrapper).setPadding(padding, padding, padding, padding);
        }
        if (radius > 0) {
            ((CardView) itemView.findViewById(R.id.skill_item)).setRadius(radius);
        }
        if (intentNavigateTo != null) {
            itemView.findViewById(R.id.skill_item_wrapper).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intentNavigateTo.putExtra("name", item.getName());
                    intentNavigateTo.putExtra("rating", item.getRating());
                    intentNavigateTo.putExtra("description", item.getDescription());
                    view.getContext().startActivity(intentNavigateTo);
                }
            });
        }
        return itemView;
    }

}