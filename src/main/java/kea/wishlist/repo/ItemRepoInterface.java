package kea.wishlist.repo;

import kea.wishlist.model.ItemModel;
import kea.wishlist.model.User;

import java.sql.SQLException;

public interface ItemRepoInterface {
    ItemModel addItem(ItemModel item) throws SQLException;
    ItemModel updateItem(ItemModel item, int id);
    ItemModel showUpdateItemForm(int id);
    boolean deleteItem(int id) throws SQLException;
    User findItemById (int id);
}
