package com.example.cosc341_porject;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class InformationActivity extends AppCompatActivity {
    private TextView tv1, tv2, tv3, tv_in1;
    private Button bt1;
    LocalData localData = null;
    String input = null;
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

        ImageView mapBtn = findViewById(R.id.mapBtn);

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InformationActivity.this, MapsActivity.class);
                intent.putExtra("address",restaurant.getAddress());
                intent.putExtra("name",restaurant.getName());
                startActivity(intent);
            }
        });

        TextView text1 = findViewById(R.id.textView18);
        TextView text2 = findViewById(R.id.textView19);
        TextView text3 = findViewById(R.id.textView20);
        TextView text4 = findViewById(R.id.textView21);

        EditText txt = (EditText) findViewById(R.id.txt);

        text1.setText("John\n\tLovely food!");
        text2.setText("David\n\tGreat Place!");
        text3.setText("Robert\n\t10/10!");


        txt.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent)
            {
                if (id == EditorInfo.IME_NULL)
                {
                    String str = "Tom\n\t" + "good" ;//txt.getText().toString();

                    text4.setText(str);

                    txt.setText("Write a comment...");

                    return true;
                }
                return false;
            }
        });
    }
}