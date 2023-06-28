package com.simform.EmployeeManagementSystem.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class UserNotFoundExceptionHandler {
    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    public String exception(){
        return "User not found";
    }
}
