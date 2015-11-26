package madyx.tripndrive.ui.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.Calendar;

import madyx.tripndrive.R;
import madyx.tripndrive.model.Site;
import madyx.tripndrive.model.SiteOutput;
import madyx.tripndrive.network.GetLocationSpiceRequest;
import madyx.tripndrive.ui.adapter.LocationSpinnerAdapter;
import madyx.tripndrive.util.CalendarUtils;

/**
 * Created by mahmoudreda on 26/11/15.
 */
public class SearchCarFormFragment extends BaseFragment {

    // ------------------------------
    // CONTRACT INTERFACES
    // ------------------------------

    public interface SearchCarForRentListener {
        public abstract void onSearch(Site rentLocation, Calendar startDate, Calendar endDate);
    }

    // ------------------------------
    // ATTRIBUTES
    // ------------------------------

    private Spinner mLocationSpinner;
    private TextView mStartDateEditText, mEndDateEditText;
    private Button mSearchButton;

    private DatePickerDialog mStartDatePickerDialog, mEndDatePickerDialog;

    private Calendar mStartCalendar, mEndCalendar;
    private Site mSelectedSite;

    private SearchCarForRentListener mSearchListener;

    // ----------------------------------
    // CONSTRUCTOR
    // ----------------------------------

    public static SearchCarFormFragment newInstance(Bundle args) {
        SearchCarFormFragment fragment = new SearchCarFormFragment();
        fragment.setArguments(args);
        return fragment;
    }

    // ------------------------------
    // LIFECYCLE
    // ------------------------------

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mSearchListener = (SearchCarForRentListener) context;
        } catch (ClassCastException exception) {
            throw new ClassCastException(context.toString() + " must implement SearchCarForRentListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_car_form, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mLocationSpinner = (Spinner) view.findViewById(R.id.spinner_location);
        mStartDateEditText = (TextView) view.findViewById(R.id.textview_start_date);
        mEndDateEditText = (TextView) view.findViewById(R.id.textview_end_date);
        mSearchButton = (Button) view.findViewById(R.id.button_search);

        setupDatePickers();

        mStartDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStartDatePickerDialog.updateDate(
                        mStartCalendar.get(Calendar.YEAR),
                        mStartCalendar.get(Calendar.MONTH),
                        mStartCalendar.get(Calendar.DAY_OF_MONTH));
                mStartDatePickerDialog.show();
            }
        });

        mEndDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEndDatePickerDialog.updateDate(
                        mEndCalendar.get(Calendar.YEAR),
                        mEndCalendar.get(Calendar.MONTH),
                        mEndCalendar.get(Calendar.DAY_OF_MONTH));
                mEndDatePickerDialog.show();
            }
        });

        mSearchButton.setOnClickListener(new OnSearchButtonClicked());

        getSpiceManager().execute(new GetLocationSpiceRequest(), new OnLocationRequestCompleted());
    }

    // ------------------------------
    // PUBLIC METHODS
    // ------------------------------

    public void setSearchListener(SearchCarForRentListener searchListener) {
        this.mSearchListener = searchListener;
    }

    // ------------------------------
    // PRIVATE METHODS
    // ------------------------------

    private void setupDatePickers() {

        mStartCalendar = Calendar.getInstance();
        mEndCalendar = Calendar.getInstance();
        mEndCalendar.add(Calendar.DATE, 1);

        mStartDateEditText.setText(CalendarUtils.calendarToString(mStartCalendar, getString(R.string.date_pattern_simple)));
        mEndDateEditText.setText(CalendarUtils.calendarToString(mEndCalendar, getString(R.string.date_pattern_simple)));

        setupStartPicker();
        setupEndPicker();
    }

    private void setupStartPicker() {
        mStartDatePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                mStartCalendar.set(year, monthOfYear, dayOfMonth);

                mStartDateEditText.setText(CalendarUtils.calendarToString(mStartCalendar, getString(R.string.date_pattern_simple)));

                // if start date is after end date, make end date after start date with 1 day
                if (mStartCalendar.after(mEndCalendar)) {

                    mEndCalendar.setTime(mStartCalendar.getTime());
                    mEndCalendar.add(Calendar.DATE, 1);

                    mEndDatePickerDialog.updateDate(
                            mEndCalendar.get(Calendar.YEAR),
                            mEndCalendar.get(Calendar.MONTH),
                            mEndCalendar.get(Calendar.DAY_OF_MONTH));

                    mEndDateEditText.setText(CalendarUtils.calendarToString(mEndCalendar, getString(R.string.date_pattern_simple)));
                }

            }
        }, mStartCalendar.get(Calendar.YEAR), mStartCalendar.get(Calendar.MONTH), mStartCalendar.get(Calendar.DAY_OF_MONTH));
    }

    private void setupEndPicker() {
        mEndDatePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                mEndCalendar.set(year, monthOfYear, dayOfMonth);

                mEndDateEditText.setText(CalendarUtils.calendarToString(mEndCalendar, getString(R.string.date_pattern_simple)));

                // if start date is after end date, make start date before end date with 1 day
                if (mStartCalendar.after(mEndCalendar)) {

                    mStartCalendar.setTime(mEndCalendar.getTime());
                    mStartCalendar.add(Calendar.DATE, -1);

                    mStartDatePickerDialog.updateDate(
                            mStartCalendar.get(Calendar.YEAR),
                            mStartCalendar.get(Calendar.MONTH),
                            mStartCalendar.get(Calendar.DAY_OF_MONTH));

                    mStartDateEditText.setText(CalendarUtils.calendarToString(mStartCalendar, getString(R.string.date_pattern_simple)));
                }

            }
        }, mEndCalendar.get(Calendar.YEAR), mEndCalendar.get(Calendar.MONTH), mEndCalendar.get(Calendar.DAY_OF_MONTH));
    }


    // ------------------------------
    // INNER CLASSES
    // ------------------------------

    private class OnSearchButtonClicked implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (!mSearchButton.isEnabled() || mSearchListener == null) {
                return ;
            }
            mSearchListener.onSearch(mSelectedSite, mStartCalendar, mEndCalendar);
        }

    } // OnSearchButtonClicked


    private class OnLocationRequestCompleted implements RequestListener<SiteOutput> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            spiceException.printStackTrace();
        }

        @Override
        public void onRequestSuccess(SiteOutput siteOutput) {
            if (siteOutput == null || siteOutput.getSites() == null || siteOutput.getSites().size() == 0) {
                return ;
            }

            final LocationSpinnerAdapter adapter = new LocationSpinnerAdapter(getActivity(), siteOutput.getSites());
            mLocationSpinner.setAdapter(adapter);
            mLocationSpinner.setOnItemSelectedListener(new OnLocationSpinnerItemSelected(adapter));
        }

    } // OnLocationRequestCompleted


    private class OnLocationSpinnerItemSelected implements AdapterView.OnItemSelectedListener {

        private final LocationSpinnerAdapter adapter;

        public OnLocationSpinnerItemSelected(LocationSpinnerAdapter adapter) {
            this.adapter = adapter;
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            mSelectedSite = adapter.getItem(position);
            if (mSelectedSite != null) {
                mSearchButton.setEnabled(true);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

    } //OnLocationSpinnerItemSelected


}
