package com.restaurant.database;

import com.restaurant.Food;

import javax.xml.transform.Result;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.logging.Logger;

public class DalFood implements DalBase {

    Scanner myObj = new Scanner(System.in);

    private final static Logger log = Logger.getLogger(DalFood.class.getName());
    private final static String basePath = System.getProperty("user.dir");
    private final static String dbInfoPath = "/src/main/resources/DatabaseInfo";
    private static Properties properties = new Properties();
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
    public void showFood() throws SQLException {
        result = statement.executeQuery("select * from food");

        while (result.next()) {
            log.info(result.getString("name") + ", " + result.getString("price"));
        }
    }

    @Override
    public void addFood() throws SQLException {
        PreparedStatement myStmt = connection.prepareStatement("insert into food(name, description, price, type, is_vegan)" + "values(?, ?, ?, ?, ?)");

        String type;

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

        int rowsAffected = myStmt.executeUpdate();

        // int adaugare = statement.executeUpdate("insert into food(name, description, price, type, is_vegan) values ('tarantula', 'pe la coaie pe la', '15', 'NORMAL', '1')");
        // System.out.println("You added " + adaugare + "food items");

        result = statement.executeQuery("select * from food");

        while (result.next()) {
            log.info(result.getString("name") + ", " + result.getString("price"));
        }
    }

    @Override
    public void updateFoodID() throws SQLException {
        PreparedStatement myStmt = connection.prepareStatement("update food set name = ?, description = ?, price = ?, type = ?, is_vegan = ? where id = ?");

        String type;

        System.out.println("Enter food id: ");
        int id = Integer.parseInt(myObj.nextLine());

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
        myStmt.setInt(6, id);

        int rowsAffected = myStmt.executeUpdate();

        // int adaugare = statement.executeUpdate("insert into food(name, description, price, type, is_vegan) values ('tarantula', 'pe la coaie pe la', '15', 'NORMAL', '1')");
        // System.out.println("You added " + adaugare + "food items");

        result = statement.executeQuery("select * from food");
    }

    @Override
    public void updateFoodName() throws SQLException {
        PreparedStatement myStmt = connection.prepareStatement("update food set description = ?, price = ?, type = ?, is_vegan = ? where name = ?");

        String type;

        System.out.println("Enter food name: ");
        String name = myObj.nextLine();

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

        // int adaugare = statement.executeUpdate("insert into food(name, description, price, type, is_vegan) values ('tarantula', 'pe la coaie pe la', '15', 'NORMAL', '1')");
        // System.out.println("You added " + adaugare + "food items");

        result = statement.executeQuery("select * from food");
    }

    @Override
    public void sortFoodName() throws SQLException {

        result = statement.executeQuery("select * from food order by name");

        while (result.next()) {
            log.info(result.getString("name") + ", " + result.getString("price"));
        }

    }

    @Override
    public void sortFoodPrice() throws SQLException {
        result = statement.executeQuery("select * from food order by price");

        while (result.next()) {
            log.info(result.getString("name") + ", " + result.getString("price"));
        }
    }

    @Override
    public void typeList() throws SQLException {
        result = statement.executeQuery("select type, count(*) as count from food group by type");

        while (result.next()) {
            log.info(result.getString("type") + ", " + result.getString("count"));
        }
    }

    @Override
    public void deleteID() throws SQLException {
        PreparedStatement myStmt = connection.prepareStatement("delete from food where id = ?");

        System.out.println("Enter food id to delete: ");
        int id = Integer.parseInt(myObj.nextLine());

        myStmt.setInt(1, id);

        int rowsAffected = myStmt.executeUpdate();

        //int stergere = statement.executeUpdate("delete from food where id =7");
        //System.out.println("You deleted " + stergere + " rows");
        //result = statement.executeQuery("select * from food");
        while (result.next()) {
            log.info(result.getString("name") + ", " + result.getString("price"));
        }
    }

    @Override
    public void deleteName() throws SQLException {
        PreparedStatement myStmt = connection.prepareStatement("delete from food where name = ?");

        System.out.println("Enter food name to delete: ");
        String name = myObj.nextLine();

        myStmt.setString(1, name);

        int rowsAffected = myStmt.executeUpdate();
        //int stergere = statement.executeUpdate("delete from food" + "where name = ?");
        //System.out.println("You deleted " + stergere + " rows");
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
