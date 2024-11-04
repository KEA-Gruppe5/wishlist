package kea.wishlist.controller;

import jakarta.servlet.http.HttpSession;
import kea.wishlist.model.ItemModel;
import kea.wishlist.model.User;
import kea.wishlist.model.WishlistModel;
import kea.wishlist.service.ItemService;
import kea.wishlist.service.UserService;
import kea.wishlist.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;
    private User user;

    @Autowired
    private UserService userService;

    //TODO fix so endpoint is /wishlist/id. Solution: add method to WishlistController
    //TODO when login / user session is ready, ensure the logged in user can only access there own wishlist
    @GetMapping("/{wishlistId}")
    public String findAllItems(Model model, @PathVariable("wishlistId") int wishlistid) throws SQLException {
        List<ItemModel> list = itemService.getAllItems(wishlistid);
        if (list.isEmpty()){
            model.addAttribute("error", "No wishlist found for the provided ID.");
            return "error";
        }
        model.addAttribute("findAllItems", list);
        return "wishlist";
    }

    @GetMapping("/{wishlistId}/addItem")
    public String showItemForm(@PathVariable("wishlistId") String wishlistId, Model model){
        model.addAttribute("showAddFormItem", new ItemModel());
        model.addAttribute("wishlistidFromGet", wishlistId);
        return "addItem";
    }

    @PostMapping("/{wishlistId}/addItem")
    public String addItem(@ModelAttribute ItemModel item, @PathVariable("wishlistId") int wishlistId) throws SQLException {
        itemService.addItem(item, wishlistId);
        return "redirect:/item/{wishlistId}";
    }


    @GetMapping("/update/{itemId}")
    public String showUpdateItemForm(@PathVariable("itemId") int itemId, Model model){
            ItemModel items = itemService.showUpdateItemForm(itemId);
            model.addAttribute("showUpdateItemForm", items );
         return "editItem";
    }

    //TODO should be put mapping
    @PostMapping("/update/{itemId}/{wishlistId}")
    public String updateItem(@PathVariable("itemId") int itemId, @ModelAttribute ItemModel itemModel,@PathVariable("wishlistId") int wishlistId){
        itemService.updateItem(itemModel,itemId);
        return "redirect:/item/{wishlistId}";
    }


    //TODO should be Deletemapping
    @PostMapping("/delete/{itemId}/{wishlistId}")
    public String deleteItem(@PathVariable int itemId, @PathVariable("wishlistId") int wishlistId) throws SQLException {
        itemService.deleteItem(itemId);
        return "redirect:/item/{wishlistId}";
    }
}
