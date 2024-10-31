package kea.wishlist.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class ConnectionManager {

    private static Connection connection;


    @Value("${spring.datasource.url}")
    private String URL;
    @Value("${spring.datasource.username}")
    private String USERNAME;
    @Value("${spring.datasource.password}")
    private String PASSWORD;


//    private final String URL;
//    private final String USERNAME;
//    private final String PASSWORD;
//
//
//    public ConnectionManager(@Value("${spring.datasource.url}") String URL,
//                             @Value("${spring.datasource.username}")String USERNAME,
//                             @Value("${spring.datasource.password}")String PASSWORD) {
//        this.URL = URL;
//        this.USERNAME = USERNAME;
//        this.PASSWORD = PASSWORD;
//    }

    public Connection getConnection(){
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
