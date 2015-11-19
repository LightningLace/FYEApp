package com.stringclevername.fyeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * CheckInForm is an activity build to direct the user to the webpage that the
 * URL code was made for. The URL is a Google Form which the student is to fill out, which
 * means that all of the collected data is saved on a Google Speradsheet.
 *
 *
 * @author Melany Diaz
 * date: November 2015
 * %this is an app build for Mobile Computing 490, Professor Pam Cutter%
 *
 * HOW TO MAINTAIN:
 * Each forum needs three different things made: A Google form, a QR code that leads to
 * that Google Form, and the URL key of that Google form. This class will use
 * the URL key to direct the student to the Google Form
 *
 * The Google Form I have used for my examples can be found at http://goo.gl/forms/NgIfE7SOxD
 * (NgIfE7SOxD is the student URL key)
 *
 */
public class CheckInForm extends AppCompatActivity {

    private WebView mWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in_form);

        //gets the webView Widget
        mWebView = (WebView) findViewById(R.id.webView);

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Get the message from the intent (the URL key that the student typed)
        Intent intent = getIntent();
        String message = intent.getStringExtra(CheckInMain.EXTRA_MESSAGE);

        String URLBase = "http://goo.gl/forms/";

        //make the completed URL by adding the URL key to the URL base
        String URL = URLBase + message;


        //load the URL
        mWebView.loadUrl(URL);
    }

}