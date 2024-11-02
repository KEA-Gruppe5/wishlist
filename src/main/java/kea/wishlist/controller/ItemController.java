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

    @GetMapping("/add")
    public String showItemForm(Model model){
        model.addAttribute("showAddFormItem", new ItemModel());
        return "addItem";
    }

    @PostMapping("{userId}/add")
    public String addItem(@ModelAttribute ItemModel item, @PathVariable("userId") String userId, HttpSession session) throws SQLException {
        User currentUser = (User) session.getAttribute("userId");
        itemService.addItem(item, currentUser);
        return "redirect:/wishlist";
    }


//    @GetMapping("/update/{id}")
//    public String showUpdateItemForm(@PathVariable int id, Model model){
//         itemService.showUpdateItemForm(id);
//         model.addAttribute("showUpdateItemForm", )
//    }

    @PostMapping("/delete/{id}")
    public String deleteItem(@PathVariable int id) throws SQLException {
        itemService.deleteItem(id);
        System.out.println(id);
        return "redirect:/wishlist";
    }
}
