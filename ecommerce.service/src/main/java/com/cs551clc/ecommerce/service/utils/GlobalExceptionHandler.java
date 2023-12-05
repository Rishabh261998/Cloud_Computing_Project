package com.cs551clc.ecommerce.service.utils;

import com.cs551clc.ecommerce.service.utils.exception.ProductUnavailableError;
import com.cs551clc.ecommerce.service.utils.exception.ProductUnavailableException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value= {BadRequestException.class, IllegalArgumentException.class})
    protected ResponseEntity<Object> handleBadRequest(Exception e, WebRequest request) {
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value= NoSuchElementException.class)
    protected ResponseEntity<Object> handleNoSuchElement(Exception e, WebRequest request) {
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value= ProductUnavailableException.class)
    protected ResponseEntity<Object> handleProductUnavailableRequest(Exception e, WebRequest request) {
        if(e.getClass()== ProductUnavailableException.class){
            return handleExceptionInternal(e, new ProductUnavailableError((ProductUnavailableException) e), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
        }
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
