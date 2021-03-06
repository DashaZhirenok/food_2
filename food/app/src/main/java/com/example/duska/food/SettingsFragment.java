package com.example.duska.food;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Duska on 05.10.2017.
 */
public class SettingsFragment extends Fragment implements View.OnClickListener {

    private Button btn_readLog, btn_deleteAll;
    private DBHelper dbHelper;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_settings, container, false);

        btn_readLog = (Button) view.findViewById(R.id.btn_readLog);
        btn_readLog.setOnClickListener(this);

        btn_deleteAll = (Button) view.findViewById(R.id.btn_deleteAll);
        btn_deleteAll.setOnClickListener(this);
        dbHelper = new DBHelper(getActivity());

        return view;

    }

    @Override
    public void onClick(View view) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        switch (view.getId())
        {
            case R.id.btn_deleteAll: //полное удаление данных из таблицы
                database.delete(DBHelper.TABLE_MENU, null, null);
                database.delete(DBHelper.TABLE_RECIPES, null, null);
                break;

            case R.id.btn_readLog: //чтение данных в log только из таблиц без рецептов
                Cursor cursor = database.query(DBHelper.TABLE_MENU, null, null, null, null, null, null);
                Cursor cursor2 = database.query(DBHelper.TABLE_RECIPES, null, null, null, null, null, null);

                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                    int nameofdishIndex = cursor.getColumnIndex(DBHelper.KEY_NAMEOFDISH);
                    int categoryIndex = cursor.getColumnIndex(DBHelper.KEY_CATEGORY);
                    int cookingtimeIndex = cursor.getColumnIndex(DBHelper.KEY_COOKINGTIME);
                    int ingredientsIndex = cursor.getColumnIndex(DBHelper.KEY_INGREDIENTS);

                    do {
                        Log.d("mLog", "-------------------1--------------" + "ID = " + cursor.getInt(idIndex) +
                                ", name of dish = " + cursor.getString(nameofdishIndex)+
                                ", category = " + cursor.getString(categoryIndex) +
                                ", cooking time = " + cursor.getString(cookingtimeIndex) +
                                ", ingredients = " + cursor.getString(ingredientsIndex));
                    } while (cursor.moveToNext());
                } else
                    Log.d("mLog", "0 rows");

                cursor.close();

                if(cursor2.moveToNext()){
                    int idIndex2  = cursor2.getColumnIndex(DBHelper.KEY_ID2);
                    int recipeIndex = cursor2.getColumnIndex(DBHelper.KEY_RECIPE);

                    do {
                        Log.d(
                                "mLog", "-------------------2--------------" +
                                "ID = " + cursor2.getString(idIndex2) +
                                ", recipe = " + cursor2.getString(recipeIndex));
                    } while (cursor2.moveToNext());

                } else
                    Log.d("mLog", "0 rows");

                cursor2.close();

                break;


        }


    }
}
