package com.stringclevername.fyeapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class PoliciesActivity extends Activity implements AdapterView.OnItemSelectedListener {

    Button goButton;
    public int spinValue;
    String myURL = new String();
    String myTags = new String();
    String startRemove = new String();
    String endRemove = new String();
    String midRemove = new String();
    Boolean opensPage = false;
    //Boolean findCopies = false;
    String policyText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policies);
        Spinner spinner = (Spinner) findViewById(R.id.spinnerPolicies);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.policies_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
        goButton = (Button) findViewById(R.id.goButton);
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
            ((TextView) findViewById(R.id.policyDisplay)).setText(policyText);
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
