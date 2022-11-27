package com.example.cosc341_porject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;



public class ThreadTest extends Thread{

    public void run(){

        String filePath ="systext.txt";
        File file = new File(filePath);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        ObjectOutputStream oos = null;

            try {
                oos = new ObjectOutputStream(new FileOutputStream(filePath));
                Dish dish1=new Dish("meal 1",10.00);
                Dish dish2=new Dish("meal 2",12.00);
                Dish dish3=new Dish("meal 3",14.00);

                Restaurant restaurant1 = new Restaurant();
                Restaurant restaurant2 = new Restaurant();

                ArrayList<Dish> menu = new ArrayList<Dish>();
                menu.add(dish1);
                menu.add(dish2);
                menu.add(dish3);
                restaurant1.setName("Wc");
                restaurant2.setName("LFC");
                restaurant1.setId(1);
                restaurant2.setId(2);

                restaurant1.setMenu(menu);
                restaurant2.setMenu(menu);
                ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
                restaurants.add(restaurant1);
                restaurants.add(restaurant2);
                LocalData localData = new LocalData();
                localData.setRestaurants(restaurants);

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
                }
            }
        }

    }
}
