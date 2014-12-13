package com.cs420.unrmaps.buildings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.PreferenceManager;

import com.cs420.unrmaps.FloorPlanActivity;
import com.cs420.unrmaps.FloorPlanActivity_ViewPager;

import java.util.ArrayList;

public class Building implements Parcelable {
    private final String name;
    private final float latitude;
    private final float longitude;
    private final ArrayList<Integer> floorPlans;

    public Building(String name, float latitude, float longitude, ArrayList<Integer> floorPlans) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.floorPlans = floorPlans;
    }

    @SuppressWarnings("unchecked")
    public Building(Parcel in) {
        this.name = in.readString();
        this.latitude = in.readFloat();
        this.longitude = in.readFloat();
        this.floorPlans = (ArrayList<Integer>) in.readSerializable();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getFloorPlans() {
        return floorPlans;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void clickHandler(Context context) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        Intent floorPlanIntent;
        if(settings.getBoolean("use viewpager", true)){
            floorPlanIntent = new Intent(context, FloorPlanActivity_ViewPager.class);
        }
        else{
            floorPlanIntent = new Intent(context, FloorPlanActivity.class);
        }
        floorPlanIntent.putExtra("building", this);
        context.startActivity(floorPlanIntent);
    }

    public static final Parcelable.Creator<Building> CREATOR = new Parcelable.Creator<Building>() {
        public Building createFromParcel(Parcel in) {
            return new Building(in);
        }
        public Building[] newArray (int size) {
            return new Building[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeFloat(latitude);
        dest.writeFloat(longitude);
        dest.writeSerializable(floorPlans);
    }
}
