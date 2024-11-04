package kea.wishlist.repo;


import kea.wishlist.model.User;
import kea.wishlist.util.ConnectionManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class UserRepository implements UserRepoInterface{
    private final ConnectionManager connectionManager;

    @Autowired
    public UserRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public User addUser(User user) throws SQLException {
        try(Connection connection = connectionManager.getConnection()){
            String query = "INSERT INTO Wishlist.users (first_name, last_name, age, email, password) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getEmail());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0){
                try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()){
                    if(generatedKeys.next()){
                        user.setId(generatedKeys.getInt(1));
                    }
                }
            }
        }
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
    public User findUserWithId(int id) {
        return null;
    }
}
