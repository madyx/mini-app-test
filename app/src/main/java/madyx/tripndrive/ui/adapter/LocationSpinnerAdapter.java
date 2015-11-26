package madyx.tripndrive.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import madyx.tripndrive.model.Site;

/**
 * Created by mahmoudreda on 26/11/15.
 */
public class LocationSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {

    private final Context mContext;
    private List<Site> mLocations;

    public LocationSpinnerAdapter(final Context context, List<Site> locations) {
        mContext = context;
        mLocations = new ArrayList<>(locations);
    }

    @Override
    public int getCount() {
        return mLocations.size();
    }

    @Override
    public Site getItem(int position) {
        return mLocations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView textView;

        if (convertView != null) {
            textView = (TextView) convertView;
        } else {
            textView = (TextView) LayoutInflater.from(mContext).inflate(android.R.layout.simple_dropdown_item_1line, parent, false);
        }

        textView.setText(getItem(position).getLabel());

        return textView;
    }


}
