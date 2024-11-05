package kea.wishlist.repo;
import kea.wishlist.model.WishlistModel;
import kea.wishlist.util.ConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("WishList_Repo")
public class WishlistRepo implements WishListRepoInterface {
    private final ConnectionManager connectionManager;
    @Autowired
    public WishlistRepo(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    //problem, need to assign
    @Override
    public void addWishList(WishlistModel wish, int user_id) throws SQLException {
        // SQL query with placeholders
        String insertQuery = "INSERT INTO WISHLISTS (user_id, name) VALUES (?,?)";

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            // Set the values in the prepared statement

            preparedStatement.setInt(1, user_id);
            preparedStatement.setString(2, wish.getName());

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
    }

    @Override
    public WishlistModel updateWishList(WishlistModel wish, int id) throws SQLException {
        String updateQuery = "UPDATE WISHLISTS SET name = ? WHERE id = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement updateList = connection.prepareStatement(updateQuery)) {

            updateList.setString(1, wish.getName());
            updateList.setInt(2, id);

            int update = updateList.executeUpdate();
            if (update == 0) {
                throw new SQLException("updating failed" + id);
            }
        }
        return wish;
    }

    @Override
    public Boolean deleteWishList(int id) throws SQLException {
        String deleteQuery = "DELETE FROM WISHLISTS WHERE id = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement deleteList = connection.prepareStatement(deleteQuery)) {
            //take the value of id and insert into the placeholder(?) in my SQL
            deleteList.setInt(1, id);
            //execute
            int rowsAffected = deleteList.executeUpdate();
            return rowsAffected > 0;

        }
    }


    @Override
    public List<WishlistModel> findAllWishlists() throws SQLException {
        String findAllQuery = "SELECT * FROM WISHLISTS";
        List<WishlistModel> wishlists = new ArrayList<>();

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(findAllQuery);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                WishlistModel wishlist = new WishlistModel();
                wishlist.setId(resultSet.getInt("id"));
                wishlist.setUserId(resultSet.getInt("user_id"));
                wishlist.setName(resultSet.getString("name"));
                wishlists.add(wishlist);
            }
        }
        return wishlists;
    }

    @Override
    public List<WishlistModel> allListByUser(int userId) throws SQLException {
        String findQuery = "SELECT * FROM WISHLISTS WHERE user_id = ?";
        List<WishlistModel> wishlists = new ArrayList<>();

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement findList = connection.prepareStatement(findQuery)) {

            findList.setInt(1, userId);
            ResultSet resultSet = findList.executeQuery();

            while (resultSet.next()) {
                WishlistModel wishList = new WishlistModel();
                wishList.setId(resultSet.getInt("id"));
                wishList.setUserId(resultSet.getInt("user_id"));
                wishList.setName(resultSet.getString("name"));
                wishlists.add(wishList);
            }
        }

        return wishlists;
    }

    @Override
    public WishlistModel oneListWithId(int id) throws SQLException {
        String findQuery = "SELECT * FROM WISHLISTS WHERE id = ?";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement findList = connection.prepareStatement(findQuery)) {

            findList.setInt(1,id);
            ResultSet resultSet = findList.executeQuery();

            if (resultSet.next()){
                WishlistModel wishList = new WishlistModel();
                wishList.setId(resultSet.getInt("id"));
                wishList.setUserId(resultSet.getInt("user_id"));
                wishList.setName(resultSet.getString("name"));
                return wishList;

            } else {
                return null;
            }
        }
    }


}
