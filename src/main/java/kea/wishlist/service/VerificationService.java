package kea.wishlist.service;

import kea.wishlist.model.VerificationToken;
import kea.wishlist.repository.UserRepository;
import kea.wishlist.repository.VerificationTokenRepository;
import kea.wishlist.exceptions.TokenIsAlreadyUsedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.sql.SQLException;
import java.util.UUID;

@Service
public class VerificationService {

    private final VerificationTokenRepository verificationTokenRepository;
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(VerificationService.class);

    public VerificationService(VerificationTokenRepository verificationTokenRepository, UserRepository userRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
        this.userRepository = userRepository;
    }

    public VerificationToken createToken(int userId) throws SQLException {
        VerificationToken token = new VerificationToken(userId, UUID.randomUUID().toString());
        return verificationTokenRepository.addToken(token);
    }

    public String createLink(int userId, String token){
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("{userId}/verify")
                .queryParam("token", token)
                .buildAndExpand(userId)
                .toUriString();
    }

    public String enableUser(int userId, String token) throws SQLException {
        VerificationToken foundToken = verificationTokenRepository.findVerificationTokenByToken(token);
        if(foundToken != null){
            if(foundToken.isUsed()){
                throw new TokenIsAlreadyUsedException();
            }
            if(foundToken.getUserId() == userId){
                if(userRepository.enableUser(userId)){
                    verificationTokenRepository.useToken(foundToken.getId());
                    logger.info("User is enabled by verification service.");
                    return "Your email was successfully verified, and your account is now activated.";
                }
            }else{
                return "The activation link does not belong to this user.";
            }
        }

        return "This token does not exist.";
    }
}
