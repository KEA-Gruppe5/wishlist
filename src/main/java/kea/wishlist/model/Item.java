package kea.wishlist.model;

public class Item {
    private int id;
    private int wishlistId;
    private String name;
    private String description;
    private String url;
    private double price;
    private String imgUrl;
    private boolean isReserved;

    public Item(int id, int wishlistId, String name, String description, String url, double price, String imgUrl, boolean isReserved) {
        this.id = id;
        this.wishlistId = wishlistId;
        this.name = name;
        this.description = description;
        this.url = url;
        this.price = price;
        this.imgUrl = imgUrl;
        this.isReserved = isReserved;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        this.isReserved = reserved;
    }

    public int getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item(){}

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

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", wishlistId=" + wishlistId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", price=" + price +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
