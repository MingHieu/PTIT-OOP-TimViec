package com.example.timviec.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.example.timviec.R;
import com.example.timviec.Utils;
import com.example.timviec.components.NonScrollListView;
import com.google.gson.Gson;

import java.util.ArrayList;

public class UserFragment extends Utils.BaseFragment {
    ArrayList<EducationItem> educationItems;
    EducationListViewAdapter educationListViewAdapter;
    NonScrollListView educationListView;

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        educationItems = new ArrayList<EducationItem>();
        educationItems.add(new EducationItem("Học Viện Công Nghệ Bưu Chính Viễn Thông ( Phía Bắc )", "Công nghệ thông tin", "10/2020", "Hiện tại"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //  Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        educationListView = view.findViewById(R.id.fragment_user_education);
        educationListViewAdapter = new EducationListViewAdapter(educationItems);
        educationListView.setAdapter(educationListViewAdapter);

        ImageView editButton = view.findViewById(R.id.fragment_user_education_edit);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                educationItems.add(new EducationItem("Học Viện Công Nghệ Bưu Chính Viễn Thông ( Phía Bắc )", "Công nghệ thông tin", "10/2020", "Hiện tại"));
//                educationListViewAdapter.notifyDataSetChanged();
                Intent i = new Intent(getActivity(), EducationScreen.class);
                i.putExtra("educationItems", new Gson().toJson(educationItems));
                startActivity(i);
            }
        });
        return view;
    }
}

class EducationListViewAdapter extends BaseAdapter {
    private final ArrayList<EducationItem> listItems;
    private int padding;
    private float radius;
    private Intent intentNavigateTo;

    public EducationListViewAdapter(ArrayList<EducationItem> listItems) {
        this.listItems = listItems;
    }

    public EducationListViewAdapter(ArrayList<EducationItem> listItems, @Nullable int padding, @Nullable float radius, @Nullable Intent intentNavigateTo) {
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
    public EducationItem getItem(int i) {
        return listItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = view != null ? view : View.inflate(viewGroup.getContext(), R.layout.education_item, null);
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
                    view.getContext().startActivity(intentNavigateTo);
                }
            });
        }
        EducationItem item = getItem(i);
        ((TextView) itemView.findViewById(R.id.education_item_name)).setText(item.getName());
        ((TextView) itemView.findViewById(R.id.education_item_major)).setText(item.getMajor());
        ((TextView) itemView.findViewById(R.id.education_item_time)).setText(item.getFromDate() + " " + item.getToDate());
        return itemView;
    }
}

class EducationItem {
    private String name;
    private String major;
    private String fromDate;
    private String toDate;

    public EducationItem(String name, String major, String fromDate, String toDate) {
        this.name = name;
        this.major = major;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "EducationItem{" +
                "name='" + name + '\'' +
                ", major='" + major + '\'' +
                ", fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                '}';
    }
}
