package com.cs420.unrmaps;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Daniel on 12/8/2014.
 */
public class FloorPlanFragment extends Fragment {

    private int mImageNum;
    private ImageView mImageView;

    static FloorPlanFragment newInstance(int imageNum){
        final FloorPlanFragment f = new FloorPlanFragment();
        final Bundle args = new Bundle();
        args.putInt("resId", imageNum);
        f.setArguments(args);
        return f;
    }

    public FloorPlanFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageNum = getArguments() != null ? getArguments().getInt("resId") : -1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_floorplan_vp, container, false);
        mImageView = (ImageView) v.findViewById(R.id.floor_plan_image_view);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final int resId = R.drawable.jcsu_1;
        mImageView.setImageResource(resId);
    }
}
