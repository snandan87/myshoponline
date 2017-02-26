package com.shop.online.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.shop.online.exception.UserDetailExist;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(value = { UserDetailExist.class })
//    protected ResponseEntity<RestResponse> handleUnknownException(UserDetailExist ex, WebRequest request) {
//  
//       return new ResponseEntity<RestResponse>(new RestResponse(Boolean.FALSE, ImmutableList.of("Exception message"), null), HttpStatus.INTERNAL_SERVER_ERROR);
//     }
}