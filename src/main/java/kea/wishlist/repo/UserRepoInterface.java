package kea.wishlist.repo;

import kea.wishlist.model.User;
import kea.wishlist.model.WishlistModel;

import java.sql.SQLException;
import java.util.List;

public interface UserRepoInterface {
    User addUser(User user) throws SQLException;
    User updateUser(User user, int id);
    boolean deletedUser(int id) throws SQLException;
    User findUserWithId (int id);

}
