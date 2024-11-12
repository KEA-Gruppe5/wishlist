package kea.wishlist.controller;

import jakarta.servlet.http.HttpSession;
import kea.wishlist.dto.UserDTO;
import kea.wishlist.model.User;
import kea.wishlist.service.PasswordValidator;
import kea.wishlist.service.UserService;
import kea.wishlist.service.VerificationService;
import kea.wishlist.exceptions.BadCredentialsException;
import kea.wishlist.exceptions.TokenIsAlreadyUsedException;
import kea.wishlist.exceptions.UserIsNotEnabledException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
public class UserController {

    private final UserService userService;
    private final VerificationService verificationService;
    private final PasswordValidator passwordValidator;

    @Autowired
    public UserController(UserService userService, VerificationService verificationService,
                          PasswordValidator passwordValidator) {
        this.userService = userService;
        this.verificationService = verificationService;
        this.passwordValidator = passwordValidator;
    }

    @GetMapping("/")
    public String index(Model model, HttpSession httpSession) {
        model.addAttribute("userId", httpSession.getAttribute("userId"));
        return "index";
    }

    @GetMapping("/register")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user/registerForm";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user) throws SQLException {
        if (passwordValidator.isValid(user.getPassword())) {
            userService.saveUser(user);
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "user/login";
    }

    @PostMapping("/login")
    public String authenticate(@ModelAttribute("user") UserDTO userDTO, HttpSession httpSession,
                               Model model) throws SQLException {
        try {
            User authenticatedUser = userService.authenticate(userDTO);
            if (authenticatedUser != null) {
                int userId = authenticatedUser.getId();
                httpSession.setAttribute("userId", userId);
                model.addAttribute("userId", userId);
                return "redirect:/" + userId + "/wishlist";
            }
        }catch(UserIsNotEnabledException | BadCredentialsException e){
            model.addAttribute("error", e.getMessage());
        }
        return "user/login";
    }


    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("userId");
        httpSession.invalidate();
        return "redirect:/";
    }

    @GetMapping("/{userId}/verify")
    public String verifyUser(@RequestParam("token") String token, @PathVariable("userId") int userId,
                             Model model) throws SQLException {
        try{
            String message = verificationService.enableUser(userId, token);
            model.addAttribute("message", message);
        }catch(TokenIsAlreadyUsedException e){
            model.addAttribute("message", e.getMessage());
        }
        return "user/verification";
    }

}
