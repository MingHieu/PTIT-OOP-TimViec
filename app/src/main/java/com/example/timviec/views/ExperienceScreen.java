package com.example.timviec.views;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
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
import com.example.timviec.model.Experience;
import com.example.timviec.model.User;
import com.example.timviec.services.ApiService;
import com.example.timviec.services.StateManagerService;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExperienceScreen extends Utils.BaseActivity {
    private ArrayList<Experience> experienceItems;
    private ExperienceListViewAdapter experienceListViewAdapter;
    private ListView experienceListView;
    private final StateManagerService stateManager = App.getContext().getStateManager();
    private final User user = stateManager.getUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_screen);

        setUpScreen("Kinh nghiệm");

        experienceListView = findViewById(R.id.experience_screen_list);

        findViewById(R.id.experience_screen_add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ExperienceScreen.this, ExperienceEditScreen.class);
                i.putExtra("createNew", true);
                startActivityForResult(i, 0);
            }
        });

        setupView();
    }

    private void setupView() {
        experienceItems = user.getDetail().getExperiences();
        experienceListViewAdapter = new ExperienceListViewAdapter(
                experienceItems,
                (int) Utils.convertDpToPixel(10, this),
                Utils.convertDpToPixel(6, this));
        experienceListView.setAdapter(experienceListViewAdapter);
        experienceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Experience item = (Experience) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(ExperienceScreen.this, ExperienceEditScreen.class);
                intent.putExtra("id", item.getId());
                intent.putExtra("name", item.getName());
                intent.putExtra("position", item.getPosition());
                intent.putExtra("from", item.getFromDate());
                intent.putExtra("to", item.getToDate());
                intent.putExtra("description", item.getDescription());
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

        ApiService.apiService.getAllExperience().enqueue(new Callback<API.getAllExperienceResponse>() {
            @Override
            public void onResponse(Call<API.getAllExperienceResponse> call, Response<API.getAllExperienceResponse> response) {
                loadingDialog.hide();
                if (response.isSuccessful()) {
                    ArrayList<Experience> experiences = response.body().getData();
                    user.getDetail().setExperiences(experiences);
                    setupView();
                } else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        CustomDialog dialog = new CustomDialog(ExperienceScreen.this, jsonObject.getString("message"), null, CustomDialog.DialogType.ERROR);
                        dialog.show();
                    } catch (Exception e) {
                        CustomDialog dialog = new CustomDialog(ExperienceScreen.this, e.getMessage(), null, null);
                        dialog.show();
                    }
                }
            }

            @Override
            public void onFailure(Call<API.getAllExperienceResponse> call, Throwable t) {
                loadingDialog.hide();
                Utils.handleFailure(ExperienceScreen.this, t);
            }
        });
    }
}

class ExperienceListViewAdapter extends BaseAdapter {
    private final ArrayList<Experience> listItems;
    private int padding;
    private float radius;
    private boolean showDescription;

    public ExperienceListViewAdapter(ArrayList<Experience> listItems) {
        this.listItems = listItems;
        this.showDescription = false;
    }

    public ExperienceListViewAdapter(ArrayList<Experience> listItems, @Nullable int padding, @Nullable float radius) {
        this.listItems = listItems;
        this.padding = padding;
        this.radius = radius;
        this.showDescription = true;
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
        ((TextView) itemView.findViewById(R.id.experience_item_position)).setText(item.getPosition());
        ((TextView) itemView.findViewById(R.id.experience_item_time)).setText(item.getFromDate() + " - " + item.getToDate());
        if (!Utils.checkEmptyInput(item.getDescription()) && this.showDescription) {
            itemView.findViewById(R.id.experience_item_description).setVisibility(View.VISIBLE);
            ((TextView) itemView.findViewById(R.id.experience_item_description)).setText(item.getDescription());
        }

        if (padding > 0) {
            itemView.findViewById(R.id.experience_item_wrapper).setPadding(padding, padding, padding, padding);
        }
        if (radius > 0) {
            ((CardView) itemView.findViewById(R.id.experience_item)).setRadius(radius);
        }

        return itemView;
    }
}