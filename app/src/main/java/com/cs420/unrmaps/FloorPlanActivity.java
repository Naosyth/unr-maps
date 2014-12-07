package com.cs420.unrmaps;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.cs420.unrmaps.buildings.Building;
import com.cs420.unrmaps.buildings.FloorAdapter;

import java.util.ArrayList;


public class FloorPlanActivity extends Activity {
    private static ArrayList<Integer> floorPlans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_plan);

        Building building = this.getIntent().getParcelableExtra("building");
        floorPlans = building.getFloorPlans();
        setTitle(building.getName());

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new FloorPlanFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_floor_plan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class FloorPlanFragment extends Fragment {
        private ListView listView;

        public FloorPlanFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_floor_plan, container, false);

            listView = (ListView) rootView.findViewById(R.id.listView);
            listView.setAdapter(new FloorAdapter(getActivity(), floorPlans, (RelativeLayout) rootView.findViewById(R.id.floor_plans)));

            return rootView;
        }
    }
}
