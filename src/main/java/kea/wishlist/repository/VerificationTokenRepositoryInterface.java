package kea.wishlist.repository;

import kea.wishlist.model.VerificationToken;

import java.sql.SQLException;

public interface VerificationTokenRepositoryInterface {

    VerificationToken addToken(VerificationToken token) throws SQLException;

    VerificationToken findVerificationTokenByToken(String token) throws SQLException;
}
