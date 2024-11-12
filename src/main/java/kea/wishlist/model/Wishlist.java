package kea.wishlist.model;


public class Wishlist {
    private int id;
    private int userId;
    private String name;


    //no arg constructor for spring to create an instance with requestBody
    public Wishlist() {
    }

    public Wishlist(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
