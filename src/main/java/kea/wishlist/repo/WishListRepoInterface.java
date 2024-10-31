package kea.wishlist.repo;

import kea.wishlist.model.ItemModel;

import java.sql.SQLException;

public interface WishListRepoInterface {

    ItemModel addWish(ItemModel Wish) throws SQLException;
    ItemModel updateWish(ItemModel wish, int id);
    ItemModel deletedWish(int id) throws SQLException;
    ItemModel findWishWithId (int id);
}
