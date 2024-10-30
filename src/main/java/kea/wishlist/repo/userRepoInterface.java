package kea.wishlist.repo;

import kea.wishlist.model.User;

import java.sql.SQLException;

public interface userRepoInterface {
    User addUser(User user) throws SQLException;
    User updateUser(User user int id);
    boolean deletedUser(int id) throws SQLException;
    User findUserWithId (int id);


}
