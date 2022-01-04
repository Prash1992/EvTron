package com.ev.evtron.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ev.evtron.R;
import com.ev.evtron.utils.PreferenceManager;


public class AccountFragment extends Fragment {
    private View view;
private TextView tvNameAccount;
private TextView tvMail;
private TextView tvMobile;
private PreferenceManager preferenceManager;
    public AccountFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_account, container, false);
        initialisation();
        setDefaults();
        return view;
    }

    private void initialisation() {
        tvNameAccount = view.findViewById(R.id.tvNameAccount);
        tvMail = view.findViewById(R.id.tvMail);
        tvMobile = view.findViewById(R.id.tvMobile);
    }

    private void setDefaults() {
        tvNameAccount.setText(getPreferenceManager().getPrefUserName());
        tvMail.setText(getPreferenceManager().getPrefUserMailId());
        tvMobile.setText(getPreferenceManager().getPrefUserMobileNumber());
    }

    public PreferenceManager getPreferenceManager() {
        if (preferenceManager == null) {
            preferenceManager = PreferenceManager.getInstance();
            preferenceManager.initialize(getContext());
        }
        return preferenceManager;
    }

}