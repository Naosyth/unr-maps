package com.cs420.unrmaps.buildings;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs420.unrmaps.R;

import java.util.ArrayList;

public class FloorAdapter extends ArrayAdapter<Integer> {
    private final ArrayList<Integer> floors;
    private final Activity context;

    public FloorAdapter(Activity context, ArrayList<Integer> floors) {
        super(context, R.layout.list_floor, floors);
        this.context = context;
        this.floors = floors;
    }

    static class ViewHolder {
        protected TextView text;
        protected ImageView image;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            view = inflator.inflate(R.layout.list_floor, parent, false);
            final ViewHolder viewHolder = new ViewHolder();

            viewHolder.text = (TextView) view.findViewById(R.id.textView);
            viewHolder.image = (ImageView) view.findViewById(R.id.imageView);

            view.setTag(viewHolder);
            viewHolder.text.setTag("floor");
        } else {
            view = convertView;
            ((ViewHolder) view.getTag()).text.setTag("floor");
        }

        ViewHolder holder = (ViewHolder) view.getTag();
        holder.text.setText("Floor: " + (position+1));
        holder.image.setImageResource(floors.get(position));

        return view;
    }
}
