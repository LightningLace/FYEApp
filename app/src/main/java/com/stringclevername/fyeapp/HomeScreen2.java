package com.stringclevername.fyeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class HomeScreen2 extends AppCompatActivity {

    private static String studentName;
    public final static String EXTRA_NAME = "com.stringclevername.fyeapp.MESSAGE";
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button btnDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get message from Intent
        Intent intent = getIntent();
        studentName = intent.getStringExtra(HomeScreen.EXTRA_NAME);

        final Toast toast = Toast.makeText(this, "Hello, " + studentName + "!", Toast.LENGTH_SHORT);
        toast.show();

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

                Toast.makeText(HomeScreen2.this, radioButton.getText(), Toast.LENGTH_SHORT).show();

            }

        });

    }

    /** Called when the user clicks the Go! button */
    public void login2(View view) {
        Intent intent = new Intent(this, SwipeActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String studentName = editText.getText().toString();
        intent.putExtra(EXTRA_NAME, studentName);
        startActivity(intent);
    }

}
