package com.ev.evtron.fragments;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ev.evtron.activities.InstantChargeActivity;
import com.ev.evtron.activities.LocationActivity;
import com.ev.evtron.pojo.jsonresponse.ChargeStation;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.VisibleRegion;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.ev.evtron.R;
import com.ev.evtron.activities.FilterActivity;
import com.ev.evtron.activities.ui.SlotDetailsActivity;
import com.ev.evtron.adapters.LocationAdapter;
import com.ev.evtron.enums.MessageViewType;
import com.ev.evtron.enums.ViewType;
import com.ev.evtron.interfaces.SlotDetailInterface;
import com.ev.evtron.interfaces.viewresponseinterface.GetChargeStationDetailsViewResponseInterface;
import com.ev.evtron.pojo.ErrorBody;
import com.ev.evtron.pojo.jsonresponse.GetChargeStationDetailsResponseModel;
import com.ev.evtron.viewmodel.GetChargeStationDetailsViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class MapsFragment extends Fragment implements GetChargeStationDetailsViewResponseInterface, SlotDetailInterface {
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    LatLng sydney = new LatLng(-34, 151);
    LatLng TamWorth = new LatLng(-31.083332, 150.916672);
    LatLng NewCastle = new LatLng(-32.916668, 151.750000);
    LatLng Brisbane = new LatLng(-27.470125, 153.021072);
    private ArrayList<LatLng> locationArrayList;
    private GoogleMap googleMapp;
    private ImageView ivMyLocation;
    private View view;
    private ImageView ivLocationsettings;
    private int clickcount = 0;
    private GetChargeStationDetailsViewModel getChargeStationDetailsViewModel;
    private ImageView cvshowlist;
    private RelativeLayout rlMap;
    private RecyclerView rcvLocation;
    public static List<ChargeStation> source = new ArrayList<>();
    private ArrayList<ChargeStation> chargeStations = new ArrayList<>();
    RecyclerView.LayoutManager RecyclerViewLayoutManager;
    LocationAdapter locationAdapter;
    LinearLayoutManager HorizontalLayout;
    private RelativeLayout rlLocation;
    private ImageView ivfilter;

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            switch (action) {
                case "load_map":
                    getCurrentLocation();
                    break;
            }
        }
    };


    public MapsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_maps, container, false);
        // Inflate the layout for this fragment

//        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);

        client = LocationServices.getFusedLocationProviderClient(requireActivity());
        LocalBroadcastManager.getInstance(requireActivity()).registerReceiver(broadcastReceiver, new IntentFilter("load_map"));
        movetocurrentlocation();

        // in below line we are initializing our array list.
//        locationArrayList = new ArrayList<>();

        // on below line we are adding our
        // locations in our array list.
//        locationArrayList.add(sydney);
//        locationArrayList.add(TamWorth);
//        locationArrayList.add(NewCastle);
//        locationArrayList.add(Brisbane);
        initialisation();
        getChargeStationDetailsViewModel = new GetChargeStationDetailsViewModel(requireContext(), this);
        setdefaults();
        setlisteners();
//        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
//            @Override
//            public void onMapReady(@NonNull final GoogleMap googleMap) {
//                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//                    @Override
//                    public void onMapClick(@NonNull LatLng latLng) {
//                        MarkerOptions markerOptions = new MarkerOptions();
//                        markerOptions.position(latLng);
//                        markerOptions.title(latLng.latitude+" : "+latLng.longitude);
//                        googleMap.clear();
//                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
//
//                        googleMap.addMarker(markerOptions);
//                    }
//                });
//            }
//        });
        return view;
    }

    private void setdefaults() {
    }

    private void AddItemsToRecyclerViewArrayList(List<ChargeStation> chargeStations) {
        // Adding items to ArrayList
        source.clear();
        source.addAll(chargeStations);
    }

    private void setlisteners() {


        cvshowlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LocationActivity.class);
                startActivity(intent);
//                Intent openLocationBottomSheet = new Intent("openLocationBottomSheet");
//                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(openLocationBottomSheet);

            }
        });
        ivMyLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movetocurrentlocation();
            }
        });
        ivLocationsettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (googleMapp != null) {
                    clickcount = clickcount + 1;
                    if (clickcount == 1) {
                        googleMapp.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                    } else if (clickcount == 2) {
                        clickcount = 0;
                        googleMapp.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    }
                }

            }
        });

        ivfilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FilterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initialisation() {
        ivMyLocation = view.findViewById(R.id.ivMyLocation);
        ivLocationsettings = view.findViewById(R.id.ivLocationsettings);
        rcvLocation = view.findViewById(R.id.rcvLocation);
        rlLocation = view.findViewById(R.id.rlLocation);
        cvshowlist = view.findViewById(R.id.cvshowlist);
        rlMap = view.findViewById(R.id.rlMap);
        ivfilter = view.findViewById(R.id.ivfilter);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(requireActivity()).unregisterReceiver(broadcastReceiver);

    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(final Location location) {
                if (location != null) {
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(@NonNull GoogleMap googleMap) {
                            googleMapp = googleMap;
//                            for (int i = 0; i < locationArrayList.size(); i++) {
//
//                                // below line is use to add marker to each location of our array list.
//                                googleMap.addMarker(new MarkerOptions().position(locationArrayList.get(i)).title("Marker"));
//
//                                // below lin is use to zoom our camera on map.
//                                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(locationArrayList.get(i),10));
//
//                                // below line is use to move our camera to the specific location.
//                               // googleMap.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList.get(i)));
//                            }
                            final LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                            MarkerOptions markerOptions = new MarkerOptions().position(latLng).title(latLng.latitude + " : " + latLng.longitude);

                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                            googleMap.addMarker(markerOptions);

//                            LatLng center = googleMap.getCameraPosition().target;
//                            Log.i("center",center.toString());

                            googleMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
                                @Override
                                public void onCameraIdle() {
                                    CameraPosition cameraPosition = googleMapp.getCameraPosition();
                                    double radiusInMeters = getMapVisibleRadius();
                                    double radiusInKilometers = radiusInMeters / 1000;
                                    double latitude = cameraPosition.target.latitude;
                                    double longitude = cameraPosition.target.longitude;
                                    Log.i("targetlatitude", latitude + "");
                                    Log.i("targetlongitude", longitude + "");

                                    Log.i("radius", radiusInKilometers + "");

                                    getChargeStationDetailsViewModel.setLatitude(String.valueOf(latitude));
                                    getChargeStationDetailsViewModel.setLongitude(String.valueOf(longitude));
                                    getChargeStationDetailsViewModel.setCurrentlat(String.valueOf(latLng.latitude));
                                    getChargeStationDetailsViewModel.setCurrentlong(String.valueOf(latLng.longitude));
                                    getChargeStationDetailsViewModel.setDistance("50000");
                                    getChargeStationDetailsViewModel.getchargestationdetails();

                                    // TODO you take it from here,
                                    // I use firebase to get relevant markers based on the lat,long,radius

                                }
                            });
                        }
                    });
                }
            }
        });
    }

    private double getMapVisibleRadius() {
        VisibleRegion visibleRegion = googleMapp.getProjection().getVisibleRegion();

        float[] distanceWidth = new float[1];
        float[] distanceHeight = new float[1];

        LatLng farRight = visibleRegion.farRight;
        LatLng farLeft = visibleRegion.farLeft;
        LatLng nearRight = visibleRegion.nearRight;
        LatLng nearLeft = visibleRegion.nearLeft;

        Location.distanceBetween(
                (farLeft.latitude + nearLeft.latitude) / 2,
                farLeft.longitude,
                (farRight.latitude + nearRight.latitude) / 2,
                farRight.longitude,
                distanceWidth
        );

        Location.distanceBetween(
                farRight.latitude,
                (farRight.longitude + farLeft.longitude) / 2,
                nearRight.latitude,
                (nearRight.longitude + nearLeft.longitude) / 2,
                distanceHeight
        );

        double radiusInMeters = Math.sqrt(Math.pow(distanceWidth[0], 2) + Math.pow(distanceHeight[0], 2)) / 2;
        return radiusInMeters;
    }

    private void movetocurrentlocation() {
        if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        } else {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
    }

    @Override
    public void chargestationdetailsreceived(GetChargeStationDetailsResponseModel getChargeStationDetailsResponseModel) {
//        Log.i("chargestadetailssize", getChargeStationDetailsResponseModel.size() + "");
        if (getChargeStationDetailsResponseModel.ChargeStation.size() != 0) {
            rlLocation.setVisibility(View.VISIBLE);
            for (int i = 0; i < getChargeStationDetailsResponseModel.ChargeStation.size(); i++) {
                final LatLng latLng = new LatLng(Double.parseDouble(getChargeStationDetailsResponseModel.ChargeStation.get(i).getLatitude()), Double.parseDouble(getChargeStationDetailsResponseModel.ChargeStation.get(i).getLongitude()));
                MarkerOptions markerOptions = new MarkerOptions().position(latLng).title(getChargeStationDetailsResponseModel.ChargeStation.get(i).getLocation());
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
                // googleMapp.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                googleMapp.addMarker(markerOptions);
            }

            RecyclerViewLayoutManager = new LinearLayoutManager(getActivity());
            rcvLocation.setLayoutManager(RecyclerViewLayoutManager);
            AddItemsToRecyclerViewArrayList(getChargeStationDetailsResponseModel.ChargeStation);
            locationAdapter = new LocationAdapter(getActivity(), getChargeStationDetailsResponseModel.ChargeStation, this);
            HorizontalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            rcvLocation.setLayoutManager(HorizontalLayout);
            rcvLocation.setAdapter(locationAdapter);
        } else {
            rlLocation.setVisibility(View.GONE);
        }
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

    @Override
    public void slotclicked(int position, ChargeStation chargeStation) {
        chargeStations.clear();
        chargeStations.add(chargeStation);
        Intent slotdetailactivity = new Intent(getActivity(), SlotDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("chargeslotdetails", (Serializable) chargeStations);
        slotdetailactivity.putExtra("chargestationdetails", bundle);
        startActivity(slotdetailactivity);
        Log.i("position_horizontal", position + "");

    }

    @Override
    public void slotInstantClicked(int position, ChargeStation chargeStation) {
        chargeStations.clear();
        chargeStations.add(chargeStation);
        Intent slotdetailactivity = new Intent(getActivity(), InstantChargeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("chargeinstantslotdetails", (Serializable) chargeStations);
        slotdetailactivity.putExtra("chargestationdetailsinstant", bundle);
        startActivity(slotdetailactivity);
    }
}