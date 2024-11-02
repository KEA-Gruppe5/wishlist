package kea.wishlist.service;

import kea.wishlist.model.WishlistModel;
import kea.wishlist.repo.WishlistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class WishlistService {
    private final WishlistRepo wishlistRepo;


    @Autowired
    public WishlistService(WishlistRepo wishlistRepo) {
        this.wishlistRepo = wishlistRepo;
    }
     public void addWishlist(WishlistModel wishlist) throws SQLException {
         wishlistRepo.addWishList(wishlist);
    }
}
