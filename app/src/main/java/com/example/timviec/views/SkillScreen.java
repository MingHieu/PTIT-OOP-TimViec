package com.example.timviec.views;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.timviec.R;
import com.example.timviec.Utils;
import com.example.timviec.model.Skill;

import java.util.ArrayList;

public class SkillScreen extends Utils.BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill_screen);
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

        ((ViewGroup) itemView.findViewById(R.id.skill_item_rate)).removeAllViews();
        for (int j = 0; j < item.getRate(); j++) {
            ImageView activeStar = new ImageView(itemView.getContext());
            activeStar.setImageResource(R.drawable.ic_star_active);
            int size = (int) Utils.convertDpToPixel(25, itemView.getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size, size);
            params.setMargins(0,0,size,0);
            activeStar.setLayoutParams(params);
            ((LinearLayout) itemView.findViewById(R.id.skill_item_rate)).addView(activeStar);
        }
        for (int j = 0; j < 5 - item.getRate(); j++) {
            ImageView inActiveStar = new ImageView(itemView.getContext());
            inActiveStar.setImageResource(R.drawable.ic_star_inactive);
            int size = (int) Utils.convertDpToPixel(25, itemView.getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size, size);
            params.setMargins(0,0,size,0);
            inActiveStar.setLayoutParams(params);
            ((LinearLayout) itemView.findViewById(R.id.skill_item_rate)).addView(inActiveStar);
        }

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
                    intentNavigateTo.putExtra("rate", item.getRate());
                    intentNavigateTo.putExtra("description", item.getDescription());
                    view.getContext().startActivity(intentNavigateTo);
                }
            });
        }
        return itemView;
    }

}