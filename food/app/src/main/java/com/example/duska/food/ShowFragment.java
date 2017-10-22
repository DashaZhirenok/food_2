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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeActivity.dbHelper = new DBHelper(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_show, container, false);

        textmenu = (TextView) view.findViewById(R.id.textmenu);
        textshow = (TextView) view.findViewById(R.id.textshow);
        show = (Button) view.findViewById(R.id.show);

        show.setOnClickListener(OncShow);

        return view;

    }

    View.OnClickListener OncShow = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!TextUtils.isEmpty(textshow.getText().toString())) //проверка условия на то, что уже textshow не пустые
            {
                return;
            }

            SQLiteDatabase database = HomeActivity.dbHelper.getWritableDatabase();

            // Делаем запрос
            Cursor cursor3 = database.query(DBHelper.TABLE_MENU, null, null, null, null, null, null);
            Cursor cursor5 = database.query(DBHelper.TABLE_RECIPES, null,null, null, null, null,null);
            try {
                //  textmenu.setText("List of menu\n");
                /// textmenu.append(DBHelper.KEY_NAMEOFDISH + ", " +
                //      DBHelper.KEY_MEALTIME + ", " +
                //    DBHelper.KEY_CATEGORY + "\n");

                // Узнаем индекс каждого столбца

                int idColumnIndex = cursor3.getColumnIndex(DBHelper.KEY_ID);
                int NameofdishColumnIndex = cursor3.getColumnIndex(DBHelper.KEY_NAMEOFDISH);
                int CategoryColumnIndex = cursor3.getColumnIndex(DBHelper.KEY_CATEGORY);
                int CookingTimeColumnIndex = cursor3.getColumnIndex(DBHelper.KEY_COOKINGTIME);
                int IngredientsColumnIndex = cursor3.getColumnIndex(DBHelper.KEY_INGREDIENTS);

                int RecipeColumnIndex = cursor5.getColumnIndex(DBHelper.KEY_RECIPE);


                // Проходим через все ряды
                while (cursor3.moveToNext() & cursor5.moveToNext()) {
                    // Используем индекс для получения строки или числ
                    int currentID = cursor3.getInt(idColumnIndex);
                    String currentNameofdish = cursor3.getString(NameofdishColumnIndex);
                    String currentCategory = cursor3.getString(CategoryColumnIndex);
                    String currentCookingTime = cursor3.getString(CookingTimeColumnIndex);
                    String currentIngredients = cursor3.getString(IngredientsColumnIndex);

                    String currentRecipe = cursor5.getString(RecipeColumnIndex);

                    // Выводим значения каждого столбца
                    textshow.append(("\n"+ "The dish " + currentID + ": " + "\n" +
                            currentNameofdish + " (" +
                            currentCategory + "). "));

                    if (TextUtils.isEmpty(currentIngredients)) //проверка условия на то, что ingredient существует
                    {
                        return;
                    }else
                    {
                        textshow.append(("\n"  + "Ingredients: " + "\n" + "1. " +
                                currentIngredients + "\n"));
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
                cursor5.close();
                HomeActivity.dbHelper.close();
            }

        }
    };


}
