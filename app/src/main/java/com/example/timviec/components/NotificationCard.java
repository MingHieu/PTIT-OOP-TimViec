package com.example.timviec.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.timviec.R;

/**
 * TODO: document your custom view class.
 */
public class NotificationCard extends FrameLayout {
    private String mTitle;
    private String mContent;


    public NotificationCard(Context context) {
        super(context);
        init(null, 0);
    }

    public NotificationCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public NotificationCard(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        inflate(getContext(), R.layout.sample_notification_card, this);

        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.NotificationCard, defStyle, 0);

        mTitle = a.getString(R.styleable.NotificationCard_notification_card_title);
        mContent = a.getString(R.styleable.NotificationCard_notification_card_content);

        ((TextView) findViewById(R.id.notification_card_title)).setText(mTitle);
        ((TextView) findViewById(R.id.notification_card_content)).setText(mContent);

        a.recycle();
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }
}

