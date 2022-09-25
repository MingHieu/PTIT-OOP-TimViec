package com.example.timviec.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.timviec.R;
import com.example.timviec.Utils;
import com.example.timviec.model.Education;
import com.example.timviec.model.Experience;
import com.example.timviec.model.Skill;

import java.util.ArrayList;

public class ExpeirenceScreen extends Utils.BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expeirence_screen);
    }
}

class ExperienceListViewAdapter extends BaseAdapter{
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
        ((TextView) itemView.findViewById(R.id.experience_item_major)).setText(item.getMajor());
        ((TextView) itemView.findViewById(R.id.experience_item_time)).setText(item.getFrom() + " - " + item.getTo());
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
                    intentNavigateTo.putExtra("major", item.getMajor());
                    intentNavigateTo.putExtra("from", item.getFrom());
                    intentNavigateTo.putExtra("to", item.getTo());
                    intentNavigateTo.putExtra("description", item.getDescription());
                    view.getContext().startActivity(intentNavigateTo);
                }
            });
        }
        return itemView;
    }
}