package com.product.product.controller;

import com.product.product.dto.ExceptionDto;
import com.product.product.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductControllerAdvice {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleProductNotFoundException(ProductNotFoundException productException) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setStatus(HttpStatus.BAD_REQUEST);
        exceptionDto.setMessage(productException.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }
}
