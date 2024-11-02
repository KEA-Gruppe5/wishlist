package kea.wishlist.repo;

import kea.wishlist.model.ItemModel;
import kea.wishlist.model.User;
import kea.wishlist.util.ConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class ItemRepo implements ItemRepoInterface{

    @Autowired
    private final ConnectionManager connectionManager;

    @Autowired
    public ItemRepo(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }


    @Override
    public ItemModel addItem(ItemModel item) throws SQLException {
        return null;
    }

    @Override
    public ItemModel updateItem(ItemModel item, int id) {
        return null;
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
