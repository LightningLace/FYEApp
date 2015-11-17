package com.stringclevername.fyeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class HomeScreen2 extends AppCompatActivity {
    Button fys;
    Button pl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen2);

        //Get student name from Intent
        Intent intent = getIntent();
        String stuName = intent.getStringExtra(HomeScreen.EXTRA_NAME);

        //set action bar title
        //getSupportActionBar().setTitle("Hello, " + stuName);

        // Buttons
        fys = (Button) findViewById(R.id.button1);
        pl = (Button) findViewById(R.id.button2);

        // first-year student click event
        fys.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launch fys Activity
                Intent i = new Intent(getApplicationContext(), fysActivity.class);
                startActivity(i);

            }
        });

        // peer leader click event
        pl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launch peer leader activity
                Intent i = new Intent(getApplicationContext(), plActivity.class);
                startActivity(i);

            }
        });

    }
}

