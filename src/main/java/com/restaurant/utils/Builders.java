package com.restaurant.utils;

import com.restaurant.Food;

import java.util.Scanner;

public class Builders {

    public static Food takeUserInputAndBuildFood(){
        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter food name: ");
        String name = myObj.nextLine();

        System.out.println("Enter food description: ");
        String description = myObj.nextLine();

        System.out.println("Enter food price: ");
        float price = Integer.parseInt(myObj.nextLine());

        System.out.println("Enter food vegan: ");
        boolean vegan = Boolean.parseBoolean(myObj.nextLine());

        Food food = new Food(name, description, price, vegan);
        Helpers.typeCheck(food);

        return food;
    }
}
