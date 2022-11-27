package com.example.cosc341_porject;

import java.io.Serializable;
import java.util.ArrayList;

public class LocalData implements Serializable {
    private User LogInUser;
    private  ArrayList<User> users=new ArrayList<User>();
    private ArrayList<Restaurant> restaurants=new ArrayList<Restaurant>();
    private ArrayList<order> orders=new ArrayList<order>();

    public LocalData(){
    }

    public User getLogInUser() {
        return LogInUser;
    }

    public void setLogInUser(User logInUser) {
        LogInUser = logInUser;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public ArrayList<order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<order> orders) {
        this.orders = orders;
    }
}
