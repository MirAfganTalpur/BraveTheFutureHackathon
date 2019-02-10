package com.example.bravethefuturefinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;

import java.util.ArrayList;

public class Talents extends AppCompatActivity {
    static private float mParallaxImageHeight;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    private static ImageView image;

    static ArrayList <String> ProfNames = new ArrayList<>();
    static ArrayList <String> ProfStories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_talents);

        ProfNames.add(getResources().getString(R.string.prof0));
        ProfNames.add(getResources().getString(R.string.prof1));
        ProfNames.add(getResources().getString(R.string.prof2));

        ProfStories.add(getResources().getString(R.string.prof_story0));
        ProfStories.add(getResources().getString(R.string.prof_story1));
        ProfStories.add(getResources().getString(R.string.prof_story2));

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.prof_container);
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

        public static Talents.PlaceholderFragment newInstance(int sectionNumber) {
            Talents.PlaceholderFragment fragment = new Talents.PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_talents, container, false);
            mScrollView = (ObservableScrollView) rootView.findViewById(R.id.scroll);
            mScrollView.setScrollViewCallbacks(this);
            mParallaxImageHeight = getResources().getDimension(R.dimen.image_height);
            Integer num = getArguments().getInt(ARG_SECTION_NUMBER)-1;

            TextView names = (TextView) rootView.findViewById(R.id.prof_name);
            names.setText(ProfNames.get(num));
            TextView stories = rootView.findViewById(R.id.prof_story);
            stories.setText(ProfStories.get(num));


            mImageView = rootView.findViewById(R.id.image);
            num.toString();
            mImageView.setImageResource(getResources().getIdentifier("@drawable/prof_image" + num,"drawable",getContext().getPackageName()));

            if (num % 2 == 0) {
                rootView.setBackgroundColor(0xFF41B6E6);
                names.setTextColor(0xFF211551);
                stories.setTextColor(0xFF211551);
            } else {
                rootView.setBackgroundColor(0xFF0077CA);
                names.setTextColor(0xFFFFFFFF);
                stories.setTextColor(0xFFFFFFFF);
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
            return Talents.PlaceholderFragment.newInstance(position + 1);
        }
        @Override
        public int getCount() {
            // Total amount of Fragments
            return 3;
        }
    }
}