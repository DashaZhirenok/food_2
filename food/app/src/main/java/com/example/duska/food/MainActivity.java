package com.example.duska.food;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAdd, btnDelete, btnClear;
    private EditText etNameofdish, etMealtime, etCategory, etIngredients1, etIngredients2, etIngredients3, etIngredients4, etIngredients5;
    private EditText etPrice1, etPrice2, etPrice3, etPrice4, etPrice5;
    private TextView text_of_recipe;
    private DBHelper dbHelper;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //android.app.ActionBar myActionBar = getActionBar();

        //For hiding android actionbar
        //myActionBar.hide();

        dbHelper = new DBHelper(this);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(this);

        btnClear = (Button) findViewById(R.id.button);
        btnClear.setOnClickListener(this);

        etNameofdish = (EditText) findViewById(R.id.etNameofdish);
        etMealtime = (EditText) findViewById(R.id.etMealtime);
        etCategory = (EditText) findViewById(R.id.etCategory);
        etPrice1 = (EditText) findViewById(R.id.etPrice1);
        etPrice2 = (EditText) findViewById(R.id.etPrice2);
        etPrice3 = (EditText) findViewById(R.id.etPrice3);
        etPrice4 = (EditText) findViewById(R.id.etPrice4);
        etPrice5 = (EditText) findViewById(R.id.etPrice5);
        etIngredients1 =(EditText) findViewById(R.id.etIngredients1);
        etIngredients2 =(EditText) findViewById(R.id.etIngredients2);
        etIngredients3 =(EditText) findViewById(R.id.etIngredients3);
        etIngredients4 =(EditText) findViewById(R.id.etIngredients4);
        etIngredients5 =(EditText) findViewById(R.id.etIngredients5);
        text_of_recipe = (TextView) findViewById(R.id.text_of_recipe);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        dbHelper = new DBHelper(this);


    }

    @Override
    public void onClick(View v) {

        if (TextUtils.isEmpty(etNameofdish.getText().toString())) // если поле Name_of_dish - пустое
        {
                Toast.makeText(getApplicationContext(),
                        "Please, fill in the field 'Name of dish'",
                        Toast.LENGTH_SHORT).show();
            return;
        }


        String nameofdish = etNameofdish.getText().toString();
        String mealtime = etMealtime.getText().toString();
        String category = etCategory.getText().toString();
        String ingredients1 = etIngredients1.getText().toString();
        String price1 = etPrice1.getText().toString();
        String price2 = etPrice2.getText().toString();
        String price3 = etPrice3.getText().toString();
        String price4 = etPrice4.getText().toString();
        String price5 = etPrice5.getText().toString();
        String ingredients2 = etIngredients2.getText().toString();
        String ingredients3 = etIngredients3.getText().toString();
        String ingredients4 = etIngredients4.getText().toString();
        String ingredients5 = etIngredients5.getText().toString();
        String recipe = text_of_recipe.getText().toString();

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        ContentValues contentValues2 = new ContentValues();
        ContentValues contentValues3 = new ContentValues();


        switch (v.getId()) {

            case R.id.btnAdd: //добавление данных в таблицу
                contentValues.put(DBHelper.KEY_NAMEOFDISH, nameofdish);
                contentValues.put(DBHelper.KEY_MEALTIME, mealtime);
                contentValues.put(DBHelper.KEY_CATEGORY, category);

                contentValues2.put(DBHelper.KEY_NUMBEROFDISH, nameofdish);
                contentValues2.put(DBHelper.KEY_INGREDIENT, ingredients1);
                contentValues2.put(DBHelper.KEY_PRICE, price1);
                database.insert(DBHelper.TABLE_LISTOFPRODUCTS, null, contentValues2);

                contentValues2.put(DBHelper.KEY_INGREDIENT, ingredients2);
                contentValues2.put(DBHelper.KEY_PRICE, price2);
                database.insert(DBHelper.TABLE_LISTOFPRODUCTS, null, contentValues2);

                contentValues2.put(DBHelper.KEY_INGREDIENT, ingredients3);
                contentValues2.put(DBHelper.KEY_PRICE, price3);
                database.insert(DBHelper.TABLE_LISTOFPRODUCTS, null, contentValues2);

                contentValues2.put(DBHelper.KEY_INGREDIENT, ingredients4);
                contentValues2.put(DBHelper.KEY_PRICE, price4);
                database.insert(DBHelper.TABLE_LISTOFPRODUCTS, null, contentValues2);

                contentValues2.put(DBHelper.KEY_INGREDIENT, ingredients5);
                contentValues2.put(DBHelper.KEY_PRICE, price5);

                contentValues3.put(DBHelper.KEY_RECIPE, recipe);
                contentValues3.put(DBHelper.KEY_NAMEOFDISHINRECIPE, nameofdish);

                database.insert(DBHelper.TABLE_MENU, null, contentValues);
                database.insert(DBHelper.TABLE_LISTOFPRODUCTS, null, contentValues2);
                database.insert(DBHelper.TABLE_RECIPES, null, contentValues3);

                                break;


            case R.id.btnDelete: // удаление какого-то конкретного блюда
            {
                if (nameofdish.equalsIgnoreCase("")) {
                    break;
                }
                int updCount = database.delete(DBHelper.TABLE_MENU, DBHelper.KEY_NAMEOFDISH + " = ? ", new String[]{nameofdish});
                int updCount2 = database.delete(DBHelper.TABLE_LISTOFPRODUCTS, DBHelper.KEY_NUMBEROFDISH + " = ?", new String[]{nameofdish});
                int updCount3 = database.delete(DBHelper.TABLE_RECIPES, DBHelper.KEY_NAMEOFDISHINRECIPE + " = ?", new String[]{nameofdish});
                Log.d("mLog", "deleted rows count = " + updCount);
                Log.d("mLog", "deleted rows count = " + updCount2);
                Log.d("mLog", "deleted rows count = " + updCount3);
            }
            case R.id.button: // удаление полей
            {
               etNameofdish.setText("");
               etMealtime.setText("");
               etCategory.setText("");
               etIngredients1.setText("");
               etIngredients2.setText("");
               etIngredients3.setText("");
               etIngredients4.setText("");
               etIngredients5.setText("");
                etPrice1.setText("");
                etPrice2.setText("");
                etPrice3.setText("");
                etPrice4.setText("");
                etPrice5.setText("");
                text_of_recipe.setText("");
            }

        }
        dbHelper.close();
    }

    }






