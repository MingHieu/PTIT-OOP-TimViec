package com.example.timviec.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.timviec.R;

/**
 * TODO: document your custom view class.
 */
public class CustomInput extends FrameLayout {
    private ImageView mIcon;
    private String mText;


    public CustomInput(Context context) {
        super(context);
        init(null, 0);
    }

    public CustomInput(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CustomInput(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        inflate(getContext(), R.layout.sample_custom_input, this);
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.CustomInput, defStyle, 0);

        TextView labelView = findViewById(R.id.custom_input_label);
        labelView.setText(a.getString(R.styleable.CustomInput_custom_input_text));

        int iconResource = a.getResourceId(R.styleable.CustomInput_custom_input_icon, -1);
        mIcon = findViewById(R.id.custom_input_icon);
        if (iconResource != -1) {
            mIcon.setImageResource(iconResource);
        } else {
            mIcon.setVisibility(View.GONE);

        }

        EditText input = findViewById(R.id.custom_input_input);
        input.setHint(a.getString(R.styleable.CustomInput_custom_input_placeholder));
        switch (a.getString(R.styleable.CustomInput_custom_input_type)) {
            case "0":
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
            case "1":
                input.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                break;
            default:
                input.setInputType(InputType.TYPE_CLASS_TEXT);
        }
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mText = input.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        a.recycle();
    }

    public void setHandleOnClickIcon(Runnable handleOnClickIcon) {
        mIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOnClickIcon.run();
            }
        });
    }

    public String getmText() {
        return mText;
    }

}