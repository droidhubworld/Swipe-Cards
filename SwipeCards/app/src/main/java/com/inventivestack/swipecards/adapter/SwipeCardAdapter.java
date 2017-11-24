package com.inventivestack.swipecards.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.inventivestack.swipecards.R;
import com.inventivestack.swipecards.model.Data;

import java.util.List;

/**
 * Created by akumar1 on 11/24/2017.
 */

public class SwipeCardAdapter extends BaseAdapter {

    public static ViewHolder viewHolder;
    private List<Data> parkingList;
    Activity activity;

    public SwipeCardAdapter(List<Data> apps, Activity activity) {
        this.parkingList = apps;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return parkingList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View rowView = convertView;


        if (rowView == null) {

            LayoutInflater inflater = activity.getLayoutInflater();
            rowView = inflater.inflate(R.layout.swipe_card_item, parent, false);
            // configure view holder
            viewHolder = new ViewHolder();
            viewHolder.DataText = (TextView) rowView.findViewById(R.id.bookText);
            viewHolder.background = (FrameLayout) rowView.findViewById(R.id.background);
            viewHolder.cardImage = (ImageView) rowView.findViewById(R.id.cardImage);
            rowView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.DataText.setText(parkingList.get(position).getDescription() + "");

        Glide.with(activity).load(parkingList.get(position).getImagePath()).into(viewHolder.cardImage);


        return rowView;
    }

    public static class ViewHolder {
        public static FrameLayout background;
        public TextView DataText;
        public ImageView cardImage;

    }
}