package ru.practicum.laterwithspringboot;

import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.laterwithspringboot.exception.InactiveUrlException;
import ru.practicum.laterwithspringboot.exception.ItemNotFoundException;
import ru.practicum.laterwithspringboot.exception.ItemRetrieverException;
import ru.practicum.laterwithspringboot.exception.UserNotFoundException;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler({UserNotFoundException.class,
            ItemNotFoundException.class,
            InactiveUrlException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handle404(Exception e) {
        log.error("{} {}", HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ExceptionHandler({ItemRetrieverException.class, PSQLException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handle400(Exception e) {
        log.error("{} {}", HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
}
