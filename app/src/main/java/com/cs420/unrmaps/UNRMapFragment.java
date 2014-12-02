package com.cs420.unrmaps;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UNRMapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UNRMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UNRMapFragment extends Fragment{

    private GoogleMap mMap;
    private LatLng mUNRLocation = new LatLng(39.542634, -119.815461);
    private MapView mMapView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getActionBar().setTitle(getClass().getSimpleName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);

        mMapView = (MapView) v.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);

        if(mMapView.getMap() == null)
            return null;
        try{
            MapsInitializer.initialize(getActivity());
        }
        catch (GooglePlayServicesNotAvailableException e){
            e.printStackTrace();
        }

        mMap = mMapView.getMap();
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        mMap.setMyLocationEnabled(false);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(mUNRLocation, 15);
        mMap.moveCamera(cameraUpdate);

        return v;

    }

    private void configureMap(GoogleMap map) {


        map.setMyLocationEnabled(false);

    }

    @Override
    public void onResume() {
        mMapView.onResume();
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
