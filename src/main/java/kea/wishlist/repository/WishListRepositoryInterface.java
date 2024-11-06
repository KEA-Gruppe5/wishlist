package kea.wishlist.repository;

import kea.wishlist.model.Wishlist;

import java.sql.SQLException;
import java.util.List;

public interface WishListRepositoryInterface {

    void addWishList(Wishlist Wish, int user_id) throws SQLException;
    Wishlist updateWishList(Wishlist wish, int id) throws SQLException;
    Boolean deleteWishList(int id) throws SQLException;

    List<Wishlist> findAllWishlists() throws SQLException;

    List<Wishlist> allListByUser(int userId) throws SQLException;

    Wishlist oneListWithId(int id) throws SQLException;



}
