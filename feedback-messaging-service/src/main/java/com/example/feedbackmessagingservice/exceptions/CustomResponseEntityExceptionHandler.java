package com.example.feedbackmessagingservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;

public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityDoesNotExistException.class)
    public final ResponseEntity<ErrorEntity> handleEntityDoesNotExist(EntityDoesNotExistException ex) {
        ErrorEntity errorEntity = new ErrorEntity(ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorEntity, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorEntity> handleAllExceptions(Exception ex) {
        ErrorEntity errorEntity = new ErrorEntity(ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorEntity, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
