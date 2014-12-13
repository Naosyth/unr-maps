package com.cs420.unrmaps.buildings;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cs420.unrmaps.R;

import java.util.ArrayList;

// Describes how to displays Building objects when used in a listview.
public class BuildingAdapter extends ArrayAdapter<Building> {
    private final ArrayList<Building> buildings;
    private final Activity context;

    public BuildingAdapter(Activity context, ArrayList<Building> buildings) {
        super(context, R.layout.list_item, buildings);
        this.context = context;
        this.buildings = buildings;
    }

    static class ViewHolder {
        protected TextView text;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        final Building building = buildings.get(position);

        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            view = inflator.inflate(R.layout.list_item, null);
            final ViewHolder viewHolder = new ViewHolder();

            // Get the textview
            viewHolder.text = (TextView) view.findViewById(R.id.list_item_textview);

            // Set the textview's text
            view.setTag(viewHolder);
            viewHolder.text.setTag(building);
        } else {
            view = convertView;
            ((ViewHolder) view.getTag()).text.setTag(building);
        }

        // Add click listener
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                building.clickHandler(context);
            }
        });

        ViewHolder holder = (ViewHolder) view.getTag();
        holder.text.setText(building.getName());

        return view;
    }
}
