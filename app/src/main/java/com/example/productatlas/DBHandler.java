package com.example.productatlas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import java.util.List;
import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    //This is which version of the database we are using.
    private static final int DATABASE_VERSION = 1;
    //This is the name of the database
    private static final String DATABASE_NAME = "store";
    //This is the name of the Table
    private static final String TABLE_ITEM = "item";
    //This is the Store Table column names.
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SHELF = "shelf";
    private static final String KEY_ITEM_DESC = "item_desc";
    private static final String KEY_PRICE = "price";
    private static final String KEY_QUANTITY = "quantity";
    private static final String KEY_CLASS = "class";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    //This is overriding a built in onCreate function..
    //it's designed to create the DB table if none exists
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_ITEM + "("
        + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
        + KEY_SHELF + " TEXT," + KEY_ITEM_DESC + " TEXT," + KEY_PRICE +
                " NUMERIC," + KEY_QUANTITY + " INTEGER," + KEY_CLASS + " TEXT)";
        db.execSQL(CREATE_CONTACTS_TABLE);
        //This area will eventually be used to pull a database from a client CSV file.. work in progress.
        //Fine.. I'll freaking hardcode it for now..
        db.execSQL("INSERT INTO item (name, shelf, item_desc, price, quantity, class) VALUES ('Avocado', '3', 'Awesome Avocados', '12', '8', 'fruit');");
        db.execSQL("INSERT INTO item (name, shelf, item_desc, price, quantity, class) VALUES ('Grapes', '3', 'Giant Grapes', '10', '11', 'fruit');");
        db.execSQL("INSERT INTO item (name, shelf, item_desc, price, quantity, class) VALUES ('Oranges', '1', 'Outrageous Oranges', '2', '8', 'fruit');");
        db.execSQL("INSERT INTO item (name, shelf, item_desc, price, quantity, class) VALUES ('Apples', '5', 'Amazing Apples', '5', '4', 'fruit');");
        db.execSQL("INSERT INTO item (name, shelf, item_desc, price, quantity, class) VALUES ('Lettuce', '4', 'Luxorious Lettuce', '1', '7', 'vegetable');");
        db.execSQL("INSERT INTO item (name, shelf, item_desc, price, quantity, class) VALUES ('Broccoli', '10', 'Beautiful Broccoli', '9', '4', 'vegetable');");
    }
    //This overrides the onUpgrade built in function
    //It's supposed to build a new database if one already exists
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drops the older table.. may need an if statement here
        //to check the versions.. I'm not sure.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM);
        //Creates the new tables.. once again, maybe an if else statement
        onCreate(db);
    }
    //THIS IS THE END OF THE INITIALIZATION PART OF THE DATABASE

//RYAN - EVERYTHING BELOW HERE IS NOT USED WITH PRODUCT ATLAS****************
//I'M LEAVING THE CODE HERE UNTIL WE KNOW WE WON'T NEED IT*******************
    //Adding a new Item
    public void addStore(Store store) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //Accessing getters for item information
        values.put(KEY_NAME, store.getName());
        values.put(KEY_SHELF, store.getShelf());
        values.put(KEY_ITEM_DESC, store.getStoreDesc());
        values.put(KEY_PRICE, store.getPrice());
        values.put(KEY_QUANTITY, store.getQuantity());
        values.put(KEY_CLASS, store.getClassification());
        //Inserting a new row.
        db.insert(TABLE_ITEM, null, values);
        db.close(); // Closing database connection
    }
    //END ADD
    //
    //This will get the information from one item using the id
    public Store findProduct(String name) {
        String query = "Select * FROM " + TABLE_ITEM + " WHERE " + KEY_NAME + " =  \"" + name + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Store item = new Store();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            item.setId(Integer.parseInt(cursor.getString(0)));
            item.setName(cursor.getString(1));
            item.setShelf(Integer.parseInt(cursor.getString(2)));
            item.setStoreDesc(cursor.getString(3));
            item.setPrice(Double.parseDouble(cursor.getString(4)));
            item.setQuantity(Integer.parseInt(cursor.getString(5)));
            item.setClassification(cursor.getString(6));
            cursor.close();
        } else {
            item = null;
        }
        db.close();
        return item;
    }
    //END FINDING AN ITEM

    //Returning all items from Database
    public List<Store> getAllShops() {
        List<Store> itemList = new ArrayList<Store>();
        // Select All Query
        String selectQuery = "SELECT * FROM "+TABLE_ITEM;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Store item = new Store();
                item.setId(Integer.parseInt(cursor.getString(0)));
                item.setName(cursor.getString(1));
                item.setShelf(Integer.parseInt(cursor.getString(2)));
                item.setStoreDesc(cursor.getString(3));
                item.setPrice(Double.parseDouble(cursor.getString(4)));
                item.setQuantity(Integer.parseInt(cursor.getString(5)));
                // Adding contact to list
                itemList.add(item);
            } while (cursor.moveToNext());
        }
        return itemList;
    }
    //END FINDING ALL DATABASE ITEMS

    //Search for the item by it's name and then delete it if it's found.
    public boolean deleteProduct(String productName) {
        boolean result = false;
        String query = "Select * FROM " + TABLE_ITEM + " WHERE " + KEY_NAME + " =  \"" + productName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Store product = new Store();

        //If found, delete it and save change result to true.
        if (cursor.moveToFirst()) {
            product.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_ITEM, KEY_ID + " = ?",
                    new String[] { String.valueOf(product.getId()) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
    //END DELETION OF ITEM
}
