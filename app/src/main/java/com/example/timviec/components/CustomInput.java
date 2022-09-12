package com.example.timviec.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.timviec.R;
import com.example.timviec.Utils;

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

        // Input Label
        ((TextView) findViewById(R.id.custom_input_label)).setText(a.getString(R.styleable.CustomInput_custom_input_text));

        // Input Required
        if (a.getBoolean(R.styleable.CustomInput_custom_input_required, false) == false) {
            Log.i(null, "" + a.getBoolean(R.styleable.CustomInput_custom_input_required, false));
            ((TextView) findViewById(R.id.custom_input_required)).setVisibility(View.GONE);
        }

        // Input Right Icon
        int iconResource = a.getResourceId(R.styleable.CustomInput_custom_input_icon, -1);
        mIcon = findViewById(R.id.custom_input_icon);
        if (iconResource != -1) {
            mIcon.setImageResource(iconResource);
        } else {
            mIcon.setVisibility(View.GONE);
        }

        // Actual Input
        EditText input = findViewById(R.id.custom_input_input);
        // Input Placeholder
        input.setHint(a.getString(R.styleable.CustomInput_custom_input_placeholder));
        // Input type
        switch (a.getString(R.styleable.CustomInput_custom_input_type)) {
            case "0":
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
            case "1":
                input.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                break;
            case "2":
                input.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                input.setGravity(Gravity.TOP);
                input.setSingleLine(false);
                input.setPadding(0, (int) Utils.convertDpToPixel(10, getContext()), 0, (int) Utils.convertDpToPixel(10, getContext()));
                ((CardView) findViewById(R.id.custom_input_wrapper)).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 300));
                break;
            default:
                input.setInputType(InputType.TYPE_CLASS_TEXT);
        }
        // Catch event when text changed
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