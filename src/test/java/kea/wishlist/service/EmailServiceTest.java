package kea.wishlist.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.times;

@ActiveProfiles("test")
@SpringBootTest
@RunWith(SpringRunner.class)
class EmailServiceTest {

    @MockBean
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailService emailService;

//    @Test
//    void testSendEmail() {
//        emailService.sendEmail("test@example");
//        Mockito.verify(javaMailSender, times(1)).send(Mockito.any(SimpleMailMessage.class));
//    }

}