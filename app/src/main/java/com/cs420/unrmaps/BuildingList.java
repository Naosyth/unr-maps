package com.cs420.unrmaps;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cs420.unrmaps.buildings.BuildingAdapter;
import com.cs420.unrmaps.buildings.BuildingData;

public class BuildingList extends ListFragment {
    private String[] mBuildingNames;
    private BuildingData data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        data = new BuildingData(getActivity());
        mBuildingNames = getResources().getStringArray(R.array.building_names);
        setListAdapter(new BuildingAdapter(getActivity(), data.getBuildingList()));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        //TODO: Open floorplan view
    }
}
