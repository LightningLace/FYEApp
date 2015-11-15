package com.stringclevername.fyeapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class RAListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ralist);
        new MyTask().execute();
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
            ((TextView)findViewById (R.id.myRAList)).setText (result);
        }

        }
    }

