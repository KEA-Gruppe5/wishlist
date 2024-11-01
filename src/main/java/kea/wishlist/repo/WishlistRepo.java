package kea.wishlist.repo;

import kea.wishlist.model.WishlistModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository("WishList_Repo")
public class WishlistRepo implements WishListRepoInterface {
    @Value("${spring.datasource.url}")
    private String URL;
    @Value("${spring.datasource.username}")
    private String USERNAME;
    @Value("${spring.datasource.password}")
    private String PASSWORD;


    //problem, need to assign
    @Override
    public WishlistModel addWishList(WishlistModel wish, ) throws SQLException {
        // SQL query with placeholders
        String insertQuery = "INSERT INTO WISHLISTS (name) VALUES (?)";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); // Assumes you have a Database connection class
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            // Set the values in the prepared statement
            preparedStatement.setInt(1, wish.getUserID());
            preparedStatement.setString(2, wish.getName());

            // Execute the update and check if successful
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating wishlist failed");
            }
            // Retrieve the generated ID, if your table has auto-increment for id
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    wish.setUserID(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating wishlist failed, no ID obtained.");
                }
            }
        }
        return wish;
    }

    @Override
    public WishlistModel updateWishList(WishlistModel wish, int id) {
        return null;
    }

    @Override
    public WishlistModel deletedWishList(int id) throws SQLException {
        return null;
    }

    @Override
    public WishlistModel findWishListWithId(int id) {
        return null;
    }


}
