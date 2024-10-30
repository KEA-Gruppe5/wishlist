package kea.wishlist.repo;


import kea.wishlist.util.ConnectionManager;
import org.apache.catalina.User;
import org.springframework.stereotype.Repository;

import java.sql.Connection;

@Repository
public class UserRepository {
    private ConnectionManager connectionManager;

    public UserRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public User saveUser(User user){
        Connection connection = connectionManager.get
    }

}
