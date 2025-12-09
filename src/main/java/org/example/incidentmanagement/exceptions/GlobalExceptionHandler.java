package org.example.incidentmanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String, Object>> handleCustomException(CustomException e) {

        Map<String , Object> response = new HashMap<>();
        response.put("code", e.getErrorCodes().getCode());
        response.put("message", e.getErrorCodes().getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

    }


}
