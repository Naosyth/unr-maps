package com.cs420.unrmaps;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cs420.unrmaps.buildings.Building;
import com.cs420.unrmaps.buildings.BuildingData;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.PriorityQueue;


public class UNRMapFragment extends Fragment implements GoogleMap.OnMapClickListener{

    private GoogleMap mMap;
    private LatLng mUNRLocation = new LatLng(39.542634, -119.815461);
    private MapView mMapView;

    private BuildingData buildingData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getActionBar().setTitle("UNR Campus Map");
        buildingData = BuildingData.getInstance(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);

        mMapView = (MapView) v.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);

        if(mMapView.getMap() == null)
            return null;
        MapsInitializer.initialize(getActivity());

        mMap = mMapView.getMap();
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        mMap.setMyLocationEnabled(false);

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if (settings.getBoolean("satellite enabled", false))
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        else
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //default the camera to UNR campus
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(mUNRLocation, 15);
        mMap.moveCamera(cameraUpdate);

        mMap.setOnMapClickListener(this);

        return v;
    }

    @Override
    public void onMapClick(LatLng latLng) {
        ArrayList<Building> buildings = buildingData.getBuildingList();

        Building closest = null;
        double minDist = 9999;
        double distance;
        for (Building building : buildings) {
            distance = Math.sqrt(Math.pow(latLng.latitude - building.getLatitude(), 2) + Math.pow(latLng.longitude - building.getLongitude(), 2));
            if (Double.compare(distance, minDist) < 0) {
                minDist = distance;
                closest = building;
            }
        }

        if (closest != null && minDist < 0.0005) {
            closest.clickHandler(getActivity());
        }
     }

    private void configureMap(GoogleMap map) {
        map.setMyLocationEnabled(false);
    }

    @Override
    public void onResume() {
        mMapView.onResume();
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if (settings.getBoolean("satellite enabled", false))
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        else
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        super.onResume();
    }

    @Override
    public void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        mMapView.onLowMemory();
        super.onLowMemory();
    }
}
