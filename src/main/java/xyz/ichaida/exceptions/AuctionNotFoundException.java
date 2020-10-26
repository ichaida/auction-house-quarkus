package xyz.ichaida.exceptions;

/**
 * Throw this error if Auction not found in db
 *
 * @author Ismail Chaida.
 */
public class AuctionNotFoundException extends Exception {
    public AuctionNotFoundException(String message) {
        super(message);
    }
}
