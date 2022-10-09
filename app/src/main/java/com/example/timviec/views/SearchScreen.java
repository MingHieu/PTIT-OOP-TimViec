package com.example.timviec.views;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.timviec.R;
import com.example.timviec.Utils;

public class SearchScreen extends Utils.BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);

        ((ImageView) findViewById(R.id.search_screen_back_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}