package com.example.timviec.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.timviec.R;


/**
 * TODO: document your custom view class.
 */
public class CustomButton extends FrameLayout {
    private TextView mBtn;
    private String mText;

    public CustomButton(Context context) {
        super(context);
        init(null, 0);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        inflate(getContext(), R.layout.sample_custom_button, this);

        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.CustomButton, defStyle, 0);

        mText = a.getString(R.styleable.CustomButton_custom_button_text);
        mBtn = findViewById(R.id.custom_button_button);
        mBtn.setText(mText);

        int color = a.getColor(R.styleable.CustomButton_custom_button_color, getResources().getColor(R.color.primary));
        mBtn.setBackgroundColor(color);

        if (a.getBoolean(R.styleable.CustomButton_custom_button_round, false)) {
            ((CardView) findViewById(R.id.custom_button_wrapper)).setRadius(99999);
        }

        a.recycle();
    }

    public void setHandleOnClick(Runnable handleOnClick) {
        mBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOnClick.run();
            }
        });
    }

    public void setmText(String mText) {
        this.mText = mText;
        mBtn.setText(mText);
    }
}