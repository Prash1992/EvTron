package com.ev.evtron.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.ev.evtron.R;
import com.ev.evtron.interfaces.AmenitiesInterface;
import com.ev.evtron.pojo.jsonresponse.ChargeStation;
import com.ev.evtron.pojo.jsonresponse.GetChargeStationDetailsResponseModel;

import java.util.ArrayList;

public class AmenitiesAdapter extends RecyclerView.Adapter<AmenitiesAdapter.MyView> {
    private ArrayList<ChargeStation> getChargeStationDetailsResponseModels;
    private AmenitiesInterface amenitiesInterface;
    private Context context;
    private boolean isAmenitiesAvailable = false;

    public AmenitiesAdapter(Context context, ArrayList<ChargeStation> getChargeStationDetailsResponseModels, AmenitiesInterface amenitiesInterface) {
        this.context = context;
        this.amenitiesInterface = amenitiesInterface;
        this.getChargeStationDetailsResponseModels = getChargeStationDetailsResponseModels;
    }


    @NonNull
    @Override
    public AmenitiesAdapter.MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_amenities, parent, false);
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AmenitiesAdapter.MyView holder, final int position) {
//        Log.e("amenitysize",getChargeStationDetailsResponseModels.get(0).getAmenitiesDetails().size()+"");
        int barfacility = getChargeStationDetailsResponseModels.get(0).getAmenitiesDetails().get(0).getBarFacility();
        int playarea = getChargeStationDetailsResponseModels.get(0).getAmenitiesDetails().get(0).getPlayArea();
        int pool = getChargeStationDetailsResponseModels.get(0).getAmenitiesDetails().get(0).getPool();
        int restaurant = getChargeStationDetailsResponseModels.get(0).getAmenitiesDetails().get(0).getRestaurant();
        int rooms = getChargeStationDetailsResponseModels.get(0).getAmenitiesDetails().get(0).getRooms();
        int wifi = getChargeStationDetailsResponseModels.get(0).getAmenitiesDetails().get(0).getWifi();

        if (barfacility == 1) {
            holder.ivbarfacility.setBackground(ContextCompat.getDrawable(context, R.drawable.bar_facility));
            isAmenitiesAvailable = true;
        }else {
            holder.ivbarfacility.setVisibility(View.GONE);
        }
        if (playarea == 1) {
            holder.ivplayarea.setBackground(ContextCompat.getDrawable(context, R.drawable.playground));
            isAmenitiesAvailable = true;
        }else{
            holder.ivplayarea.setVisibility(View.GONE);
        }

        if (pool == 1) {
            holder.ivpool.setBackground(ContextCompat.getDrawable(context, R.drawable.pool));
            isAmenitiesAvailable = true;
        } else {
            holder.ivpool.setVisibility(View.GONE);
        }


        if (restaurant == 1) {
            holder.ivrestaurant.setBackground(ContextCompat.getDrawable(context, R.drawable.restaurant));
            isAmenitiesAvailable = true;
        } else {
            holder.ivrestaurant.setVisibility(View.GONE);
        }

        if (rooms == 1) {
            holder.ivrooms.setBackground(ContextCompat.getDrawable(context, R.drawable.rooms));
            isAmenitiesAvailable = true;
        } else {
            holder.ivrooms.setVisibility(View.GONE);
        }

        if (wifi == 1) {
            holder.ivwifi.setBackground(ContextCompat.getDrawable(context, R.drawable.wifi));
            isAmenitiesAvailable = true;
        }else {
            holder.ivwifi.setVisibility(View.GONE);
        }

        if (!isAmenitiesAvailable){
            holder.tvNotAvailable.setVisibility(View.VISIBLE);
        }

//        holder.ivamenities.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                amenitiesInterface.amenitiesclicked(position);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return getChargeStationDetailsResponseModels.size();
    }

    public class MyView extends RecyclerView.ViewHolder {

        ImageView ivbarfacility;
        ImageView ivplayarea;
        ImageView ivpool;
        ImageView ivrestaurant;
        ImageView ivrooms;
        ImageView ivwifi;
        TextView tvNotAvailable;
        public MyView(@NonNull View itemView) {
            super(itemView);
            ivbarfacility = itemView.findViewById(R.id.ivbarfacility);
            ivplayarea = itemView.findViewById(R.id.ivplayarea);
            ivpool = itemView.findViewById(R.id.ivpool);
            ivrestaurant = itemView.findViewById(R.id.ivrestaurant);
            ivrooms = itemView.findViewById(R.id.ivrooms);
            ivwifi = itemView.findViewById(R.id.ivwifi);
            tvNotAvailable = itemView.findViewById(R.id.tvNotAvailable);
        }
    }
}
