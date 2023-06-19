package com.example.test_app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

public class ChatMessageWidget extends LinearLayout {
    private TextView titleTextView;
    private ImageView tail;

    public ChatMessageWidget(Context context) {
        super(context);
        init();
    }

    public ChatMessageWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.chat_message, this, true);
        titleTextView = findViewById(R.id.txtBanner);
        tail= findViewById(R.id.txtBannerTail);
    }

    public void setData(String title, boolean value) {

        if(!value){
            tail.setImageResource(R.drawable.bg_banner_tail_answer);
            tail.setVisibility(INVISIBLE);
        }

        titleTextView.setText(title);
        // boolean 값에 따라 아이콘을 왼쪽 또는 오른쪽에 표시합니다.
        int gravity = value ? Gravity.END : Gravity.START;
        this.setGravity(gravity);
    }
}
