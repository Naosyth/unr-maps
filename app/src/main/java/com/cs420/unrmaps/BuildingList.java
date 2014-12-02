package com.cs420.unrmaps;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class BuildingList extends ListFragment {
    private String[] mBuildingNames;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBuildingNames = getResources().getStringArray(R.array.building_names);
        setListAdapter(new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1,
                mBuildingNames));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        //TODO: Open floorplan view
    }
}
