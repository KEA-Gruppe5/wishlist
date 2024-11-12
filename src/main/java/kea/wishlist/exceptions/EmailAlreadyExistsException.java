package kea.wishlist.exceptions;

public class EmailAlreadyExistsException extends RuntimeException{

    public EmailAlreadyExistsException() {
        super("User with this email is already registered.");
    }
}
