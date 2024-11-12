package kea.wishlist.controller;

import jakarta.servlet.http.HttpSession;
import kea.wishlist.model.Item;
import kea.wishlist.model.Wishlist;
import kea.wishlist.service.ItemService;
import kea.wishlist.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/{userId}/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;
    private final ItemService itemService;

    @Autowired
    public WishlistController(WishlistService wishlistService, ItemService itemService) {
        this.wishlistService = wishlistService;
        this.itemService = itemService;
    }


    ///TODO change error msg to only show when the wishlist are NOT found at all
    @GetMapping("/{wishlistId}")
    public String findAllItems(Model model, @PathVariable("wishlistId") int wishlistid,
                               HttpSession httpSession) throws SQLException {
        if(httpSession.getAttribute("userId")!=null) {
            List<Item> list = itemService.getAllItems(wishlistid);
            model.addAttribute("findAllItems", list);
        }
        return "wishlist/wishlist";
    }


    @GetMapping("/create")
    public String showCreateWishlistForm(@PathVariable int userId, Model model) {
        model.addAttribute("wishlist", new Wishlist());
        model.addAttribute("userId", userId);
        return "wishlist/newWishlist";
    }
    @PostMapping("/create")
    public String createWishlist(@PathVariable("userId") int userId, @ModelAttribute Wishlist wishlist, Model model) {
        try {
            wishlistService.createWishlist(wishlist, userId);
            model.addAttribute("message", "Wishlist created successfully with ID: " + wishlist.getId());
        } catch (SQLException e) {
            model.addAttribute("message", "Error creating wishlist: " + e.getMessage());
        }
        return "redirect:/" + userId + "/wishlist";
    }
    @PostMapping("/{wishlistId}/delete")
    public String deleteWishlist(@PathVariable int wishlistId, @PathVariable int userId) throws SQLException {
        wishlistService.deleteWishlist(wishlistId);
        return "redirect:/" + userId + "/wishlist";
    }
    @GetMapping("/{wishlistId}/update")
    public String editForm(@PathVariable int wishlistId, Model model) throws SQLException {
        Wishlist wishlist = wishlistService.findWishlistById(wishlistId);
        if (wishlist == null){
            return "redirect:/error";
        }
        model.addAttribute("wishlist", wishlist);
        return "wishlist/editWishlist";
    }

    @PutMapping("/{wishlistId}/update")
    public String update(@PathVariable int wishlistId, @ModelAttribute Wishlist updatedWishlist) throws SQLException {
        int userId;
        try {
            wishlistService.updateWishlist(updatedWishlist, wishlistId);
            userId = updatedWishlist.getUserId();
        } catch (SQLException e) {
            return "redirect:/error";
        }
        return "redirect:/" + userId + "/wishlist";
    }

    // Display all wishlists for a specific user
    @GetMapping("")
    public String getWishlistsByUserId(@PathVariable int userId, Model model,
                                       HttpSession httpSession) {
        try {
            if(httpSession.getAttribute("userId")!=null) {
                List<Wishlist> wishlists = wishlistService.getWishlistsByUserId(userId);
                model.addAttribute("wishlists", wishlists);
                model.addAttribute("userId", userId);
                if (wishlists.isEmpty()) {
                    model.addAttribute("message", "No wishlists found for this user.");
                }
            }

        } catch (SQLException e) {
            // Add a log statement here to confirm the catch block is reached
            System.out.println("Error occurred in getWishlistsByUserId");
            e.printStackTrace();  // This will print the exact error details in the console

            model.addAttribute("error", "An error occurred while fetching the wishlists: " + e.getMessage());
            return "error";  // Return the error page if an exception occurs
        }
        return "wishlist/allWishlists";
    }

}
