package com.ev.evtron.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ev.evtron.R;
import com.ev.evtron.interfaces.SlotDetailInterface;
import com.ev.evtron.pojo.jsonresponse.ChargeStation;
import com.ev.evtron.pojo.jsonresponse.GetChargeStationDetailsResponseModel;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.MyView> {
    private List<ChargeStation> chargeStations;
    private SlotDetailInterface slotDetailInterface;
    private Context context;

    public LocationAdapter(Context context, List<ChargeStation> chargeStations, SlotDetailInterface slotDetailInterface) {
        this.context = context;
        this.slotDetailInterface = slotDetailInterface;
        this.chargeStations = chargeStations;
    }


    @NonNull
    @Override
    public LocationAdapter.MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationAdapter.MyView holder, final int position) {
        holder.tvLocationName.setText(chargeStations.get(position).getLocation());
        holder.tvKm.setText(chargeStations.get(position).getDistance());
        holder.tvPlug.setText(chargeStations.get(position).chargerBayDetails.get(0).name);
        Log.i("plug type", chargeStations.get(position).chargerBayDetails.get(0).name);
        Log.i("km", chargeStations.get(position).getDistance());
        holder.tvDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slotDetailInterface.slotclicked(position, chargeStations.get(position));
            }
        });
        holder.btnInstant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slotDetailInterface.slotInstantClicked(position, chargeStations.get(position));
            }
        });
        holder.tvLocationDistrict.setText(chargeStations.get(position).district);
        int slotsAvailable = chargeStations.get(position).getACChargerCount() + chargeStations.get(position).getDCChargerCount();
        holder.tvAvailableslots.setText("Open Available Slots: " + slotsAvailable);
        String avgRating = chargeStations.get(0).avgRating;
        switch (avgRating) {
            case "1":
                holder.ivOneStar.setBackgroundResource(R.drawable.ic_baseline_star_grey_24);
                break;
            case "2":
                holder.ivOneStar.setBackgroundResource(R.drawable.ic_baseline_star_grey_24);
                holder.ivTwoStar.setBackgroundResource(R.drawable.ic_baseline_star_grey_24);
                break;
            case "3":
                holder.ivOneStar.setBackgroundResource(R.drawable.ic_baseline_star_grey_24);
                holder.ivTwoStar.setBackgroundResource(R.drawable.ic_baseline_star_grey_24);
                holder.ivThreeStar.setBackgroundResource(R.drawable.ic_baseline_star_grey_24);
                break;
            case "4":
                holder.ivOneStar.setBackgroundResource(R.drawable.ic_baseline_star_grey_24);
                holder.ivTwoStar.setBackgroundResource(R.drawable.ic_baseline_star_grey_24);
                holder.ivThreeStar.setBackgroundResource(R.drawable.ic_baseline_star_grey_24);
                holder.ivFourStar.setBackgroundResource(R.drawable.ic_baseline_star_grey_24);
                break;
            case "5":
                holder.ivOneStar.setBackgroundResource(R.drawable.ic_baseline_star_grey_24);
                holder.ivTwoStar.setBackgroundResource(R.drawable.ic_baseline_star_grey_24);
                holder.ivThreeStar.setBackgroundResource(R.drawable.ic_baseline_star_grey_24);
                holder.ivFourStar.setBackgroundResource(R.drawable.ic_baseline_star_grey_24);
                holder.ivFiveStar.setBackgroundResource(R.drawable.ic_baseline_star_grey_24);
                break;
        }


        Picasso.get().load(chargeStations.get(position).getImgUrl()).resize(70, 120).into(holder.ivImage);
    }


    @Override
    public int getItemCount() {
        return chargeStations.size();
    }

    public class MyView extends RecyclerView.ViewHolder {

        TextView tvLocationName;
        CardView cvLocation;
        TextView tvKm;
        TextView tvPlug;
        TextView tvLocationDistrict;
        TextView tvAvailableslots;
        Button tvDetails;
        Button btnInstant;
        ImageView ivImage;
        ImageView ivOneStar;
        ImageView ivTwoStar;
        ImageView ivThreeStar;
        ImageView ivFourStar;
        ImageView ivFiveStar;

        public MyView(@NonNull View itemView) {
            super(itemView);
            tvLocationName = itemView.findViewById(R.id.tvLocationName);
            cvLocation = itemView.findViewById(R.id.cvLocation);
            tvKm = itemView.findViewById(R.id.tvKm);
            tvPlug = itemView.findViewById(R.id.tvPlug);
            tvDetails = itemView.findViewById(R.id.tvDetails);
            btnInstant = itemView.findViewById(R.id.btnInstant);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvLocationDistrict = itemView.findViewById(R.id.tvLocationDistrict);
            tvAvailableslots = itemView.findViewById(R.id.tvAvailableslots);
            ivOneStar = itemView.findViewById(R.id.ivOneStar);
            ivTwoStar = itemView.findViewById(R.id.ivTwoStar);
            ivThreeStar = itemView.findViewById(R.id.ivThreeStar);
            ivFourStar = itemView.findViewById(R.id.ivFourStar);
            ivFiveStar = itemView.findViewById(R.id.ivFiveStar);
        }
    }
}
