package com.ev.evtron.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ev.evtron.R;
import com.ev.evtron.interfaces.SlotDetailInterface;
import com.ev.evtron.pojo.jsonresponse.SlotDetailResponseModel;

import java.util.List;

public class SlotDetailAdapter extends RecyclerView.Adapter<SlotDetailAdapter.ViewHolder> {
    private List<SlotDetailResponseModel> slotDetailResponseModels;
    private Context context;
    private SlotDetailInterface slotDetailInterface;

    public SlotDetailAdapter(Context context, List<SlotDetailResponseModel> slotDetailResponseModels, SlotDetailInterface slotDetailInterface) {
        this.context = context;
        this.slotDetailResponseModels = slotDetailResponseModels;
        this.slotDetailInterface = slotDetailInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.slot_detail_list, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final SlotDetailResponseModel slotDetailResponseModel = slotDetailResponseModels.get(position);
        holder.tvslotList.setText(slotDetailResponseModel.getTime());
        holder.tvslotList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // slotDetailInterface.slotclicked(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return slotDetailResponseModels.size();
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

        TextView tvslotList;
//        CheckBox rbslotcheked;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvslotList = itemView.findViewById(R.id.tvslotList);
//            rbslotcheked = itemView.findViewById(R.id.rbslotcheked);
        }
    }

}
