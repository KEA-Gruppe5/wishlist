package kea.wishlist.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class EmailServiceTest {

   @Autowired
    private EmailService emailService;

    @Test
    void testSendEmail(){
        emailService.sendEmail("viktoria.aaris@gmail.com");
    }

}