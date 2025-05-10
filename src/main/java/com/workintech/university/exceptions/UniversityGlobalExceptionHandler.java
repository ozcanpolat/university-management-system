package com.workintech.university.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class UniversityGlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<UniversityErrorResponse> handleException(UniversityException exception){
        UniversityErrorResponse universityErrorResponse = new UniversityErrorResponse(
                exception.getMessage(),
                exception.getHttpStatus().value(),
                System.currentTimeMillis(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(universityErrorResponse, exception.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<UniversityErrorResponse> handleException(Exception exception){
        UniversityErrorResponse universityErrorResponse = new UniversityErrorResponse(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                System.currentTimeMillis(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(universityErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
