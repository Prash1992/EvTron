package com.ev.evtron.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.ev.evtron.R;

import io.feeeei.circleseekbar.CircleSeekBar;

public class InstantChargeActivity extends AppCompatActivity {

    //private CircleSeekBar mHourSeekbar;

    private CircleSeekBar mMinuteSeekbar;
    private TextView tvEstimatedTime;

    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instant_charge);

        initialisation();
        setDefaults();
        setListeners();
    }

    private void setDefaults() {

    }

    private void setListeners() {
//        mHourSeekbar.setOnSeekBarChangeListener(new CircleSeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onChanged(CircleSeekBar seekbar, int curValue) {
//                changeText(curValue, mMinuteSeekbar.getCurProcess());
//            }
//        });

        mMinuteSeekbar.setOnSeekBarChangeListener(new CircleSeekBar.OnSeekBarChangeListener() {
            @Override
            public void onChanged(CircleSeekBar seekbar, int curValue) {
                changeText(curValue);
            }
        });

       // mHourSeekbar.setCurProcess(16);

        mMinuteSeekbar.setCurProcess(0);
       // mHourSeekbar.setMaxProcess(22);
    }

    private void initialisation() {
       // mHourSeekbar = (CircleSeekBar) findViewById(R.id.seek_hour);
        mMinuteSeekbar = (CircleSeekBar) findViewById(R.id.seek_minute);
        mTextView = (TextView) findViewById(R.id.textview);
        tvEstimatedTime = (TextView) findViewById(R.id.tvEstimatedTime);
    }


    private void changeText(int hour, int minute) {
        String hourStr = hour > 9 ? hour + "" : "0" + hour;
        String minuteStr = minute > 9 ? minute + "" : "0" + minute;
        if (Integer.parseInt(hourStr) < 16 ){
            //Toast.makeText(getApplicationContext(),"Unable to select",Toast.LENGTH_SHORT).show();
            mTextView.setTextColor(getResources().getColor(R.color.red));

        }else {
            mTextView.setText(hourStr + ":" + minuteStr);
            mTextView.setTextColor(getResources().getColor(R.color.green));
        }
    }
    private void changeText(int minute) {
        //String hourStr = hour > 9 ? hour + "" : "0" + hour;
        String minuteStr = minute > 9 ? minute + "" : "0" + minute;
//        String time = tvEstimatedTime.getText().toString();
//        tvEstimatedTime.setText();
//        if (Integer.parseInt(hourStr) < 16 ){
//            //Toast.makeText(getApplicationContext(),"Unable to select",Toast.LENGTH_SHORT).show();
//            mTextView.setTextColor(getResources().getColor(R.color.red));
//
//        }else {
            mTextView.setText(minuteStr);
//            mTextView.setTextColor(getResources().getColor(R.color.green));
       // }
    }

}