package com.ev.evtron.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ev.evtron.R;

public class BookingConfirmActivity extends AppCompatActivity {
    private ImageView ivBackBooking;
    private Button btnProceed;
    private Bundle bundle;
    private String bookingInternalId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirm);
        initialisation();
        setdefaults();
        setlisteners();

    }

    private void initialisation() {
        ivBackBooking = findViewById(R.id.ivBackBooking);
        btnProceed = findViewById(R.id.btnProceed);
    }


    private void setdefaults() {
        bundle = getIntent().getExtras();
        if (bundle != null) {
            bookingInternalId = bundle.getString("BookingInternaId");
        }
    }

    private void setlisteners() {
        ivBackBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChargingPassActivity.class);
                intent.putExtra("BookingInternaId",bookingInternalId);
                startActivity(intent);
            }
        });

    }
}