package com.ev.evtron.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class CurrentGridViewAdapter extends BaseAdapter {

    private Activity activity;
    private String[] strings;
    private String[] id;
    private String[] slotStatusCode;
    public List selectedPositions;

    public CurrentGridViewAdapter(String[] strings,String[] id, String[] slotStatusCode,Activity activity) {
        this.strings = strings;
        this.id = id;
        this.activity = activity;
        this.slotStatusCode = slotStatusCode;
        selectedPositions = new ArrayList<>();
    }


    @Override
    public int getCount() {
        return id.length;
    }

    @Override
    public Object getItem(int i) {
        return id[i];
    }

    public Object getSlotStatusitem(int i) {
        return slotStatusCode[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CurrentGridItemView customView = (view == null) ? new CurrentGridItemView(activity) : (CurrentGridItemView) view;
        customView.display(strings[i], selectedPositions.contains(i));
        if (slotStatusCode[i].equals("1")) {
            Log.i("print", "print");
            customView.blockdisplay(true);
        }

        return customView;
    }
}
