package kea.wishlist.service;

import kea.wishlist.repo.WishlistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {
    private final WishlistRepo wishlistRepo;


    @Autowired
    public WishlistService(WishlistRepo wishlistRepo) {
        this.wishlistRepo = wishlistRepo;
    }
    public WishlistModel addWishlist(WishlistModel wishlist) throws SQLException {
        return wishlistRepo.addWishList(wishlist);
    }
}
