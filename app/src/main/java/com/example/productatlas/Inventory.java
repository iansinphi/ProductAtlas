package com.example.productatlas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class Inventory extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        Bundle b = getIntent().getExtras();

        final ListView listView = findViewById(R.id.listView);

        //For DB query
        DBHandler dbHandler = new DBHandler(this);
        Store item = new Store();

        String queryType = b.getString("queryType");
        String categoryQuery = null;
        String itemQuery = null;

        //If user selected item in the Search by Category Screen. This means the query is a
        //preselected category, which should automatically contain at least one item in the
        //database. Return and display results of query in sorted ascending order.
        if (queryType.equals("categoryQuery")) {
            categoryQuery = b.getString("categoryQuery");

            List<String> itemList = dbHandler.getItemsFromCategorySearch(categoryQuery);

            final ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemList);
            listView.setAdapter(arrayAdapter1);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String itemClicked = arrayAdapter1.getItem(position);

                    launchMain(itemClicked);
                }
            });
        }
        //Else if user searched for item in the mainInventory screen
        //This means that the item may or may NOT be in the database!
        //However, at least one character will be returned so that you do not need to worry
        //about querying an empty string.
        //This query must search and return all items that match this query or contain a
        //substring of this search and then display the results in sorted ascending order.
        else if (queryType.equals("itemQuery")) {
            itemQuery = b.getString("itemQuery");

            List<String> itemList = dbHandler.getItemsFromItemNameSearch(itemQuery);

            if (!itemList.isEmpty()) {
                final ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemList);

                listView.setAdapter(arrayAdapter2);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String itemClicked = arrayAdapter2.getItem(position);

                        launchMain(itemClicked);
                    }
                });
            } else {
                //AlertDialog box to test that string is passed back to Main from Inventory.
                new AlertDialog.Builder(this)
                        .setTitle("No Item Found.")
                        .setMessage("No items matching your search were found.")

                        //Specifying a listener allows you to take an action before dismissing the dialog.
                        //The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //Launch Main with no item clicked.
                                launchMain();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        }
    }

    private void launchMain(String itemClicked) {
        Intent intent = new Intent(Inventory.this, MainActivity.class);

        //For DB query
        DBHandler dbHandler = new DBHandler(this);
        Store item = new Store();
        item = dbHandler.findProduct(itemClicked);

        Bundle b = new Bundle();
        b.putInt("id", item.getId());
        b.putString("name", item.getName());
        b.putString("description", item.getStoreDesc());
        b.putDouble("price", item.getPrice());
        b.putInt("quantity", item.getQuantity());
        b.putString("category", item.getClassification());
        intent.putExtras(b);
        startActivity(intent);
    }

    //No item clicked.
    private void launchMain() {
        Intent intent = new Intent(Inventory.this, MainActivity.class);
        startActivity(intent);
    }
}
