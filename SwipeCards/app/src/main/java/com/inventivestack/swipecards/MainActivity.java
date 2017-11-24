package com.inventivestack.swipecards;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.inventivestack.swipecards.adapter.SwipeCardAdapter;
import com.inventivestack.swipecards.model.Data;
import com.inventivestack.swipecards.widget.SwipeCardView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static SwipeCardAdapter mAdapter;
    private ArrayList<Data> al;
    private SwipeCardView swipeCardView;

    public static void removeBackground() {
        mAdapter.viewHolder.background.setVisibility(View.GONE);
        mAdapter.notifyDataSetChanged();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeCardView = (SwipeCardView) findViewById(R.id.frame);

        al = new ArrayList<>();
        al.add(new Data("http://thewallpaper.co/wp-content/uploads/2016/10/Android-Wallpaper-Size-Free-Download-download-desktop-wallpapers-amazing-mac-desktop-wallpapers-4k-hd-pictures-smart-phone-1920x1200-768x480.jpg", "Description goes here..."));
        al.add(new Data("https://hdwallsource.com/img/2014/1/best-android-backgrounds-18911-19399-hd-wallpapers.jpg", "Description goes here..."));
        al.add(new Data("http://thewallpaper.co/wp-content/uploads/2016/03/best-wallpaper-picture-jvxr-hd-artworks-art-background-images-android-wallpapers-2406x1504-768x480.jpg", "Description goes here..."));
        al.add(new Data("http://4hdwallpapers.com/wp-content/uploads/2013/04/Android-Tablet-Wallpaper.jpg", "Description goes here..."));
        al.add(new Data("http://www.phtheme.com/androidimg/allimg/150910/106_150910133321_1.jpeg", ""));
        al.add(new Data("http://www.mulierchile.com/android-home-screen-wallpaper/android-home-screen-wallpaper-017.jpg", "Description goes here..."));

        mAdapter = new SwipeCardAdapter(al, this);
        swipeCardView.setAdapter(mAdapter);
        swipeCardView.setFlingListener(new SwipeCardView.onCardListener() {
            @Override
            public void removeFirstObjectInAdapter() {

            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                al.remove(0);
                mAdapter.notifyDataSetChanged();
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Toast.makeText(MainActivity.this, "Dislike", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {

                al.remove(0);
                mAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Like", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {

            }

            @Override
            public void onScroll(float scrollProgressPercent) {

                View view = swipeCardView.getSelectedView();
                view.findViewById(R.id.background).setAlpha(0);
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });


        // Optionally add an OnItemClickListener
        swipeCardView.setOnItemClickListener(new SwipeCardView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {

                View view = swipeCardView.getSelectedView();
                view.findViewById(R.id.background).setAlpha(0);

                mAdapter.notifyDataSetChanged();
            }
        });
    }
}
