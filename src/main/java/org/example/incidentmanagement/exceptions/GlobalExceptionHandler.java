package org.example.incidentmanagement.exceptions;

import org.example.incidentmanagement.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse> handleCustomException(CustomException e) {

        ApiResponse response = new ApiResponse(
                e.getErrorCodes().getCode(),
                e.getErrorCodes().getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);

//        response.put("code", e.getErrorCodes().getCode());
//        response.put("message", e.getErrorCodes().getMessage());

    }


}
