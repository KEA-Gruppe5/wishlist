package kea.wishlist.repo;

import kea.wishlist.model.WishlistModel;
import kea.wishlist.util.ConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository("WishList_Repo")
public class WishlistRepo implements WishListRepoInterface {

    @Autowired
    private ConnectionManager connectionManager;

    //problem, need to assign
    @Override
    public WishlistModel addWishList(WishlistModel wish) throws SQLException {
        // SQL query with placeholders
        String insertQuery = "INSERT INTO WISHLISTS (userId, name) VALUES (?,?)";

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            // Set the values in the prepared statement
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, wish.getName());


            preparedStatement.executeUpdate();
            // Execute the update and check if successful
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating wishlist failed");
            }
            // Retrieve the generated ID, bec table has auto-increment for id
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    wish.setId(generatedKeys.getInt(1));
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
