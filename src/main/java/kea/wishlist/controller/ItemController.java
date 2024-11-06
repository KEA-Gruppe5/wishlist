package kea.wishlist.controller;

import kea.wishlist.model.Item;
import kea.wishlist.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;

@Controller
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{wishlistId}/add")
    public String showItemForm(@PathVariable("wishlistId") String wishlistId, Model model) {
        model.addAttribute("showItemForm", new Item());
        model.addAttribute("wishlistId", wishlistId);
        return "item/addItem";
    }

    @PostMapping("/{wishlistId}/add")
    public String addItem(@ModelAttribute Item item, @PathVariable("wishlistId") int wishlistId) throws SQLException {
        itemService.addItem(item, wishlistId);
        return "redirect:/wishlist/{wishlistId}";
    }


    @GetMapping("/{itemId}/update")
    public String showUpdateItemForm(@PathVariable("itemId") int itemId, Model model) throws SQLException {
        Item item = itemService.findItemById(itemId);
        model.addAttribute("item", item);
        return "item/editItem";
    }

    //TODO should be put mapping
    @PostMapping("/{itemId}/update")
    public String updateItem(@PathVariable("itemId") int itemId, @ModelAttribute Item item) {
        itemService.updateItem(item, itemId);
        return "redirect:/wishlist/" + item.getWishlistId();
    }


    //TODO should be Deletemapping
    @PostMapping("/{itemId}/delete")
    public String deleteItem(@PathVariable int itemId) throws SQLException {
        Item item = itemService.findItemById(itemId);
        itemService.deleteItem(itemId);
        return "redirect:/wishlist/" + item.getWishlistId();
    }
}
