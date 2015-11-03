package com.stringclevername.fyeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class HomeScreen extends AppCompatActivity {
    public final static String EXTRA_NAME = "com.stringclevername.fyeapp.MESSAGE";
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button btnDisplay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        addListenerOnButton();

    }

    public void addListenerOnButton() {

        radioGroup = (RadioGroup) findViewById(R.id.radioStudentType);
        btnDisplay = (Button) findViewById(R.id.button1);

        btnDisplay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);

                Toast.makeText(HomeScreen.this, radioButton.getText(), Toast.LENGTH_SHORT).show();

            }

        });

    }

    /** Called when the user clicks the Go! button */
    public void login(View view) {
        Intent intent = new Intent(this, HomeScreen2.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String studentName = editText.getText().toString();
        intent.putExtra(EXTRA_NAME, studentName);
        startActivity(intent);
    }
}