package kea.wishlist.controller;

import jakarta.servlet.http.HttpSession;
import kea.wishlist.model.ItemModel;
import kea.wishlist.model.User;
import kea.wishlist.service.ItemService;
import kea.wishlist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.sql.SQLException;

@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private UserService userService;

    @GetMapping("")
    public String findAllItems(Model model) throws SQLException {
        model.addAttribute("findAllItems", itemService.getAllItems());
        return "wishlist";
    }

    @GetMapping("/add")
    public String showItemForm(Model model){
        model.addAttribute("showAddFormItem", new ItemModel());
        return "addItem";
    }

    @PostMapping("/{wishlistId}/add")
    public String addItem(@ModelAttribute ItemModel item, @PathVariable("wishlistId") String wishlistId, HttpSession session) throws SQLException {
        User currentUser = (User) session.getAttribute("userId");
        itemService.addItem(item, currentUser);
        return "redirect:/wishlist";
    }


    @GetMapping("/{wishlistId}/update")
    public String showUpdateItemForm(@PathVariable int wishlistId, Model model){
        ItemModel items = itemService.showUpdateItemForm(wishlistId);
         model.addAttribute("showUpdateItemForm", items );
         return "editItem";
    }

    @PostMapping("/update")
    public String updateItem(@RequestParam("id") int id, @ModelAttribute ItemModel itemModel){
        itemService.updateItem(itemModel,id);
        return "redirect:/wishlist";
    }

    @PostMapping("/delete/{id}")
    public String deleteItem(@PathVariable int id) throws SQLException {
        itemService.deleteItem(id);
        System.out.println(id);
        return "redirect:/wishlist";
    }
}
