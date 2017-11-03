package com.example.duska.food;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Duska on 12.10.2017.
 */
public class CategoryFragment extends Fragment {

    ListView lvCategory;
    ArrayList<HashMap<String, Object>> myArrayList;
    private SimpleAdapter adapter;
    private HashMap<String, Object> map;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_category, container, false);

        lvCategory = (ListView) view.findViewById(R.id.lvCategory);

        setLvCategory();

        lvCategory.setAdapter(adapter);
        lvCategory.setOnItemClickListener(onClick);

        return view;

    }

    public AdapterView.OnItemClickListener onClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            //передаем категорию
            HashMap<String, Object> currentMap = new HashMap<>();
            currentMap = (HashMap) lvCategory.getItemAtPosition(position);
            String currentCategoryName = (String) currentMap.get("Category");
            Integer currentCategoryImg = (Integer) currentMap.get("Icon");

            Intent goToCurrentCategory = new Intent(CategoryFragment.this.getActivity(), ShowActivity.class);
            goToCurrentCategory.putExtra("Category", currentCategoryName);
            goToCurrentCategory.putExtra("Icon", currentCategoryImg);
            startActivity(goToCurrentCategory);

        }
    };

    private void setLvCategory(){
        // Создадим массивы имён категорий
        String[] categories = { "Вегетарианские блюда", "Выпечка", "Горячее", "Завтраки", "Заготовки", "Закуски", "Десерты", "Рецепты для микроволновой печи",
                "Рецепты для мультиварки", "Рецепты для хлебопечки", "Напитки", "Салаты", "Соусы" , "Супы"}; //14

        myArrayList = new ArrayList<HashMap<String, Object>>();

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

        adapter = new SimpleAdapter(getContext(), myArrayList, R.layout.category_list_row, from, to);
    }

}
