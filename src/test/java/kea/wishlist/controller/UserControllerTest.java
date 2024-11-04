package kea.wishlist.controller;

import kea.wishlist.model.User;
import kea.wishlist.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

    @Test
    void testGettingForm() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("registerForm"));
    }

    @Test
    void testUserRegistration() throws Exception {
        User user = new User("FirstName", "LastName", "email@test", 22, "kea123");
        mockMvc.perform(post("/register")
                .sessionAttr("user", user))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"));

        //checks that the service was called
        verify(userService, times(1)).saveUser(any(User.class));
    }

}