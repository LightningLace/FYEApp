package com.stringclevername.fyeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * The home screen for the app. Has the user enter a name which is used to address them
 * in the rest of the app, then goes to the main activity.
 *
 * Created by Ariah Lacey
 */
public class HomeScreen extends AppCompatActivity {
    public final static String EXTRA_NAME = "com.stringclevername.fyeapp.MESSAGE";
    public final static String EXTRA_FROMMAIN = null;

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
        intent.putExtra(EXTRA_FROMMAIN, "true");
        startActivity(intent);
    }
}