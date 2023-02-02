package ua.klieshchunov.exception;

public class ComputerAlreadyExistsException extends EntityAlreadyExistsException {
    public ComputerAlreadyExistsException(String message) {
        super(message);
    }
}
