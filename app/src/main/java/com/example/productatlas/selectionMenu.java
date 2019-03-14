package com.example.productatlas;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public abstract class selectionMenu extends AppCompatActivity implements View.OnClickListener {

    private String searchBoxTextValue;

    Button searchButton = (Button) findViewById(R.id.app_bar_search);

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.support_simple_spinner_dropdown_item);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder_search = new AlertDialog.Builder(selectionMenu.this);
                builder_search.setTitle("SearchBar");

                //set up input
                final EditText dialogInput = new EditText(selectionMenu.this);
                dialogInput.setInputType(InputType.TYPE_TEXT_FLAG_AUTO_CORRECT);

                //set up buttons
                builder_search.setPositiveButton("Find!", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        //populate accessable variable for the database query
                        searchBoxTextValue  = dialogInput.getText().toString();
                    }
                });

                builder_search.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder_search.show();
            }
        });
    }
}
