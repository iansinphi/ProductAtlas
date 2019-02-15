package com.example.productatlas;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

// JSTENNET added activity splash screen

public class splashScreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 5000;

// public class splashScreen declared with a local variable TIME_OUT
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable(){
            @Override

            // creates instance runs function to set intent to view content and then time out to
            // the main activity

            public void run(){
                Intent mainIntent = new Intent(splashScreen.this, MainActivity.class);
                startActivity(mainIntent);
                finish();

                // IPHILLIPS changed mapScreen to MainActivity to match convention
            }

        },SPLASH_TIME_OUT);
    }

}














