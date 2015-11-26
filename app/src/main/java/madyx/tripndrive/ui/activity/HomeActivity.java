package madyx.tripndrive.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import java.util.Calendar;

import madyx.tripndrive.R;
import madyx.tripndrive.model.Car;
import madyx.tripndrive.model.Site;
import madyx.tripndrive.ui.fragment.CarDetailFragment;
import madyx.tripndrive.ui.fragment.CarsListFragment;
import madyx.tripndrive.ui.fragment.SearchCarFormFragment;
import madyx.tripndrive.util.NavigationHelper;

public class HomeActivity extends AppCompatActivity implements SearchCarFormFragment.SearchCarForRentListener, CarsListFragment.OnCarSelection {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        attachSearchCarFragment();
    }

    @Override
    public void onSearch(Site rentLocation, Calendar startDate, Calendar endDate) {

        Bundle args = new Bundle();
        args.putSerializable(CarsListFragment.ARG_LOCATION, rentLocation);
        args.putSerializable(CarsListFragment.ARG_START_DATE, startDate);
        args.putSerializable(CarsListFragment.ARG_END_DATE, endDate);

        CarsListFragment fragment = CarsListFragment.newInstance(args);
        fragment.setCarSelectionListener(this);

        NavigationHelper.displayFragment(
                getSupportFragmentManager(),
                R.id.frame_layout_container,
                fragment,
                true);
    }

    @Override
    public void onCarSelected(Car car) {
        Bundle args = new Bundle();
        args.putSerializable(CarDetailFragment.ARG_CAR, car);

        CarDetailFragment fragment = CarDetailFragment.newInstance(args);

        NavigationHelper.displayFragment(
                getSupportFragmentManager(),
                R.id.frame_layout_container,
                fragment,
                true);
    }

    private void attachSearchCarFragment() {

        SearchCarFormFragment fragment = SearchCarFormFragment.newInstance(null);
        fragment.setSearchListener(this);

        NavigationHelper.displayFragment(
                getSupportFragmentManager(),
                R.id.frame_layout_container,
                fragment,
                true);
    }

}
