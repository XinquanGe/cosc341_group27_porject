package com.example.cosc341_porject;

import java.io.Serializable;
import java.util.ArrayList;

public class order implements Serializable {
    private Restaurant restaurant=null;
    private ArrayList<Dish> an_order=null;
    private double tprice;
    private boolean paid = false;
    public order(){}
    public order(Restaurant restaurant, ArrayList<Dish> an_order, double tprice){
        this.an_order=an_order;
        this.restaurant=restaurant;
        this.tprice=tprice;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public ArrayList<Dish> getAn_order() {
        return an_order;
    }

    public void setAn_order(ArrayList<Dish> an_order) {
        this.an_order = an_order;
    }

    public double getTprice() {
        return tprice;
    }

    public void setTprice(double tprice) {
        this.tprice = tprice;
    }
}
