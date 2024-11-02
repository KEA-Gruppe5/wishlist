package kea.wishlist.controller;

import kea.wishlist.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.sql.SQLException;

@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping("/delete/{id}")
    public String deleteItem(@PathVariable int id) throws SQLException {
        itemService.deleteItem(id);
        System.out.println(id);
        return "redirect:/wishlist";
    }
}
