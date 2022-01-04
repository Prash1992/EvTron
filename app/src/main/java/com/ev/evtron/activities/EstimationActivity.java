package com.ev.evtron.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ev.evtron.R;
import com.ev.evtron.enums.MessageViewType;
import com.ev.evtron.enums.ViewType;
import com.ev.evtron.interfaces.viewresponseinterface.BookingSlotViewResponseInterface;
import com.ev.evtron.pojo.ErrorBody;
import com.ev.evtron.pojo.jsonresponse.SlotBookingResponseModel;
import com.ev.evtron.viewmodel.SlotBookingViewModel;

public class EstimationActivity extends AppCompatActivity implements BookingSlotViewResponseInterface {
    private SeekBar sbPush;
    private TextView tvTotalam;
    private TextView tvTotalamount;
    private Bundle bundle;
    private String date1 = "";
    private String date2 = "";
    private String slotString1 = "";
    private String slotString2 = "";
    private int chargerBayId = 0;
    private SlotBookingViewModel slotBookingViewModel;

    private String bookingInternalId="";

    private TextView tvBookingId;
    private TextView tvStartTime;
    private TextView tvEndTime;
    private TextView tvunitCharge;
    private TextView tvhourlyrate;
    private TextView tvTotalTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimation);
        initialisation();
        slotBookingViewModel = new SlotBookingViewModel(this,this);
        setDefaults();
        setListeners();
    }

    private void setDefaults() {
        bundle = getIntent().getExtras();
        if (bundle != null) {
            date1 = bundle.getString("currentDate");
            date2 = bundle.getString("nextDate");
            slotString1 = bundle.getString("slotString1");
            slotString2 = bundle.getString("slotString2");
            chargerBayId = bundle.getInt("chargerBayId");
            Log.i("date1", date1);
            Log.i("date2", date2);
            Log.i("slotString1", slotString1);
            Log.i("slotString2", slotString2);
            Log.i("chargerBayId", chargerBayId+"");

            slotBookingViewModel.setChargerbayId(String.valueOf(chargerBayId));
            slotBookingViewModel.setDate1(date1);
            slotBookingViewModel.setDate2(date2);
            slotBookingViewModel.setSlotString1(slotString1);
            slotBookingViewModel.setSlotString2(slotString2);
            slotBookingViewModel.bookSlots();
        }
    }

    private void setListeners() {
        sbPush.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (seekBar.getProgress() > 40) {
                    tvTotalam.setVisibility(View.GONE);
                    tvTotalamount.setVisibility(View.GONE);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(final SeekBar seekBar) {
                if (seekBar.getProgress() < 95) {
                    seekBar.setProgress(0);
                    tvTotalam.setVisibility(View.VISIBLE);
                    tvTotalamount.setVisibility(View.VISIBLE);
                } else if (seekBar.getProgress() > 95) {
                    Intent intent = new Intent(getApplicationContext(), BookingConfirmActivity.class);
                    intent.putExtra("BookingInternaId",bookingInternalId);
                    startActivity(intent);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            seekBar.setProgress(0);
                            tvTotalam.setVisibility(View.VISIBLE);
                            tvTotalamount.setVisibility(View.VISIBLE);
                        }
                    }, 1000);

                }
            }
        });
    }

    private void initialisation() {
        sbPush = findViewById(R.id.sbPush);
        tvTotalam = findViewById(R.id.tvTotalam);
        tvTotalamount = findViewById(R.id.tvTotalamount);
        tvBookingId = findViewById(R.id.tvBookingId);
        tvStartTime = findViewById(R.id.tvStartTime);
        tvEndTime = findViewById(R.id.tvEndTime);
        tvunitCharge = findViewById(R.id.tvunitCharge);
        tvhourlyrate = findViewById(R.id.tvhourlyrate);
        tvTotalTime = findViewById(R.id.tvTotalTime);
    }

    @Override
    public void getBookedSlotReceived(SlotBookingResponseModel slotBookingResponseModel) {
        tvBookingId.setText(slotBookingResponseModel.bookingId);
        tvStartTime.setText(slotBookingResponseModel.startDateTime);
        tvEndTime.setText(slotBookingResponseModel.endDateTime);
        tvunitCharge.setText(slotBookingResponseModel.unitsConsumed);
        tvhourlyrate.setText(slotBookingResponseModel.estimatedAmountPerHour);
        tvTotalTime.setText(slotBookingResponseModel.totalHours);
        tvTotalamount.setText(slotBookingResponseModel.totalEstimatedAmount);
        bookingInternalId = String.valueOf(slotBookingResponseModel.bookingInternalId);

    }

    @Override
    public void onFailure(ErrorBody errorBody, int statuscode) {

    }

    @Override
    public void ShowErrorMessage(MessageViewType messageViewType, String errorMessage) {

    }

    @Override
    public void ShowErrorMessage(MessageViewType messageViewType, ViewType viewType, String errorMessage) {

    }
}