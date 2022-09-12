package com.example.timviec.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.timviec.R;


/**
 * TODO: document your custom view class.
 */
public class CustomButton extends FrameLayout {
    private TextView mBtn;

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

        mBtn = findViewById(R.id.custom_button_button);
        mBtn.setText(a.getString(
                R.styleable.CustomButton_custom_button_text));

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
}