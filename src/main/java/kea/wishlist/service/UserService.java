package kea.wishlist.service;

import kea.wishlist.model.User;
import kea.wishlist.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) throws SQLException {
        return userRepository.saveUser(user);
    }
}
