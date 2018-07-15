package ru.bellintegrator.practice.organization.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.bellintegrator.practice.organization.service.OrganizationService;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(OrganizationService.OrgValidationException.class)
    protected ResponseEntity<AwesomeException> handleValidationException () {
        return new ResponseEntity<> (new AwesomeException("Неверный формат входных данных из ControllerAdvice"), HttpStatus.BAD_REQUEST);
    }

    private static class AwesomeException {
        private String message;

        AwesomeException (String message) {
            this.message = message;
        }

    }
}