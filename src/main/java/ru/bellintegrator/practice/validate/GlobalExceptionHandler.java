package ru.bellintegrator.practice.validate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.bellintegrator.practice.validate.view.ErrorView;

/*
Обработка исключений
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    /*
    Обработка собственного исключения при проверке данных
     */

    @ExceptionHandler(RequestValidationException.class)
    @ResponseStatus
    public ErrorView  validationException (RequestValidationException e) {
        log.debug(e.getMessage());
        ErrorView errorView = new ErrorView(e.getMessage());
        return errorView;//e.getMessage();
    }

    /*
    Обработка остальных исключений
     */

    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Ошибка сервера")
    public ErrorView  unhandleException (Exception e) {
        //потом убрать
        e.printStackTrace();
        log.debug(e.getMessage());

        ErrorView errorView = new ErrorView("Ошибка сервера");
        return errorView;
    }
}