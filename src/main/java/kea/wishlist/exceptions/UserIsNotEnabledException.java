package kea.wishlist.exceptions;

public class UserIsNotEnabledException extends RuntimeException{
    public UserIsNotEnabledException() {
        super("This email has not been verified yet.");
    }
}
