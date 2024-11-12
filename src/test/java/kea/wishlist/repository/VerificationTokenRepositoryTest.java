package kea.wishlist.repository;

import kea.wishlist.model.User;
import kea.wishlist.model.VerificationToken;
import kea.wishlist.service.VerificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.SQLException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@ActiveProfiles("test")
@SpringBootTest
@ExtendWith({MockitoExtension.class})
class VerificationTokenRepositoryTest {
    private VerificationToken verificationToken;

    private static final Logger logger = LoggerFactory.getLogger(VerificationTokenRepositoryTest.class);

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private VerificationService verificationService;

    @BeforeEach
    public void setup() throws SQLException {
        verificationToken = new VerificationToken(1, UUID.randomUUID().toString());
    }


    @Test
    @DisplayName("Integration test adding new token to repository")
    void addToken() throws SQLException {
        VerificationToken savedToken = verificationTokenRepository.addToken(verificationToken);
        assertNotNull(savedToken);
        assertEquals(1, savedToken.getUserId());
        assertEquals(1, savedToken.getId()); // 6 because there are inserted 5 users at initialization

        logger.info("Test add token: " + savedToken);
    }
}