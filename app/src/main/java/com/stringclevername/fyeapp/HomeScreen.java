package com.stringclevername.fyeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class HomeScreen extends AppCompatActivity {
    public final static String EXTRA_NAME = "com.stringclevername.fyeapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }

    /** Called when the user clicks the Go! button */
    public void login1(View view) {
        Intent intent = new Intent(this, SwipeActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String studentName = editText.getText().toString();
        intent.putExtra(EXTRA_NAME, studentName);
        startActivity(intent);
    }
}