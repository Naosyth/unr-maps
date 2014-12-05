package com.cs420.unrmaps.buildings;

import android.content.Context;
import android.content.res.XmlResourceParser;

import com.cs420.unrmaps.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class BuildingData {
    private ArrayList<Building> buildingList = new ArrayList<Building>();

    public BuildingData(Context context) {
        generateBuildingList(context);
    }

    private void generateBuildingList(Context context) {
        String name = "";
        float latitude = 0.0f;
        float longitude = 0.0f;
        ArrayList<Integer> floors = new ArrayList<Integer>();;

        XmlResourceParser xrp = context.getResources().getXml(R.xml.buildings);
        try {
            xrp.next();
            int eventType = xrp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG && xrp.getName().equalsIgnoreCase("building")) {
                    name = "";
                    latitude = 0.0f;
                    longitude = 0.0f;
                    floors = new ArrayList<Integer>();
                } else if (eventType == XmlPullParser.END_TAG && xrp.getName().equalsIgnoreCase("building")) {
                    buildingList.add(new Building(name, latitude, longitude, floors));
                } else if (eventType == XmlPullParser.START_TAG) {
                    if (xrp.getName().equalsIgnoreCase("name")) {
                        xrp.next();
                        name = xrp.getText();
                    } else if (xrp.getName().equalsIgnoreCase("lat")) {
                        xrp.next();
                        latitude = Float.parseFloat(xrp.getText());
                    } else if (xrp.getName().equalsIgnoreCase("long")) {
                        xrp.next();
                        longitude = Float.parseFloat(xrp.getText());
                    } else if (xrp.getName().equalsIgnoreCase("floorplan")) {
                        xrp.next();
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
