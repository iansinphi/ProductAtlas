package com.example.productatlas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import android.widget.ListView;

//import com.google.gson.Gson;

public class Inventory extends AppCompatActivity {

    //Content view Inventory, at least, created here.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        Bundle b = getIntent().getExtras();

        //For DB query
        DBHandler dbHandler = new DBHandler(this);
        Store item = new Store();

        if(b != null) {
            String queryType = b.getString("queryType");
            String categoryQuery = null;
            String itemQuery = null;

//            DBHandler dbHandler = new Gson().fromJson("DBHandler", DBHandler.class);

            //If user selected item in the Search by Category Screen. This means the query is a
            //preselected category, which should automatically contain at least one item in the
            //database. Return and display results of query in sorted ascending order.
            if (queryType.equals("categoryQuery")) {
                categoryQuery = b.getString("categoryQuery");

                //Query gets ALL items from database
                String[] itemList = dbHandler.getAllShops();
                List<String> specList = new ArrayList<String>();
                for(int i = 0; i < itemList.length; i++){
                    if(itemList[i].indexOf(categoryQuery) > 0){
                        specList.add(itemList[i]);
                    }
                }

                //TextView textView = (TextView) findViewById(R.id.textView);
                ListView listView = (ListView) findViewById(R.id.listView);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, specList);
                listView.setAdapter(arrayAdapter);

                //if(item == null) {
                  //  String notFound = "Item not found!";
                    //textView.setText(notFound);
                //}
                //else
                //    textView.setText(item.getName());

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

                item = dbHandler.findProduct(itemQuery);

                TextView textView = (TextView) findViewById(R.id.textView);
                if(item == null) {
                    String notFound = "Item not found!";
                    textView.setText(notFound);
                }
                else {
                    textView.setText(item.getName() + " " + item.getStoreDesc() + " " + item.getQuantity() + " " + item.getShelf());
                }

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
