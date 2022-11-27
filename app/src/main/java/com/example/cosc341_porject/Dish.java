package com.example.cosc341_porject;

import java.io.Serializable;

public class Dish implements Serializable {
    private String name;
    private double price;
    private String describe;
    private  int num =0;

    public Dish(String name, double price){
        this.name=name;
        this.price=price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
