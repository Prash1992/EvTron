package com.ev.evtron.activities.ui.ui;


import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.ev.evtron.R;

import com.ev.evtron.utils.PreferenceManager;


import static com.facebook.FacebookSdk.getApplicationContext;

public class BottomSheetEVownerDialog extends BottomSheetDialogFragment {
    private View v;
    private Button btnEventhusiastic;
    private TextView tvHoldOn;
    private PreferenceManager preferenceManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.bottom_sheet_evowner_layout,
                container, false);
        inititialisation();
        setdefaults();
        setlisteners();
        return v;
    }

    private void setlisteners() {

        btnEventhusiastic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent evownerintent = new Intent("ev_owner");
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(evownerintent);
            }
        });

        tvHoldOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent evownerintent = new Intent("dismiss_bottom_sheet");
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(evownerintent);
            }
        });
    }


    private void setdefaults() {

    }

    private void inititialisation() {
        btnEventhusiastic = v.findViewById(R.id.btnEventhusiastic);
        tvHoldOn = v.findViewById(R.id.tvHoldOn);


    }

    public PreferenceManager getPreferenceManager() {
        if (preferenceManager == null) {
            preferenceManager = PreferenceManager.getInstance();
            preferenceManager.initialize(getApplicationContext());
        }
        return preferenceManager;
    }


}
