package com.example.cosc341_porject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MenuPage extends AppCompatActivity {
    private ListView lvMenu;
    private TextView tv1,tv_menu1;
    private Button bt1;
    private order or=new order();
    double totalNumber = 0;
    ArrayList<Dish> dishes = new ArrayList<Dish>();
    LocalData localData= new LocalData();
    ArrayList<order> orders = new ArrayList<order>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);

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
            if(localData!=null){
                orders = localData.getOrders();
                Log.d("as", "localDate Read secessed");
            }
        String user="XXX";
        if( localData.getLogInUser()!=null){
            user = localData.getLogInUser().getName();
        }
        tv_menu1=findViewById(R.id.tv_menu_1);
        tv_menu1.setText("Hello! "+user);


        Intent intent = getIntent();
        Restaurant restaurant = (Restaurant) intent.getSerializableExtra("Restaurant");

        Log.d("as", " "+restaurant.getName()+" r"+restaurant.getMenu().get(0).getName());

        tv1=findViewById(R.id.tv_totalprice);
        lvMenu=findViewById(R.id.lv_menu);
        listViewAdapterMenu menuAdapter = new listViewAdapterMenu(MenuPage.this,restaurant.getMenu());
        lvMenu.setAdapter(menuAdapter);


        menuAdapter.notifyDataSetChanged();
        menuAdapter.setPricesListener(new listViewAdapterMenu.PricesListener() {

        public void totalPrices(){
            totalNumber = 0;
            for (int i=0;i<restaurant.getMenu().size();i++){
                totalNumber= totalNumber + restaurant.getMenu().get(i).getPrice()*restaurant.getMenu().get(i).getNum();
                tv1.setText("Total price: $ "+totalNumber);
            }
        }
        });

        bt1=findViewById(R.id.bt_me_order);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i=0;i<restaurant.getMenu().size();i++){
                    if(restaurant.getMenu().get(i).getNum()!=0)
                        dishes.add(restaurant.getMenu().get(i));
                }

                if(dishes.size()==0){
                    Toast.makeText(MenuPage.this, "Order is empty!", Toast.LENGTH_SHORT).show();
                }else{
                    Log.d("as",restaurant.getName());
                or.setRestaurant(restaurant);
                or.setTprice(totalNumber);
                or.setAn_order(dishes);


                    Log.d("test1","Restaurantnum "+localData.getRestaurants().size());
                    orders.add(or);
                    localData.setOrders(orders);
                    FileOutputStream ops = null;
                    try {
                        ops = openFileOutput(filePath, MODE_PRIVATE);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    Log.d("test2","ordernum "+localData.getOrders().size());
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

                Intent intent1 = new Intent(MenuPage.this,MainActivity.class);
                startActivity(intent1);
                }
            }
        });
    }
}