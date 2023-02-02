package ua.klieshchunov.exception;

public class ClientAlreadyExistsException extends EntityAlreadyExistsException {
    public ClientAlreadyExistsException(String message) {
        super(message);
    }
}
