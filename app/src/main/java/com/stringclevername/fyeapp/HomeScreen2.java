package com.stringclevername.fyeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
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
    private void checkButtonClick() {

        Button myButton = (Button) findViewById(R.id.button1);
        myButton.setOnClickListener(new View.OnClickListener() {

            /** Called when the user clicks the "Go!" button **/
            @Override
            public void onClick(View view) {

                //get selected radio button from radioGroup
                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioStudentType);
                int selectedId = radioGroup.getCheckedRadioButtonId();

                //button IDs (to check against selected ID)
                int fysButtonID = R.id.radioFys;
                int plButtonID = R.id.radioPl;

                if (selectedId == fysButtonID) {
                    Intent fysIntent = new Intent (getApplicationContext(), fysActivity.class);
                    startActivity (fysIntent);
                }
                else {
                    Intent plIntent = new Intent (getApplicationContext(), plActivity.class);
                    startActivity (plIntent);
                }

            }
        });

    }

}
