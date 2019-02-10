package com.example.bravethefuturefinal;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
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

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;

public class MainActivity extends AppCompatActivity {

    static private float mParallaxImageHeight;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    static public ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(1);

    }

    public void toHome (View view){
        mViewPager.setCurrentItem(1);
    }

    public void toAbout(View view){
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    public void toEvents(View view){
        Intent intent = new Intent(this, Events.class);
        startActivity(intent);
    }

    public void toDonate(View view) {

        String url = "https://uoit.ca/payment_gateways/advancement/donations/index.php";

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

    }

    public void toContact(View view) {

        String url = "https://bravethefuture.uoit.ca/contact/index.php";

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

    }

    public void toStudents(View view){
        Intent intent = new Intent(this, Students.class);
        startActivity(intent);
    }

    public void toTalents(View view){
        Intent intent = new Intent(this, Talents.class);
        startActivity(intent);
    }

    public void toPlaces(View view){
        Intent intent = new Intent(this, Places.class);
        startActivity(intent);
    }

    public void toResearch(View view){
        Intent intent = new Intent(this, Research.class);
        startActivity(intent);
    }

    public static class PlaceholderFragment extends Fragment implements ObservableScrollViewCallbacks {

        private ImageView mImageView, mImage2, mImage3;
        private ObservableScrollView mScrollView;
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {}

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View rootView = null;

            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 1:
                    rootView = inflater.inflate(R.layout.fragment_nav, container, false);
                    break;
                case 2:
                    rootView = inflater.inflate(R.layout.fragment_main, container, false);
                    mScrollView = (ObservableScrollView) rootView.findViewById(R.id.scroll);
                    mScrollView.setScrollViewCallbacks(this);
                    mParallaxImageHeight = getResources().getDimension(R.dimen.parallax_img_height);

                    mImageView = rootView.findViewById(R.id.splashImage);
                    mImageView.animate().alpha(1f).setDuration(2000);

                    // Animation Code: Adds two more image views in fragment main.
//                    mImage2 = rootView.findViewById(R.id.splashImage1);
//                    mImage2.animate().alpha(1f).setDuration(2000);
//
//                    mImage3 = rootView.findViewById(R.id.splashImage2);
//                    mImage3.animate().alpha(1f).setDuration(2000);
//
//                    ValueAnimator vAnimator = ObjectAnimator.ofFloat(mImage2, "y", -100f);
//                    ValueAnimator vAnimator2 = ObjectAnimator.ofFloat(mImage2, "x", 100f);
//                    AnimatorSet set = new AnimatorSet();
//                    set.playTogether(vAnimator,vAnimator2);
//                    set.setStartDelay(2000);
//
//                    set.setDuration(2000);
//                    set.start();
//
//                    set.setStartDelay(2000);
//                    set.reverse();

                    break;
                case 3:
                    rootView = inflater.inflate(R.layout.fragment_priorities, container, false);
                    break;
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
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
