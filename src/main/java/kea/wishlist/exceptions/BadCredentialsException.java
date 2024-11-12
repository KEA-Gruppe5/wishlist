package kea.wishlist.exceptions;

public class BadCredentialsException extends RuntimeException{
    public BadCredentialsException() {
        super("Invalid email or password.");
    }
}
