package kea.wishlist.controller;

import kea.wishlist.model.Item;
import kea.wishlist.model.User;
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
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    @Autowired
    private ItemService itemService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    //MOVED BY KRISTOFFER, TO GET /wishlist endpoint
    //TODO fix so endpoint is /wishlist/id. Solution: add method to WishlistController
    //TODO when login / user session is ready, ensure the logged in user can only access there own wishlist
    ///TODO change error msg to only show when the wishlist are NOT found at all
    @GetMapping("/{wishlistId}")
    public String findAllItems(Model model, @PathVariable("wishlistId") int wishlistid) throws SQLException {
        List<Item> list = itemService.getAllItems(wishlistid);
//        if (list.isEmpty()){
//            model.addAttribute("error", "No wishlist found for the provided ID.");
//            return "error";
//        }
        model.addAttribute("findAllItems", list);
        return "wishlist";
    }


    @GetMapping("/{userId}/create")
    public String showCreateWishlistForm(@PathVariable int userId, Model model) {
        User user = new User();
        user.setId(userId);
        model.addAttribute("wishlist", new Wishlist());
        model.addAttribute("userId", userId);
        return "newWishlist";
    }
    @PostMapping("/{userId}/save")
    public String createWishlist(@PathVariable("userId") int user_id, @ModelAttribute Wishlist wishlist, Model model) {
        try {
            wishlistService.addWishlist(wishlist, user_id);
            model.addAttribute("message", "Wishlist created successfully with ID: " + wishlist.getId());
        } catch (SQLException e) {
            model.addAttribute("message", "Error creating wishlist: " + e.getMessage());
        }
        return "redirect:/wishlist/1/main";

    }
    @DeleteMapping("/{id}/delete")
    public String deleteWishList(@PathVariable int id) throws SQLException {
        wishlistService.deleteWishList(id);
        return "redirect:/main";
    }
    @GetMapping("/{id}/update")
    public String updateForm(@PathVariable int id, Model model) throws SQLException {
        Wishlist wishlist = wishlistService.oneListWithID(id);
        if (wishlist == null){
            return "redirect:/error";
        }
        model.addAttribute("wishlist", wishlist);
        return "updateWishListform";
    }
    @PutMapping("/{id}/update")
    public String update(@PathVariable int id, @ModelAttribute Wishlist updatedWishlist) throws SQLException {
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
            List<Wishlist> wishlists = wishlistService.getWishlistsByUserId(userId);

            System.out.println("Wishlists for user " + userId + ": " + wishlists);


            model.addAttribute("wishlists", wishlists);
            model.addAttribute("userId", userId);
            if (wishlists.isEmpty()) {
                model.addAttribute("message", "No wishlists found for this user.");
            }
            return "main";

        } catch (SQLException e) {
            // Add a log statement here to confirm the catch block is reached
            System.out.println("Error occurred in getWishlistsByUserId");
            e.printStackTrace();  // This will print the exact error details in the console

            model.addAttribute("error", "An error occurred while fetching the wishlists: " + e.getMessage());
            return "error";  // Return the error page if an exception occurs
        }

    }

}
