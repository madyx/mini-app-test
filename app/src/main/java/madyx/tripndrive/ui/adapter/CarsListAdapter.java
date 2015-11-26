package madyx.tripndrive.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import madyx.tripndrive.R;
import madyx.tripndrive.model.Car;

/**
 * Created by mahmoudreda on 26/11/15.
 */
public class CarsListAdapter<CarItemViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ViewHolders.CarItemViewHolder.CarItemSelectionListener {

    public interface CarSelectionListener {
        public void onCarSelected(Car car);
    }

    private final Context mContext;
    private List<Car> mCars;

    private CarSelectionListener mCarSelectionListener;

    public CarsListAdapter(final Context context, List<Car> cars) {
        mContext = context;
        mCars = new ArrayList<>(cars);
    }

    public void setCarSelectionListener(CarSelectionListener mCarSelectionListener) {
        this.mCarSelectionListener = mCarSelectionListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_car, parent, false);
        return new ViewHolders.CarItemViewHolder(mContext, v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolders.CarItemViewHolder) holder).bindItem(mCars.get(position), this);
    }

    @Override
    public int getItemCount() {
        return mCars.size();
    }

    @Override
    public void onCarSelected(Car carItem) {
        if (mCarSelectionListener != null) {
            mCarSelectionListener.onCarSelected(carItem);
        }
    }

}
