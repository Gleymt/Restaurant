package com.restaurant;

import com.restaurant.database.DalFood;

public class Main {
    public static void main(String[] args) {
        try {
            DalFood dalFood = new DalFood();
            dalFood.showFood();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
