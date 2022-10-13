package com.restaurant;

import com.restaurant.database.DalFood;

import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    public static void printMenu(String[] options) {
        for (String option : options) {
            System.out.println(option);
        }
        System.out.println("Choose action: ");
    }

    public static void main(String[] args) {
        String[] options = {"1- Add food",
                            "2- Update food id",
                            "3- Update food name",
                            "4- Sort food by price",
                            "5- Sort food by name",
                            "6- Show how many types of food there are",
                            "7- Delete food by id",
                            "8- Delete food by name",
                            "9- Exit",
        };

        Scanner scanner = new Scanner(System.in);
        int option = 1;
        while (option != 9) {
            printMenu(options);
            try {
                DalFood dalFood = new DalFood();
                option = scanner.nextInt();
                switch (option) {
                    case 1:
                        dalFood.addFood();
                        break;
                    case 2:
                        dalFood.updateFoodID();
                        break;
                    case 3:
                        dalFood.updateFoodName();
                        break;
                    case 4:
                        dalFood.sortFoodPrice();
                        break;
                    case 5:
                        dalFood.sortFoodName();
                        break;
                    case 6:
                        dalFood.typeList();
                        break;
                    case 7:
                        dalFood.deleteID();
                        break;
                    case 8:
                        dalFood.deleteName();
                        break;
                    case 9:
                        exit(0);

                }
            } catch (Exception ex) {
                System.out.println("Enter a value between 1 and " + options.length);
                scanner.next();
            }
        }

        try {
            DalFood dalFood = new DalFood();
            // dalFood.showFood();
            //dalFood.addFood();
            // dalFood.sortFoodPrice();
            // dalFood.sortFoodName();
            // dalFood.typeList();
            // dalFood.deleteID();
            // dalFood.deleteName();
            //dalFood.updateFoodID();
            // dalFood.updateFoodName();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
