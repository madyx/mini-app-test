package madyx.tripndrive.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Calendar;

import madyx.tripndrive.R;
import madyx.tripndrive.model.Car;
import madyx.tripndrive.model.Site;
import madyx.tripndrive.ui.fragment.CarsListFragment;
import madyx.tripndrive.ui.fragment.SearchCarFormFragment;

public class HomeActivity extends AppCompatActivity implements SearchCarFormFragment.SearchCarForRentListener, CarsListFragment.OnCarSelection {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        attachSearchCarFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    @Override
    public void onSearch(Site rentLocation, Calendar startDate, Calendar endDate) {
        Bundle args = new Bundle();
        args.putSerializable(CarsListFragment.ARG_LOCATION, rentLocation);
        args.putSerializable(CarsListFragment.ARG_START_DATE, startDate);
        args.putSerializable(CarsListFragment.ARG_END_DATE, endDate);

        CarsListFragment fragment = CarsListFragment.newInstance(args);
        fragment.setCarSelectionListener(this);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_layout_container, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onCarSelected(Car car) {
        Intent intent = new Intent(this, CarDetailActivity.class);
        intent.putExtra(CarDetailActivity.EXTRA_CAR, car);
        startActivity(intent);
    }

    private void attachSearchCarFragment() {
        SearchCarFormFragment fragment = SearchCarFormFragment.newInstance(null);
        fragment.setSearchListener(this);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.frame_layout_container, fragment);
        ft.commit();
    }

}
