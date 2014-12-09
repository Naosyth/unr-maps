package com.cs420.unrmaps;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cs420.unrmaps.buildings.Building;
import com.cs420.unrmaps.buildings.BuildingData;

import java.util.ArrayList;

/**
 * Created by Daniel on 12/8/2014.
 */
public class FloorPlanActivity_ViewPager extends Activity {

    private ExtendedViewPager mPager;
    private Building building;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_plan_viewpager);
        building = getIntent().getParcelableExtra("building");

        mPager = (ExtendedViewPager) findViewById(R.id.floor_plan_pager);
        mPager.setAdapter(new TouchImageAdapter(building));

    }

    static class TouchImageAdapter extends PagerAdapter {

        private ArrayList<Integer> images;

        public TouchImageAdapter(Building building){
            images = building.getFloorPlans();
        }


        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            TouchImageView img = new TouchImageView(container.getContext());
            img.setImageResource(images.get(position));
            container.addView(img, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            return img;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
