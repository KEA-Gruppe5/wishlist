package kea.wishlist.model;

public class ItemModel {
    private int wishlistId;
    private String name;
    private String description;
    private String url;
    private double price;
    private String imgUrl;

    public ItemModel(int wishlistId, String name, String description, String url, double price, String imgUrl) {
        this.wishlistId = wishlistId;
        this.name = name;
        this.description = description;
        this.url = url;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public int getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }

    public ItemModel(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}
