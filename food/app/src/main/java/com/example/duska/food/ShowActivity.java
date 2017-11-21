package com.example.duska.food;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private GridLayoutManager lLayout;
    private DBHelper dbHelper;
    private String currentCategoryFromIntent;
    private int currentIconFromIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(onClickBack);

        List<ItemObject> rowListItem = getAllItemList();
        lLayout = new GridLayoutManager(ShowActivity.this, 2);

        recyclerView.setLayoutManager(lLayout);

        RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(ShowActivity.this, rowListItem);
        recyclerView.setAdapter(rcAdapter);

    }

    View.OnClickListener onClickBack = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ShowActivity.this.finish();
        }
    };

    private List<ItemObject> getAllItemList(){

        List<ItemObject> allItems = new ArrayList<ItemObject>();
        dbHelper = new DBHelper(this);
        //откроем для чтения
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        // Делаем запрос
        Cursor cursor = database.query(DBHelper.TABLE_MENU, null, null, null, null, null, null);

        ArrayList<String> nameOfDishList = new ArrayList<>();
        ArrayList<Integer> imgPhotoOfDish = new ArrayList<>();

        try {
            int nameofdishColumnIndex = cursor.getColumnIndex(DBHelper.KEY_NAMEOFDISH);
            int categoryColumnIndex = cursor.getColumnIndex(DBHelper.KEY_CATEGORY);

            Intent intentFromFragmentCategory = getIntent();
            currentCategoryFromIntent = intentFromFragmentCategory.getStringExtra("Category");
            currentIconFromIntent = intentFromFragmentCategory.getIntExtra("Icon",0);

            while (cursor.moveToNext()){

                String currentCategory = cursor.getString(categoryColumnIndex);

                if(currentCategory.equalsIgnoreCase(currentCategoryFromIntent)){
                    String currentNameofdish = cursor.getString(nameofdishColumnIndex);
                    nameOfDishList.add(currentNameofdish);
                    imgPhotoOfDish.add(currentIconFromIntent);
                }

            }
        }

        finally {
            cursor.close();
        }

        for(int i=0; i<nameOfDishList.size(); i++){
            allItems.add(new ItemObject(nameOfDishList.get(i), imgPhotoOfDish.get(i)));
        }


        return allItems;
    }


}
