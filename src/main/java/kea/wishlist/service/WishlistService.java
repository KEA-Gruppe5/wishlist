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
     public void addWishlist(Wishlist wishlist, int user_id) throws SQLException {
         wishlistRepository.addWishList(wishlist, user_id);
    }
    public Wishlist updateWishList(Wishlist wishlist, int id) throws SQLException {
        return wishlistRepository.updateWishList(wishlist,id);
    }
    public void deleteWishList(int id)throws SQLException{
        wishlistRepository.deleteWishList(id);
    }
    public List<Wishlist> getAllWishlists() throws SQLException {
        return wishlistRepository.findAllWishlists();
    }
    public List<Wishlist> getWishlistsByUserId(int userId) throws SQLException {
        return wishlistRepository.allListByUser(userId);
    }

    public Wishlist oneListWithID(int id) throws SQLException{
        return wishlistRepository.oneListWithId(id);
    }

}
