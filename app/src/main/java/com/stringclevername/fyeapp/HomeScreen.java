
package com.stringclevername.fyeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

public class HomeScreen extends AppCompatActivity {
    public final static String EXTRA_NAME = "com.stringclevername.fyeapp.MESSAGE";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_home_screen);

    }

    /** Called when the user clicks the "Next" button */
    public void login(View view) {
        Intent intent = new Intent(getApplicationContext(), HomeScreen2.class);

        //Get student name to use throughout app
        EditText editText = (EditText) findViewById(R.id.editText);
        String studentName = editText.getText().toString();
        intent.putExtra(EXTRA_NAME, studentName);

        //launch next activity
        startActivity(intent);
    }
}