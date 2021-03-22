package com.leogersen.alfornoapi.infrastructure.web;

import com.leogersen.alfornoapi.domain.user.DuplicatedUserException;
import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WebRequestExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public RestResponseError handlerException(RepositoryConstraintViolationException e) {
        return RestResponseError.fromValidationError(e.getErrors());

    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public RestResponseError handlerException(DuplicatedUserException e) {
        return RestResponseError.fromMessage(e.getMessage());

    }
}
