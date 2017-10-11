package com.example.duska.food;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Duska on 05.10.2017.
 */
public class MainFragment extends Fragment implements View.OnClickListener{

    private Button btnAdd, btnDelete;
    private EditText etNameofdish, etMealtime, etCategory, etCookingTime, etIngredients1;
    private TextView text_of_recipe;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HomeActivity.dbHelper = new DBHelper(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_main, container, false);

        btnAdd = (Button) view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnDelete = (Button) view.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(this);

        etNameofdish = (EditText) view.findViewById(R.id.etNameofdish);
        etMealtime = (EditText) view.findViewById(R.id.etMealtime);
        etCategory = (EditText) view.findViewById(R.id.etCategory);
        etCookingTime = (EditText) view.findViewById(R.id.etCookingTime);

        etIngredients1 =(EditText) view.findViewById(R.id.etIngredients1);

        text_of_recipe = (TextView) view.findViewById(R.id.text_of_recipe);

        return view;

    }


    @Override
    public void onClick(View view) {
        if (TextUtils.isEmpty(etNameofdish.getText().toString())) // если поле Name_of_dish - пустое
        {
            Toast.makeText(getActivity().getApplicationContext(),
                    "Please, fill in the field 'Name of dish'",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            // the first table
            String nameofdish = etNameofdish.getText().toString();
            String mealtime = etMealtime.getText().toString();
            String category = etCategory.getText().toString();
            String cookingtime = etCookingTime.getText().toString();

            // the second table
            String ingredients1 = etIngredients1.getText().toString();

            // the third table
            String recipe = text_of_recipe.getText().toString();

            SQLiteDatabase database = HomeActivity.dbHelper.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            ContentValues contentValues2 = new ContentValues();
            ContentValues contentValues3 = new ContentValues();


            switch (view.getId()) {

                case R.id.btnAdd: //добавление данных в таблицу
                    contentValues.put(DBHelper.KEY_NAMEOFDISH, nameofdish);
                    contentValues.put(DBHelper.KEY_MEALTIME, mealtime);
                    contentValues.put(DBHelper.KEY_CATEGORY, category);
                    contentValues.put(DBHelper.KEY_COOKINGTIME, cookingtime);

                    contentValues2.put(DBHelper.KEY_NUMBEROFDISH, nameofdish);
                    contentValues2.put(DBHelper.KEY_INGREDIENT, ingredients1);

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


            }
            HomeActivity.dbHelper.close();
        }
    }
}
