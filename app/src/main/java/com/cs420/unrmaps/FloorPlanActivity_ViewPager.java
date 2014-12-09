package com.cs420.unrmaps;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

/**
 * Created by Daniel on 12/8/2014.
 */
public class FloorPlanActivity_ViewPager extends FragmentActivity {

    private ImagePagerAdapter mAdapter;
    private ViewPager mPager;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_plan_viewpager);

        mAdapter = new ImagePagerAdapter(getSupportFragmentManager(), 4);
        mPager = (ViewPager) findViewById(R.id.floor_plan_pager);
        mPager.setAdapter(mAdapter);

    }
}
