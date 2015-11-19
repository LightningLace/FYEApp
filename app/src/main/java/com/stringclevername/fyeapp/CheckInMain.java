package com.stringclevername.fyeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * CheckInMain is a class that serves three purposes:
 *      1. Create the related buttons and direct them to their perspective classes
 *      2. Scan the QR code prsented by the user
 *      3. Direct the user to the webpage that the QR code was made for. The QR code leads
 *         to a Google Form which the student is to fill out, which means that all of the
 *         collected data is saved on a Google Speradsheet.
 *
 * @author Melany Diaz
 * date: November 2015
 * %this is an app build for Mobile Computing 490, Professor Pam Cutter%
 *
 * HOW TO MAINTAIN:
 * Each forum needs three different things made: A Google form, a QR code that leads to
 * that Google Form, and the URL key of that Google form. This class will use that QR read
 * to direct the student to the Google *
 */
public class CheckInMain extends AppCompatActivity {

    Button scanbtn;
    Button connectbtn;
    private WebView mWebView;
    public final static String EXTRA_MESSAGE = "com.stringclevername.fyeapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in_main);

        //get the connect Button
        connectbtn = (Button) findViewById(R.id.connect);
        //set the intent of the Connect button (which is to got to the CheckInForm
        connectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CheckInForm.class);

                //save the User Input (which would be the URL key) and make it available
                //to use in it's perspective class
                EditText editText = (EditText) findViewById(R.id.code);
                String message = editText.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, message);

                startActivity(intent);
            }
        });


        //get the GQ Button
        scanbtn = (Button) findViewById(R.id.scanQR);
        //Set the QR's listener, which will direct the user to the QR Scanner
        scanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //QR Scanner details
                IntentIntegrator integrator = new IntentIntegrator(CheckInMain.this);
                integrator.addExtra("SCAN_WIDTH", 640);
                integrator.addExtra("SCAN_HEIGHT", 480);
                integrator.addExtra("SCAN_MODE", "QR_CODE_MODE,PRODUCT_MODE");
                integrator.addExtra("Scan the Forum QR Code!", "Scanner Start!");
                integrator.initiateScan(IntentIntegrator.QR_CODE_TYPES);

            }

        });

    }

    //The QR code should read a URL to the scan, so after Scanning the QR Code,
    // onActivityResult gets the information recieved (the URL)
    //from the QR Code and appropriately directs it to open the webview
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            //we have a result
            //scanConent gives the content of the QR code. In this case, it should be the URL
            //of the Google Form
            String scanContent = scanningResult.getContents();

            //scanFormat specifies to the scanner what kind of code the scanner read, in
            //this instance we should recieve that the scanner found a QR code
            String scanFormat = scanningResult.getFormatName();

            setContentView(R.layout.activity_check_in_form);
            mWebView = (WebView) findViewById(R.id.webView);

            // Enable Javascript
            WebSettings webSettings = mWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);

            //set the webview to open the URL the code found
            mWebView.loadUrl(scanContent);

        }
        else{
            //if the scan returned blank
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }


    }

}