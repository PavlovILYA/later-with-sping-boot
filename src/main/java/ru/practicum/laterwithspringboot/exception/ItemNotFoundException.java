package ru.practicum.laterwithspringboot.exception;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException() {
    }

    public ItemNotFoundException(Long id) {
        super("Item " + id + " not found");
    }
}
