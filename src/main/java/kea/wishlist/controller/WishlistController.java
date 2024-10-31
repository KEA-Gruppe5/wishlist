package kea.wishlist.controller;

import kea.wishlist.service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/wishlist")
public class WishlistController  {
    private WishlistService wishlistService;


//    @GetMapping("/deleteWi")
//    @DeleteMapping("/deleteWishlist/{id}")
//    public boolean deleteWishlist(@RequestParam String id){
//        return wishlistService.deleteWishlist(id);
//    }

}
