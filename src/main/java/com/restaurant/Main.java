package com.restaurant;

import com.restaurant.database.DalFood;
import com.restaurant.utils.Builders;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import static com.restaurant.utils.Helpers.printMenu;
import static java.lang.System.exit;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final String[] firstMenuOptions = {
            "1- Add food",
            "2- Update",
            "3- Sort",
            "4- Delete",
            "5- Exit",
            "Choose action: "
    };

    private static final String[] updateSubMenu = {
            "1- Update food by id",
            "2- Update food by name",
            "3- Update a specific element of an item",
            "4- Return to main menu",
            "Choose action: "
    };

    private static final String[] updateSubMenu2 = {
            "1- Update name",
            "2- Update description",
            "3- Update price",
            "4- Update vegan state",
            "5- Return to previous menu",
            "Choose action: "
    };

    private static final String[] sortSubMenu = {
            "1- Sort food by price",
            "2- Sort food by name",
            "3- Display the number of products in each price category",
            "4- Return to main menu",
            "Choose action: "
    };

    private static final String[] deleteSubMenu = {
            "1- Delete food by id",
            "2- Delete food by name",
            "3- Return to main menu",
            "Choose action: "
    };

    public static void main(String[] args) throws IOException {
        int option = 1;
        final DalFood dalFood = new DalFood();
        DalFood.createFile(DalFood.restaurantDatabase, DalFood.username, DalFood.time);

        while (option != 9) {
            printMenu(firstMenuOptions);
            try {
                option = scanner.nextInt();
                switch (option) {
                    case 1:
                        Food food = Builders.takeUserInputAndBuildFood();
                        dalFood.addFood(food);
                        break;
                    case 2:
                        int updateMenu = 1;
                        while (updateMenu != 4) {
                            printMenu(updateSubMenu);
                            updateMenu = scanner.nextInt();
                            switch (updateMenu) {
                                case 1:
                                    System.out.println("Please enter the id of the food you want to update");
                                    int updateById = scanner.nextInt();
                                    dalFood.updateFoodByID(updateById);
                                    break;
                                case 2:
                                    System.out.println("Please enter the name of the food you want to update");
                                    String updateByName = scanner.next();
                                    dalFood.updateFoodByName(updateByName);
                                    break;
                                case 3:
                                    int updateMenu2 = 1;
                                    while (updateMenu2 != 5) {
                                        printMenu(updateSubMenu2);
                                        updateMenu2 = scanner.nextInt();
                                        switch (updateMenu2) {
                                            case 1:
                                                System.out.println("Please enter the id of the food you want to modify");
                                                int id = scanner.nextInt();
                                                dalFood.updateOnlyName(id);
                                                break;
                                            case 2:
                                                System.out.println("Please enter the id of the food you want to modify");
                                                int id1 = scanner.nextInt();
                                                dalFood.updateOnlyDescription(id1);
                                                break;
                                            case 3:
                                                System.out.println("Please enter the id of the food you want to modify");
                                                int id2 = scanner.nextInt();
                                                dalFood.updateOnlyPrice(id2);
                                                break;
                                            case 4:
                                                System.out.println("Please enter the id of the food you want to modify");
                                                int id3 = scanner.nextInt();
                                                dalFood.updateOnlyVegan(id3);
                                                break;
                                            case 5:
                                                break;
                                        }
                                    }
                                    break;
                                case 4:
                                    break;
                            }
                        }
                        break;
                    case 3:
                        int sortMenu = 1;
                        while (sortMenu != 4) {
                            printMenu(sortSubMenu);
                            sortMenu = scanner.nextInt();
                            switch (sortMenu) {
                                case 1:
                                    dalFood.sortAllFoodByPrice();
                                    break;
                                case 2:
                                    dalFood.sortAllFoodByName();
                                    break;
                                case 3:
                                    dalFood.typeList();
                                    break;
                                case 4:
                                    break;
                            }
                        }
                        break;
                    case 4:
                        int deleteMenu = 1;
                        while (deleteMenu != 3) {
                            printMenu(deleteSubMenu);
                            deleteMenu = scanner.nextInt();
                            switch (deleteMenu) {
                                case 1:
                                    System.out.println("Please type the id of the food you want to delete");
                                    long id = scanner.nextLong();
                                    dalFood.deleteID(id);
                                    break;
                                case 2:
                                    System.out.println("Please type the name of the food you want to delete");
                                    String name = scanner.next();
                                    dalFood.deleteName(name);
                                    break;
                                case 3:
                                    break;
                            }
                        }
                        break;
                    case 5:
                        DalFood.closeLog(DalFood.time);
                        exit(0);

                }
            } catch (SQLException ex) {
                System.out.println("There is a problem with db");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Error. Please enter a value between 1 and " + firstMenuOptions.length + ". To continue, enter any key.");
                scanner.next();
            }
        }
    }
}
