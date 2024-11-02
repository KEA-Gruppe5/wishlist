package kea.wishlist.controller;
import kea.wishlist.model.User;
import kea.wishlist.model.WishlistModel;
import kea.wishlist.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/wishList")
public class WishlistController  {

    @Autowired
    private final WishlistService wishlistService;
    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping("/{userId}/create")
    public String showCreateWishlistForm(@PathVariable int userId, Model model) {
        User user = new User();
        user.setId(userId);

        model.addAttribute("wishlist", new WishlistModel());
        model.addAttribute("userId", userId);
        return "newWishlist";
    }

    @PostMapping("/{userId}/save")
    public String createWishlist(@PathVariable int userId, @ModelAttribute WishlistModel wishlist, Model model) {
        try {
            wishlist.setUserId(userId); // Set the user ID in the wishlist
            // Call the service to save the wishlist
            wishlistService.addWishlist(wishlist);
            model.addAttribute("message", "Wishlist created successfully with ID: " + wishlist.getId());
        } catch (SQLException e) {
            model.addAttribute("message", "Error creating wishlist: " + e.getMessage());
        }
        return "redirect:/newWishlist"; // Return the view name
    }




}
