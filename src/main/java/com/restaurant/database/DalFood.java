package com.restaurant.database;

import com.restaurant.Food;
import com.restaurant.utils.Helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Logger;

public class DalFood implements DalBase {

    Scanner myObj = new Scanner(System.in);

    private final static Logger log = Logger.getLogger(DalFood.class.getName());
    private final static String basePath = System.getProperty("user.dir");
    private final static String dbInfoPath = "/src/main/resources/DatabaseInfo";
    private final static Properties properties = new Properties();
    private static String root;
    private static String username;
    private static String password;
    private static String restaurantDatabase;

    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet result = null;

    public DalFood() throws IOException {
        properties.load(new FileInputStream(basePath + dbInfoPath));
        root = properties.getProperty("database");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
        restaurantDatabase = properties.getProperty("dbname");
        connect();
    }

    @Override
    public void showAllFood() throws SQLException {
        result = statement.executeQuery("select * from food");

        while (result.next()) {
            log.info(result.getString("name") + ", " + result.getString("price"));
        }
    }

    @Override
    public void sortAllFoodByName() throws SQLException {

        result = statement.executeQuery("select * from food order by name");

        while (result.next()) {
            log.info(result.getString("name") + ", " + result.getString("price"));
        }

    }

    @Override
    public void sortAllFoodByPrice() throws SQLException {
        result = statement.executeQuery("select * from food order by price");

        while (result.next()) {
            log.info(result.getString("name") + ", " + result.getString("price"));
        }
    }

    @Override
    public void addFood(Food food) throws SQLException {
        PreparedStatement myStmt = connection.prepareStatement("insert into food(name, description, price, type, is_vegan)" + "values(?, ?, ?, ?, ?)");

        myStmt.setString(1, food.getName());
        myStmt.setString(2, food.getDescription());
        myStmt.setFloat(3, food.getPrice());
        myStmt.setString(4, food.getType());
        myStmt.setBoolean(5, food.isVegan());

        int rowsAffected = myStmt.executeUpdate();

        log.info("Food: " + food.getName() + " was added. Rows affected: " + rowsAffected);
    }

    @Override
    public void updateFoodID(long id) throws SQLException {
        //select food by id to object
        //get input from user
        //modify selected food from user input
        //log updates
      //  Food food = selectFoodById(id);

        PreparedStatement myStmt = connection.prepareStatement("update food set name = ?, description = ?, price = ?, type = ?, is_vegan = ? where id = ?");

        String type;

        System.out.println("Enter food id: ");
        id = Integer.parseInt(myObj.nextLine());

        System.out.println("Enter food name: ");
        String name = myObj.nextLine();

        System.out.println("Enter food description: ");
        String description = myObj.nextLine();

        System.out.println("Enter food price: ");
        int price = Integer.parseInt(myObj.nextLine());

        System.out.println("Enter food vegan: ");
        boolean vegan = Boolean.parseBoolean(myObj.nextLine());

        myStmt.setString(1, name);
        myStmt.setString(2, description);
        myStmt.setInt(3, price);
        if (price <= 5) {
            type = "CHEAP";
        } else if (price > 5 && price <= 15) {
            type = "NORMAL";
        } else {
            type = "EXPENSIVE";
        }
        myStmt.setString(4, type);
        myStmt.setBoolean(5, vegan);
       // myStmt.setLong(6, food.getId());

        int rowsAffected = myStmt.executeUpdate();

        result = statement.executeQuery("select * from food");
    }

    @Override
    public void updateFoodName(String name) throws SQLException {
        PreparedStatement myStmt = connection.prepareStatement("update food set description = ?, price = ?, type = ?, is_vegan = ? where name = ?");

        String type;

        System.out.println("Enter food name: ");
        name = myObj.nextLine();

        System.out.println("Enter food description: ");
        String description = myObj.nextLine();

        System.out.println("Enter food price: ");
        int price = Integer.parseInt(myObj.nextLine());

        System.out.println("Enter food vegan: ");
        boolean vegan = Boolean.parseBoolean(myObj.nextLine());

        myStmt.setString(5, name);
        myStmt.setString(1, description);
        myStmt.setInt(2, price);
        if (price <= 5) {
            type = "CHEAP";
        } else if (price > 5 && price <= 15) {
            type = "NORMAL";
        } else {
            type = "EXPENSIVE";
        }
        myStmt.setString(3, type);
        myStmt.setBoolean(4, vegan);

        int rowsAffected = myStmt.executeUpdate();

        result = statement.executeQuery("select * from food");
    }

    @Override
    public void typeList() throws SQLException {
        result = statement.executeQuery("select type, count(*) as count from food group by type");

        while (result.next()) {
            log.info(result.getString("type") + ", " + result.getString("count"));
        }
    }

    @Override
    public void deleteID(long id) throws SQLException {
        PreparedStatement myStmt = connection.prepareStatement("delete from food where id = ?");

        System.out.println("Enter food id to delete: ");
        id = Integer.parseInt(myObj.nextLine());

        myStmt.setLong(1, id);

        int rowsAffected = myStmt.executeUpdate();

        while (result.next()) {
            log.info(result.getString("name") + ", " + result.getString("price"));
        }
    }

    @Override
    public void deleteName(String name) throws SQLException {
        PreparedStatement myStmt = connection.prepareStatement("delete from food where name = ?");

        System.out.println("Enter food name to delete: ");
        name = myObj.nextLine();

        myStmt.setString(1, name);

        int rowsAffected = myStmt.executeUpdate();

        result = statement.executeQuery("select * from food");
        while (result.next()) {
            log.info(result.getString("name") + ", " + result.getString("price"));
        }
    }


    private void connect() {
        try {
            connection = DriverManager.getConnection(root + restaurantDatabase, username, password);
            log.info("Connection successful to: " + restaurantDatabase);
            statement = connection.createStatement();
        } catch (Exception e) {
            log.warning("Can not connect to the database: " + restaurantDatabase);
        }
    }


}
