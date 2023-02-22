package com.example.demo.controller;

import com.example.demo.service.exception.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
@Slf4j
public class AdviceController {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> entityNotFoundExceptionHandler(EntityNotFoundException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(new ErrorResponse("Entity not found  " + exception.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> noHandlerFoundExceptionHandler(NoHandlerFoundException exception) {
        log.error("Handler for this request not found! \n" + exception.getRequestURL() + "\n" + exception.getMessage());
        return new ResponseEntity<>(new ErrorResponse("Handler for this request not found!"), HttpStatus.BAD_REQUEST);
    }


    @Data
    @AllArgsConstructor
    private static class ErrorResponse {
        private String error;
    }


}
