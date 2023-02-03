package ua.klieshchunov.exception;

public class EntityAlreadyExistsException extends EntityException {
    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
