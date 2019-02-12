package com.example.productatlas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG =
                                    MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchInventory(View view) {
        Log.d(LOG_TAG, "Button clicked");
        Intent intent = new Intent(this, Inventory.class);
        startActivity(intent);
    }
}

//Test: GitHub test. To be deleted. Isaiah