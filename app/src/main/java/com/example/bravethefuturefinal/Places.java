package com.example.bravethefuturefinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

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


public class Places extends AppCompatActivity {
    static private float mParallaxImageHeight;

    static ArrayList<String> places = new ArrayList<>();
    static ArrayList<String> sub = new ArrayList<>();
    static ArrayList<String> desc = new ArrayList<>();

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_places);

        //Places
        places.add("ACE");
        places.add("SIRC");
        places.add("CARIE");
        places.add("CERL");

        // Subs
        sub.add("AUTOMOTIVE CENTRE OF EXCELLENCE");
        sub.add("SOFTWARE AND INFORMATICS RESEARCH CENTRE");
        sub.add("CENTRE FOR ADVANCED RESEARCH, INNOVATION AND ENTREPRENEURSHIP");
        sub.add("CLEAN ENERGY RESEARCH LABORATORY");

        // Descs
        desc.add("The ACE research and testing facility offers chambers and technology for climatic, structural durability and life-cycle testing. Facilities include one of the largest and most sophisticated climatic wind tunnels (CWT) on the planet. In the CWT, wind speeds can reach 300 kilometres per hour with temperatures that range from -40 to +60°C. With our solar arrays and storm generators we can create any weather conditions imaginable, from sweltering jungle downpours to the paralyzing cold of an arctic storm. We use these chambers to test automotive and aerospace products, to improve the performance of elite athletes and to provide services to many other markets, including the Unmanned Aerial Vehicle industry, film and television, and motorsports.");
        desc.add("The Software and Informatics Research Centre (SIRC) accommodates the university’s need for new spaces for research, study and future active learning.\n" +
                "\n" +
                "Opened in 2017, the four-storey building houses:\n" +
                "\n" +
                "• Flexible learning spaces featuring multiple seating configurations, room divisibility and the newest audio-visual technology.\n" +
                "\n" +
                "• Approximately 280 square metres of student study space.");
        desc.add("CARIE will create a home for the university’s innovation enterprise and partnership mandate. It will be designed to work for faculty members who are involved in collaborative research as well as their undergraduate and graduate students. The facility will bring together talent, services and resources that catalyze R&D and enable the university to support and grow its capacity for research, partnerships, technology transfer and community outreach.\n" +
                "\n" +
                "At CARIE, faculty, students and industry partners will tackle complex industry and societal challenges to develop solutions to real-world problems. The new building will provide space and resources to support fundamental and applied research, an entrepreneurship centre, conference facility and academic and office space.");
        desc.add("Located on the University of Ontario Institute of Technology’s (UOIT) north Oshawa campus, CERL is a world-class facility where researchers are working on the world's first lab-scale demonstration of a copper-chlorine cycle for thermochemical water splitting and nuclear hydrogen production. Using nuclear, solar or other heat sources (such as waste heat from industrial plant emissions), the Cu-Cl cycle promises to achieve higher efficiencies, lower environmental impact and lower costs of hydrogen production than any other existing technology.");


        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
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

        public static Places.PlaceholderFragment newInstance(int sectionNumber) {
            Places.PlaceholderFragment fragment = new Places.PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Instantiates and shows the view.
            View rootView = inflater.inflate(R.layout.fragment_places, container, false);
            mScrollView = (ObservableScrollView) rootView.findViewById(R.id.scroll);
            mScrollView.setScrollViewCallbacks(this);
            mParallaxImageHeight = getResources().getDimension(R.dimen.image_height);
            Integer num = getArguments().getInt(ARG_SECTION_NUMBER)-1;

            TextView place = rootView.findViewById(R.id.places);
            place.setText(places.get(num));

            TextView subs = rootView.findViewById(R.id.sub);
            subs.setText(sub.get(num));

            TextView descs = rootView.findViewById(R.id.desc);
            descs.setText(desc.get(num));

            mImageView = rootView.findViewById(R.id.image);
            num.toString();
            mImageView.setImageResource(getResources().getIdentifier("@drawable/place_image" + num,"drawable",getContext().getPackageName()));

            if (num % 2 == 0) {
                rootView.setBackgroundColor(0xFF003c71);
                place.setTextColor(0xFFFFFFFF);
                subs.setTextColor(0xFFFFCD3A);
                descs.setTextColor(0xFFFFFFFF);

            } else {
                rootView.setBackgroundColor(0xFF211551);
                place.setTextColor(0xFFFFFFFF);
                subs.setTextColor(0xFFFFCD3A);
                descs.setTextColor(0xFFFFFFFF);
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
            return Places.PlaceholderFragment.newInstance(position + 1);
        }
        @Override
        public int getCount() {
            // Total amount of Fragments
            return 4;
        }
    }
}