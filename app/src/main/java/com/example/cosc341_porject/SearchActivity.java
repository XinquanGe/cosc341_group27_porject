package com.example.cosc341_porject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    private ListView lv1;

    ArrayList<Restaurant> restaurants =new ArrayList<Restaurant>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent intent = getIntent();
       restaurants = (ArrayList<Restaurant>) intent.getSerializableExtra("Restaurants");

        lv1=findViewById(R.id.lv_search);

        lv1.setAdapter(new listViewAdapter(SearchActivity.this,restaurants));

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(SearchActivity.this,InformationActivity.class);
                intent.putExtra("Restaurant",restaurants.get(i));
                startActivity(intent);
            }
        });
    }
}