package kea.wishlist.repo;

import kea.wishlist.model.ItemModel;
import kea.wishlist.model.User;
import kea.wishlist.util.ConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Repository
public class ItemRepo implements ItemRepoInterface{

    @Autowired
    private final ConnectionManager connectionManager;

    private List<ItemModel> itemModelList = new ArrayList<>();

    @Autowired
    public ItemRepo(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public List<ItemModel> findAllItems() throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            String query = "SELECT * FROM items WHERE wishlistId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            Map<Integer, ItemModel> map = new HashMap<>();
            while (resultSet.next()) {
                int wishlistId = resultSet.getInt("wishlistId");
                ItemModel itemModel = map.get(wishlistId);
                if (itemModel == null) {
                    itemModel = new ItemModel();
                    itemModel.setWishlistId(wishlistId);
                    itemModel.setName(resultSet.getString("name"));
                    itemModel.setDescription(resultSet.getString("description"));
                    itemModel.setPrice(resultSet.getDouble("price"));
                    itemModel.setDescription(resultSet.getString("link"));
                    itemModel.setDescription(resultSet.getString("imgUrl"));
                    map.put(wishlistId, itemModel);
                }
                itemModelList = new ArrayList<>(map.values());
            }
            return itemModelList;
        }
    }


    @Override
    public ItemModel addItem(ItemModel item) throws SQLException {
        String query = "INSERT INTO items (wishlistId, name, description, price, link, imgUrl) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, item.getWishlistId()); //get wishlistId
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
    public ItemModel updateItem(ItemModel item, int id) {
        try(Connection connection = connectionManager.getConnection()) {
            String query = "UPDATE item SET wishlistId = ?, name = ?, description = ?, price = ?, link = ?, imgUrl = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, item.getWishlistId());
            preparedStatement.setString(2, item.getName());
            preparedStatement.setString(3,item.getDescription());
            preparedStatement.setDouble(4,item.getPrice());
            preparedStatement.setString(5,item.getUrl());
            preparedStatement.setString(6, item.getImgUrl());
            preparedStatement.setInt(7,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return item;
    }

    @Override
    public ItemModel showUpdateItemForm(int id) {
        for (ItemModel i : itemModelList){
            if (i.getWishlistId() == id){
                return i;
            }
        }
        throw new NoSuchElementException("No item found for wishlist with id " + id);
    }

    @Override
    public boolean deleteItem(int id){
        try (Connection connection = connectionManager.getConnection()){
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
    public User findItemById(int id) {
        return null;
    }
}

