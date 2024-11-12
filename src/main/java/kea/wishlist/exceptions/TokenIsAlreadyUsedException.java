package kea.wishlist.exceptions;

public class TokenIsAlreadyUsedException extends RuntimeException{

    public TokenIsAlreadyUsedException() {
        super("This token had already been used.");
    }
}
