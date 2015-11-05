package com.stringclevername.fyeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * This activity contains all the stuff.
 */
public class SwipeActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);

        // Get the user's name from the intent
        Intent intent = getIntent();
        String name = "Welcome " + intent.getStringExtra(HomeScreen.EXTRA_NAME) + "!";
        setTitle(name);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_swipe, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     *
     * TO ADD A NEW FRAGMENT:
     *
     * 1. Add your fragment as a public static class within the SwipeActivity class.
     * Make sure it extends Fragment and contains a public constructor. Change the onCreate
     * method to onCreateView, with appropriate parameters. See PlaceholderFragment below,
     * and note the rootView variable.
     *
     * 2. In the layout file for your class, change the <code>tools:context</code> attribute
     * to "com.stringclevername.fyeapp.SwipeActivity$YourClassName". You may have to make other
     * changes to the layout to make it work as a fragment, like changing the id (make sure it's
     * not main_content) and the padding. See placeholder_fragment_swipe.xml.
     *
     * 3. In the getItem method add a new case to the switch (or multiple cases if your
     * fragment has multiple pages) that returns a new instance of your fragment
     * (e.g. <code>return new YourFragment()</code>).
     *
     * 4. Change the return value of getCount to match the new number of pages.
     *
     * 5. Add a case to getPageTitle that returns the title for your page.
     *
     * The PlaceholderFragment has been left in to give an example how this
     * all works. Feel free to take it out once you've got the hang of it.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            // The positions determine the order in which the pages are viewed. Starts at 0.
            switch (position) {
                case 0:
                    return PlaceholderFragment.newInstance(position + 1);
                case 1:
                    return PlaceholderFragment.newInstance(position + 1);
                // You can add a case for a new page here, but make sure to change
                // the case numbers to fit.
                case 2:
                    return PlaceholderFragment.newInstance(position + 1);
                // Or just put your new page here as the next case.
            }
                return null; // This should never be returned.
        }

        @Override
        /**
         * Returns the number of pages to show.
         */
        public int getCount() {
            // Show n total pages.
            return 3;
        }

        @Override
        /**
         * Returns the page title.
         */
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.placeholder_fragment_swipe, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }
}
