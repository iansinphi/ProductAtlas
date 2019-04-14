package com.example.productatlas;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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

    InputStream is;

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //IMPLEMENT CSV in ASSETS FOLDER---------------------------------------
        AssetManager assetManager = context.getAssets();

        try {

            is = assetManager.open("grocerydatabase.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //-------------------------------------------------------------------------

    //This is overriding a built in onCreate function..
    //it's designed to create the DB table if none exists
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Changed Price from Numeric to Integer
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_ITEM + "("
        + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
        + KEY_SHELF + " TEXT," + KEY_ITEM_DESC + " TEXT," + KEY_PRICE +
                " NUMERIC," + KEY_QUANTITY + " INTEGER," + KEY_CLASS + " TEXT)";
        db.execSQL(CREATE_CONTACTS_TABLE);
        //This area is used to pull a database from a client CSV file
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            ContentValues contentValues = new ContentValues();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] x = line.split(",");
                contentValues.put(KEY_NAME, x[0]);
                contentValues.put(KEY_SHELF, x[1]);
                contentValues.put(KEY_ITEM_DESC, x[2]);
                contentValues.put(KEY_PRICE, x[3]);
                contentValues.put(KEY_QUANTITY, x[4]);
                contentValues.put(KEY_CLASS,x[5]);

                db.insert(TABLE_ITEM, null, contentValues);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
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



    //Isaiah - Adding these three functions, getItemsFromItemNameSearch, getItemsFromCategorySearch,
    // and getItemInformation for the project to query by category and by item name and then to
    // retrieve all of one item's information:

    //Returning all items containing substring searched by user.
    public List<String> getItemsFromItemNameSearch(String itemQuery) {
        List<Store> itemList = new ArrayList<Store>();
        List<String> queryResult = new ArrayList<String>();

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
                item.setClassification(cursor.getString(6));
                // Adding contact to list
                itemList.add(item);


                if (item.getName().toLowerCase().contains(itemQuery.toLowerCase())) {
                    queryResult.add(item.getName());
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        cursor = null;

        return queryResult;
    }

    //Return all items from category search.
    public List<String> getItemsFromCategorySearch(String categoryQuery) {
        List<Store> itemList = new ArrayList<Store>();
        List<String> queryResult = new ArrayList<String>();

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
                item.setClassification(cursor.getString(6));
                // Adding contact to list
                itemList.add(item);

                if (categoryQuery.equals(item.getClassification())) {
                    queryResult.add(item.getName());
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        cursor = null;

        return queryResult;
    }

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
}
