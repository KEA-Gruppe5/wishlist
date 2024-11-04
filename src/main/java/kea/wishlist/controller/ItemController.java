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

    @Autowired
    private UserService userService;


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
    public String addItem(@ModelAttribute ItemModel item, @PathVariable("wishlistId") String wishlistId, HttpSession session) throws SQLException {
        User currentUser = (User) session.getAttribute("wishlistId");
        itemService.addItem(item, currentUser);
        return "redirect:/wishlist";
    }


    @GetMapping("/{wishlistId}/update")
    public String showUpdateItemForm(@PathVariable("wishlistId") int wishlistId, Model model){
            ItemModel items = itemService.showUpdateItemForm(wishlistId);
            model.addAttribute("showUpdateItemForm", items );
         return "editItem";
    }

    @PostMapping("/update")
    public String updateItem(@RequestParam("id") int id, @ModelAttribute ItemModel itemModel){
        itemService.updateItem(itemModel,id);
        return "redirect:/wishlist";
    }

    @PostMapping("/delete/{itemId}")
    public String deleteItem(@PathVariable int itemId) throws SQLException {
        itemService.deleteItem(itemId);
        System.out.println(itemId);
        return "redirect:/wishlist";
    }
}
