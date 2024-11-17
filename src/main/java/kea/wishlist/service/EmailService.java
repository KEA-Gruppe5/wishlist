package kea.wishlist.service;

import kea.wishlist.model.User;
import kea.wishlist.model.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final VerificationService verificationService;
    @Value("${SMTP_USERNAME}")
    private String fromEmail;



    @Autowired
    public EmailService(JavaMailSender javaMailSender, VerificationService verificationService) {
        this.javaMailSender = javaMailSender;
        this.verificationService = verificationService;
    }


    public void sendEmail(User user, String link) {
        SimpleMailMessage email = new SimpleMailMessage();
        String message = "Hi " + user.getFirstName() + "!" +
                "\nThank you for your registration on " + ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() +
                "\nPlease click the link below in order to activate your account " + user.getEmail() + ":\n" +
                link + "\n" + "\nCheers,\n" + "Wishlist.kea team";
        email.setFrom(fromEmail);
        email.setTo(user.getEmail());
        email.setSubject("Wishlist.kea: confirm your email address");
        email.setText(message);
        javaMailSender.send(email);
    }

}
