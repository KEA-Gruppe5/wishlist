package kea.wishlist.service;

import kea.wishlist.model.User;
import kea.wishlist.repo.UserRepository;
import kea.wishlist.util.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(User user) throws SQLException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.addUser(user);
    }


}
