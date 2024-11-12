package kea.wishlist.model;

public class VerificationToken {

    private int id;
    private int userId;
    private String token;
    private boolean isUsed;

    public VerificationToken(int id, int userId, String token, boolean isUsed) {
        this.id = id;
        this.userId = userId;
        this.token = token;
        this.isUsed = isUsed;
    }

    public VerificationToken(int userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    @Override
    public String toString() {
        return "VerificationToken{" +
                "id=" + id +
                ", userId=" + userId +
                ", token='" + token + '\'' +
                ", isUsed=" + isUsed +
                '}';
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }
}
