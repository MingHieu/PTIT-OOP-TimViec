package com.example.timviec.components;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.timviec.R;
import com.example.timviec.views.JobDetailScreen;

/**
 * TODO: document your custom view class.
 */
public class JobCard extends FrameLayout {
    private String mName, mCompany, mMoney, mAddress, mTime;
    private CardView mJobCard;

    public JobCard(Context context) {
        super(context);
        init(null, 0);
    }

    public JobCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public JobCard(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        inflate(getContext(), R.layout.sample_job_card, this);

        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.JobCard, defStyle, 0);

        mName = a.getString(R.styleable.JobCard_job_card_job_name);
        mCompany = a.getString((R.styleable.JobCard_job_card_job_company));
        mMoney = a.getString((R.styleable.JobCard_job_card_job_money));
        mAddress = a.getString(R.styleable.JobCard_job_card_job_address);
        mTime = a.getString(R.styleable.JobCard_job_card_job_time);

        ((TextView) findViewById(R.id.job_card_job_name)).setText(mName);
        ((TextView) findViewById(R.id.job_card_job_company)).setText(mCompany);
        ((TextView) findViewById(R.id.job_card_job_money)).setText(mMoney);
        ((TextView) findViewById(R.id.job_card_job_address)).setText(mAddress);
        ((TextView) findViewById(R.id.job_card_job_time)).setText(mTime);

        mJobCard = findViewById(R.id.job_card);
        mJobCard.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), JobDetailScreen.class);
                getContext().startActivity(i);
            }
        });

        a.recycle();
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmCompany() {
        return mCompany;
    }

    public void setmCompany(String mCompany) {
        this.mCompany = mCompany;
    }

    public String getmMoney() {
        return mMoney;
    }

    public void setmMoney(String mMoney) {
        this.mMoney = mMoney;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }
}