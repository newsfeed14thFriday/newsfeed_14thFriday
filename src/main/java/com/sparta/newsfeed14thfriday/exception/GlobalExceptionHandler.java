package com.sparta.newsfeed14thfriday.exception;

import com.sparta.newsfeed14thfriday.entity_common.ApiResponse;
import jakarta.persistence.Entity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateEmailException.class)
    public ApiResponse<?> duplicateEmailException(DuplicateEmailException e) {
        ApiResponse<?> response = ApiResponse.createError(e.getMessage(),HttpStatus.BAD_REQUEST.value());
        return response;
    }

    @ExceptionHandler(DuplicateNameException.class)
    public ApiResponse<?> duplicateNameException(DuplicateNameException e) {
        ApiResponse<?> response = ApiResponse.createError(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        return response;
    }
    @ExceptionHandler(DeletedUserIdException.class)
    public ApiResponse<?> deletedUserIdException(DeletedUserIdException e) {
        ApiResponse<?> response = ApiResponse.createError(e.getMessage(), HttpStatus.NOT_FOUND.value());
        return response;
    }

    @ExceptionHandler(AlreadyDeletedException.class)
    public ApiResponse<?> alreadyDeletedUserException(AlreadyDeletedException e) {
        ApiResponse<?> response = ApiResponse.createError(e.getMessage(), HttpStatus.CONFLICT.value());
        return response;
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ApiResponse<?> emailNotFoundException(EmailNotFoundException e) {
        ApiResponse<?> response = ApiResponse.createError(e.getMessage(), HttpStatus.NOT_FOUND.value());
        return response;
    }
}
