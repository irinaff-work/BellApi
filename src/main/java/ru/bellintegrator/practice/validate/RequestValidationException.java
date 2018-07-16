package ru.bellintegrator.practice.validate;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Неверный формат входных данных" )
public class RequestValidationException extends RuntimeException  {

    public RequestValidationException(String message) {
        super(message);
    }
}