package com.example.cosc341_porject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class OrderList extends AppCompatActivity {
    private ListView lvOrder;
    private Button bt1;
    private TextView tv_orderlist1;
    LocalData localData = new LocalData();
    ArrayList<order> orders = new ArrayList<order>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

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
        tv_orderlist1=findViewById(R.id.tv_orderlist_1);
        tv_orderlist1.setText("Hello! "+user);
        orders=localData.getOrders();

        lvOrder=findViewById(R.id.lv_orderlist);
        listViewAdapterOrderPage orderPageAdapter = new listViewAdapterOrderPage(OrderList.this,orders);
        lvOrder.setAdapter(orderPageAdapter);


        lvOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder dialog = new AlertDialog.Builder (OrderList.this);

                dialog.setTitle("Pay Confirm");
                dialog.setMessage("You want to pay?");
                dialog.setCancelable(true);
                dialog.setPositiveButton("OK", new DialogInterface. OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        orders.get(i).setPaid(true);
                        orderPageAdapter.notifyDataSetChanged();

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

                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface. OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dialog.show();

            }
        });
        bt1=findViewById(R.id.orderlist_home);


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderList.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}