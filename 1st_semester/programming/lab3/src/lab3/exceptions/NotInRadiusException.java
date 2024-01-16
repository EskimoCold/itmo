package lab3.exceptions;

public class NotInRadiusException extends RuntimeException {
    public NotInRadiusException(String message) {
        super(message);
    }
}