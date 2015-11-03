package com.stringclevername.fyeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HomeScreen2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get message from Intent
        Intent intent = getIntent();
        String studentName = intent.getStringExtra(HomeScreen.EXTRA_NAME);

        final Toast toast = Toast.makeText(this, "Hello, " + studentName + "!", Toast.LENGTH_SHORT);
        toast.show();

    }

}
