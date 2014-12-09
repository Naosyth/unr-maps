package com.cs420.unrmaps;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Daniel on 12/8/2014.
 */
public class ImagePagerAdapter extends FragmentStatePagerAdapter{
    private final int mSize;

    public ImagePagerAdapter(FragmentManager fm, int size){
        super(fm);
        mSize = size;
    }

    @Override
    public Fragment getItem(int position) {
        return FloorPlanFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return mSize;
    }
}
