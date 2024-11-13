package kea.wishlist.repository;

import kea.wishlist.model.VerificationToken;
import kea.wishlist.util.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
@Repository
public class VerificationTokenRepository implements VerificationTokenRepositoryInterface{

    private final ConnectionManager connectionManager;
    private static final Logger logger = LoggerFactory.getLogger(VerificationTokenRepository.class);

    public VerificationTokenRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public VerificationToken addToken(VerificationToken token) throws SQLException {
        try(Connection connection = connectionManager.getConnection()){
            String query = "INSERT INTO Wishlist.tokens (user_id, token, is_used) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, token.getUserId());
            preparedStatement.setString(2,token.getToken());
            preparedStatement.setBoolean(3, token.isUsed());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0){
                try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()){
                    if(generatedKeys.next()){
                        token.setId(generatedKeys.getInt(1));
                    }
                }
            }
        }
        logger.info("add new token: " + token);
        return token;
    }

    @Override
    public VerificationToken findVerificationTokenByToken(String token) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            String query = "SELECT * FROM Wishlist.tokens WHERE token = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, token);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                VerificationToken verificationToken = new VerificationToken();
                verificationToken.setId(resultSet.getInt("id"));
                verificationToken.setUserId(resultSet.getInt("user_id"));
                verificationToken.setToken(resultSet.getString("token"));
                verificationToken.setUsed(resultSet.getBoolean("is_used"));
                return verificationToken;
            }
            return null;
        }
    }

    public boolean useToken(int id) throws SQLException {
        try (Connection connection = connectionManager.getConnection()){
            String query = "UPDATE Wishlist.tokens SET is_used = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1,true);
            preparedStatement.setInt(2, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 1){
                logger.info("Token was used.");
                return true;
            }
        }
        return false;
    }
}
