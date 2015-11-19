package com.stringclevername.fyeapp;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

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

        if ((intent.getStringExtra(Info5.EXTRA_FRAG)).equals("fyfFrag")){
            mViewPager.setCurrentItem(5); // sets the current item to the FYF fragment if coming from FYF-related activities
        }
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
     * The PlaceholderFragment has been left in (though commented) to give an example how this
     * all works.
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
                /*case 0:
                    return PlaceholderFragment.newInstance(position + 1);
                case 1:
                    return PlaceholderFragment.newInstance(position + 1);
                // You can add a case for a new page here, but make sure to change
                // the case numbers to fit.
                case 2:
                    return PlaceholderFragment.newInstance(position + 1);
                // Or just put your new page here as the next case.
                */
                case 0:
                    return new scheduleActivity();
                case 1:
                    return new RAListActivity();
                case 2:
                    return new DSAListActivity();
                case 3:
                    return new PoliciesActivity();
                case 4:
                    return new FYFFirstScreen(); // if moved, remember to change "fyfFrag" behavior

            }
                return null; // This should never be returned.
        }

        @Override
        /**
         * Returns the number of pages to show.
         */
        public int getCount() {
            // Show n total pages.
            return 5;
        }

        @Override
        /**
         * Returns the page title.
         */
        public CharSequence getPageTitle(int position) {
            switch (position) {
                /*case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
                    */
                case 0:
                    return "Schedule";
                case 1:
                    return "RA List";
                case 2:
                    return "DSA List";
                case 3:
                    return "Policies";
                case 4:
                    return "FYF Check-In";
            }
            return null;
        }
    }

    /*
     * A placeholder fragment containing a simple view.
     */
    // public static class PlaceholderFragment extends Fragment {
        /*
         * The fragment argument representing the section number for this
         * fragment.
         */
        //private static final String ARG_SECTION_NUMBER = "section_number";

        /*
         * Returns a new instance of this fragment for the given section
         * number.
         */
       /* public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }*/

       // public PlaceholderFragment() {
        //}

       /* @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.placeholder_fragment_swipe, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }*/
    //}

    /**
     * Displays a schedule for the work, allowing user to select a day for more detail.
     * [INCOMPLETE: does not connect to a schedule yet.]
     *
     * @author Ariah Lacey
     *
     * Modified by Jordan Meiller  11/17/15
     *          Modified to work as a Fragment. Added documentation.
     *
     */
    public static class scheduleActivity extends Fragment {

        ListView listView ;
        View rootView;

        public scheduleActivity(){}

        @Override
        public View onCreateView(LayoutInflater inflator, ViewGroup container, Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            rootView = inflator.inflate(R.layout.content_fys, container, false);

            // Get ListView object from xml
            listView = (ListView) rootView.findViewById(R.id.list);

            // static values to show in ListView
            String[] values = new String[] {
                    "Tuesday: ",
                    "Wednesday: ",
                    "Thursday: ",
                    "Friday: ",
                    "Saturday: ",
                    "Sunday: ",
            };

            // Define a new Adapter
            // First parameter - Context
            // Second parameter - Layout for the row
            // Third parameter - ID of the TextView to which the data is written
            // Fourth - the Array of data

            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_list_item_1, values);


            // Assign adapter to ListView
            listView.setAdapter(adapter);

            // ListView Item Click Listener
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    // ListView Clicked item value
                    String itemValue = (String) listView.getItemAtPosition(position);

                    // Show Alert
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Position :" + position + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                            .show();
                }
            });

            return rootView;
        }
    }

    /**
     * A fragment for displaying the list of RAs.
     *
     * Created by Noah Getz on
     *
     * Modifed by Jordan Meiller on 11/15/12
     *          Modified to use as a Fragment instead of an Activity
     *
     */
    public static class RAListActivity extends Fragment {

        View rootView;

        public RAListActivity(){}

        @Override
        public View onCreateView(LayoutInflater inflator, ViewGroup container, Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            rootView = inflator.inflate(R.layout.activity_ralist, container, false);
            new MyTask().execute();
            return rootView;
        }

        private class MyTask extends AsyncTask<Void, Void, String> {

            @Override
            protected String doInBackground(Void... params) {
                String hallInfo = "Not connected to internet, connect to the internet to get the RA List";
                String  hallInfo2 = "";
                try {
                    Document doc = Jsoup.connect("https://reason.kzoo.edu/reslife/staff/").get();
                    hallInfo = doc.select("h3, p").text();
                    hallInfo = hallInfo.substring(64);
                    hallInfo = hallInfo.replace("Text Only/ Printer-Friendly Login Sitemap Map and Directions Contacts Directories Nondiscrimination Policy Consumer Information Official Disclaimer Search this site ", "");


                    char previous = 'x';
                    boolean dontCopy = false;

                    //This loop cleans up the string from the Webscraped document to make it more organized

                    for (int i = 0; i < hallInfo.length(); i++){
                        char c = hallInfo.charAt(i);

                        if(dontCopy && !Character.isLetter(c)){
                            //This does nothing, but skips any blank spaces (tabs, extra spaces, returns, ect) that somehow wouldnt remove through other loops
                        }
                        else{
                            hallInfo2 += c;
                            dontCopy = false;
                        }

                        if(i > 5 && !dontCopy && (hallInfo.substring(i-3,i+1).equals("Hall") || hallInfo.substring(i-5,i+1).equals("Houses"))){
                            hallInfo2 += ":" + '\n';
                            dontCopy = true;
                        }

                        if(!dontCopy && (previous == 'R' && c == 'A')){
                            hallInfo2 += '\n';
                            dontCopy = true;
                        }

                        previous = c;
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    hallInfo2 = hallInfo;
                }

                return hallInfo2;
            }

            @Override
            protected void onPostExecute(String result) {
                ((TextView)rootView.findViewById(R.id.myRAList)).setText (result);
            }

        }
    }

    /**
     * A fragment for displaying or linking to College policies.
     *
     * Created by Noah Getz on
     *
     * Modifed by Jordan Meiller on 11/15/12
     *          Modified to use as a Fragment instead of an Activity
     *
     */
    public static class PoliciesActivity extends Fragment implements AdapterView.OnItemSelectedListener {

        Button goButton;
        public int spinValue;
        String myURL = "";
        String myTags = "";
        String startRemove = "";
        String endRemove = "";
        String midRemove = "";
        Boolean opensPage = false;
        //Boolean findCopies = false;
        String policyText;
        View rootView;

        public PoliciesActivity(){}


        @Override
        public View onCreateView(LayoutInflater inflator, ViewGroup container, Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            rootView = inflator.inflate(R.layout.activity_policies, container, false);
            Spinner spinner = (Spinner) rootView.findViewById(R.id.spinnerPolicies);
            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                    R.array.policies_array, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            spinner.setPrompt("Choose a policy...");

            spinner.setAdapter(new NothingSelectedSpinnerAdapter(adapter, R.layout.contact_spinner_row_nothing_selected, getContext()));

            spinner.setOnItemSelectedListener(this);
            goButton = (Button) rootView.findViewById(R.id.goButton);
            //Ignore the commented out webview methods, I'm only leaving them for referecne
            //final WebView webview = (WebView) findViewById(R.id.webView);
            goButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    //setContentView(webview);

                    //Depending on what you pick on the spinner, it gets info from a different website with student policies
                    //startRemove and endRemove are substrings that may be found on the pages that are not needed for explaining the policies
                    //Some policies link to many others or are too complex to webscrape into organized text which we use opensPage for
                    //opensPage takes you to the webpage of the policy itself on the mobile device's browser
                    if(spinValue == 0){
                        //webview.loadUrl("https://reason.kzoo.edu/studev/policies/dishonest/?textonly=1");
                        myURL = "https://reason.kzoo.edu/studev/policies/dishonest/?";
                        opensPage = true;
                    }
                    else if(spinValue == 1){
                        //webview.loadUrl("https://reason.kzoo.edu/security/policies/alcohol/?textonly=1");
                        myURL = "https://reason.kzoo.edu/studev/policies/alcohol/?";
                        myTags = "p, li";
                        startRemove = "General Information ";
                        endRemove = "Policy Interpretation";
                    }
                    else if(spinValue == 2){
                        //webview.loadUrl("https://reason.kzoo.edu/security/policies/bikepolicy/?textonly=1");
                        myURL = "http://reason.kzoo.edu/security/policies/bikepolicy/";
                        myTags = "p";
                        endRemove = "Text Only/ Printer-Friendly";
                    }
                    else if(spinValue == 3){
                        //webview.loadUrl("https://reason.kzoo.edu/studev/policies/classbehave/?textonly=1");
                        myURL = "https://reason.kzoo.edu/studev/policies/classbehave/?";
                        myTags = "p, ul";
                        endRemove = "Acknowledgements";
                    }
                    else if(spinValue == 4){
                        //webview.loadUrl("https://reason.kzoo.edu/is/policies/?textonly=1");
                        myURL = "https://reason.kzoo.edu/is/policies/?";
                        opensPage = true;
                    }
                    else if(spinValue == 5){
                        //webview.loadUrl("https://reason.kzoo.edu/studev/policies/drug/?textonly=1");
                        myURL = "https://reason.kzoo.edu/studev/policies/drug/?";
                        myTags = "p";
                        endRemove = "Text Only/ Printer-Friendly";
                    }
                    else if(spinValue == 6){
                        //webview.loadUrl("https://reason.kzoo.edu/studev/policies/fire/?textonly=1");
                        myURL = "https://reason.kzoo.edu/studev/policies/fire/?";
                        opensPage = true;
                    }
                    else if(spinValue == 7){
                        //webview.loadUrl("https://reason.kzoo.edu/studev/policies/freedom/?textonly=1");
                        myURL = "https://reason.kzoo.edu/studev/policies/freedom/?";
                        myTags = "p, ul";
                        endRemove = "Stu Dev Home Honor System Conduct Departments Staff Calendar Resources Policies";
                        midRemove = " (See Honor System.)";
                    }
                    else if(spinValue == 8){
                        //webview.loadUrl("https://reason.kzoo.edu/hr/policies/?textonly=1");
                        myURL = "https://reason.kzoo.edu/studev/policies/hr/?";
                        opensPage = true;
                    }
                    else if(spinValue == 9){
                        //webview.loadUrl("https://reason.kzoo.edu/security/idkey/?textonly=1");
                        myURL = "http://reason.kzoo.edu/security/idkey/";
                        myTags = "p, ul";
                        endRemove = "Campus Security Home Facilities Policies Report Crimes ID and Key Cards Alcohol/Drug Student Responsibility Warning System Employee Parking and Registration Agreement";
                    }
                    else if(spinValue == 10){
                        //webview.loadUrl("https://reason.kzoo.edu/studev/policies/parental/?textonly=1");
                        myURL = "https://reason.kzoo.edu/studev/policies/parental/?";
                        myTags = "p, ul";
                        endRemove = "Stu Dev Home Honor System Conduct Departments Staff Calendar Resources Policies";
                    }
                    else if(spinValue == 11){
                        //webview.loadUrl("https://reason.kzoo.edu/studev/policies/posting1/?textonly=1");
                        myURL = "https://reason.kzoo.edu/studev/policies/posting1/?";
                        myTags = "p, ul";
                        startRemove = "Social Policies and Regulations: Posting of Signs  ";
                        endRemove = "Stu Dev Home Honor System Conduct Departments Staff Calendar Resources Policies";
                    }
                    else if(spinValue == 12){
                        //webview.loadUrl("https://reason.kzoo.edu/reslife/policies/?textonly=1");
                        myURL = "https://reason.kzoo.edu/reslife/policies/?";
                        opensPage = true;
                    }
                    else if(spinValue == 13){
                        //webview.loadUrl("https://reason.kzoo.edu/studev/policies/sexmisconduct/?textonly=1");
                        myURL = "https://reason.kzoo.edu/studev/policies/sexmisconduct/?";
                        myTags = "p, ul";
                        endRemove = "Stu Dev Home Honor System Conduct Departments Staff Calendar Resources Policies";
                    }
                    else if(spinValue == 14){
                        //webview.loadUrl("https://reason.kzoo.edu/studev/policies/smoking/?textonly=1");
                        myURL = "https://reason.kzoo.edu/studev/policies/smoking/?";
                        myTags = "p";
                        endRemove = "Text Only/ Printer-Friendly";
                    }
                    else if(spinValue == 15){
                        //webview.loadUrl("https://reason.kzoo.edu/studev/policies/ssn/?textonly=1");
                        myURL = "https://reason.kzoo.edu/studev/policies/ssn/?";
                        myTags = "p, ul";
                        endRemove = "Stu Dev Home Honor System Conduct Departments Staff Calendar Resources Policies";
                    }
                    else if(spinValue == 16){
                        //webview.loadUrl("https://reason.kzoo.edu/studev/policies/solicitation/?textonly=1");
                        myURL = "https://reason.kzoo.edu/studev/policies/solicitation/?";
                        myTags = "p";
                        endRemove = "Text Only/ Printer-Friendly";
                    }
                    else {
                        //webview.loadUrl("https://reason.kzoo.edu/studev/policies/weapons/?textonly=1");
                        myURL = "https://reason.kzoo.edu/studev/policies/weapons/?";
                        myTags = "p, ul";
                        endRemove = "Stu Dev Home Honor System Conduct Departments Staff Calendar Resources Policies";
                    }

                    //Where the URL is either used to take you to the website or get the policy on the textview
                    if(opensPage){
                        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(myURL));
                        startActivity(i);
                    }
                    else{
                        new MyTask().execute();
                    }

                    opensPage = false;
                }
            });

            return rootView;
        }

        private class MyTask extends AsyncTask<Void, Void, String> {

            @Override
            protected String doInBackground(Void... params) {
                policyText = "Error getting policies! Are you connected to the internet?";
                try {
                    Document doc = Jsoup.connect(myURL).get();
                    policyText = doc.select(myTags).text();
                    policyText = policyText.substring(27); //Gets rid of uneeded text at the beginning of the string (so does the next line)
                    policyText = policyText.replace(startRemove, "");
                    policyText = policyText.replace(midRemove, ""); //Gets rid of uneeded text somewhere
                    //Removes uneeded text at/after the endRemove substring of the imported doc after cutting off uneeded beginning string which may be equivalent
                    policyText = policyText.substring(1, policyText.lastIndexOf(endRemove) - 1);
                    System.out.println(policyText);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return policyText;
            }

            @Override
            protected void onPostExecute(String result) {
                ((TextView) rootView.findViewById(R.id.policyDisplay)).setText(policyText);
            }
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            spinValue = position-1;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }


    }

    public static class DSAListActivity extends Fragment implements AdapterView.OnItemSelectedListener {

        Button goButton;
        Button infoButton;
        int spinValue;
        String myURL = "";
        String DSAText = "";
        String[] majors = {"African Studies", "American Studies", "Anthropology and Sociology", "Art History", "Biochemistry and Molecular Biology", "Biological Physics", "Biology",
                "Business", "Chemistry", "Chinese", "Classics", "Community and Global Health", "Computer Science", "Critical Theory", "East Asian Studies", "Economics", "3/2 Engineering", "English",
                "Environmental Studies", "French", "German", "History", "International Area Studies", "Japanese", "Jewish Studies", "Math", "Media Studies", "Music", "Neuroscience", "Philosophy",
                "Physics", "Political Science", "Psychology", "Public Policy and Urban Affairs", "Religion", "Spanish", "Art", "Theatre Arts", "Women, Gender & Sexuality"};

        String[] urls = {"https://reason.kzoo.edu/advising/dsas/african_studies/", "https://reason.kzoo.edu/advising/dsas/american_studies/", "https://reason.kzoo.edu/advising/dsas/anso/",
                "https://reason.kzoo.edu/advising/dsas/art_history/", "https://reason.kzoo.edu/advising/dsas/biochem/", "https://reason.kzoo.edu/advising/dsas/biological_physics/",
                "https://reason.kzoo.edu/advising/dsas/bio/", "https://reason.kzoo.edu/advising/dsas/business/", "https://reason.kzoo.edu/advising/dsas/chem/",
                "https://reason.kzoo.edu/advising/dsas/chinese/", "https://reason.kzoo.edu/advising/dsas/classics/", "https://reason.kzoo.edu/advising/dsas/cgh/",
                "https://reason.kzoo.edu/advising/dsas/computer_sci/", "https://reason.kzoo.edu/advising/dsas/critical_theory/", "https://reason.kzoo.edu/advising/dsas/east_asian_studies/",
                "https://reason.kzoo.edu/advising/dsas/economics/", "https://reason.kzoo.edu/advising/dsas/32_engineering/", "https://reason.kzoo.edu/advising/dsas/english/",
                "https://reason.kzoo.edu/advising/dsas/enviro_studies/", "https://reason.kzoo.edu/advising/dsas/french/", "https://reason.kzoo.edu/advising/dsas/german/",
                "https://reason.kzoo.edu/advising/dsas/history/", "https://reason.kzoo.edu/advising/dsas/ias/", "https://reason.kzoo.edu/advising/dsas/japanese/",
                "https://reason.kzoo.edu/advising/dsas/jewish_studies/", "https://reason.kzoo.edu/advising/dsas/math/", "https://reason.kzoo.edu/advising/dsas/media_studies/",
                "https://reason.kzoo.edu/advising/dsas/music/", "https://reason.kzoo.edu/advising/dsas/neuroscience/", "https://reason.kzoo.edu/advising/dsas/philosophy/",
                "https://reason.kzoo.edu/advising/dsas/physics/", "https://reason.kzoo.edu/advising/dsas/poli_sci/", "https://reason.kzoo.edu/advising/dsas/psychology/",
                "https://reason.kzoo.edu/advising/dsas/ppua/", "https://reason.kzoo.edu/advising/dsas/religion/", "https://reason.kzoo.edu/advising/dsas/spanish/", "https://reason.kzoo.edu/advising/dsas/art/",
                "https://reason.kzoo.edu/advising/dsas/theatre/", "https://reason.kzoo.edu/advising/dsas/womens_studies/"};
        View rootView;


        public DSAListActivity(){}

        @Override
        public View onCreateView(LayoutInflater inflator, ViewGroup container, Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            rootView= inflator.inflate(R.layout.activity_dsalist, container, false);

            Spinner spinner = (Spinner) rootView.findViewById(R.id.spinnerDSA);
            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                    R.array.dsa_array, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinner.setPrompt("Choose a department...");
            // Apply the adapter to the spinner
            spinner.setAdapter(new NothingSelectedSpinnerAdapter(adapter, R.layout.contact_spinner_row_nothing_selected2, getContext()));

            spinner.setOnItemSelectedListener(this);

            //Sets up the More Info Button to take you to the web page of the DSA
            infoButton = (Button) rootView.findViewById(R.id.moreInfoButton);
            infoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    myURL = urls[spinValue];
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(myURL));
                    startActivity(i);
                }
            });

            goButton = (Button) rootView.findViewById(R.id.goButton2);

            goButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    myURL = urls[spinValue];
                    new MyTask().execute();
                }
            });
            return rootView;
        }

        private class MyTask extends AsyncTask<Void, Void, String> {

            @Override
            protected String doInBackground(Void... params) {
                DSAText = "Error getting DSA information! Are you connected to the internet?";
                try {
                    Document doc = Jsoup.connect(myURL).get();
                    DSAText = doc.select("div.pagetitle, div.contentmain").text();
                    DSAText = DSAText.replace(" DSA Spotlights", "");
                    System.out.println(DSAText);
                    String[] info = {"Email:", "E-mail:", "Hometown:", "Major", "Minor", "Concentration", "Study Abroad:", "Favorite Ice Cream Flavor:", "Best Adjective to Describe You:", "In 10 words or less, why should a student want to be involved in this department?"};

                    //After getting the text from the URL, this loop organizes it by adding new lines in between each point
                    DSAText = DSAText.substring(0, DSAText.indexOf(majors[spinValue])) + '\n' + DSAText.substring(DSAText.indexOf(majors[spinValue]), DSAText.length());

                    for(int i =0; i < 10; i++){
                        if(i == 9){
                            DSAText = DSAText.substring(0, DSAText.indexOf(info[i]));
                        }
                        else if(DSAText.contains(info[i])){
                            DSAText = DSAText.substring(0, DSAText.indexOf(info[i])) + '\n' + DSAText.substring(DSAText.indexOf(info[i]), DSAText.length());
                        }
                    }


                    System.out.println(DSAText);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return DSAText;
            }

            @Override
            protected void onPostExecute(String result) {
                ((TextView) rootView.findViewById(R.id.textViewDSA)).setText(DSAText);
            }
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            spinValue = position-1;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    /**
     *
     * This is the main screen and activity of the forums app. It is organized into 3
     * different parts
     *  1.Forum check in: a button that will direct the user to the forum check-in activity
     *  2. Forum information: A button that will lead to a series of pop-up explaining
     *      to the user what a forum is and how many they need to go to
     *  3. Forum shcedule: This also doubles as the title of the app. This portion consists o
     *      of the title, and 5 speech bubble shaped buttons, each button will direct the user to
     *      the according group and display that groups forum schedule
     *
     * @author Melany Diaz
     * date: November 2015
     * %this is an app build for Mobile Computing 490, Professor Pam Cutter%
     *
     * Modified by Jordan Meiller  11/18/15
     *          Modified to work as a Fragment
     */
    public static class FYFFirstScreen extends Fragment{

        Button checkInBtn;
        Button forumInfoBtn;
        ImageButton group1;
        ImageButton group2;
        ImageButton group3;
        ImageButton group4;
        View rootView;

        public FYFFirstScreen(){}

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            rootView = inflater.inflate(R.layout.activity_fyf_first_screen, container, false);


            /*
             * The following lines of code will get and declare the 7 buttons that
             * are in this activity
             */
            //Check-in
            checkInBtn= (Button) rootView.findViewById(R.id.checkInButton);

            //Forum Information
            forumInfoBtn = (Button) rootView.findViewById(R.id.forumButton);


            //Each group button
            group1 = (ImageButton) rootView.findViewById(R.id.group1);
            group2 = (ImageButton) rootView.findViewById(R.id.group2);
            group3 = (ImageButton) rootView.findViewById(R.id.group3);
            group4 = (ImageButton) rootView.findViewById(R.id.group4);



            /*
             * The following will implement the listeners for each of the seven buttons
             * each listener directs the user to the according following activity
             */

            //Check-In
            checkInBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Opening check in activity
                    Intent i = new Intent(getActivity().getApplicationContext(), CheckInMain.class);
                    startActivity(i);
                }
            });


            //Forum Information
            forumInfoBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Opening forum popuips
                    Intent i = new Intent(getActivity().getApplicationContext(), Info1.class);
                    startActivity(i);
                }
            });

            //Group1
            group1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getActivity().getApplicationContext(), Group1.class);
                    startActivity(i);
                }
            });

            //Group2
            group2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getActivity().getApplicationContext(), Group2.class);
                    startActivity(i);
                }
            });

            //Group3
            group3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getActivity().getApplicationContext(), Group3.class);
                    startActivity(i);
                }
            });

            //Group4
            group4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getActivity().getApplicationContext(), Group4.class);
                    startActivity(i);
                }
            });

            return rootView;
        }

    }
}