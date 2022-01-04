package com.ev.evtron.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ev.evtron.R;

public class ChargingPassActivity extends AppCompatActivity {
    private Button btnStart;
private Bundle bundle;
    private String bookingInternalId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charging_pass);
        initialisation();
        setDefaults();
        setListeners();
    }

    private void setDefaults() {
        bundle = getIntent().getExtras();
        if (bundle!=null){
            bookingInternalId = bundle.getString("BookingInternaId");
        }
    }

    private void setListeners() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ReviewActivity.class);
                intent.putExtra("BookingInternaId",bookingInternalId);
                startActivity(intent);
            }
        });
    }

    private void initialisation() {
        btnStart = findViewById(R.id.btnStart);
    }

}