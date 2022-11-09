package com.restaurant.utils;

import com.restaurant.Food;
import com.restaurant.FoodTypes;

public class Helpers {

    public static void typeCheck(Food food) {
        if (food.getPrice() <= 5) {
            food.setType(FoodTypes.CHEAP.name());
        } else if (food.getPrice() > 5 && food.getPrice() <= 15) {
            food.setType(FoodTypes.NORMAL.name());
        } else {
            food.setType(FoodTypes.EXPENSIVE.name());
        }
    }

    public static void printMenu(String[] options) {
        for (String option : options) {
            System.out.println(option);
        }
    }

    public static boolean isFloat(String number) {
        try {
            Float.parseFloat(number);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
