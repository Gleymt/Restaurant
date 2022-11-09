package com.restaurant.utils;

import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validations {
    public static String checkString(Scanner scanner, String pattern, String inputName, int minLength, int maxLength) {
        Pattern p = Pattern.compile(pattern);
        boolean error = true;
        String name = "";

        while (error) {
            name = scanner.nextLine();
            Matcher matcher = p.matcher(name);
            if (matcher.matches()) {
                System.out.printf("%s you entered: %s \n", inputName.substring(0, 1).toUpperCase() + inputName.substring(1), name);
                if (name.length() <= minLength || name.length() >= maxLength) {
                    System.out.printf("Please enter a %s between %s and %s characters. \n", inputName, minLength, maxLength);
                } else {
                    error = false;
                }
            } else {
                System.out.printf("Please enter a %s containing only letters and spaces. \n", inputName);
            }
        }

        return name;
    }

    public static float checkFloat(Scanner scanner, String inputName) {
        boolean error = true;
        float priceToReturn = 0;
        while (error) {
            String price = scanner.next();
            if (Helpers.isFloat(price)) {
                priceToReturn = Float.parseFloat(price);
                if (priceToReturn <= 0) {
                    System.out.printf("Please enter a positive number for %s\n", inputName);
                } else {
                    error = false;
                }
            } else {
                System.out.printf("Please enter a number for %s\n", inputName);
            }
        }

        return priceToReturn;
    }

    public static boolean checkBoolean(Scanner scanner, String inputName) {
        boolean vegan = false;
        boolean error = true;

        while (error) {
            String userInput = scanner.next();
            if (Objects.equals(userInput, "false") || Objects.equals(userInput, "true")) {
                vegan = Boolean.parseBoolean(userInput);
                System.out.printf("Your answer is: %s", vegan);
                error = false;
            } else {
                System.out.printf("Please enter a true/false option to see if food is %s \n", inputName);
            }
        }
        return vegan;
    }
}
