package com.example.productatlas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
//import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //creates an instance of the layout view MainActivity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //RYAN - initiates the database creation
        final DBHandler dbHandler = new DBHandler(this);
        dbHandler.getWritableDatabase();

        EditText editText = (EditText) findViewById(R.id.textInputEditText);

        //Perform action on key press; here, this key press determines if the string entered is not
        //empty and if so calls launchInventory.
        editText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press

                    TextView search = (TextView) findViewById(R.id.textInputEditText);
                    String stringSearch = search.getText().toString();
                    Log.d(LOG_TAG, search.getText().toString());

                    if (stringSearch != null && !stringSearch.isEmpty()) {
//                        launchInventory(stringSearch, dbHandler);
                        launchInventory(stringSearch);
                    }

                    return true;
                }
                return false;
            }
        });

        //On keyboard search click; here, this on-screen key press determines if the string entered
        //is not empty and if so calls launchInventory.
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    TextView search = (TextView) findViewById(R.id.textInputEditText);
                    String stringSearch = search.getText().toString();
                    Log.d(LOG_TAG, search.getText().toString());

                    if (stringSearch != null && !stringSearch.isEmpty()) {
//                        launchInventory(stringSearch, dbHandler);
                        launchInventory(stringSearch);
                    }

                    return true;
                }
                return false;
            }
        });
    }

    public void launchQuickSearch(MenuItem menuItem) {
        Intent intent = new Intent(this, quickSearch.class);
        startActivity(intent);
    }

    public void exitToStartup(MenuItem menuItem) {
        Log.d(LOG_TAG, "Button clicked");
        Intent intent = new Intent(this, startUpScreen.class);
        startActivity(intent);
    }

    //Item query is passed to Inventory activity for querying by item name or other attributes.
//    private void launchInventory(String itemQuery, DBHandler dbHandler) {
    private void launchInventory(String itemQuery) {
        Intent intent = new Intent(this, Inventory.class);
        Bundle b = new Bundle();
        b.putString("queryType", "itemQuery");
        b.putString("itemQuery", itemQuery);
        intent.putExtras(b);
//        intent.putExtra("DBHandler", new Gson().toJson(dbHandler));
        startActivity(intent);
    }
}
