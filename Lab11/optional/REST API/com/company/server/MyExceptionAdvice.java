package com.company.server;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionAdvice {
    @ExceptionHandler(MyException.class)
    public ErrorMessage handleNotFoundException(MyException ex) {
        ErrorMessage responseMsg = new ErrorMessage(ex.getMessage());
        return responseMsg;
    }
}