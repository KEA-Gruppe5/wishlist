package kea.wishlist.repo;

import kea.wishlist.model.WishlistModel;

import java.sql.SQLException;
import java.util.List;

public interface WishListRepoInterface {

    void addWishList(WishlistModel Wish, int user_id) throws SQLException;
    WishlistModel updateWishList(WishlistModel wish, int id) throws SQLException;
    Boolean deleteWishList(int id) throws SQLException;

    List<WishlistModel> findAllWishlists() throws SQLException;

    List<WishlistModel> allListByUser(int userId) throws SQLException;

    WishlistModel oneListWithId(int id) throws SQLException;



}
