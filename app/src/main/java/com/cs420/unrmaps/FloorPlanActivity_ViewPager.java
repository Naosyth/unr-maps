package com.cs420.unrmaps;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cs420.unrmaps.buildings.Building;
import com.cs420.unrmaps.buildings.BuildingData;

import java.lang.ref.WeakReference;
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
        mPager.setAdapter(new TouchImageAdapter(building, this));

    }

    public void loadBitmap(int resId, TouchImageView imageView){
        //placeholder
        BitmapWorkerTask task = new BitmapWorkerTask(imageView);
        task.execute(resId);
    }

    static class TouchImageAdapter extends PagerAdapter {

        private ArrayList<Integer> images;
        private Context mContext;

        public TouchImageAdapter(Building building, Context context){
            images = building.getFloorPlans();
            mContext = context;
        }


        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            TouchImageView img = new TouchImageView(container.getContext());

            //load floorplan image
            ((FloorPlanActivity_ViewPager)mContext).loadBitmap(images.get(position), img);
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

    //Async task used for loading images
    private class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {
        private final WeakReference<TouchImageView> imageViewReference;
        private int data = 0;

        public BitmapWorkerTask(TouchImageView imageView) {
            // Use a WeakReference to ensure the ImageView can be garbage collected
            imageViewReference = new WeakReference<TouchImageView>(imageView);
        }

        // Decode image in background.
        @Override
        protected Bitmap doInBackground(Integer... params) {
            data = params[0];
            BitmapFactory.Options options = new BitmapFactory.Options();

            //needed due to bug, samples from the image file at 1:1 ratio
            options.inSampleSize = 1;

            //decode file
            return BitmapFactory.decodeResource(getResources(), data, options );
        }

        // Once complete, see if ImageView is still around and set bitmap.
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (imageViewReference != null && bitmap != null) {
                final ImageView imageView = imageViewReference.get();
                if (imageView != null) {

                    //set bitmap in the view
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
    }
}
