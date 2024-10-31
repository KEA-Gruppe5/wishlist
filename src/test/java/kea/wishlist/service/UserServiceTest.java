package kea.wishlist.service;

import kea.wishlist.model.User;
import kea.wishlist.util.PasswordEncoder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Test
    public void testPasswordEncoder(){
        System.out.println(passwordEncoder.encode("kea123"));
        System.out.println(passwordEncoder.matches("kea123",
                "fRbmizSZuWZcVrJJnVwRkoxNfu+pZzGtUCRnw8uCfFo="));
    }

//    @Test
//    public void testMatching(){
//        System.out.println(passwordEncoder.matches("kea123",
//                "fRbmizSZuWZcVrJJnVwRkoxNfu+pZzGtUCRnw8uCfFo="));
//    }

}