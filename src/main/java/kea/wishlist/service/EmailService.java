package kea.wishlist.service;

import kea.wishlist.model.User;
import kea.wishlist.model.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final VerificationService verificationService;

    @Autowired
    public EmailService(JavaMailSender javaMailSender, VerificationService verificationService) {
        this.javaMailSender = javaMailSender;
        this.verificationService = verificationService;
    }


    public void sendEmail(User user, String link) {
        SimpleMailMessage email = new SimpleMailMessage();
        StringBuilder message = new StringBuilder();
        message.append("Hi ").append(user.getFirstName()).append("!")
                .append("\nThank you for your registration on ").append(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString())
                .append("\nPlease click the link below in order to activate your account ").append(user.getEmail()).append(":\n")
                .append(link).append("\n").append("\nCheers,\n").append("Wishlist.kea team");
        email.setTo(user.getEmail());
        email.setSubject("Wishlist.kea: confirm your email address");
        email.setText(message.toString());
        javaMailSender.send(email);
    }

}
