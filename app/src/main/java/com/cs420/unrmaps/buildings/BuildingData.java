package com.cs420.unrmaps.buildings;

import android.content.Context;
import android.content.res.XmlResourceParser;

import com.cs420.unrmaps.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

// Singleton object which represents all Building objects
public class BuildingData {
    private static BuildingData instance;
    private ArrayList<Building> buildingList = new ArrayList<Building>();

    private BuildingData(Context context) {
        generateBuildingList(context);
    }

    // Returns the singleton instance
    public static synchronized BuildingData getInstance(Context context) {
        if (instance == null)
            instance = new BuildingData(context);
        return instance;
    }

    // Parses the building XML and populates BuildingData
    private void generateBuildingList(Context context) {
        String name = "";
        float latitude = 0.0f;
        float longitude = 0.0f;
        ArrayList<Integer> floors = new ArrayList<Integer>();;

        // Get the CML resource
        XmlResourceParser xrp = context.getResources().getXml(R.xml.buildings);
        try {
            xrp.next();
            int eventType = xrp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                // Look for a <building> tag
                if (eventType == XmlPullParser.START_TAG && xrp.getName().equalsIgnoreCase("building")) {
                    name = "";
                    latitude = 0.0f;
                    longitude = 0.0f;
                    floors = new ArrayList<Integer>();
                // Save the building if we reach the close building tag </building>
                } else if (eventType == XmlPullParser.END_TAG && xrp.getName().equalsIgnoreCase("building")) {
                    buildingList.add(new Building(name, latitude, longitude, floors));
                // Otherwise look for other tags inbetween the open and close building tags
                } else if (eventType == XmlPullParser.START_TAG) {
                    // Name
                    if (xrp.getName().equalsIgnoreCase("name")) {
                        xrp.next();
                        name = xrp.getText();
                    // Latitude
                    } else if (xrp.getName().equalsIgnoreCase("lat")) {
                        xrp.next();
                        latitude = Float.parseFloat(xrp.getText());
                    // Longitude
                    } else if (xrp.getName().equalsIgnoreCase("long")) {
                        xrp.next();
                        longitude = Float.parseFloat(xrp.getText());
                    // Start of the list of floorplan image resources
                    } else if (xrp.getName().equalsIgnoreCase("floorplan")) {
                        xrp.next();
                        // Get all floors represented by <item> tags
                        while (!(xrp.getEventType() == XmlPullParser.END_TAG && xrp.getName().equalsIgnoreCase("floorplan"))) {
                            if (xrp.getEventType() == XmlPullParser.START_TAG && xrp.getName().equalsIgnoreCase("item")) {
                                xrp.next();
                                floors.add(context.getResources().getIdentifier(xrp.getText(), "drawable", context.getPackageName()));
                            }
                            xrp.next();
                        }
                    }

                }

                eventType = xrp.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Building> getBuildingList() {
        return buildingList;
    }
}
