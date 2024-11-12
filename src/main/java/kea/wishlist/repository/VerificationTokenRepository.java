package kea.wishlist.repository;

import kea.wishlist.model.VerificationToken;
import kea.wishlist.util.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class VerificationTokenRepository implements VerificationTokenRepositoryInterface{

    private final ConnectionManager connectionManager;
    private static final Logger logger = LoggerFactory.getLogger(VerificationTokenRepository.class);

    public VerificationTokenRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public VerificationToken addToken(VerificationToken token) throws SQLException {
        try(Connection connection = connectionManager.getConnection()){
            String query = "INSERT INTO Wishlist.token (user_id, token, is_used) VALUES (?, ?, ?)";
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
}
