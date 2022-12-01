package com.example.cosc341_porject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class InformationActivity extends AppCompatActivity {
    private TextView tv1, tv2, tv3, tv_in1;
    private Button bt1;
    LocalData localData = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        String filePath = "systext.txt";
        FileInputStream ips = null;
        try {
            ips = openFileInput(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectInputStream ois = null;
        try{
            ois = new ObjectInputStream(ips);
            localData = (LocalData) ois.readObject();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(ips != null){
                try {
                    ips.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String user="XXX";
        if( localData.getLogInUser()!=null){
         user = localData.getLogInUser().getName();
        }

        tv1=findViewById(R.id.tv_restaurantName);
        tv2=findViewById(R.id.tv_restaurantTime);
        tv3=findViewById(R.id.tv_restaurantaddress);
        tv_in1=findViewById(R.id.tv_in_1);
        bt1=findViewById(R.id.bt_in_order);

        tv_in1.setText("Hello! "+user);

        Intent intent = getIntent();
        Restaurant restaurant = (Restaurant) intent.getSerializableExtra("Restaurant");

        tv1.setText(restaurant.getName());
        if(restaurant.getHours()!=null)
            tv2.setText("Opening Hours: "+restaurant.getHours());
        if(restaurant.getAddress()!=null)
            tv3.setText("Address: "+restaurant.getAddress());

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InformationActivity.this,MenuPage.class);
                intent.putExtra("Restaurant",restaurant);
                startActivity(intent);
            }
        });

        Button homeBtn = findViewById(R.id.home3);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InformationActivity.this,MainActivity.class);
                finish();
            }
        });


    }
}