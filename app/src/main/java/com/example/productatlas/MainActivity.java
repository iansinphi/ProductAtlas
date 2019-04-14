package com.example.productatlas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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

        Bundle b = getIntent().getExtras();

        //Jordan D. -- get pins by id so we can conditionally set their visibilities
        ImageView pinA1 = (ImageView) findViewById(R.id.pinA1);
        ImageView pinA2 = (ImageView) findViewById(R.id.pinA2);
        ImageView pinB1 = (ImageView) findViewById(R.id.pinB1); //Fruit
        ImageView pinB2 = (ImageView) findViewById(R.id.pinB2); //Vegetable
        ImageView pinC = (ImageView) findViewById(R.id.pinC);
        ImageView pinD = (ImageView) findViewById(R.id.pinD);

        //Jordan D. -- get search bar by id so we can show the latest item to be selected from the search.
        TextInputEditText searchBar = (TextInputEditText) findViewById(R.id.textInputEditText);

        //If something was past in from...
        if (b != null) {
            final String name = b.getString("name");
            final int shelf = b.getInt("shelf");
            final String description = b.getString("description");
            final Double price = b.getDouble("price");
            final int quantity = b.getInt("quantity");
            final String category = b.getString("category");

            //Activate the blip corresponding to this item's shelf number here and hide the rest.
            switch(shelf){
                case 1:
                    pinC.setVisibility(View.VISIBLE);
                    pinC.setClickable(true);

                    break;
                case 2:
                    pinB1.setVisibility(View.VISIBLE);
                    pinB1.setClickable(true);

                    break;
                case 3:
                    pinB2.setVisibility(View.VISIBLE);
                    pinB2.setClickable(true);

                    break;
                case 4:
                    pinA1.setVisibility(View.VISIBLE);
                    pinA1.setClickable(true);

                    break;
                case 5:
                    pinA2.setVisibility(View.VISIBLE);
                    pinA2.setClickable(true);

                    break;
                case 6:
                    pinD.setVisibility(View.VISIBLE);
                    pinD.setClickable(true);

                    break;
            }

            //Jordan D.  -- change text in the search bar to help people to remember that which they searched.
            searchBar.setHint(name);

            pinC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    launchInfoScreen(name, shelf, description, price, quantity, category);
                }
            });

            pinB1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    launchInfoScreen(name, shelf, description, price, quantity, category);
                }
            });

            pinB2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    launchInfoScreen(name, shelf, description, price, quantity, category);
                }
            });

            pinA1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    launchInfoScreen(name, shelf, description, price, quantity, category);
                }
            });

            pinA2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    launchInfoScreen(name, shelf, description, price, quantity, category);
                }
            });

            pinD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    launchInfoScreen(name, shelf, description, price, quantity, category);
                }
            });
        }

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
                        launchInventory(stringSearch);

                        finish();
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
                        launchInventory(stringSearch);

                        finish();
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
        Intent intent = new Intent(this, startUpScreen.class);
        startActivity(intent);

        finish();
    }

    //Item query is passed to Inventory activity for querying by item name or other attributes.
    private void launchInventory(String itemQuery) {
        Intent intent = new Intent(this, Inventory.class);
        Bundle b = new Bundle();
        b.putString("queryType", "itemQuery");
        b.putString("itemQuery", itemQuery);
        intent.putExtras(b);
        startActivity(intent);
    }

    private void launchInfoScreen(String name, int shelf, String description, double price,
                                  int quantity, String category) {
        Intent intent = new Intent(this, infoScreen.class);
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
