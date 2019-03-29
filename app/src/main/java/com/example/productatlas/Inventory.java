package com.example.productatlas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.graphics.Color;

import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Inventory extends AppCompatActivity {

    //Content view Inventory, at least, created here.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        Bundle b = getIntent().getExtras();


        if(b != null) {
            String queryType = b.getString("queryType");
            String categoryQuery = null;
            String itemQuery = null;

            //If user selected item in the Search by Category Screen. This means the query is a
            //preselected category, which should automatically contain at least one item in the
            //database. Return and display results of query in sorted ascending order.
            if (queryType.equals("categoryQuery")) {
                categoryQuery = b.getString("categoryQuery");

                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText(categoryQuery);

                //Query items of this category here.
            }
            //Else if user searched for item in the mainInventory screen
            //This means that the item may or may NOT be in the database!
            //However, at least one character will be returned so that you do not need to worry
            //about querying an empty string.
            //This query must search and return all items that match this query or contain a
            //substring of this search and then display the results in sorted ascending order.
            else if (queryType.equals("itemQuery")) {
                itemQuery = b.getString("itemQuery");

                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText(itemQuery);

                //Query the names or other attributes for this query here.
            }

            //Use this code to update the TextView and display results.
            //...actually, will need to use a ListView instead of a TextView so that user can select
            //option. The textView is just for demonstration for now then.
//            TextView textView = (TextView) findViewById(R.id.textView);
//            textView.setText(categoryQuery);
        }
    }
}
