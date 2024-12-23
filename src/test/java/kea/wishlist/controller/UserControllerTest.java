package kea.wishlist.controller;

import kea.wishlist.model.User;
import kea.wishlist.model.VerificationToken;
import kea.wishlist.service.PasswordValidator;
import kea.wishlist.service.UserService;
import kea.wishlist.service.VerificationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@ActiveProfiles("test")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private VerificationService verificationService;

    @MockBean
    private PasswordValidator passwordValidator;

    @Test
    void testGettingForm() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("user/registerForm"));
    }



    @Test
    void testUserRegistration_RedirectsToLogin() throws Exception {
        // Arrange
        User user = new User("FirstName", "LastName", "email@test", 22, "kea123");

        // Mock password validation
        when(passwordValidator.isValid(user.getPassword())).thenReturn(true);

        // Mock saveUser to return the user
        when(userService.saveUser(any(User.class))).thenReturn(user);

        // Act & Assert
        mockMvc.perform(post("/register")
                        .param("firstName", "FirstName") // Simulate form field
                        .param("lastName", "LastName")
                        .param("email", "email@test")
                        .param("age", "22")
                        .param("password", "kea123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login")); // Assert redirection to /login

        // Verify that saveUser was called
        verify(userService, times(1)).saveUser(any(User.class));
    }

}