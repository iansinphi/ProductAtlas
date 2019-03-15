package com.example.productatlas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;

import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

public class quickSearch extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private static String[] CATEGORIES = new String[] {
            "Coffee", "Beverages", "Snacks", "Breakfast & Cereal", "Meals", "Condiments", "Pasta",
            "Candy & Gum", "Soups", "Canned Goods", "Emergency Food", "Baking Center",
            "International Food", "Gift Baskets"
    };

    private MaterialSearchView mMaterialSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //creates an instance of the layout view MainActivity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quick_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        mMaterialSearchView = findViewById(R.id.searchView);

        final ListView listView = findViewById(R.id.listView);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, CATEGORIES);
        listView.setAdapter(arrayAdapter);

        mMaterialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            //Text user enters is sent into query parameter here. The first match (highest on the
            //list and closest to the search bar) from the query is sent into launchInventory for
            //querying.
            //This is the first out of two places that launchInventory is called.
            @Override
            public boolean onQueryTextSubmit(String query) {
                //If one of the categories matches or contains a substring of query, then go to
                //Inventory Screen.
                String itemFound = null;

                for (String s: CATEGORIES) {
                    if (s.toLowerCase().equals(query) || s.toLowerCase().contains(query)) {
                        itemFound = s;

                        break;
                    }
                }

                if (itemFound != null) {
                    launchInventory(itemFound);
                }

                return true;
            }

            //This method updates the items in the ListView to show only the items that contain the
            //user's entered substring as the user enters text.
            @Override
            public boolean onQueryTextChange(String newText) {
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(quickSearch.this, android.R.layout.simple_list_item_1);

                if (newText != null && !newText.isEmpty()) {
                    for (String s : CATEGORIES) {
                        if (s.toLowerCase().contains(newText.toLowerCase()) )
                            arrayAdapter.add(s);
                    }
                } else {
                    arrayAdapter.addAll(CATEGORIES);
                }

                listView.setAdapter(arrayAdapter);

                return false;
            }
        });

        //This method sends the item that the user clicks on as a string to the launchInventory
        //method.
        //This is the second out of two places that launchInventory is called.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemClicked = arrayAdapter.getItem(position);

                launchInventory(itemClicked);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.quick_search_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.searchMenu);
        mMaterialSearchView.setMenuItem(menuItem);
        return super.onCreateOptionsMenu(menu);
    }

    //Note for database implementation and normal description of this method:
    //Parameter categoryQuery contains a preselected category selected by the user and is sent to
    //the launchActivity Screen, where the database is queried for all items in this category.
    //If one of the categories matches or contains a substring of query, then go to
    //Inventory Screen.
    private void launchInventory(String categoryQuery) {
        Intent intent = new Intent(quickSearch.this, Inventory.class);
        Bundle b = new Bundle();
        b.putString("queryType", "categoryQuery");
        b.putString("categoryQuery", categoryQuery);
        intent.putExtras(b);
        startActivity(intent);
    }
}
