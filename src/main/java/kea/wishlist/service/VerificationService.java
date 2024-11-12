package kea.wishlist.service;

import kea.wishlist.model.VerificationToken;
import kea.wishlist.repository.VerificationTokenRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.UUID;

@Service
public class VerificationService {

    private final VerificationTokenRepository verificationTokenRepository;

    public VerificationService(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }


//    public String getVerified(User user){
//        if(link.isExpired())return "Link is expired.";
//        user.setEnabled(true);
//        return "Your account is successfully activated."
//    }

    public VerificationToken createToken(int userId) throws SQLException {
        VerificationToken token = new VerificationToken(userId, UUID.randomUUID().toString());
        return verificationTokenRepository.addToken(token);
    }

    public void verify(int userId, String token) {

    }
}
