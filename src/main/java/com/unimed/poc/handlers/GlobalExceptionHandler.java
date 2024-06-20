package com.unimed.poc.handlers;

import com.unimed.poc.exceptions.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<Object> handleStudentNotFoundException(StudentNotFoundException ex) {
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }
}