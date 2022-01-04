package com.ev.evtron.activities.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.ev.evtron.R;

import com.ev.evtron.activities.LocationActivity;
import com.ev.evtron.activities.SelectSlotActivity;
import com.ev.evtron.adapters.AmenitiesAdapter;
import com.ev.evtron.adapters.ChargeBayAdapter;
import com.ev.evtron.interfaces.AmenitiesInterface;
import com.ev.evtron.interfaces.ChargeBayInterface;
import com.ev.evtron.interfaces.viewresponseinterface.SlotDetailViewResponseInterface;
import com.ev.evtron.pojo.ErrorBody;
import com.ev.evtron.pojo.jsonresponse.ChargeStation;
import com.ev.evtron.pojo.jsonresponse.GetChargeStationDetailsResponseModel;
import com.ev.evtron.pojo.jsonresponse.GetfavouriteResponseModel;
import com.ev.evtron.pojo.jsonresponse.UpdateFavouriteResponseModel;
import com.ev.evtron.viewmodel.SlotDetailViewModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class SlotDetailsActivity extends AppCompatActivity implements AmenitiesInterface, ChargeBayInterface, SlotDetailViewResponseInterface {
    private ImageView ivCancelSlotDetails;
    private ArrayList<ChargeStation> getChargeStationDetailsResponseModel;
    private SlotDetailViewModel slotDetailViewModel;
    private TextView tvStationStatus;
    RecyclerView.LayoutManager RecyclerViewLayoutManager;
    private RelativeLayout rlCall;
    private RelativeLayout rlImage;
    private String imageUrl = "";
    AmenitiesAdapter amenitiesAdapter;
    ChargeBayAdapter chargeBayAdapter;
    LinearLayoutManager HorizontalLayout;
    private String latitude = "";
    private String longitude = "";
    private Button btncheckslots;
    private int chargerbayid = 0;
    private int stationId = 0;
    private String name = "";
    private String address = "";
    private String district = "";
    private String chargerType = "";
    private String mobileNumber = "";
    private RecyclerView rcvChargeBayDetails;
    private TextView tvName;
    private TextView tvAddress;
    private TextView tvDistrict;
    private TextView tvDistance;
    private TextView tvTimeAvailable;
    private RecyclerView rcvamenities;
    private ImageView ivDirection;
    private ImageView ivOneStar;
    private ImageView ivTwoStar;
    private ImageView ivThreeStar;
    private ImageView ivFourStar;
    private ImageView ivFiveStar;
    private int ACChargerCount = 0;
    private int DCChargerCount = 0;
    private String avgRating = "";
    private ImageView ivFavourite;
    private boolean isFavourite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        setContentView(R.layout.activity_slot_details);
        // About StrictMode Learn More at => http://stackoverflow.com/questions/8258725/strict-mode-in-android-2-2
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        initialisation();
        slotDetailViewModel = new SlotDetailViewModel(this, this);
        Bundle args = this.getIntent().getBundleExtra("chargestationdetails");
        if (args != null) {
            getChargeStationDetailsResponseModel = (ArrayList<ChargeStation>) args.getSerializable("chargeslotdetails");
            Log.i("getcharge", getChargeStationDetailsResponseModel + "");
        }

        setdefaults();
        setlisteners();
    }

    private void setlisteners() {
        rlCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + mobileNumber));
                startActivity(intent);
            }
        });
        ivCancelSlotDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        ivDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + latitude + "," + longitude);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        btncheckslots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SelectSlotActivity.class);
                intent.putExtra("chargebayid", chargerbayid);
                intent.putExtra("name", name);
                intent.putExtra("address", address);
                intent.putExtra("district", district);
                intent.putExtra("chargerType", chargerType);
                intent.putExtra("stationId", stationId);
                ActivityOptions options = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.fade_out);
                startActivity(intent, options.toBundle());
            }
        });

        ivFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slotDetailViewModel.setFavStationId(String.valueOf(stationId));
                if (isFavourite) {
                    slotDetailViewModel.setFlag(0);
                } else {
                    slotDetailViewModel.setFlag(1);
                }
                slotDetailViewModel.updateFavourite();
            }
        });
    }

    public Bitmap getBitmapFromURL(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void setdefaults() {
        if (getChargeStationDetailsResponseModel != null) {
            Log.i("printlog", getChargeStationDetailsResponseModel.get(0).chargeStationName);
            tvName.setText(getChargeStationDetailsResponseModel.get(0).chargeStationName);
            tvAddress.setText(getChargeStationDetailsResponseModel.get(0).address);
            tvDistrict.setText(getChargeStationDetailsResponseModel.get(0).district);
            tvDistance.setText(getChargeStationDetailsResponseModel.get(0).distance);
            tvTimeAvailable.setText(getChargeStationDetailsResponseModel.get(0).chargerBayDetails.get(0).timings);
            latitude = getChargeStationDetailsResponseModel.get(0).latitude;
            longitude = getChargeStationDetailsResponseModel.get(0).longitude;
            chargerbayid = getChargeStationDetailsResponseModel.get(0).chargerBayDetails.get(0).getChargerBayId();
            name = getChargeStationDetailsResponseModel.get(0).chargeStationName;
            address = getChargeStationDetailsResponseModel.get(0).address;
            district = getChargeStationDetailsResponseModel.get(0).district;
            chargerType = getChargeStationDetailsResponseModel.get(0).getChargerBayDetails().get(0).chargerType;
            ACChargerCount = getChargeStationDetailsResponseModel.get(0).ACChargerCount;
            DCChargerCount = getChargeStationDetailsResponseModel.get(0).DCChargerCount;
            if (ACChargerCount == 1){
                chargerType = "AC";
            }else if (DCChargerCount ==1){
                chargerType = "DC";
            }
            stationId = getChargeStationDetailsResponseModel.get(0).chargeStationId;
            avgRating = getChargeStationDetailsResponseModel.get(0).avgRating;
            imageUrl = getChargeStationDetailsResponseModel.get(0).getImgUrl();
//            Picasso.get().load(getChargeStationDetailsResponseModel.get(0).getImgUrl()).resize(70, 120).into(rlImage);
            Picasso.get().load(imageUrl).into(new Target() {

                                                  @Override
                                                  public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                                      rlImage.setBackground(new BitmapDrawable(getApplicationContext().getResources(), bitmap));
                                                  }

                                                  @Override
                                                  public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                                                  }

                                                  @Override
                                                  public void onPrepareLoad(Drawable placeHolderDrawable) {

                                                  }
                                              });

                    setRating(avgRating);
            if (getChargeStationDetailsResponseModel.get(0).status.equals("Open")) {
                tvStationStatus.setText("Open");
            } else {
                tvStationStatus.setText("Closed");
            }
            mobileNumber = getChargeStationDetailsResponseModel.get(0).getContact();
//            try {
//                URL url = new URL(imageUrl);
//                Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//                Drawable image = new BitmapDrawable(getApplicationContext().getResources(), bitmap);
//
//                rlImage.setBackground(image);
//
//            } catch (Exception e) {
//                Log.i("imageException", e + "");
//                e.printStackTrace();
//            }
//            Bitmap myImage = getBitmapFromURL(imageUrl);
//            //BitmapDrawable(obj) convert Bitmap object into drawable object.
//            Drawable dr = new BitmapDrawable(myImage);
//            rlImage.setBackgroundDrawable(dr);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
            ChargeBayAdapter chargeBayAdapter = new ChargeBayAdapter(getApplicationContext(), getChargeStationDetailsResponseModel, this);
            rcvChargeBayDetails.setLayoutManager(linearLayoutManager);
            rcvChargeBayDetails.setAdapter(chargeBayAdapter);

            RecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());
            rcvamenities.setLayoutManager(RecyclerViewLayoutManager);
            amenitiesAdapter = new AmenitiesAdapter(getApplicationContext(), getChargeStationDetailsResponseModel, this);
            HorizontalLayout = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
            rcvamenities.setLayoutManager(HorizontalLayout);
            rcvamenities.setAdapter(amenitiesAdapter);

            slotDetailViewModel.setLatitude(latitude);
            slotDetailViewModel.setLongitude(longitude);
            slotDetailViewModel.getFavourite();
//
//            rcvchargebay.setLayoutManager(RecyclerViewLayoutManager);
//            chargeBayAdapter = new ChargeBayAdapter(getApplicationContext(), getChargeStationDetailsResponseModel, this);
//            HorizontalLayout = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
//            rcvchargebay.setLayoutManager(HorizontalLayout);
//            rcvchargebay.setAdapter(chargeBayAdapter);
        }
    }

    private void setRating(String avgRating) {
        if (avgRating.equals("1")) {
            ivOneStar.setBackgroundResource(R.drawable.ic_baseline_star_24);
        } else if (avgRating.equals("2")) {
            ivOneStar.setBackgroundResource(R.drawable.ic_baseline_star_24);
            ivTwoStar.setBackgroundResource(R.drawable.ic_baseline_star_24);
        } else if (avgRating.equals("3")) {
            ivOneStar.setBackgroundResource(R.drawable.ic_baseline_star_24);
            ivTwoStar.setBackgroundResource(R.drawable.ic_baseline_star_24);
            ivThreeStar.setBackgroundResource(R.drawable.ic_baseline_star_24);
        } else if (avgRating.equals("4")) {
            ivOneStar.setBackgroundResource(R.drawable.ic_baseline_star_24);
            ivTwoStar.setBackgroundResource(R.drawable.ic_baseline_star_24);
            ivThreeStar.setBackgroundResource(R.drawable.ic_baseline_star_24);
            ivFourStar.setBackgroundResource(R.drawable.ic_baseline_star_24);
        } else if (avgRating.equals("5")) {
            ivOneStar.setBackgroundResource(R.drawable.ic_baseline_star_24);
            ivTwoStar.setBackgroundResource(R.drawable.ic_baseline_star_24);
            ivThreeStar.setBackgroundResource(R.drawable.ic_baseline_star_24);
            ivFourStar.setBackgroundResource(R.drawable.ic_baseline_star_24);
            ivFiveStar.setBackgroundResource(R.drawable.ic_baseline_star_24);
        }

    }

    private void initialisation() {
        ivCancelSlotDetails = findViewById(R.id.ivCancelSlotDetails);
        rlCall = findViewById(R.id.rlCall);
        btncheckslots = findViewById(R.id.btncheckslots);
        rcvChargeBayDetails = findViewById(R.id.rcvChargeBayDetails);
        tvName = findViewById(R.id.tvName);
        tvAddress = findViewById(R.id.tvAddress);
        tvStationStatus = findViewById(R.id.tvStationStatus);
        tvDistance = findViewById(R.id.tvDistance);
        tvDistrict = findViewById(R.id.tvDistrict);
        tvTimeAvailable = findViewById(R.id.tvTimeAvailable);
        rcvamenities = findViewById(R.id.rcvamenities);
        ivDirection = findViewById(R.id.ivDirection);
        ivOneStar = findViewById(R.id.ivOneStar);
        ivTwoStar = findViewById(R.id.ivTwoStar);
        ivThreeStar = findViewById(R.id.ivThreeStar);
        ivFourStar = findViewById(R.id.ivFourStar);
        ivFiveStar = findViewById(R.id.ivFiveStar);
        ivFavourite = findViewById(R.id.ivFavourite);
        rlImage = findViewById(R.id.rlImage);
    }

    @Override
    public void amenitiesclicked(int position) {
        Log.i("amenitiesposition", position + "");
    }

    @Override
    public void chargebayselected(int position) {

    }

    @Override
    public void getFavouriteStatus(UpdateFavouriteResponseModel updateFavouriteResponseModel) {
        if (updateFavouriteResponseModel.msg.equals("This chargestation recorded as your favourite.")) {
            isFavourite = true;
            ivFavourite.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_favorite_24));
        } else {
            isFavourite = false;
            ivFavourite.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.materialfavoriteborder));

        }
        Toast.makeText(getApplicationContext(), updateFavouriteResponseModel.msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getFavourite(GetfavouriteResponseModel getfavouriteResponseModel) {
        String favouriteIds = getfavouriteResponseModel.favIds;
        String strArray[] = favouriteIds.split(",");
        boolean enableFavourite = false;

        //print elements of String array
        for (int i = 0; i < strArray.length; i++) {
            Log.i("seperatedstring", strArray[i]);
            if (strArray[i].equals(String.valueOf(stationId))) {
                enableFavourite = true;
            }
        }
        if (enableFavourite) {
            isFavourite = true;
            ivFavourite.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_favorite_24));
        } else {
            isFavourite = false;
            ivFavourite.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.materialfavoriteborder));
        }
    }

    @Override
    public void onFailureUpdateFavourite(ErrorBody errorBody, int statuscode) {
    }

    @Override
    public void onFailureGetFavourite(ErrorBody errorBody, int statuscode) {

    }
}