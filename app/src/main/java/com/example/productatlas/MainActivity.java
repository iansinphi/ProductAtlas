package com.example.productatlas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;
import android.view.Menu;
import android.database.sqlite.SQLiteDatabase;

// IPHILLIPS commit some change

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }
// IPHILLIPS add function to create options menu, main_menu

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //RYAN - initiates the database creation
        DBHandler dbHandler = new DBHandler(this);
        dbHandler.getWritableDatabase();
    }
    //creates an instance of the layout view MainActivity

    public void launchInventory(MenuItem menuItem) {
        Intent intent = new Intent(this, Inventory.class);
        startActivity(intent);
    }
}

// intent signals when to start activity, Inventory. This is initiated by a Button press.










