package com.example.productatlas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class startUpScreen extends AppCompatActivity {

    //Declaring my spinner and button used to access maps
    Spinner mySpinner;
    private Button mapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up_screen);

        //Associating mySpinner with the spinner in the xml code, spinner1
        mySpinner = (Spinner) findViewById(R.id.spinner1);

        //Creating an array to hold the store names that will appear in the drop down menu
        List<String> storeName = new ArrayList<>();
        storeName.add("Real Fake Doors");
        storeName.add("Elliot\'s Grocery");
        storeName.add("Ants in my Eyes Johnson Electronics");

        //These next three lines incorporate an adapter which is needed to view the above array in the drop down menu, the last line associates the adapter with the spinner created
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(startUpScreen.this, android.R.layout.simple_spinner_item, storeName);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        //This section handles the selection of each drop down menu name
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //Retain the store names here for ease of use later
                String storeName0 = "Real Fake Doors";
                String storeName1 = "Elliot\'s Grocery";
                String storeName2 = "Ants in my Eyes Johnson Electronics";
                String name = parent.getItemAtPosition(position).toString();

                //The following if else statements act as a shuttle to the functions add below that will open that stores map. Right now we only have Elliot's grocery
                // so that is the only function at the bottom of this file. When adding another map just follow the outline of that function and add a function call
                // to it in the onClick function under the appropriate if else statement. Any questions see Jesse.
                if (name.equals(storeName0))
                {
                    //Associates mapButton with the button in the xml code, button.
                    mapButton = (Button) findViewById(R.id.button);
                    //Starts listening for a click of the button
                    mapButton.setOnClickListener(new View.OnClickListener(){
                        @Override
                        //Tells the program what to do when the button is clicked
                        public void onClick(View v){
                            Toast.makeText(startUpScreen.this, "Map does not exist for: Real Fake Doors", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else if (name.equals(storeName1))
                {
                    mapButton = (Button) findViewById(R.id.button);
                    mapButton.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v){
                            launchMap();
                        }
                    });
                }
                else if (name.equals(storeName2))
                {
                    mapButton = (Button) findViewById(R.id.button);
                    mapButton.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v){
                            Toast.makeText(startUpScreen.this, "Map does not exist for: Ants in my Eyes Johnson Electronics", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }

            //Does nothing but is needed
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    //Function mentioned above, this will be called when Elliot's Grocery is selected.
    public void launchMap() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
