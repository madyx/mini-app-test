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

    public static class CarItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public interface CarItemSelectionListener {
            public abstract void onCarSelected(Car carItem);
        }

        private final Context mContext;
        private final ImageView mImageView;
        private final TextView mCarModelNameTextView;
        private CarItemSelectionListener mSelectionListener;

        protected Car mCarItem;

        protected CarItemViewHolder(final Context context, View itemView) {
            super(itemView);

            mContext = context;
            mImageView = (ImageView) itemView.findViewById(R.id.car_imageview);
            mCarModelNameTextView = (TextView) itemView.findViewById(R.id.car_model_textview);

            itemView.setOnClickListener(this);
        }

        public void bindItem(Car carItem, final CarItemSelectionListener selectionListener) {
            if (carItem == null) {
                return ;
            }

            mSelectionListener = selectionListener;

            if (carItem.getImages() != null && carItem.getImages().size() > 0) {
                Picasso.with(mContext).load(carItem.getImages().get(0).getSmallUrl()).into(mImageView);
            }

            mCarModelNameTextView.setText(String.format("%s %s", carItem.getModelBrand(), carItem.getModelName()));
        }

        @Override
        public void onClick(View v) {
            if (mSelectionListener != null && mCarItem != null) {
                mSelectionListener.onCarSelected(mCarItem);
            }
        }
    }

}
