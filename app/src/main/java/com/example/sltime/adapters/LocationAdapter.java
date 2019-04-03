package com.example.sltime.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sltime.R;
import com.example.sltime.model.Location;

import java.util.ArrayList;
import java.util.List;

public class LocationAdapter extends ArrayAdapter {
    List list = new ArrayList<>();
    public LocationAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(Location object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }
    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;
        LocationHolder locationHolder = null;
        row = convertView;
        if(row == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.station_list,parent,false);
            locationHolder = new LocationHolder();
            locationHolder.station=(TextView)row.findViewById(R.id.station);
            row.setTag(locationHolder);
        }
        else {
            locationHolder=(LocationHolder)row.getTag();

        }

        Location location = (Location) this.getItem(position);
        locationHolder.station.setText(location.getStation());

        return row;
    }

    static  class LocationHolder{
        TextView station;
    }
    public void clear(){
        list.clear();

    }
}
