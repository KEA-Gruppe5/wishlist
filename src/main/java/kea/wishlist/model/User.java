package kea.wishlist.model;

import java.util.List;

public class User {

    private String firstName;
    private String lastName;
    private String name;
    private String password;



    public User(String firstName, String lastName, String name, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.name = name;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
