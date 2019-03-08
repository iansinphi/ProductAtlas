package com.example.projectatlas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.content.Intent;
import android.widget.AdapterView;

public class mapScreen extends AppCompatActivity {
    private static final String LOG_TAG =
            mapScreen.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_screen);
    }

    public void launchInventory(View view) {
        Log.d(LOG_TAG, "Button clicked");
        Intent intent = new Intent(this, inventoryScreen.class);
        startActivity(intent);
    }

}