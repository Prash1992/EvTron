package com.ev.evtron.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.ev.evtron.R;
import com.ev.evtron.interfaces.ChargeBayInterface;
import com.ev.evtron.pojo.jsonresponse.ChargeStation;
import com.ev.evtron.pojo.jsonresponse.GetChargeStationDetailsResponseModel;

import java.util.ArrayList;

public class ChargeBayAdapter extends RecyclerView.Adapter<ChargeBayAdapter.MyView> {
    private ArrayList<ChargeStation> getChargeStationDetailsResponseModels;
    private ChargeBayInterface chargeBayInterface;
    private Context context;

    public ChargeBayAdapter(Context context, ArrayList<ChargeStation> getChargeStationDetailsResponseModels, ChargeBayInterface chargeBayInterface) {
        this.context = context;
        this.chargeBayInterface = chargeBayInterface;
        this.getChargeStationDetailsResponseModels = getChargeStationDetailsResponseModels;
    }


    @NonNull
    @Override
    public ChargeBayAdapter.MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chargebay, parent, false);
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ChargeBayAdapter.MyView holder, final int position) {
        for (int i = 0; i < getChargeStationDetailsResponseModels.get(0).getChargerBayDetails().size(); i++) {
            holder.tvChargerType.setText(getChargeStationDetailsResponseModels.get(0).chargerBayDetails.get(i).chargerType);
            holder.tvChargerPrice.setText(getChargeStationDetailsResponseModels.get(0).chargerBayDetails.get(i).pricePerHour);
            holder.tvChargerRange.setText(getChargeStationDetailsResponseModels.get(0).chargerBayDetails.get(i).range);
            holder.tvChargerBays.setText(Integer.toString(getChargeStationDetailsResponseModels.get(0).chargerBaysCount));
            if (i==0)
            holder.rbRadioButton.setChecked(true);
        }

//        for (int i=0;i<getChargeStationDetailsResponseModels.get(0).getChargerBayDetails().size();i++){
//            String chargerModel = getChargeStationDetailsResponseModels.get(0).getChargerBayDetails().get(i).getChargerModel();
//            String chargertype = getChargeStationDetailsResponseModels.get(0).getChargerBayDetails().get(i).getChargerType();
//            String name = getChargeStationDetailsResponseModels.get(0).getChargerBayDetails().get(i).getName();
//            String powerOutput = getChargeStationDetailsResponseModels.get(0).getChargerBayDetails().get(i).getPowerOutput();
//            String priceperhour = getChargeStationDetailsResponseModels.get(0).getChargerBayDetails().get(i).getPricePerHour();
//
//            if (chargerModel.equals("AC Charger")){
//                holder.ivac.setBackground(ContextCompat.getDrawable(context, R.drawable.ac));
//            }else {
//                holder.ivac.setBackground(ContextCompat.getDrawable(context, R.drawable.dc));
//            }
//
//            if (chargertype.equals("Fast")){
//                holder.ivFastcharging.setBackground(ContextCompat.getDrawable(context,R.drawable.fast_charge));
//            }else {
//                holder.ivFastcharging.setVisibility(View.GONE);
//            }
//
//            holder.tvname.setText(name);
//            holder.tvKw.setText(powerOutput);
//            holder.tvchargeperhour.setText(context.getString(R.string.rs)+priceperhour);
//
//            holder.rlLocation.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    holder.rlLocation.setBackground(ContextCompat.getDrawable(context,R.drawable.round_medium_button));
//                }
//            });
//        }

    }

    @Override
    public int getItemCount() {
        return getChargeStationDetailsResponseModels.size();
    }

    public class MyView extends RecyclerView.ViewHolder {
        private TextView tvChargerType;
        private TextView tvChargerRange;
        private TextView tvChargerPrice;
        private TextView tvChargerBays;
        private RadioButton rbRadioButton;

        public MyView(@NonNull View itemView) {
            super(itemView);
            tvChargerType = itemView.findViewById(R.id.tvChargerType);
            tvChargerRange = itemView.findViewById(R.id.tvChargerRange);
            tvChargerPrice = itemView.findViewById(R.id.tvChargerPrice);
            tvChargerBays = itemView.findViewById(R.id.tvChargerBays);
            rbRadioButton = itemView.findViewById(R.id.rbRadioButton);
        }
    }
}
