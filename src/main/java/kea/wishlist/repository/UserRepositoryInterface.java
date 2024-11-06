package kea.wishlist.repository;

import kea.wishlist.model.User;

import java.sql.SQLException;

public interface UserRepositoryInterface {
    User addUser(User user) throws SQLException;
    User updateUser(User user, int id);
    boolean deletedUser(int id) throws SQLException;
    User findUserByEmail (String email) throws SQLException;
}
