package kea.wishlist.controller;

import kea.wishlist.model.User;
import kea.wishlist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(){
        return "registerForm";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user) throws SQLException {
        userService.saveUser(user);
        return "redirect:/login";
    }

}
