package com.example.duska.food;

/**
 * Created by Duska on 02.11.2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper  extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "foodDb2";
    public static final String TABLE_MENU = "food";
    public static final String TABLE_LISTOFPRODUCTS="listofproducts";
    public static final String TABLE_RECIPES = "recipes";

            //the first table
    public static final String KEY_ID = "_id";
    public static final String KEY_NAMEOFDISH = "nameofdish";
    public static final String KEY_CATEGORY = "category";
    public static final String KEY_COOKINGTIME = "cookingtime";
    public static final String KEY_INGREDIENTS = "ingredients";

          //the second table
    public static final String KEY_ID2 = "_id";
    public static final String KEY_NAMEOFDISHINRECIPE = "nameofdishinrecipe";
    public static final String KEY_RECIPE = "recipe";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("LOG_TAG", "Обновление базы данных с версии " + oldVersion + " до версии " + newVersion + ", которое удалит все старые данные");
        db.execSQL("drop table if exists " + TABLE_MENU +  TABLE_RECIPES + ";");

        onCreate(db);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_MENU + "(" + KEY_ID
                + " integer primary key," + KEY_NAMEOFDISH + " text," +  KEY_CATEGORY + " text," + KEY_COOKINGTIME + " text," + KEY_INGREDIENTS + " text" +")"); //CREATE THE FIRST TABLE

        db.execSQL("create table " + TABLE_RECIPES + "(" + KEY_ID2
                + " integer primary key," + KEY_NAMEOFDISHINRECIPE + " text," + KEY_RECIPE + " text" + ")"); //CREATE THE FIRST TABLE

    }

}
