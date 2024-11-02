package kea.wishlist.controller;

import kea.wishlist.model.WishlistModel;
import kea.wishlist.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller("/wishlist")
public class WishlistController  {
    private WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping
    public ResponseEntity<String> createWishlist(@RequestBody WishlistModel wishlist) {
        try {
            wishlistService.addWishlist(wishlist);
            return ResponseEntity.ok("Wishlist created successfully with ID: " + wishlist.getId());
        } catch (SQLException e) {
            return ResponseEntity.status(500).body("Error creating wishlist: " + e.getMessage());
        }
    }

}
