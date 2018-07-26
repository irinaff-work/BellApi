package ru.bellintegrator.practice.validate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(RequestValidationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Неверный формат входных данных")
    public String validationException (RequestValidationException e) {
        log.debug(e.getMessage());
        return e.getMessage();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Ошибка сервера")
    public String unhandleException (Exception e) {
        e.printStackTrace();
        log.debug(e.getMessage());
        return e.getMessage();
    }
}