package com.example.productatlas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

        Bundle b = getIntent().getExtras();

        //Jordan D. -- get pins by id so we can conditionally set their visibilities
        ImageView PinA1 = (ImageView) findViewById(R.id.pinA1);
        ImageView PinA2 = (ImageView) findViewById(R.id.pinA2);
        ImageView PinB1 = (ImageView) findViewById(R.id.pinB1);
        ImageView PinB2 = (ImageView) findViewById(R.id.pinB2);
        ImageView PinC = (ImageView) findViewById(R.id.pinC);
        ImageView PinD = (ImageView) findViewById(R.id.pinD);

        if (b != null) {
            int id = b.getInt("id");
            String name = b.getString("name");
            String description = b.getString("description");
            Double price = b.getDouble("price");
            int quantity = b.getInt("quantity");
            String category = b.getString("category");
 
            //Activate the blip corresponding to this item's shelf number here and hide the rest.
            //Jordan D. -- so glad java 7 introduced strings in switches.
            switch (category){
                case "Vegetable":
                    PinB2.setVisibility(PinB2.VISIBLE);
                    break;
                case "Fruit":
                    PinB1.setVisibility(PinB1.VISIBLE);
                    break;

            }
            //AlertDialog box to test that string is passed back to Main from Inventory.
            new AlertDialog.Builder(this)
                    .setTitle("Item Information to Be Viewed in Information Screen")
                    .setMessage("ID: " + id + "\n"
                            + "Name: " + name + "\n"
                            + "Description: " + description + "\n"
                            + "Price: " + price + "\n"
                            + "Quantity: " + quantity + "\n"
                            + "Category: " + category + "\n")

                    //Specifying a listener allows you to take an action before dismissing the dialog.
                    //The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //Continue
                        }
                    })

                    //A null listener allows the button to dismiss the dialog and take no further action.
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else {
            //Hide all blips.
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
    private void launchInventory(String itemQuery) {
        Intent intent = new Intent(this, Inventory.class);
        Bundle b = new Bundle();
        b.putString("queryType", "itemQuery");
        b.putString("itemQuery", itemQuery);
        intent.putExtras(b);
        startActivity(intent);
    }
}
