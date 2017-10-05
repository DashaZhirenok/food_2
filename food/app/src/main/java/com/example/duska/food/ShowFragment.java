package com.example.duska.food;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Duska on 05.10.2017.
 */
public class ShowFragment extends Fragment {
    Button show;
    TextView textmenu, textshow;
    DBHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_show, container, false);

        textmenu = (TextView) view.findViewById(R.id.textmenu);
        textshow = (TextView) view.findViewById(R.id.textshow);
        show = (Button) view.findViewById(R.id.show);

        show.setOnClickListener(OncShow);
        dbHelper = new DBHelper(getActivity().getApplicationContext());

        return view;

    }

    View.OnClickListener OncShow = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!TextUtils.isEmpty(textshow.getText().toString())) //проверка условия на то, что уже textshow не пустые
            {
                return;
            }

            SQLiteDatabase database = dbHelper.getWritableDatabase();

            // Делаем запрос
            Cursor cursor3 = database.query(DBHelper.TABLE_MENU, null, null, null, null, null, null);
            Cursor cursor4 = database.query(DBHelper.TABLE_LISTOFPRODUCTS, null,null, null, null, null,null);
            Cursor cursor5 = database.query(DBHelper.TABLE_RECIPES, null,null, null, null, null,null);
            try {
                //  textmenu.setText("List of menu\n");
                /// textmenu.append(DBHelper.KEY_NAMEOFDISH + ", " +
                //      DBHelper.KEY_MEALTIME + ", " +
                //    DBHelper.KEY_CATEGORY + "\n");

                // Узнаем индекс каждого столбца
                int idColumnIndex = cursor3.getColumnIndex(DBHelper.KEY_ID);
                int IngredientColumnIndex = cursor4.getColumnIndex(DBHelper.KEY_INGREDIENT);
                int Ingredient2ColumnIndex = cursor4.getColumnIndex(DBHelper.KEY_INGREDIENT);
                int Ingredient3ColumnIndex = cursor4.getColumnIndex(DBHelper.KEY_INGREDIENT);
                int Ingredient4ColumnIndex = cursor4.getColumnIndex(DBHelper.KEY_INGREDIENT);
                int Ingredient5ColumnIndex = cursor4.getColumnIndex(DBHelper.KEY_INGREDIENT);

                int id2ColumnIndex = cursor4.getColumnIndex(DBHelper.KEY_ID2);
                int MealtimeColumnIndex = cursor3.getColumnIndex(DBHelper.KEY_MEALTIME);
                int NameofdishColumnIndex = cursor3.getColumnIndex(DBHelper.KEY_NAMEOFDISH);
                int CategoryColumnIndex = cursor3.getColumnIndex(DBHelper.KEY_CATEGORY);

                int RecipeColumnIndex = cursor5.getColumnIndex(DBHelper.KEY_RECIPE);


                // Проходим через все ряды
                while (cursor3.moveToNext() && cursor4.moveToNext() & cursor5.moveToNext()) {
                    // Используем индекс для получения строки или числа
                    int currentID2 = cursor4.getInt(id2ColumnIndex);
                    int currentID = cursor3.getInt(idColumnIndex);
                    String currentNameofdish = cursor3.getString(NameofdishColumnIndex);
                    String currentMealtime = cursor3.getString(MealtimeColumnIndex);
                    String currentCategory = cursor3.getString(CategoryColumnIndex);

                    String currentIngredient = cursor4.getString(IngredientColumnIndex);
                    cursor4.moveToNext();
                    String currentIngredient2 = cursor4.getString(Ingredient2ColumnIndex);
                    cursor4.moveToNext();
                    String currentIngredient3 = cursor4.getString(Ingredient3ColumnIndex);
                    cursor4.moveToNext();
                    String currentIngredient4 = cursor4.getString(Ingredient4ColumnIndex);
                    cursor4.moveToNext();
                    String currentIngredient5 = cursor4.getString(Ingredient5ColumnIndex);

                    String currentRecipe = cursor5.getString(RecipeColumnIndex);

                    // Выводим значения каждого столбца
                    textshow.append(("\n"+ "The dish " + currentID + ": " + "\n" +
                            currentNameofdish + " (" +
                            currentMealtime + ", " +
                            currentCategory + "). "));

                    if (TextUtils.isEmpty(currentIngredient)) //проверка условия на то, что ingredient существует
                    {
                        return;
                    }else
                    {
                        textshow.append(("\n"  + "Ingredients: " + "\n" + "1. " +
                                currentIngredient + "\n"));
                    }

                    if (TextUtils.isEmpty(currentIngredient2)) //проверка условия на то, что ingredient существует
                    {
                        return;
                    }else
                    {
                        textshow.append(("2. " + currentIngredient2 + "\n"));
                    }
                    if (TextUtils.isEmpty(currentIngredient3)) //проверка условия на то, что ingredient существует
                    {
                        return;
                    }else
                    {
                        textshow.append(("3. " + currentIngredient3 + "\n"));
                    }
                    if (TextUtils.isEmpty(currentIngredient4)) //проверка условия на то, что ingredient существует
                    {
                        return;
                    }else
                    {
                        textshow.append(("4. " + currentIngredient4 + "\n"));
                    }
                    if (TextUtils.isEmpty(currentIngredient5)) //проверка условия на то, что ingredient существует
                    {
                        return;
                    }else
                    {
                        textshow.append(("5. " + currentIngredient5 + "\n"));
                    }

                    textshow.append(("\n"));

                    if (TextUtils.isEmpty(currentRecipe)) //проверка условия на то, что recipe существует
                    {
                        textshow.append(("\n"+"____________________________"));
                        return;
                    }else
                    {
                        textshow.append(("Text of recipe: " + "\n" + currentRecipe));
                    }
                    textshow.append(("\n"+"____________________________"));

                }
            }
            finally {
                // Всегда закрываем курсор после чтения
                cursor3.close();
                cursor4.close();
            }

        }
    };


}
