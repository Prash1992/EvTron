package com.ev.evtron.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ev.evtron.R;
import com.ev.evtron.interfaces.LocationListInterface;
import com.ev.evtron.pojo.jsonresponse.ChargeStation;
import com.ev.evtron.pojo.jsonresponse.GetChargeStationDetailsResponseModel;

import java.util.List;

public class LocationListAdapter extends RecyclerView.Adapter<LocationListAdapter.ViewHolder> {
    private List<ChargeStation> chargeStations;
    private Context context;
    private LocationListInterface locationListInterface;

    public LocationListAdapter(Context context, List<ChargeStation> chargeStations, LocationListInterface locationListInterface) {
        this.context = context;
        this.chargeStations = chargeStations;
        this.locationListInterface = locationListInterface;
    }


    @NonNull
    @Override
    public LocationListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.location_list, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationListAdapter.ViewHolder holder, final int position) {
        ChargeStation chargeStation = chargeStations.get(position);

        holder.tvLocationNames.setText(chargeStation.getLocation());
        holder.tvKm.setText(chargeStation.getDistance());
        holder.tvPlug.setText(chargeStation.getChargerBayDetails().get(0).name);
        holder.tvLocationNames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationListInterface.locationclicked(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return chargeStations.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvLocationNames;
        TextView tvKm;
        TextView tvPlug;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLocationNames = itemView.findViewById(R.id.tvLocationNames);
            tvKm = itemView.findViewById(R.id.tvKm);
            tvPlug = itemView.findViewById(R.id.tvPlug);
        }
    }
}
