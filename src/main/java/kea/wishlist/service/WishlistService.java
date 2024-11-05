package kea.wishlist.service;

import kea.wishlist.model.WishlistModel;
import kea.wishlist.repo.WishlistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class WishlistService {
    private final WishlistRepo wishlistRepo;


    @Autowired
    public WishlistService(WishlistRepo wishlistRepo) {
        this.wishlistRepo = wishlistRepo;
    }
     public void addWishlist(WishlistModel wishlist, int user_id) throws SQLException {
         wishlistRepo.addWishList(wishlist, user_id);
    }
    public WishlistModel updateWishList(WishlistModel wishlistModel, int id) throws SQLException {
        return wishlistRepo.updateWishList(wishlistModel,id);
    }
    public void deleteWishList(int id)throws SQLException{
        wishlistRepo.deleteWishList(id);
    }
    public List<WishlistModel> getAllWishlists() throws SQLException {
        return wishlistRepo.findAllWishlists();
    }
    public List<WishlistModel> getWishlistsByUserId(int userId) throws SQLException {
        return wishlistRepo.allListByUser(userId);
    }

    public WishlistModel oneListWithID(int id) throws SQLException{
        return wishlistRepo.oneListWithId(id);
    }

}
