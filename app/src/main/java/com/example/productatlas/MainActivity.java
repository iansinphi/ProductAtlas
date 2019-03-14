package com.example.productatlas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.util.Log;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.TextView;

import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private static String[] SUGGESTION = new String[] {
            "Apple", "Amazon", "Microsoft", "Alphabet", "Google", "Samsung", "Asus",
            "HP", "Intel", "Qualcomn", "AMD", "Oracle", "Facebook", "Spotify"
    };

    private MaterialSearchView mMaterialSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //creates an instance of the layout view MainActivity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //RYAN - initiates the database creation
        DBHandler dbHandler = new DBHandler(this);
        dbHandler.getWritableDatabase();

        //The rest here (as of 3/13/19) creates the search bar. Isaiah

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        mMaterialSearchView = findViewById(R.id.searchView);
//        mMaterialSearchView.setSuggestions(SUGGESTION);

        final ListView listView = findViewById(R.id.listView);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, SUGGESTION);
        listView.setAdapter(arrayAdapter);

        mMaterialSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, SUGGESTION);
                listView.setAdapter(arrayAdapter);
            }
        });

        mMaterialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1);

                if (newText != null && !newText.isEmpty()) {
                    for (String s : SUGGESTION) {
                        if (s.toLowerCase().contains(newText))
                            arrayAdapter.add(s);
                    }
                } else {
                    arrayAdapter.addAll(SUGGESTION);
                }

                listView.setAdapter(arrayAdapter);

                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.main_menu, menu);

        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.searchMenu);
        mMaterialSearchView.setMenuItem(menuItem);
        return super.onCreateOptionsMenu(menu);
    }

//    public void launchInventory(MenuItem menuItem) {
//        // intent signals when to start activity, Inventory. This is initiated by a Button press.
//        Log.d(LOG_TAG, "Button clicked");
//        Intent intent = new Intent(this, Inventory.class);
//        startActivity(intent);
//    }

    //Jordan's function, but I do not know where to put. Isaiah
//    public String getSearchString(){
//        TextView search = (TextView) findViewById(R.id.textInputEditText);
//        return (String) search.getText();
//    }
}
