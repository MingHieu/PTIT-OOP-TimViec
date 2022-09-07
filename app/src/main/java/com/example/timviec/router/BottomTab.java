package com.example.timviec.router;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;


import com.example.timviec.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class BottomTab extends AppCompatActivity {

    private BottomNavigationView mBottomNav;
    private ViewPager2 mViewerPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_tab);

        mBottomNav = findViewById(R.id.bottom_tab);
        mViewerPage = findViewById(R.id.view_pager);

        setUpNavigation();
    }

    public void setUpNavigation() {
        ViewerPageAdapter viewerPageAdapter = new ViewerPageAdapter(BottomTab.this);
        mViewerPage.setAdapter(viewerPageAdapter);

        mBottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.home_screen:
                        mViewerPage.setCurrentItem(0);
                        break;
                    case R.id.order_screen:
                        mViewerPage.setCurrentItem(1);
                        break;
                    case R.id.notification_screen:
                        mViewerPage.setCurrentItem(2);
                        break;
                    case R.id.setting_screen:
                        mViewerPage.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });

        mViewerPage.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        mBottomNav.getMenu().findItem(R.id.home_screen).setChecked(true);
                        break;
                    case 1:
                        mBottomNav.getMenu().findItem(R.id.order_screen).setChecked(true);
                        break;
                    case 2:
                        mBottomNav.getMenu().findItem(R.id.notification_screen).setChecked(true);
                        break;
                    case 3:
                        mBottomNav.getMenu().findItem(R.id.setting_screen).setChecked(true);
                        break;
                }
            }
        });
    }
}