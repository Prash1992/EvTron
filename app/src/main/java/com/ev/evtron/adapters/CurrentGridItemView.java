package com.ev.evtron.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ev.evtron.R;


public class CurrentGridItemView extends FrameLayout {
    private TextView textView;
    private TextView tvtime;

    public CurrentGridItemView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.current_item_grid, this);
        textView = (TextView) getRootView().findViewById(R.id.text);
        tvtime = (TextView) getRootView().findViewById(R.id.tvtime);
    }

    public void display(String text, boolean isSelected) {
        tvtime.setText(text);
        display(isSelected);
        blockdisplay(isSelected);
    }

    public void display(boolean isSelected) {
        textView.setBackgroundResource(isSelected ? R.drawable.layout_bg_green : R.drawable.layout_bg_white);
    }
    public void blockdisplay(boolean isSelected) {
        textView.setBackgroundResource(isSelected ? R.drawable.layout_bg : R.drawable.layout_bg_white);
    }

    public void isclickable(boolean iscickable){
        if (!iscickable){
            textView.setClickable(false);
            textView.setEnabled(false);
        }

    }
}
