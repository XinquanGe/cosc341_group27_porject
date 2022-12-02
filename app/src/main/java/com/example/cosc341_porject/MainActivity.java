package com.example.cosc341_porject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    private Button bt1, bt2, bt3, bt4;
    private EditText et1;
    private ImageButton ib1;
    private String s=null;
    private boolean isLogIn = false;
    LocalData localData = null;
    ArrayList<Restaurant> restaurants = null;
    ActivityResultLauncher<Intent> register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String filePath = "systext.txt";
        File file = new File(filePath);
        if (!file.exists()) {

        FileOutputStream ops = null;
        try {
            ops = openFileOutput(filePath, MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(ops);
            Dish dish1 = new Dish("meal 1", 10.00);
            Dish dish2 = new Dish("meal 2", 12.00);
            Dish dish3 = new Dish("meal 3", 14.50);
            Dish dish4 = new Dish("meal 4", 21.00);
            Dish dish5 = new Dish("meal 5", 31.50);
            Dish dish6 = new Dish("meal 6", 33.00);

            Restaurant restaurant1 = new Restaurant();
            Restaurant restaurant2 = new Restaurant();
            Restaurant restaurant3 = new Restaurant();
            Restaurant restaurant4 = new Restaurant();

            ArrayList<Dish> menu1 = new ArrayList<Dish>();
            menu1.add(dish1);
            menu1.add(dish2);
            menu1.add(dish3);

            ArrayList<Dish> menu2 = new ArrayList<Dish>();
            menu2.add(dish4);
            menu2.add(dish5);
            menu2.add(dish6);

            restaurant1.setName("Restaurant1");
            restaurant2.setName("Restaurant2");
            restaurant3.setName("Restaurant3");
            restaurant4.setName("Restaurant4");

            restaurant1.setAddress("A Way");
            restaurant2.setAddress("B Way");
            restaurant3.setAddress("C Way");
            restaurant4.setAddress("D Way");

            restaurant1.setHours("9:00 - 21:00");
            restaurant2.setHours("10:00 - 22:00");
            restaurant3.setHours("12:00 - 20:30");
            restaurant4.setHours("00:00 - 00:00");


            restaurant1.setId(1);
            restaurant2.setId(2);
            restaurant1.setId(3);
            restaurant2.setId(4);

            restaurant1.setMenu(menu1);
            restaurant2.setMenu(menu1);
            restaurant3.setMenu(menu2);
            restaurant4.setMenu(menu2);
            ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
            restaurants.add(restaurant1);
            restaurants.add(restaurant2);
            restaurants.add(restaurant3);
            restaurants.add(restaurant4);
            LocalData localData = new LocalData();
            localData.setRestaurants(restaurants);
            localData.setOrders(new ArrayList<order>());

            ArrayList<User> users=new ArrayList<User>();;
            User BlueCat = new User("Tom", "Cat");
            users.add(BlueCat);
            localData.setUsers(users);

            oos.writeObject(localData);
            oos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ops != null) {
                try {
                    ops.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

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
        if(localData!=null){
            restaurants = localData.getRestaurants();
        }else
            Log.d("localData Status","No localData Read");



        lv=findViewById(R.id.lv_1);
        lv.setAdapter(new listViewAdapter(MainActivity.this,restaurants));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(isLogIn==false){
                    Toast.makeText(MainActivity.this, "Please Log In", Toast.LENGTH_SHORT).show();
                }else{
                Intent intent = new Intent(MainActivity.this,InformationActivity.class);
                intent.putExtra("Restaurant",localData.getRestaurants().get(i));
                et1.setText("");
                startActivity(intent);
                }
            }
        });


        bt1=findViewById(R.id.bt_search);
        bt2=findViewById(R.id.home);
        bt3=findViewById(R.id.orderlist);
        bt4=findViewById(R.id.bt_startlogin);
        ib1=findViewById(R.id.ib);
        et1=findViewById(R.id.et_1);
        et1.setText("");


        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    s = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isLogIn==false){
                    Toast.makeText(MainActivity.this, "Please Log In", Toast.LENGTH_SHORT).show();
                }else {
                    if (s == null || s.equals("")) {
                        Toast.makeText(MainActivity.this, "Cannot searh with empty!", Toast.LENGTH_SHORT).show();
                    } else {
                        ArrayList<Restaurant> restaurants1 = new ArrayList<Restaurant>();
                        for (int i = 0; i < restaurants.size(); i++) {
                            if (restaurants.get(i).getName().contains(s)) {
                                restaurants1.add(restaurants.get(i));
                            }
                        }
                        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                        intent.putExtra("Restaurants", restaurants1);
                        et1.setText("");
                        startActivity(intent);
                    }
                }
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isLogIn==false){
                    Toast.makeText(MainActivity.this, "Please Log In", Toast.LENGTH_SHORT).show();
                }else{
                Intent intent = new Intent(MainActivity.this,OrderList.class);

                startActivity(intent);
            }
            }
        });

       register= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                    if(result!=null){
                        Intent intent = result.getData();
                        if(intent!=null && result.getResultCode()== Activity.RESULT_OK){

                            isLogIn = (boolean) intent.getSerializableExtra("isLogin");
                            User user = (User) intent.getSerializableExtra("User");

                            localData.setLogInUser(user);

                            bt4.setText("Hello! "+user.getName());

                            FileOutputStream ops = null;
                            try {
                                ops = openFileOutput(filePath, MODE_PRIVATE);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            ObjectOutputStream oos = null;
                            try {
                                oos=new ObjectOutputStream(ops);
                                oos.writeObject(localData);
                                oos.flush();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                if(oos != null){
                                    try {
                                        oos.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }if(ops != null){
                                    try {
                                        ops.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                        }
                    }
            }
        });

        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                register.launch(intent);
            }
        });

        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isLogIn==false){
                    Toast.makeText(MainActivity.this, "Please Log In", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(MainActivity.this,InformationActivity.class);
                    intent.putExtra("Restaurant",localData.getRestaurants().get(0));
                    et1.setText("");
                    startActivity(intent);
                }
            }
        });
    }
}

