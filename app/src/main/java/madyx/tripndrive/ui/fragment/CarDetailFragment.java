package madyx.tripndrive.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import madyx.tripndrive.R;
import madyx.tripndrive.model.Car;

/**
 * Created by mahmoudreda on 26/11/15.
 */
public class CarDetailFragment extends BaseFragment {

    public static final String ARG_CAR = CarDetailFragment.class.getName() + ".ARG_CAR";

    private Car mCar;
    private ImageView mCarImageView;

    // ----------------------------------
    // CONSTRUCTOR
    // ----------------------------------

    public static CarDetailFragment newInstance(Bundle args) {
        CarDetailFragment fragment = new CarDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!getArguments().containsKey(CarDetailFragment.ARG_CAR)) {
            return ;
        }

        mCar = (Car) getArguments().getSerializable(CarDetailFragment.ARG_CAR);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_car_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCarImageView = (ImageView) view.findViewById(R.id.car_imageview);

        if (mCar == null) {
            return ;
        }

        if (mCar.getImages() != null && mCar.getImages().size() > 0) {
            Picasso.with(getActivity()).load(mCar.getImages().get(0).getMediumUrl()).into(mCarImageView);
        }

    }


}
