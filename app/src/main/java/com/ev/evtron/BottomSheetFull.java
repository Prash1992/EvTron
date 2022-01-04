package com.ev.evtron;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import com.ev.evtron.pojo.jsonresponse.ChargeStation;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.ev.evtron.adapters.LocationListAdapter;
import com.ev.evtron.fragments.MapsFragment;
import com.ev.evtron.interfaces.LocationListInterface;
import com.ev.evtron.pojo.jsonresponse.GetChargeStationDetailsResponseModel;

import java.util.ArrayList;
import java.util.List;

public class BottomSheetFull extends BottomSheetDialogFragment implements LocationListInterface {

    private RecyclerView rcvLocationList;

    public BottomSheetFull() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final com.google.android.material.bottomsheet.BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        final View view = View.inflate(getContext(), R.layout.fragment_bottom_sheet_full, null);
        rcvLocationList = view.findViewById(R.id.rcvLocationList);
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);

        dialog.setContentView(view);

        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
//        bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO);

        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
//                if (BottomSheetBehavior.STATE_EXPANDED == newState){
//
//                }
//

                Log.i("newState", newState + "");
                if (BottomSheetBehavior.STATE_COLLAPSED == newState) {
                    dismiss();
                }

                if (BottomSheetBehavior.STATE_HIDDEN == newState) {
                    dismiss();
                }

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.i("slideoffset", slideOffset + "");
            }
        });

//        ivCloseLocationList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dismiss();
//            }
//        });
        if (MapsFragment.source != null) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            rcvLocationList.addItemDecoration(new DividerItemDecoration(rcvLocationList.getContext(), DividerItemDecoration.VERTICAL));

            List<ChargeStation> chargeStations = new ArrayList<>(MapsFragment.source);
            LocationListAdapter locationListAdapter = new LocationListAdapter(getActivity(), chargeStations, this);
            rcvLocationList.setLayoutManager(linearLayoutManager);
            rcvLocationList.setAdapter(locationListAdapter);
        }

        return dialog;

    }

    @Override
    public void locationclicked(int position) {
        Log.i("LocationPosition", position + "");
    }

    //    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_bottom_sheet_full, container, false);
//    }
}