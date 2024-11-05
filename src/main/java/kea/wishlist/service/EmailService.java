package kea.wishlist.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public void sendEmail(String receiver){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(receiver);
        message.setSubject("Confirm your registration at Wishlist.kea");
        message.setText("*verification link*");
        javaMailSender.send(message);
    }


}
