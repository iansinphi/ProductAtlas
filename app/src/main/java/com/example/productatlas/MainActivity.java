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

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //creates an instance of the layout view MainActivity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //RYAN - initiates the database creation
        DBHandler dbHandler = new DBHandler(this);
        dbHandler.getWritableDatabase();
    }

    public void launchInventory(MenuItem menuItem) {
        // intent signals when to start activity, Inventory. This is initiated by a Button press.
        Log.d(LOG_TAG, "Button clicked");
        Intent intent = new Intent(this, Inventory.class);
        startActivity(intent);
    }

    //I added this function but do not know how Jordan wants to implement. Isaiah
    //This function uncommented crashes now, but will(?) bring up search feature after Menu class /
    //      support_simple_spinner_dropdown_item.xml file / AndroidManifest.xml file is debugged /
    //      is updated, to my current understanding. Isaiah.
    public void launchSearch(MenuItem menuItem) {
//        Log.d(LOG_TAG, "Button clicked");
//        Intent intent = new Intent(this, selectionMenu.class);
//        startActivity(intent);
    }

    //Jordan's function, but I do not know where to put. Isaiah
//    public String getSearchString(){
//        TextView search = (TextView) findViewById(R.id.textInputEditText);
//        return (String) search.getText();
//    }
}
