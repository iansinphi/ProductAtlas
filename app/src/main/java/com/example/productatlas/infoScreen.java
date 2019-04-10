package com.example.productatlas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class infoScreen extends AppCompatActivity {

    private Button ticketButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_screen);

        //These strings are used below to help display the item information. -Jesse
        String itemName;
        String price;
        String quantity;
        String type;

        //Below commented out is the part that needs to be included into the activity that is querying the data. Once the data is gather add it into
        //  an string array and call this function passing it the array. It will then launch the info screen. -Jesse

        //public void passArray(String [] beesKnees){
        //       //infoScreen here is the destination where we are sending the array. -Jesse
        //        Intent intent = new Intent(this, infoScreen.class);
        //
        //        //This putExtra command works in junction with the getExtras in the inBetween to send and gather the array. -Jesse
        //        intent.putExtra("itemInfo", beesKnees);
        //
        //        startActivity(intent);
        //    }

        //This is the other half of the call that picks up the array from where it is coming from. -Jesse
        Bundle extras = getIntent().getExtras();

        //This command creates another string array that has the same contents as the one passed in. -Jesse
        String[] itemInfo = extras.getStringArray("itemInfo");

        //This for loop will go through the array and decide where to put the individual strings. Once item does not match itemInfo the loop exists. -Jesse
        for(String item:itemInfo){

            //This if statement looks for the string in the first array section to equal item, which if the first time through will be true. -Jesse
            if (itemInfo[0] == item){
                itemName = item;
                //This will then dynamically assign the string from the array to the nameView textview (The name of the item text box). -Jesse
                TextView firstNameView = (TextView) findViewById(R.id.nameView);
                firstNameView.setText("Name of Item: " + itemName);
            }
            //Checks to see if itemInfo in array position 1 is equal to item and if so displays that string in the text box for price. -Jesse
            else if (itemInfo[1] == item){
                price = item;
                TextView lastNameView = (TextView) findViewById(R.id.priceView);
                lastNameView.setText("Price: " + price);
            }
            //Checks to see if itemInfo in array position 2 is equal to item and if so displays that string in the text box for quantity. -Jesse
            else if (itemInfo[2] == item){
                quantity = item;
                TextView lastNameView = (TextView) findViewById(R.id.quantityView);
                lastNameView.setText("Quantity: " + quantity);
            }
            //This is where things change up some. Since we wanted to dynamically assign pictures based off of type of the item I add in an extra set of if else
            // statements to look for that type and display that image. -Jesse
            else if (itemInfo[3] == item){
                type = item;
                //Had to change the string we passed in to integer in order to the comparison below (i.e. number ==1). -Jesse
                int number = Integer.parseInt(type);

                //Checks to see if the product is a vegetable, if so it then displays the veggie photo. -Jesse
                //Disclaimer!!! if anyone wants to change the pictures they can but try to get something in the 1200x900 range. -Jesse
                if(number == 1) {

                    ImageView fruits = (ImageView) findViewById(R.id.imageView);
                    fruits.setImageResource(R.drawable.veggies);
                }
                //Checks to see if the product is a fruit, if so it then displays the fruit photo. -Jesse
                else if(number == 2) {

                    ImageView fruits = (ImageView) findViewById(R.id.imageView);
                    fruits.setImageResource(R.drawable.fruit);
                }
                //Checks to see if the product is a meat, if so it then displays the meat photo. -Jesse
                //Disclaimer!!! DO NOT google meat collage, learn from my mistakes. -Jesse
                else if(number == 3) {

                    ImageView fruits = (ImageView) findViewById(R.id.imageView);
                    fruits.setImageResource(R.drawable.meats);
                }
                //Checks to see if the product is a dairy, if so it then displays the dairy photo. -Jes
                else if(number == 4) {

                    ImageView fruits = (ImageView) findViewById(R.id.imageView);
                    fruits.setImageResource(R.drawable.dairy);
                }

            }
        }

        //Commented the below out because I do not see the ticket screen in the java files, so I cannot add a destination for the button to launch. -Jesse
        //ticketButton = (Button) findViewById(R.id.button);
        //ticketButton.setOnClickListener(new View.OnClickListener(){
          //  @Override
         //   public void onClick(View v){
         //       launchTicketScreen();
          //  }
       // });


    }

    //Used to launch the ticket screen
    //public void launchTicketScreen() {
    //    Intent intent = new Intent(this, ticketScreenHere.class);
     //   startActivity(intent);
    //}
}


