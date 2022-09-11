package ru.practicum.laterwithspringboot.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(Long id) {
        super("User " + id + " not found");
    }
}
