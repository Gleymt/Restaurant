package com.restaurant.utils;

import com.restaurant.Food;
import com.restaurant.FoodTypes;

public class Helpers {
    public static boolean checkStringLength(String entry, int length) {
        return false;
    }


    public static void typeCheck(Food food) {

        if (food.getPrice() <= 5) {
            food.setType(FoodTypes.CHEAP.toString());
        } else if (food.getPrice() > 5 && food.getPrice() <= 15) {
            food.setType(FoodTypes.NORMAL.toString());
        } else {
            food.setType(FoodTypes.EXPENSIVE.toString());
        }
    }
}
