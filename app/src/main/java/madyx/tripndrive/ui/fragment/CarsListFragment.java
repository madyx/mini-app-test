package madyx.tripndrive.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.Calendar;

import madyx.tripndrive.R;
import madyx.tripndrive.model.Car;
import madyx.tripndrive.model.CarsOutput;
import madyx.tripndrive.model.Site;
import madyx.tripndrive.network.GetCarsSpiceRequest;
import madyx.tripndrive.ui.adapter.CarsListAdapter;

/**
 * Created by mahmoudreda on 26/11/15.
 */
public class CarsListFragment extends BaseFragment {

    // ------------------------------
    // CONTRACT INTERFACES
    // ------------------------------

    public interface OnCarSelection {
        public abstract void onCarSelected(Car car);
    }

    // ------------------------------
    // CONSTANTS
    // ------------------------------

    public static final String ARG_LOCATION = CarsListFragment.class.getName() + ".ARG_LOCATION";
    public static final String ARG_START_DATE = CarsListFragment.class.getName() + ".ARG_START_DATE";
    public static final String ARG_END_DATE = CarsListFragment.class.getName() + ".ARG_END_DATE";

    // ------------------------------
    // ATTRIBUTES
    // ------------------------------

    private RecyclerView mCarsRecyclerView;
    private OnCarSelection mCarSelectionListener;

    // ----------------------------------
    // CONSTRUCTOR
    // ----------------------------------

    public static CarsListFragment newInstance(Bundle args) {
        CarsListFragment fragment = new CarsListFragment();
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
            mCarSelectionListener = (OnCarSelection) context;
        } catch (ClassCastException exception) {
            throw new ClassCastException(context.toString() + " must implement OnCarSelection");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!getArguments().containsKey(CarsListFragment.ARG_LOCATION)) {
            return ;
        }

        Site location = (Site) getArguments().getSerializable(CarsListFragment.ARG_LOCATION);
        Calendar startDate = (Calendar) getArguments().getSerializable(CarsListFragment.ARG_START_DATE);
        Calendar endDate = (Calendar) getArguments().getSerializable(CarsListFragment.ARG_END_DATE);

        getSpiceManager().execute(new GetCarsSpiceRequest(location, startDate, endDate), new OnCarsRequestCompleted());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cars_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCarsRecyclerView = (RecyclerView) view.findViewById(R.id.cars_recyclerview);
        mCarsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    // ------------------------------
    // PUBLIC METHODS
    // ------------------------------

    public void setCarSelectionListener(OnCarSelection carSelectionListener) {
        this.mCarSelectionListener = carSelectionListener;
    }


    // ------------------------------
    // INNER CLASSES
    // ------------------------------

    private class OnCarsRequestCompleted implements RequestListener<CarsOutput> {
        @Override
        public void onRequestFailure(SpiceException spiceException) {
            spiceException.printStackTrace();
        }

        @Override
        public void onRequestSuccess(CarsOutput carsOutput) {

            if (carsOutput == null || carsOutput.getCars() == null || carsOutput.getCars().size() == 0) {
                Toast.makeText(getActivity(), R.string.sorry_no_cars_available, Toast.LENGTH_LONG).show();
                getActivity().onBackPressed();
                return ;
            }

            CarsListAdapter adapter = new CarsListAdapter(getActivity(), carsOutput.getCars());

            adapter.setCarSelectionListener(new CarsListAdapter.CarSelectionListener() {
                @Override
                public void onCarSelected(Car car) {
                    if (mCarSelectionListener != null) {
                        mCarSelectionListener.onCarSelected(car);
                    }
                }
            });

            mCarsRecyclerView.setAdapter(adapter);
        }
    } // OnCarsRequestCompleted


}
