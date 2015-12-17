package madyx.tripndrive.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Calendar;

import madyx.tripndrive.R;
import madyx.tripndrive.model.Car;
import madyx.tripndrive.util.CalendarUtils;

public class CarDetailActivity extends AppCompatActivity {

    public static final String EXTRA_CAR = CarDetailActivity.class.getName() + ".EXTRA_CAR";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);

        Intent intent = getIntent();
        final Car car = (Car) intent.getSerializableExtra(EXTRA_CAR);

        if (car == null) {
            return ;
        }

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        String title = String.format("%s %s (%d)", car.getModelBrand(), car.getModelName(), car.getCarYear());
        collapsingToolbar.setTitle(title);

        loadCarImage(car);

        fillCarInfo(car);
    }

    private void loadCarImage(Car car) {
        final ImageView imageView = (ImageView) findViewById(R.id.parallax_imageview);
        Picasso.with(this).load(car.getImages().get(0).getMediumUrl()).into(imageView);
    }

    private void fillCarInfo(Car car) {
        TextView brandTextView = (TextView) findViewById(R.id.car_brand_textview);
        TextView modelTextView = (TextView) findViewById(R.id.car_model_textview);
        TextView doorsTextView = (TextView) findViewById(R.id.car_doors_textview);
        TextView yearTextView = (TextView) findViewById(R.id.car_year_textview);
        TextView kilometersTextView = (TextView) findViewById(R.id.car_kilometers_textview);
        TextView seatsTextView = (TextView) findViewById(R.id.car_seats_textview);
        TextView fuelTypeTextView = (TextView) findViewById(R.id.car_fueltype_textview);
        TextView babySeatTextView = (TextView) findViewById(R.id.car_baby_seat_textview);
        TextView availabilityStartTextView = (TextView) findViewById(R.id.car_availability_date_start_textview);
        TextView availabilityEndTextView = (TextView) findViewById(R.id.car_availability_date_end_textview);

        brandTextView.setText(getString(R.string.car_brand, car.getModelBrand()));
        modelTextView.setText(getString(R.string.car_model, car.getModelName()));
        doorsTextView.setText(getString(R.string.car_doors, car.getDoorNumber()));
        yearTextView.setText(getString(R.string.car_year, car.getCarYear()));
        kilometersTextView.setText(getString(R.string.car_kilometers, car.getKmIncluded()));
        seatsTextView.setText(getString(R.string.car_seats, car.getPlaceNumber()));
        fuelTypeTextView.setText(getString(R.string.car_fuel_type, car.getGazLabel()));
        babySeatTextView.setText(getString(R.string.car_baby_seats, car.getBabyChair()));

        Calendar cStart = CalendarUtils.stringToCalendar(car.getStart(), "yyyy-MM-dd'T'HH:mm:sss");
        String sStart = CalendarUtils.calendarToString(cStart, "yyyy-MM-dd HH:mm");
        availabilityStartTextView.setText(getString(R.string.car_available_since, sStart));

        Calendar cEnd = CalendarUtils.stringToCalendar(car.getStart(), "yyyy-MM-dd'T'HH:mm:sss");
        String sEnd = CalendarUtils.calendarToString(cEnd, "yyyy-MM-dd HH:mm");
        availabilityEndTextView.setText(getString(R.string.car_available_until, sEnd));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_car_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
