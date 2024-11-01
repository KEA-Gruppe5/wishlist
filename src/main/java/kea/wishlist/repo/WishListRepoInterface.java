package kea.wishlist.repo;

import kea.wishlist.model.User;
import kea.wishlist.model.WishModel;
import kea.wishlist.model.WishlistModel;

import java.sql.SQLException;

public interface WishListRepoInterface {

    WishlistModel addWishList(WishlistModel Wish) throws SQLException;
    WishlistModel updateWishList(WishlistModel wish, int id);
    WishlistModel deletedWishList(int id) throws SQLException;
    WishlistModel findWishListWithId (int id);
}
