package com.example.timviec;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class Utils {
    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float convertDpToPixel(float dp, Context context) {
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px      A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context) {
        return px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    static public class BaseActivity extends AppCompatActivity {
        // Hide keyboard when press outside EditText
        @Override
        public boolean dispatchTouchEvent(MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                View v = getCurrentFocus();
                if (v instanceof EditText) {
                    Rect outRect = new Rect();
                    v.getGlobalVisibleRect(outRect);
                    if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                        v.clearFocus();
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                }
            }
            return super.dispatchTouchEvent(event);
        }

        @Override
        public void onBackPressed() {
            super.onBackPressed();
            overridePendingTransition(0, R.anim.slide_out_right);
        }

        @Override
        public void startActivity(Intent intent) {
            super.startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        }

        public void setUpScreen(String screenTitle) {
            TextView actionBarTitle = findViewById(R.id.action_bar_title);
            if (actionBarTitle != null) {
                actionBarTitle.setText("Học vấn");
            }

            ImageView actionBarBackButton = findViewById(R.id.action_bar_back_button);
            if (actionBarBackButton != null) {
                actionBarBackButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBackPressed();
                    }
                });
            }
        }

        public void setUpScreen() {
            TextView actionBarTitle = findViewById(R.id.action_bar_title);
            if(actionBarTitle != null){
                actionBarTitle.setVisibility(View.GONE);
            }

            ImageView actionBarBackButton = findViewById(R.id.action_bar_back_button);
            if (actionBarBackButton != null) {
                actionBarBackButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBackPressed();
                    }
                });
            }

            LinearLayout actionBarBorder = findViewById(R.id.action_bar_border);
            if(actionBarBorder != null){
                actionBarBorder.setVisibility(View.GONE);
            }
        }
    }

    static public class BaseFragment extends Fragment {
        @Override
        public void startActivity(Intent intent) {
            super.startActivity(intent);
            getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        }
    }
}

