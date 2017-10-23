package com.example.duska.food;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView tvNameOfDish;
    public ImageView nameOfDish_Photo;

    public RecyclerViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        tvNameOfDish = (TextView)itemView.findViewById(R.id.tvNameOfDish);
        nameOfDish_Photo = (ImageView)itemView.findViewById(R.id.nameOfDish_Photo);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Clicked Country Position = " + getPosition(), Toast.LENGTH_SHORT).show();
    }
}