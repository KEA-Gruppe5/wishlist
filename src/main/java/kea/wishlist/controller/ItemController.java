package kea.wishlist.controller;

import jakarta.servlet.http.HttpSession;
import kea.wishlist.model.Item;
import kea.wishlist.model.User;
import kea.wishlist.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
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
    public String showItemForm(@PathVariable("wishlistId") String wishlistId, Model model,
                               HttpSession httpSession) {
        model.addAttribute("showItemForm", new Item());
        model.addAttribute("wishlistId", wishlistId);
        model.addAttribute("userId", httpSession.getAttribute("userId"));
        return "item/addItem";
    }

    @PostMapping("/{wishlistId}/add")
    public String addItem(HttpSession httpSession,
                          @ModelAttribute Item item,
                          @PathVariable("wishlistId") int wishlistId) throws SQLException {
        if(httpSession.getAttribute("userId")!=null) {
            itemService.addItem(item, wishlistId);
        }
        Integer userId = (Integer) httpSession.getAttribute("userId");
        return "redirect:/" + userId + "/wishlist/" + wishlistId;
    }


    @GetMapping("/{itemId}/update")
    public String showUpdateItemForm(@PathVariable("itemId") int itemId, Model model) throws SQLException {
        Item item = itemService.findItemById(itemId);
        model.addAttribute("item", item);
        return "item/editItem";
    }

    //TODO should be put mapping
    @PostMapping("/{itemId}/update")
    public String updateItem(@PathVariable("itemId") int itemId, @ModelAttribute Item item, HttpSession session) throws SQLException {
        Integer userId = (Integer) session.getAttribute("userId");
        itemService.updateItem(item, itemId);
        int wishlistId = itemService.findItemById(itemId).getWishlistId();
        return "redirect:/" + userId + "/wishlist/" + wishlistId;
    }


    //TODO should be Deletemapping
    @PostMapping("/{itemId}/delete")
    public String deleteItem(@PathVariable int itemId, HttpSession session) throws SQLException {
        int wishlistId = itemService.findItemById(itemId).getWishlistId();
        Integer userId = (Integer) session.getAttribute("userId");
        itemService.deleteItem(itemId);
        return "redirect:/" + userId + "/wishlist/" + wishlistId;
    }

    @PostMapping("/{itemId}/reserve")
    public String reserveItem(@PathVariable int itemId, HttpSession session) throws SQLException {
        int wishlistId = itemService.findItemById(itemId).getWishlistId();
        Integer userId = (Integer) session.getAttribute("userId");
        itemService.reserveGift(itemId);
        return "redirect:/" + userId + "/wishlist/" + wishlistId;
    }
}
