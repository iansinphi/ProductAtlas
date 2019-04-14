package com.example.productatlas;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ticketScreen extends AppCompatActivity {

    private Button returnButton;
    private Button submitButton;
    private EditText name;
    private EditText problem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_screen);

        Bundle b = getIntent().getExtras();

        //The item information to be submitted with the ticket info to the output file.
        //They may need to be declared final.
        String itemName = b.getString("name");
        int shelf = b.getInt("shelf");
        String description = b.getString("description");
        Double price = b.getDouble("price");
        int quantity = b.getInt("quantity");
        String category = b.getString("category");

        name = (EditText) findViewById(R.id.editText2);
        problem = (EditText) findViewById(R.id.editText);

        submitButton = (Button) findViewById(R.id.submit);
        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String custName = name.getText().toString();
                String description = problem.getText().toString();

                if(TextUtils.isEmpty(custName)){
                    Toast.makeText(ticketScreen.this, "Please make sure both boxes are filled out, thank you.", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(description)){
                    Toast.makeText(ticketScreen.this, "Please make sure both boxes are filled out, thank you.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ticketScreen.this, "Ticket has been submitted successfully.", Toast.LENGTH_SHORT).show();

                    //Add the function that Ryan is building to send ticket information here.
                    String[] emailTo = {"@gmail.com"}; //This is the recipients ID. It would be used to hold business email
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setData(Uri.parse("mailto:"));
                    emailIntent.setType("message/rfc822"); //This format will cause the phone to primarily look at email apps to use.

                    emailIntent.putExtra(Intent.EXTRA_EMAIL, emailTo);
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, custName);
                    emailIntent.putExtra(Intent.EXTRA_TEXT, description);

                    try {
                        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                        finish();
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(ticketScreen.this,
                                "There is no email client installed.", Toast.LENGTH_SHORT).show();
                    }
                    finish();
                }

            }
        });

        returnButton = (Button) findViewById(R.id.returnInfo);
        returnButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }
}
