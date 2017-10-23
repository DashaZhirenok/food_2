package com.example.duska.food;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    private GridLayoutManager lLayout;
    DBHelper dbHelper;

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

        RecyclerView rView = (RecyclerView)findViewById(R.id.recycler_view);

        rView.setLayoutManager(lLayout);

        RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(ShowActivity.this, rowListItem);
        rView.setAdapter(rcAdapter);

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
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        // Делаем запрос
        Cursor cursor = database.query(DBHelper.TABLE_MENU, null, null, null, null, null, null);
        ArrayList<String> nameOfDishList = new ArrayList<>();
        ArrayList<Integer> imgPhotoOfDish = new ArrayList<>();
        try {
            int nameofdishColumnIndex = cursor.getColumnIndex(DBHelper.KEY_NAMEOFDISH);

            while (cursor.moveToNext()){
                String currentNameofdish = cursor.getString(nameofdishColumnIndex);
                nameOfDishList.add(currentNameofdish);
                imgPhotoOfDish.add(R.drawable.im_soup);
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
