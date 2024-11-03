package kea.wishlist.controller;

import kea.wishlist.model.User;
import kea.wishlist.model.WishlistModel;
import kea.wishlist.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/wishList")
public class WishlistController {



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
    @DeleteMapping("/{id}/delete")
    public String deleteWishList(@PathVariable int id) throws SQLException {
        wishlistService.deleteWishList(id);
        return "redirect:/main";
    }
    @GetMapping("/{id}/update")
    public String updateForm(@PathVariable int id, Model model) throws SQLException {
        WishlistModel wishlist = wishlistService.oneListWithID(id);
        if (wishlist == null){
            return "redirect/error";
        }
        model.addAttribute("wishlist", wishlist);
        return "updateWishListform";
    }
    @PutMapping("/{id}/update")
    public String update(@PathVariable int id, @ModelAttribute WishlistModel updatedWishlist) throws SQLException {
        try {
            wishlistService.updateWishList(updatedWishlist, id);
        } catch (SQLException e) {
            return "redirect:/error";
        }
        return "redirect:/main";
    }

    // Display all wishlists for a specific user
    @GetMapping("/{userId}/main")
    public String getWishlistsByUserId(@PathVariable int userId, Model model) {
        try {
            List<WishlistModel> wishlists = wishlistService.getWishlistsByUserId(userId);
            model.addAttribute("wishlists", wishlists);
            if (wishlists.isEmpty()) {
                model.addAttribute("message", "No wishlists found for this user.");
            }
            return "main";

        } catch (SQLException e) {
            model.addAttribute("error", "An error occurred while fetching the wishlists.");
            return "error";
        }
    }
    @GetMapping("/main")
    public String showMain(){
        return "main";
    }



}
