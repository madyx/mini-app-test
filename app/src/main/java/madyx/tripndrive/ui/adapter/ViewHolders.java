package madyx.tripndrive.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import madyx.tripndrive.R;
import madyx.tripndrive.model.Car;

/**
 * Created by mahmoudreda on 26/11/15.
 */
public abstract class ViewHolders {

    public static class CarItemViewHolder extends RecyclerView.ViewHolder {

        public interface CarItemSelectionListener {
            public abstract void onCarSelected(Car carItem);
        }

        private final Context mContext;
        private final ImageView mImageView;
        private final TextView mCarModelNameTextView;
        private final TextView mCarAdditionalInfoTextView;
        private CarItemSelectionListener mSelectionListener;


        protected Car mCarItem;

        protected CarItemViewHolder(final Context context, View itemView) {
            super(itemView);

            mContext = context;
            mImageView = (ImageView) itemView.findViewById(R.id.car_imageview);
            mCarModelNameTextView = (TextView) itemView.findViewById(R.id.car_model_textview);
            mCarAdditionalInfoTextView = (TextView) itemView.findViewById(R.id.car_additional_info_textview);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mSelectionListener != null && mCarItem != null) {
                        mSelectionListener.onCarSelected(mCarItem);
                    }
                }
            });
        }

        public void bindItem(Car carItem, final CarItemSelectionListener selectionListener) {
            if (carItem == null) {
                return ;
            }

            if (carItem.getImages() != null && carItem.getImages().size() > 0) {
                Picasso.with(mContext).load(carItem.getImages().get(0).getSmallUrl()).into(mImageView);
            }

            mCarItem = carItem;
            mSelectionListener = selectionListener;

            String carModelAndYear = String.format("%s %s (%d)", carItem.getModelBrand(), carItem.getModelName(), carItem.getCarYear());
            mCarModelNameTextView.setText(carModelAndYear);

            mCarAdditionalInfoTextView.setText(mContext.getResources().getQuantityString(R.plurals.car_places, carItem.getPlaceNumber(), carItem.getPlaceNumber()));
        }

    }; // CarItemViewHolder

}
