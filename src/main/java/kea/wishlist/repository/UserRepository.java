package kea.wishlist.repository;


import kea.wishlist.model.User;
import kea.wishlist.util.ConnectionManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class UserRepository implements UserRepositoryInterface {
    private final ConnectionManager connectionManager;
    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    @Autowired
    public UserRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public User addUser(User user) throws SQLException {
        try(Connection connection = connectionManager.getConnection()){
            String query = "INSERT INTO Wishlist.users (first_name, last_name, age, email, password, is_enabled) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setBoolean(6, false);
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0){
                try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()){
                    if(generatedKeys.next()){
                        user.setId(generatedKeys.getInt(1));
                    }
                }
            }
        }
        logger.info("add new user: " + user);
        return user;
    }

    @Override
    public User updateUser(User user, int id) {
        return null;
    }

    @Override
    public boolean deletedUser(int id){
        return false;
    }

    @Override
    public User findUserByEmail(String email) throws SQLException {
        try(Connection connection = connectionManager.getConnection()){
            String query = "SELECT * FROM Wishlist.users WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setAge(resultSet.getInt("age"));
                user.setPassword(resultSet.getString("password"));
                user.setEnabled(resultSet.getBoolean("is_enabled"));
                return user;
            }
            return null;
        }
    }


    public boolean enableUser(int userId) throws SQLException {
        try (Connection connection = connectionManager.getConnection()){
            String query = "UPDATE users SET is_enabled = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1,true);
            preparedStatement.setInt(2, userId);
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 1){
                logger.info("User was enabled.");
                return true;
            }
        }
        return false;
    }
}
