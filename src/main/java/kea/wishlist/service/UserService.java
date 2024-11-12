package kea.wishlist.service;

import kea.wishlist.dto.UserDTO;
import kea.wishlist.model.User;
import kea.wishlist.model.VerificationToken;
import kea.wishlist.repository.UserRepository;
import kea.wishlist.util.EmailAlreadyExistsException;
import kea.wishlist.util.PasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final VerificationService verificationService;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       EmailService emailService, VerificationService verificationService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.verificationService = verificationService;
    }

    public User saveUser(User user) throws SQLException {
        logger.info("saveUser is called.");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        checkIfUserAlreadyExists(user.getEmail());
        User savedUser = userRepository.addUser(user);
        logger.info("Password:" + savedUser.getPassword());
        if(savedUser.getId() != 0){
           verifyUser(savedUser);
        }
        return savedUser;
    }

    public void verifyUser(User user) throws SQLException {
        VerificationToken token = verificationService.createToken(user.getId());
        String verificationLink = verificationService.createLink(user.getId(), token.getToken());
        emailService.sendEmail(user, verificationLink);
        logger.info("Email is sent.");
    }

    public void checkIfUserAlreadyExists(String email) throws SQLException {
        logger.info("checkIfUserAlreadyExists is called.");
        User foundByEmailUser = userRepository.findUserByEmail(email);
        if(foundByEmailUser != null){
            throw new EmailAlreadyExistsException();
        }
    }
    public User authenticate(UserDTO userDTO) throws SQLException {
        User user = userRepository.findUserByEmail(userDTO.getEmail());
        if(user != null){
            boolean isPasswordCorrect = passwordEncoder.matches(userDTO.getPassword(),
                    user.getPassword());
            if(isPasswordCorrect){  //in case password is encrypted
                logger.info("User authenticated: " + user);
                return user;
            }
            if(user.getPassword().equals(userDTO.getPassword())){ //if it is not encrypted
                logger.info("User authenticated: " + user);
                return user;
            }
            logger.info("User is not authenticated.");
            logger.info("Password:" + userDTO.getPassword());
            logger.info("Password in db: " + user.getPassword());
        }
        return null;
    }
}
