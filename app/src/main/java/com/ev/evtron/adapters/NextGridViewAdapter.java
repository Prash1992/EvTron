package com.ev.evtron.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class NextGridViewAdapter extends BaseAdapter {

    private Activity activity;
    private String[] strings;
    private String[] id1;
    private String[] slotStatusCodeNextDay;
    public List selectedPositions;

    public NextGridViewAdapter(String[] strings,String[] id1,String[] slotStatusCodeNextDay, Activity activity) {
        this.strings = strings;
        this.activity = activity;
        this.id1 = id1;
        this.slotStatusCodeNextDay = slotStatusCodeNextDay;
        selectedPositions = new ArrayList<>();
    }


    @Override
    public int getCount() {
        return id1.length;
    }

    @Override
    public Object getItem(int i) {
        return id1[i];
    }

    public Object getSlotStatusCodeNextDayItem(int i) {
        return slotStatusCodeNextDay[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        NextGridItemView customView = (view == null) ? new NextGridItemView(activity) : (NextGridItemView) view;
        customView.display(strings[i], selectedPositions.contains(i));
        if (slotStatusCodeNextDay[i].equals("1")) {
            Log.i("print", "print");
            customView.blockdisplay(true);
        }

        return customView;
    }
}
