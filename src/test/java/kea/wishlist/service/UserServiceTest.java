package kea.wishlist.service;

import kea.wishlist.model.User;
import kea.wishlist.model.VerificationToken;
import kea.wishlist.repository.UserRepository;
import kea.wishlist.util.PasswordEncoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ActiveProfiles("test")
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private EmailService emailService;
    @Mock
    private VerificationService verificationService;

    @InjectMocks
    private UserService userService;

    private User user;
    private VerificationToken verificationToken;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @BeforeEach
    public void setup() throws SQLException {
        MockitoAnnotations.openMocks(this);
        user = new User(1, "first name", "last name", "email", 25, "password");
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        verificationToken = new VerificationToken();
        verificationToken.setToken("testToken");
    }

    @DisplayName("Test adding new user in service layer")
    @Test
    void testSaveUser() throws SQLException {
        // Mock repository behavior and service methods
        when(verificationService.createToken(any(Integer.class))).thenReturn(verificationToken);
        when(userRepository.addUser(any(User.class))).thenReturn(user);
        doNothing().when(emailService).sendEmail(any(User.class), any(String.class));

        // Act
        User savedUser = userService.saveUser(user);

        // Assert
        assertNotNull(savedUser, "Saved user should not be null");
        assertEquals("first name", savedUser.getFirstName(), "First names are not equal.");
        assertEquals("encodedPassword", savedUser.getPassword(), "Password is not encoded.");

        // Verify the interactions
        verify(passwordEncoder, times(1)).encode("password");
        verify(userRepository, times(1)).addUser(user);
        verify(emailService, times(1)).sendEmail(any(User.class), any(String.class));
        verify(verificationService, times(1)).createToken(any(Integer.class));
        logger.info("testSaveUser - " + savedUser);
    }

}