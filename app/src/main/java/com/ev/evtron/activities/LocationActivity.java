package com.ev.evtron.activities;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ev.evtron.R;
import com.ev.evtron.adapters.LocationListAdapter;
import com.ev.evtron.fragments.MapsFragment;
import com.ev.evtron.interfaces.LocationListInterface;
import com.ev.evtron.pojo.jsonresponse.ChargeStation;

import java.util.ArrayList;
import java.util.List;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;


public class LocationActivity extends Activity implements LocationListInterface {

    private RecyclerView rcvLocationList;
    private RelativeLayout rlrelative;
    private ImageView ivBack;
    BlurView blurlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_location);

        initialisation();
        blurbackground();
setdefaults();
setlisteners();

    }

    private void blurbackground() {
        float radius = 21f;
        View decorview = getWindow().getDecorView();
        ViewGroup viewGroup = (ViewGroup) decorview.findViewById(android.R.id.content);
        Drawable windowBackground = decorview.getBackground();
         blurlayout.setupWith(viewGroup)
                 .setFrameClearDrawable(windowBackground)
                 .setBlurAlgorithm(new RenderScriptBlur(this))
                 .setBlurRadius(radius)
                 .setHasFixedTransformationMatrix(true);
    }

    private void setlisteners() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setdefaults() {


        if (MapsFragment.source != null) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
            List<ChargeStation> chargeStations = new ArrayList<>(MapsFragment.source);
            LocationListAdapter locationListAdapter = new LocationListAdapter(getApplicationContext(), chargeStations, this);
            rcvLocationList.setLayoutManager(linearLayoutManager);
            rcvLocationList.setAdapter(locationListAdapter);
        }

    }

    private void initialisation() {
        rcvLocationList = findViewById(R.id.rcvLocationList);
        rlrelative = findViewById(R.id.rlrelative);
        ivBack = findViewById(R.id.ivBack);
        blurlayout = findViewById(R.id.blurlayout);

    }

    @Override
    public void locationclicked(int position) {
        Log.i("LocationPosition", position + "");
    }
}