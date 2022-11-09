package com.restaurant.utils;

import com.restaurant.Food;

import java.util.Scanner;

public class Builders {

    private static final String pattern = "^[A-Za-z\\s !.?',~`:\"]*$";

    public static Food takeUserInputAndBuildFood() {
        Scanner myObj = new Scanner(System.in);

        System.out.print("Enter new food name: ");
        String name = Validations.checkString(myObj, pattern, "name", 3, 100);

        System.out.print("Enter new food description: ");
        String description = Validations.checkString(myObj, pattern, "description", 5, 200);

        System.out.print("Enter new food price: ");
        float price = Validations.checkFloat(myObj, "price");

        System.out.print("Is food vegan? [true/false]");
        boolean vegan = Validations.checkBoolean(myObj, "vegan");

        Food food = new Food(name, description, price, vegan);
        Helpers.typeCheck(food);

        return food;
    }

}
