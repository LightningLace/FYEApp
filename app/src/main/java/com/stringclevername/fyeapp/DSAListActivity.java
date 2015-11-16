package com.stringclevername.fyeapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

public class DSAListActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button goButton;
    Button infoButton;
    int spinValue;
    String myURL = new String();
    String DSAText = new String();
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsalist);

        Spinner spinner = (Spinner) findViewById(R.id.spinnerDSA);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.dsa_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        //Sets up the More Info Button to take you to the web page of the DSA
        infoButton = (Button) findViewById(R.id.moreInfoButton);
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                myURL = urls[spinValue];
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(myURL));
                startActivity(i);
            }
        });

        goButton = (Button) findViewById(R.id.goButton2);

        goButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                myURL = urls[spinValue];
                new MyTask().execute();
            }
        });
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
            ((TextView) findViewById(R.id.textViewDSA)).setText(DSAText);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinValue = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
