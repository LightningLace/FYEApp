package com.stringclevername.fyeapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.util.DisplayMetrics;
import android.widget.Button;

import k12md02converter.firstyearforums.R;

public class Info3 extends Activity {

    /**
     * This activity takes the information shown in the corresponding
     * xml file and will shrink it to show as a pop up when the user
     * clicks the button leading to this activity
     *
     * @author Melany Diaz
     * @date November 2015
     * %this is an app build for Mobile Computing 490, Professor Pam Cutter%
     *
     */

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info3);

        //gets the metrics of the activity (before it is shrunk)
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        //using these metrics, this shrinks the width by 20% and the height by 40%
        getWindow().setLayout((int)(width *.8),(int)(height*.6));

        //get the button
        button = (Button) findViewById(R.id.info3);
        //the intent of this button is to take to the user to the next pop-up
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Opening check in activity
                Intent i = new Intent(getApplicationContext(), Info4.class);
                startActivity(i);
            }
        });

    }

}