package kea.wishlist.repository;

import kea.wishlist.model.Item;
import kea.wishlist.util.ConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Repository
public class ItemRepository implements ItemRepositoryInterface {

    @Autowired
    private final ConnectionManager connectionManager;
    private final List<Item> itemList = new ArrayList<>();

    @Autowired
    public ItemRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public List<Item> findAllItems(int wishlistId) throws SQLException {
        itemList.clear();
        try (Connection connection = connectionManager.getConnection()) {

            String query = "SELECT wishlists.id AS wishlist_Id, " +  
                    "wishlists.user_Id, " +
                    "items.id, " +
                    "items.name, " +
                    "items.description, " +
                    "items.price, " +
                    "items.link, " +
                    "items.img_url, " +  
                    "items.is_reserved " +
                    "FROM wishlists " +
                    "JOIN items ON wishlists.id = items.wishlist_Id " +
                    "WHERE wishlists.id = ?";


            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, wishlistId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Item item = new Item();
                
                item.setId(resultSet.getInt("id"));
                item.setWishlistId(resultSet.getInt("wishlist_id"));
                item.setName(resultSet.getString("name"));
                item.setDescription(resultSet.getString("description"));
                item.setPrice(resultSet.getDouble("price"));
                item.setUrl(resultSet.getString("link"));
                item.setImgUrl(resultSet.getString("img_url"));
                item.setReserved(resultSet.getBoolean("is_reserved"));

                itemList.add(item);
            }
            return itemList;
        }
    }


    @Override
    public Item addItem(Item item, int wishlistId) throws SQLException {
        String query = "INSERT INTO items (wishlist_id, name, description, price, link, img_url) VALUES (?, ?, ?, ?, ?, ?)";
        System.out.println("URL being saved to database: " + item.getUrl());
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, wishlistId);
            preparedStatement.setString(2, item.getName());
            preparedStatement.setString(3, item.getDescription());
            preparedStatement.setDouble(4, item.getPrice());
            preparedStatement.setString(5, item.getUrl());
            preparedStatement.setString(6, item.getImgUrl());
            preparedStatement.executeUpdate();

        }

        return item;
    }

    @Override
    public Item updateItem(Item item, int id) {
        try (Connection connection = connectionManager.getConnection()) {
            String query = "UPDATE items SET name = ?, description = ?, price = ?, link = ?, img_url = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);


            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getDescription());
            preparedStatement.setDouble(3, item.getPrice());
            preparedStatement.setString(4, item.getUrl());
            preparedStatement.setString(5, item.getImgUrl());

            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return item;
    }

    @Override
    public boolean deleteItem(int id) {
        try (Connection connection = connectionManager.getConnection()) {
            String query = "DELETE FROM items WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Item findItemById(int id) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            String query = "SELECT * FROM items WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getInt("id"));
                        item.setWishlistId(resultSet.getInt("wishlist_id"));
                        item.setName(resultSet.getString("name"));
                        item.setDescription(resultSet.getString("description"));
                        item.setPrice(resultSet.getInt("price"));
                        item.setUrl(resultSet.getString("link"));
                        item.setImgUrl(resultSet.getString("img_url"));
                return item;
            } else {
                throw new NoSuchElementException("No item found with id " + id);
            }
        }
    }

    public void reserveGift(int id) throws SQLException {
        try (Connection connection = connectionManager.getConnection()){
            String query = "UPDATE items SET is_reserved = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1,true);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        }
    }
}
