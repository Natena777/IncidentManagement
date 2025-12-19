package org.example.incidentmanagement.exceptions;

import org.example.incidentmanagement.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



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


    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidation(
            MethodArgumentNotValidException ex) {

        ApiResponse response = new ApiResponse(
                ResponseCodes.VALIDATION_ERROR.getCode(),
                ex.getBindingResult()
                        .getFieldErrors()
                        .get(0)
                        .getDefaultMessage()
        );

        return ResponseEntity
                .badRequest()
                .body(response);
    }


}
