package com.example.productatlas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class infoScreen extends AppCompatActivity {

    private Button ticketButton;
    private Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_screen);

        //This is the other half of the call that picks up the array from where it is coming from. -Jesse
        Bundle b = getIntent().getExtras();

        //These strings are used below to help display the item information. -Jesse
        final String itemName = b.getString("name");
        final int shelf = b.getInt("shelf");
        final String description = b.getString("description");
        final Double price = b.getDouble("price");
        final int quantity = b.getInt("quantity");
        final String category = b.getString("category");

        TextView firstNameView = (TextView) findViewById(R.id.nameView);
        TextView middleNameView = (TextView) findViewById(R.id.priceView);
        TextView lastNameView = (TextView) findViewById(R.id.quantityView);

        ImageView categoryPicture = (ImageView) findViewById(R.id.imageView);

        firstNameView.setText("Name of Item: " + itemName);
        middleNameView.setText("Price: " + price);
        lastNameView.setText("Quantity: " + quantity);

        switch (category.toLowerCase()) {
            case "fruit":
                categoryPicture.setImageResource(R.drawable.fruit);

                break;
            case "vegetable":
                categoryPicture.setImageResource(R.drawable.veggies);

                break;
            case "meat":
                categoryPicture.setImageResource(R.drawable.meats);

                break;
            case "dairy":
                categoryPicture.setImageResource(R.drawable.dairy);

                break;
//            case "pasta":
//                categoryPicture.setImageResource(R.drawable.);
//
//                break;
//            case "breakfast & cereal":
//                categoryPicture.setImageResource(R.drawable.);
//
//                break;
        }

        ticketButton = (Button) findViewById(R.id.reportIssue);
        ticketButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                launchTicketScreen(itemName, shelf, description, price, quantity, category);

                finish();
            }
        });

        returnButton = (Button) findViewById(R.id.returnMap);
        returnButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }

    public void launchTicketScreen(String name, int shelf, String description, double price,
                                   int quantity, String category) {
        Intent intent = new Intent(this, ticketScreen.class);
        Bundle b = new Bundle();
        b.putString("name", name);
        b.putInt("shelf", shelf);
        b.putString("description", description);
        b.putDouble("price", price);
        b.putInt("quantity", quantity);
        b.putString("category", category);
        intent.putExtras(b);
        startActivity(intent);
    }
}


