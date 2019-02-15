package com.example.productatlas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.content.Intent;

// IPHILLIPS commit

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG =
                                    MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //creates an instance of the layout view MainActivity

    public void launchInventory(View view) {
        Log.d(LOG_TAG, "Button clicked");
        Intent intent = new Intent(this, Inventory.class);
        startActivity(intent);
    }
}

// intent signals when to start activity, Inventory. This is initiated by a Button press.










