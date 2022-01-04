package com.ev.evtron.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.ev.evtron.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SlotBookingActivity extends AppCompatActivity {

    private TextView tvTime;
    private TextView tvToTime;
    private TextView tvDate;
    int hour12hrs;
    int minutes;
    private LinearLayout llFromtime;
    private LinearLayout llTotime;
    private LinearLayout llDate;
    private String checkmin;
    private Calendar calendar;
    DatePickerDialog.OnDateSetListener date;
    private Button btnbookslots;
    private ImageView ivCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        setContentView(R.layout.activity_slot_booking);
        initialisation();
        setdefaults();
        setlisteners();
    }

    private void initialisation() {
        tvTime = findViewById(R.id.tvTime);
        tvToTime = findViewById(R.id.tvToTime);
        llFromtime = findViewById(R.id.llFromtime);
        llTotime = findViewById(R.id.llTotime);
        tvDate = findViewById(R.id.tvDate);
        llDate = findViewById(R.id.llDate);
        btnbookslots = findViewById(R.id.btnbookslots);
        ivCancel = findViewById(R.id.ivCancel);
    }

    private void setdefaults() {
        calendar = Calendar.getInstance();
        hour12hrs = calendar.get(Calendar.HOUR);
        minutes = calendar.get(Calendar.MINUTE);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        Log.i("day",day+"");

        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

    }

    private void setlisteners() {
        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });
        btnbookslots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_type_identifier_dialog();
            }
        });


        llFromtime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(SlotBookingActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    Calendar calendar = Calendar.getInstance();
                    int hour12hrs = calendar.get(Calendar.HOUR);
                    int minutes = calendar.get(Calendar.MINUTE);

                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        String AM_PM;
                        if (hourOfDay < 12) {
                            AM_PM = "AM";
                        } else {
                            AM_PM = "PM";
                        }
                        String checklength = String.valueOf(hourOfDay);
                        checkmin = String.valueOf(minutes);
                        if (checklength.length() == 1) {
                            checklength = "0" + hourOfDay;

                            if (checkmin.length() == 1) {
                                checkmin = "0" + minutes;
                                tvTime.setText(checklength + ":" + checkmin + " " + AM_PM);
                            } else {
                                tvTime.setText(checklength + ":" + minutes + " " + AM_PM);
                            }

                        } else {
                            if (checkmin.length() == 1) {
                                checkmin = "0" + minutes;
                                tvTime.setText(hourOfDay + ":" + checkmin + " " + AM_PM);
                            } else {
                                tvTime.setText(hourOfDay + ":" + minutes + " " + AM_PM);

                            }
                        }
                    }
                }, hour12hrs, minutes, false);
                timePickerDialog.show();

            }
        });
        llTotime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(SlotBookingActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    Calendar calendar = Calendar.getInstance();
                    int hour12hrs = calendar.get(Calendar.HOUR);
                    int minutes = calendar.get(Calendar.MINUTE);

                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        String AM_PM;
                        if (hourOfDay < 12) {
                            AM_PM = "AM";
                        } else {
                            AM_PM = "PM";
                        }
                        String checklength = String.valueOf(hourOfDay);
                        checkmin = String.valueOf(minutes);
                        if (checklength.length() == 1) {
                            checklength = "0" + hourOfDay;

                            if (checkmin.length() == 1) {
                                checkmin = "0" + minutes;
                                tvToTime.setText(checklength + ":" + checkmin + " " + AM_PM);
                            } else {
                                tvToTime.setText(checklength + ":" + minutes + " " + AM_PM);
                            }

                        } else {
                            if (checkmin.length() == 1) {
                                checkmin = "0" + minutes;
                                tvToTime.setText(hourOfDay + ":" + checkmin + " " + AM_PM);
                            } else {
                                tvToTime.setText(hourOfDay + ":" + minutes + " " + AM_PM);

                            }
                        }
                    }
                }, hour12hrs, minutes, false);
                timePickerDialog.show();

            }
        });

        llDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(SlotBookingActivity.this, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        tvDate.setText(sdf.format(calendar.getTime()));
    }

    /**
     * @brief Show logout dialog to logout from the application
     */
    public void show_type_identifier_dialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.slot_confirm_dialog);
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.75);
        int height = (int) (getResources().getDisplayMetrics().heightPixels);

        dialog.getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        Button btnOk = (Button) dialog.findViewById(R.id.btnOk);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                onBackPressed();
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });
        dialog.show();
    }
}