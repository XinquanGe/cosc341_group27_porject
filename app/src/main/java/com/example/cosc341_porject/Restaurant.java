package com.example.cosc341_porject;

import java.io.Serializable;
import java.util.ArrayList;

public class Restaurant implements Serializable {
    private int id;
    private String name;
    private String address;
    private String hours;
    private ArrayList<Dish> menu;

    public Restaurant(){}
    public Restaurant(int id, String name, String address, String hours){
        this.id=id;
        this.name=name;
        this.address=address;
        this.hours=hours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public ArrayList<Dish> getMenu() {
        return menu;
    }

    public void setMenu(ArrayList<Dish> menu) {
        this.menu = menu;
    }

    void addMenu(Dish dish){
        menu.add(dish);
    }

}
