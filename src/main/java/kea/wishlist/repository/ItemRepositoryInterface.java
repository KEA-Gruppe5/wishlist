package kea.wishlist.repository;

import kea.wishlist.model.Item;
import kea.wishlist.model.User;

import java.sql.SQLException;

public interface ItemRepositoryInterface {
    Item addItem(Item item, int wishlistId) throws SQLException;
    Item updateItem(Item item, int id);

    boolean deleteItem(int id) throws SQLException;
    Item findItemById (int id);
}
