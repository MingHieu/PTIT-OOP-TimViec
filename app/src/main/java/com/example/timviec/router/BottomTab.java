package com.example.timviec.router;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.timviec.R;
import com.example.timviec.views.HistoryFragment;
import com.example.timviec.views.HomeFragment;
import com.example.timviec.views.NotificationFragment;
import com.example.timviec.views.UserFragment;
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
        // Make content not stretch horizontally when over scroll
        View child = mViewerPage.getChildAt(0);
        if (child instanceof RecyclerView) {
            child.setOverScrollMode(View.OVER_SCROLL_NEVER);
        }
        setUpNavigation();
    }

    public void setUpNavigation() {
        BottomTabAdapter bottomTabAdapter = new BottomTabAdapter(BottomTab.this);
        mViewerPage.setAdapter(bottomTabAdapter);

        mBottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.home_screen:
                        mViewerPage.setCurrentItem(0);
                        break;
                    case R.id.history_screen:
                        mViewerPage.setCurrentItem(1);
                        break;
                    case R.id.notification_screen:
                        mViewerPage.setCurrentItem(2);
                        break;
                    case R.id.user_screen:
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
                        mBottomNav.getMenu().findItem(R.id.history_screen).setChecked(true);
                        break;
                    case 2:
                        mBottomNav.getMenu().findItem(R.id.notification_screen).setChecked(true);
                        break;
                    case 3:
                        mBottomNav.getMenu().findItem(R.id.user_screen).setChecked(true);
                        break;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, R.anim.slide_out_right);
    }
}


class BottomTabAdapter extends FragmentStateAdapter {
    public BottomTabAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new HistoryFragment();
            case 2:
                return new NotificationFragment();
            case 3:
                return new UserFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
