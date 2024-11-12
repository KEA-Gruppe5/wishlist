package kea.wishlist.repository;

import kea.wishlist.model.Wishlist;

import java.sql.SQLException;
import java.util.List;

public interface WishListRepositoryInterface {

    Wishlist addWishlist(Wishlist wishlist, int userId) throws SQLException;
    Wishlist updateWishlist(Wishlist wish, int id) throws SQLException;
    Boolean deleteWishlist(int id) throws SQLException;

    List<Wishlist> findAllWishlists() throws SQLException;

    List<Wishlist> findAllListsByUserId(int userId) throws SQLException;

    Wishlist findWishlistById(int id) throws SQLException;



}
