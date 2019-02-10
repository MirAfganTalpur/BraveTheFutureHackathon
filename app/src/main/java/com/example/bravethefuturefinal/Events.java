package com.example.bravethefuturefinal;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;

import java.util.ArrayList;

import static java.lang.Double.SIZE;

public class Events extends AppCompatActivity {

    static private float mParallaxImageHeight;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    static ArrayList <String> EventName = new ArrayList<>();
    static ArrayList <String> EventDescription = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_events);

        EventName.add(getResources().getString(R.string.event0));
        EventName.add(getResources().getString(R.string.event1));
        EventName.add(getResources().getString(R.string.event2));

        EventDescription.add(getResources().getString(R.string.eventdesc0));
        EventDescription.add(getResources().getString(R.string.eventdesc1));
        EventDescription.add(getResources().getString(R.string.eventdesc2));

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.events_container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    public void home(View view){
        Intent myIntent = new Intent(this, MainActivity.class);
        this.startActivity(myIntent);
    }


    public static class PlaceholderFragment extends Fragment implements ObservableScrollViewCallbacks {
        private ImageView mImageView;
        private ObservableScrollView mScrollView;

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_events, container, false);
            mScrollView = (ObservableScrollView) rootView.findViewById(R.id.scroll);
            mScrollView.setScrollViewCallbacks(this);
            mParallaxImageHeight = getResources().getDimension(R.dimen.image_height);
            Integer num = getArguments().getInt(ARG_SECTION_NUMBER)-1;

            TextView title = (TextView) rootView.findViewById(R.id.event_title);
            title.setText(EventName.get(num));
            TextView description = rootView.findViewById(R.id.event_descriptions);
            description.setText(EventDescription.get(num));

            mImageView = rootView.findViewById(R.id.image);
            num.toString();
            mImageView.setImageResource(getResources().getIdentifier("@drawable/event_image" + num,"drawable",getContext().getPackageName()));
            Button live = rootView.findViewById(R.id.live_button);
            live.setVisibility(View.INVISIBLE);/*
            if (num!=0) {

            }*/
            if (num % 2 == 0) {
                rootView.setBackgroundColor(0xFFC71566);

            } else {
                rootView.setBackgroundColor(0xFF211551);

            }
            return rootView;
        }

        @Override
        public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
            float alpha = Math.min(1, (float) scrollY / mParallaxImageHeight);
            mImageView.setTranslationY(scrollY / 2);
        }
        @Override
        public void onDownMotionEvent() {
        }
        @Override
        public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }
        @Override
        public int getCount() {
            // Total amount of Fragments
            return 3;
        }
    }
}
