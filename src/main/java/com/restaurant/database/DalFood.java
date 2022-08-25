package com.restaurant.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

public class DalFood implements DalBase {

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
