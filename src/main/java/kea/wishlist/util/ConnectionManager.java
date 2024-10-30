package kea.wishlist.util;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static Connection connection;

    @Value("${spring.datasource.url}")
    private String URL;
    @Value("${spring.datasource.username}")
    private String USERNAME;
    @Value("${spring.datasource.password}")
    private String PASSWORD;


    public ConnectionManager(String URL, String USERNAME, String PASSWORD) {
        this.URL = URL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }

    private static Connection getConnection(String URL, String USERNAME, String PASSWORD){
        if (connection != null){
            return connection;
        }
        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            System.out.println("Could not connect to DB");
            throw new RuntimeException(e);
        }
        return connection;
    }
}
