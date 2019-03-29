package com.example.productatlas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class infoScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_screen);

        String itemName;
        String price;
        String quantity;
        String type;

        Bundle extras = getIntent().getExtras();

        String[] itemInfo = extras.getStringArray("itemInfo");

        for(String item:itemInfo){
            Log.i("infoScreen", String.valueOf(item));

            if (itemInfo[0] == item){
                itemName = item;
                TextView firstNameView = (TextView) findViewById(R.id.nameView);
                firstNameView.setText("Name of Item: " + itemName);
            }
            else if (itemInfo[1] == item){
                price = item;
                TextView lastNameView = (TextView) findViewById(R.id.priceView);
                lastNameView.setText("Price: " + price);
            }
            else if (itemInfo[2] == item){
                quantity = item;
                TextView lastNameView = (TextView) findViewById(R.id.quantityView);
                lastNameView.setText("Quantity: " + quantity);
            }
            else if (itemInfo[3] == item){
                type = item;

            }
        }


    }
}

