package com.example.duska.food;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by Duska on 12.10.2017.
 */
public class CategoryFragment extends Fragment {

    private ImageView btn_go;
    ListView lvCategory;
    ArrayList<HashMap<String, Object>> myArrayList;
    SimpleAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        lvCategory = (ListView) view.findViewById(R.id.lvCategory);
       // btn_go = (Button) view.findViewById(R.id.ColName);
        // Создадим массивы имён котов
        String[] categories = { "Вегетарианские блюда", "Выпечка", "Горячее", "Завтраки", "Заготовки", "Закуски", "Десерты", "Рецепты для микроволновой печи",
                "Рецепты для мультиварки", "Рецепты для хлебопечки", "Напитки", "Салаты", "Соусы" , "Супы"}; //14

        myArrayList = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> map;

        int[] imgIcon = { R.drawable.im_vegan, R.drawable.im_baking, R.drawable.im_hot, R.drawable.im_breakfast,R.drawable.im_jam,
                R.drawable.im_snacks, R.drawable.im_dessert, R.drawable.im_microwave, R.drawable.im_slow_cooker, R.drawable.im_bread,
                R.drawable.im_drink, R.drawable.im_salad, R.drawable.im_sauce, R.drawable.im_soup};
        // заполняем map
        for (int i=0; i<categories.length; i++) {
            map = new HashMap<String, Object>();
            map.put("Icon", imgIcon[i]);
            map.put("Category", categories[i]);
            myArrayList.add(map);
        }

        // Массив имен атрибутов, из которых будут читаться данные
        String[] from = { "Icon", "Category" };

        // Массив идентификаторов компонентов, в которые будем вставлять данные
        int[] to = { R.id.ColIcon, R.id.ColName};

        adapter = new SimpleAdapter(getContext(), myArrayList,
                R.layout.category_list_row, from, to);
        lvCategory.setAdapter(adapter);
        lvCategory.setOnItemClickListener(onClick);

        return view;

    }

    public AdapterView.OnItemClickListener onClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            HashMap<String, Objects> currentMap = new HashMap<>();
            currentMap = (HashMap) lvCategory.getItemAtPosition(position);

            Intent myIntent = new Intent(CategoryFragment.this.getActivity(), ShowActivity.class);
            startActivity(myIntent);

        }
    };


}
