package com.example.bravethefuturefinal;

import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;

import java.util.ArrayList;

public class Students extends AppCompatActivity {

    static private float mParallaxImageHeight;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    private static ImageView logo;

    static ArrayList <String> StudentNames = new ArrayList<>();
    static ArrayList <String> StudentStories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_students);

        StudentNames.add(getResources().getString(R.string.student0));
        StudentNames.add(getResources().getString(R.string.student1));
        StudentNames.add(getResources().getString(R.string.student2));

        StudentStories.add(getResources().getString(R.string.student_story0));
        StudentStories.add(getResources().getString(R.string.student_story1));
        StudentStories.add(getResources().getString(R.string.student_story2));

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.student_container);
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

        public static Students.PlaceholderFragment newInstance(int sectionNumber) {
            Students.PlaceholderFragment fragment = new Students.PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_students, container, false);
            mScrollView = (ObservableScrollView) rootView.findViewById(R.id.scroll);
            mScrollView.setScrollViewCallbacks(this);
            mParallaxImageHeight = getResources().getDimension(R.dimen.image_height);
            Integer num = getArguments().getInt(ARG_SECTION_NUMBER)-1;

            TextView names = (TextView) rootView.findViewById(R.id.student_name);
            names.setText(StudentNames.get(num));
            TextView stories = rootView.findViewById(R.id.student_stories);
            stories.setText(StudentStories.get(num));

            mImageView = rootView.findViewById(R.id.image);
            num.toString();
            mImageView.setImageResource(getResources().getIdentifier("@drawable/student_image" + num,"drawable",getContext().getPackageName()));

            logo = rootView.findViewById(R.id.logo);
            if (num % 2 == 0) {
                rootView.setBackgroundColor(0xFFFFFFFF);
                logo.setImageResource(R.drawable.logo_small_dark);
                names.setTextColor(0xFF211551);
                stories.setTextColor(0xFF211551);

            } else {
                rootView.setBackgroundColor(0xFF003C71);
                logo.setImageResource(R.drawable.logo_small);
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
            return Students.PlaceholderFragment.newInstance(position + 1);
        }
        @Override
        public int getCount() {
            // Total amount of Fragments
            return 3;
        }
    }
}