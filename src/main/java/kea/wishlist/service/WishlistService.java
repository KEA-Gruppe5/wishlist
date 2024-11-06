package kea.wishlist.service;

import kea.wishlist.model.Wishlist;
import kea.wishlist.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class WishlistService {
    private final WishlistRepository wishlistRepository;


    @Autowired
    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }
     public Wishlist createWishlist(Wishlist wishlist, int userId) throws SQLException {
         return wishlistRepository.addWishlist(wishlist, userId);
    }
    public Wishlist updateWishlist(Wishlist wishlist, int id) throws SQLException {
        return wishlistRepository.updateWishlist(wishlist,id);
    }
    public void deleteWishlist(int id)throws SQLException{
        wishlistRepository.deleteWishlist(id);
    }
    public List<Wishlist> getWishlistsByUserId(int userId) throws SQLException {
        return wishlistRepository.findAllListsByUserId(userId);
    }

    public Wishlist findWishlistById(int id) throws SQLException{
        return wishlistRepository.findWishlistById(id);
    }

}
