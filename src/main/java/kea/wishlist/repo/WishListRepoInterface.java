package kea.wishlist.repo;

import kea.wishlist.model.User;
import kea.wishlist.model.WishModel;

import java.sql.SQLException;

public interface WishListRepoInterface {

    WishModel addWish(WishModel Wish) throws SQLException;
    WishModel updateWish(WishModel wish, int id);
    WishModel deletedWish(int id) throws SQLException;
    WishModel findWishWithId (int id);
}
