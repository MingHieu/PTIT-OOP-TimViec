package com.example.timviec.components;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;

import com.example.timviec.R;
import com.example.timviec.Utils;
import com.kal.rackmonthpicker.RackMonthPicker;
import com.kal.rackmonthpicker.listener.DateMonthDialogListener;
import com.kal.rackmonthpicker.listener.OnCancelMonthDialogListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * TODO: document your custom view class.
 */
public class CustomInput extends FrameLayout {
    private String mText;
    private EditText mInput;
    private Boolean showPass = false;

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

        // Show password icon
        ImageView showPassBtn = findViewById(R.id.custom_input_icon);
        if (a.getString(R.styleable.CustomInput_custom_input_type).equals("1")) {
            showPassBtn.setImageResource(R.drawable.ic_eye);
        } else {
            showPassBtn.setVisibility(View.GONE);
        }

        // Border radius
        if (!a.getBoolean(R.styleable.CustomInput_custom_input_radius, true)) {
            ((CardView) findViewById(R.id.custom_input_wrapper)).setRadius(0);
            ((CardView) findViewById(R.id.custom_input_inner_wrapper)).setRadius(0);

        }

        // Border color
        int borderColor = a.getInt(R.styleable.CustomInput_custom_input_border_color, getResources().getColor(R.color.black));
        ((CardView) findViewById(R.id.custom_input_wrapper)).setCardBackgroundColor(borderColor);

        // Actual Input
        mInput = findViewById(R.id.custom_input_input);
        mInput.setHint(a.getString(R.styleable.CustomInput_custom_input_placeholder));
        switch (a.getString(R.styleable.CustomInput_custom_input_type)) {
            case "1":
                mInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                showPassBtn.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (showPass) {
                            showPass = false;
                            showPassBtn.setImageResource(R.drawable.ic_eye);
                            mInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        } else {
                            showPass = true;
                            showPassBtn.setImageResource(R.drawable.ic_eye_close);
                            mInput.setInputType(InputType.TYPE_CLASS_TEXT);
                        }
                    }
                });
                break;
            case "2":
                mInput.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                mInput.setSingleLine(false);
                mInput.setGravity(Gravity.TOP);
                mInput.setPadding(0, (int) Utils.convertDpToPixel(10, getContext()), 0, (int) Utils.convertDpToPixel(10, getContext()));
                ((CardView) findViewById(R.id.custom_input_wrapper)).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 300));
                break;
            case "3":
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_NoActionBar, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        // i - year, i1 - month, i2 - day
                        calendar.set(i, i1, i2);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        setValue(simpleDateFormat.format(calendar.getTime()));
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                datePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                mInput.setInputType(InputType.TYPE_NULL);
                mInput.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        datePickerDialog.show();
                    }
                });
                break;
            case "4":
                RackMonthPicker rackMonthPicker = new RackMonthPicker(getContext()).setPositiveButton(new DateMonthDialogListener() {
                    @Override
                    public void onDateMonth(int month, int startDate, int endDate, int year, String monthLabel) {
                        setValue(month + "/" + year);
                    }
                }).setNegativeButton(new OnCancelMonthDialogListener() {
                    @Override
                    public void onCancel(AlertDialog dialog) {
                        dialog.dismiss();
                    }
                });
                mInput.setInputType(InputType.TYPE_NULL);
                mInput.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        rackMonthPicker.show();
                    }
                });
                break;
            default:
                mInput.setInputType(InputType.TYPE_CLASS_TEXT);
        }
        // Catch event when text changed
        mInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mText = mInput.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        a.recycle();
    }

    public String getValue() {
        return mText;
    }

    public void setValue(String mText) {
        this.mText = mText;
        mInput.setText(mText);
    }
}