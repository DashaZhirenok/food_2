package com.example.duska.food;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Duska on 05.10.2017.
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    private FloatingActionButton btnAdd;
    private EditText etNameofdish, etCookingTime, etIngredients1;
    private TextView text_of_recipe;
    private Spinner spinnerCategory;
    private String[] categories = { "Вегетарианские блюда", "Выпечка", "Горячее", "Завтраки", "Заготовки", "Закуски",
            "Десерты", "Рецепты для микроволновой печи", "Рецепты для мультиварки", "Рецепты для хлебопечки",
            "Напитки", "Салаты", "Соусы" , "Супы"}; //14

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HomeActivity.dbHelper = new DBHelper(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_main, container, false);

        btnAdd = (FloatingActionButton) view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        etNameofdish = (EditText) view.findViewById(R.id.etNameofdish);
        etCookingTime = (EditText) view.findViewById(R.id.etCookingTime);

        etIngredients1 =(EditText) view.findViewById(R.id.etIngredients1);

        text_of_recipe = (TextView) view.findViewById(R.id.text_of_recipe);

        spinnerCategory = (Spinner) view.findViewById(R.id.spinnerCategory);

        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapterForSpinner = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, categories);
        // Определяем разметку для использования при выборе элемента
        adapterForSpinner.setDropDownViewResource(R.layout.spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinnerCategory.setAdapter(adapterForSpinner);
        spinnerCategory.setOnItemSelectedListener(onClickSpinner);

        return view;

    }

    @Override
    public void onClick(View view) {


        Boolean isFound = false;
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
            String category = spinnerCategory.getSelectedItem().toString();
            String cookingtime = etCookingTime.getText().toString();

            // the second table
            String ingredients1 = etIngredients1.getText().toString();

            // the third table
            String recipe = text_of_recipe.getText().toString();

            SQLiteDatabase database_2 = HomeActivity.dbHelper.getReadableDatabase();

            ContentValues contentValues = new ContentValues();
            ContentValues contentValues2 = new ContentValues();


            switch (view.getId()) {

                case R.id.btnAdd: //добавление данных в таблицу

                {
                    Cursor cursor = database_2.query(DBHelper.TABLE_MENU, null, null, null, null, null, null);
                    try {
                        int nameofdishColumnIndex = cursor.getColumnIndex(DBHelper.KEY_NAMEOFDISH);

                        while (cursor.moveToNext()){
                            String currentNameofdish = cursor.getString(nameofdishColumnIndex);
                            if(currentNameofdish.equalsIgnoreCase(nameofdish)||currentNameofdish.equalsIgnoreCase(nameofdish+" ")) {
                                isFound = true;
                                break;
                            }

                        }
                    }
                    finally {
                        cursor.close();
                    }

                    if(isFound)
                    {
                        Toast toast = Toast.makeText(getContext(),
                                "Повтор", Toast.LENGTH_SHORT);
                        toast.show();
                        break;
                    }
                    else {
                        SQLiteDatabase database = HomeActivity.dbHelper.getWritableDatabase();
                        contentValues.put(DBHelper.KEY_NAMEOFDISH, nameofdish);
                        contentValues.put(DBHelper.KEY_CATEGORY, category);
                        contentValues.put(DBHelper.KEY_COOKINGTIME, cookingtime);
                        contentValues.put(DBHelper.KEY_INGREDIENTS, ingredients1);

                        contentValues2.put(DBHelper.KEY_RECIPE, recipe);
                        contentValues2.put(DBHelper.KEY_NAMEOFDISHINRECIPE, nameofdish);

                        database.insert(DBHelper.TABLE_MENU, null, contentValues);
                        database.insert(DBHelper.TABLE_RECIPES, null, contentValues2);
                        break;
                    }


                }

                /*case R.id.btnDelete: // удаление какого-то конкретного блюда
                {
                    if (nameofdish.equalsIgnoreCase("")) {
                        break;
                    }
                    SQLiteDatabase database = HomeActivity.dbHelper.getWritableDatabase();
                    int updCount = database.delete(DBHelper.TABLE_MENU, DBHelper.KEY_NAMEOFDISH + " = ? ", new String[]{nameofdish});
                    int updCount3 = database.delete(DBHelper.TABLE_RECIPES, DBHelper.KEY_NAMEOFDISHINRECIPE + " = ?", new String[]{nameofdish});
                    Log.d("mLog", "deleted rows count = " + updCount);
                    Log.d("mLog", "deleted rows count = " + updCount3);

                    break;
                }*/

                case R.layout.spinner_item:
                {
                    if(TextUtils.isEmpty(etNameofdish.getText().toString()))
                    break;
                }


            }
            HomeActivity.dbHelper.close();
        }
    }


    AdapterView.OnItemSelectedListener onClickSpinner = new AdapterView.OnItemSelectedListener(){

        public void onItemSelected(AdapterView<?> parent,
                                   View itemSelected, int selectedItemPosition, long selectedId) {

            if(itemSelected!=null) {
                itemSelected.setEnabled(false);
                itemSelected.setClickable(false);
                //((EditText) itemSelected).setTextColor(getResources().getColor(R.color.color_text));
                Toast toast = Toast.makeText(getContext(),
                        "Ваш выбор: " + categories[selectedItemPosition], Toast.LENGTH_SHORT);
                toast.show();
            }


        }
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };

}
