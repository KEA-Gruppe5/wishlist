package kea.wishlist.service;

import kea.wishlist.model.User;
import kea.wishlist.repository.UserRepository;
import kea.wishlist.util.EmailAlreadyExistsException;
import kea.wishlist.util.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    public User saveUser(User user) throws SQLException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User foundByEmailUser = userRepository.findUserByEmail(user.getEmail());
        if(foundByEmailUser != null){
            throw new EmailAlreadyExistsException();
        }
        User savedUser = userRepository.addUser(user);
        if(savedUser!=null &&  savedUser.getId()!=0){
            emailService.sendEmail(savedUser.getEmail());
        }
        return savedUser;
    }


}
